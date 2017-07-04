package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

import co.edu.unicartagena.birch.persistencia.FPQuery;
import co.edu.unicartagena.birch.persistencia.IFP;
import co.edu.unicartagena.birch.persistencia.IQuery;
import co.edu.unicartagena.birch.persistencia.SelectorDePersistencias;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * La función de esta clase es asegurarse de que el archivo ingresado sea un
 * archivo válido admitido por el componente. Para que un archivo sea
 * considerado como válido debe cumplir con las siguientes condiciones:
 *
 * <ul>
 * <li> Debe ser un archivo XMI o XML bien formado.</li>
 * <li> Debe haber sido exportado en alguna de las versiones de XMI que admite
 * el componente (2.1, 2.4.1, 2.4.2), en otras palabras, el documento debe
 * ajustarse a su respectivo documento Shema.</li>
 * <li> Debe haber sido exportado para la versión 2.4.1 o 2.1 de UML.</li>
 * </ul>
 *
 * La clase mantiene en memoria la versión de UML con la que fue construido el
 * archivo con el fin de que el componente utilice las sentencias adecuadas para
 * recoilar la información de cada versión.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.4
 * @since 27/07/2016
 */
public final class Validador implements IVC {

    /**
     * URL de la ubicación relativa del Schema para la validación de archivos
     * XMI en su versión 2.4.2
     */
    private final String URL_SCHEMA_XMI_2_4_2
            = "/co/edu/unicartagena/birch/schemas/2.4.2 Schema.xsd";

    /**
     * URL de la ubicación relativa del Schema para la validación de archivos
     * XMI en su versión 2.1
     */
    private final String URL_SCHEMA_XMI_2_1
            = "/co/edu/unicartagena/birch/schemas/2.1 Schema.xsd";

    /**
     * Un variable que identifica la versión de XMI con la cuál fue exportado el
     * archivo que se esté trabajando. Esto es para conocer qué tipo de
     * sentencias se utilizarán para recopilar los datos.
     */
    private Version version;

    /**
     * Instancia única de la clase (Patrón Singletón).
     */
    private static Validador instance;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    private Validador() {

    }

    /**
     * Método que retorna una única instancia de la clase.
     *
     * @return una instancia de la clase.
     */
    public static Validador getInstance() {
        if (instance == null) {
            instance = new Validador();
        }

        return instance;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que verifica la validez de un archivo XMI.
     *
     * @param path un String con la ruta del archivo.
     *
     * @return returna true en caso de que el archivo sea válido, o false en
     * caso contrario.
     */
    @Override
    public String verificarArchivoXMI(String path) {
        //Se verifica que la extención del archivo 
        //corresponda a un archivo XMI o XML.
        if (isExtencionCorrecta(path)) {
            //Si el archivo es un archivo XMI o XML.
            //Se verifica que el archivo existe y que haya sido bien formado.
            if (this.isArchivoBienFormado(path)) {
                //Si el archivo está bien formado.
                //Se verifica que el archivo se ajuste a las versiones
                //admitidas por el componente.
                if (this.isArchivoXMIAdmitido(path)) {
                    //SI el archivo se ajunta a las versiones admitidas.
                    //Se retorna el valor correspondiente para un archivo válido.
                    return "The file is valid.";
                } else {
                    //Si el archivo no se ajusta a ninguna de 
                    //las versiones admitidas.
                    //Se retorna el valor correspondiente para dicho caso.
                    return "Sorry, The files are only accepted in versions 2.1, "
                            + "2.4.1 and 2.4.2 of XMI.";
                }
            } else {
                //Si el archivo no ha sido bien formado.
                //Se retorna el valor correspondiente para dicho caso.
                return "The file is missing or has not been well-formed.";
            }
        } else {
            //Si la extención del archivo no es la adecuada.
            //Se retorna el valor corresondiente para dicho caso.
            return "The file extension is not correct.";
        }
    }

    /**
     * Método que permite comprobar si el artefacto al que se le desea calcular
     * métricas existe.
     *
     * @param nombre un String que representa el nombre del artefacto.
     * @param path la ruta del archivo.
     * @param isIdSearch atributoque permite especificar si el artefacto a
     * evaluar se busca a través de la id o del nombre.
     * @return true en caso de que el artefacto exista en el diagrama.
     */
    @Override
    public boolean verificarArtefacto(String nombre, String path, boolean isIdSearch) {
        String sentencia = this.version.armarSentenciaArtefacto(nombre, path, 
                "MA Verificar artefacto", isIdSearch);
        String result = this.getResult(sentencia);

        return result.equals("true");
    }

    /**
     * Método que retorna una instancia de la interfáz IVersionSentencias la
     * cuál contiene las sentencias necesarias para trabajar con cada versión de
     * UML en particular.
     *
     * @return la versión con la cuál se está trabajando.
     */
    @Override
    public Version getVersionEnUso() {
        return version;
    }

    /**
     * Método que libera el espacio en memoria utilizado por la clase.
     *
     * Nota: ëste método es llamado una vez que el proceso de cálculo de
     * métricas es llevado a cabo, con el propósito de liberar espacio en
     * memoria innecesario.
     *
     * @throws java.lang.Throwable
     */
    @Override
    @SuppressWarnings("FinalizeCalledExplicitly")
    public void disposeSingleton() throws Throwable {
        instance.finalize();
        this.finalize();
    }

    /**
     * Método que verifica si la extención del archivo es válida.
     *
     * @param path la ruta del archivo.
     * @return true en caso de que la extención del archivo corresponda a un
     * archivo xmi o xml.
     */
    private boolean isExtencionCorrecta(String path) {
        return path.endsWith("xmi") || path.endsWith("xml");
    }

    /**
     * Método que comprueba, a través de una sentencia XQuery si el archivo
     * existe o si el mismo está bien formado.
     *
     * @param path la ruta del archivo.
     * @return true en caso de que el archivo esté ien formado.
     */
    private boolean isArchivoBienFormado(String path) {
        String sentencia = "doc-available(\"" + path + "\")";
        return this.getResult(sentencia).equals("true");
    }

    /**
     * Método que ejecuta una sentencia y retorna el resultado.
     *
     * @param sentencia
     * @return
     */
    private String getResult(String sentencia) {
        //Declaración de variables.
        IFP persistencia = new SelectorDePersistencias();
        String dato = "NA";

        try {
            //Se obtiene la clase encargada de la persistencia XQuery.
            IQuery pXQuery = persistencia
                    .persistenciasQuery()
                    .getPersistencias(FPQuery.XQUERY);

            //Se ejecuta la sentencia.
            XQResultSequence result = (XQResultSequence) pXQuery
                    .ejecutarQuery(sentencia);

            //Se ignora el primer elemento.
            result.next();
            //Se otiene el elemento en forma de String.
            dato = result.getItemAsString(null);

            //Se cierra la conexión con XQuery.
            pXQuery.terminarConexion();

            //Se retornan el resultado.
            return dato;
        } catch (XQException ex) {
            Logger.getLogger(Validador.class.getName())
                    .log(Level.SEVERE, null, ex);
            return dato;
        } catch (Throwable ex) {
            Logger.getLogger(Validador.class.getName())
                    .log(Level.SEVERE, null, ex);
            return dato;
        }
    }

    /**
     * Método que verifica si el archivo es válido en relación a los archivos
     * Schema de las versiones 2.1, 2.4.1 y 2.4.2.
     *
     * @param path la ruta del archivo.
     * @return true en caso de que el archivo corresponda a alguna de esas
     * versiones.
     */
    private boolean isArchivoXMIAdmitido(String path) {
        if (isValidoContraSchema(this.URL_SCHEMA_XMI_2_1, path)) {
            version = new FactoriaDeVersiones().getIntances("2.1");
            return true;
        } else if (isValidoContraSchema(this.URL_SCHEMA_XMI_2_4_2, path)) {
            version = new FactoriaDeVersiones().getIntances("2.4.2");
            return true;
        }
        return false;
    }

    /**
     * Método auxiliar que valida un archivo con respecto a un archivo Schema
     * especifico.
     *
     * @param urlSchema la ruta del archivo Schema.
     * @param path la ruta del archivo.
     * @return true en caso de que el resultado de la validación sea positivo.
     */
    private boolean isValidoContraSchema(String urlSchema, String path) {
        try {
            //Se prepara el Schema.
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory
                    .newSchema(Validador.class.getResource(urlSchema));

            //Se crea el validador.
            Validator validator = schema.newValidator();

            //Se define el manejador de excepciones del validador.
            final List exceptions = new LinkedList();
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception)
                        throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception)
                        throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception)
                        throws SAXException {
                    exceptions.add(exception);
                }
            });

            //Se comprueba el XMI.
            //validator.validate(new StreamSource(new File(path)));
            validator.validate(new StreamSource(path));

            //Resultado de la validación.
            if (exceptions.isEmpty()) {
                return true;
            } else {
                System.err.println(exceptions.toString());
                return false;
            }
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Validador.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
