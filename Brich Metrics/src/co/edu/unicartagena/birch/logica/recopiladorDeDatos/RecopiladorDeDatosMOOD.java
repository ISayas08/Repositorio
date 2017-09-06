package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.IVC;
import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.Validador;
import co.edu.unicartagena.birch.persistencia.FPQuery;
import co.edu.unicartagena.birch.persistencia.IFP;
import co.edu.unicartagena.birch.persistencia.IQuery;
import co.edu.unicartagena.birch.persistencia.SelectorDePersistencias;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

/**
 * Clase encargada de obtener todos los datos necesarios para hacer el cálculo
 * de las métricas de FyR (MOOD)(Fernando Brito y Rogerio carpuca).
 *
 * @author Ismael Sayas Arrieta
 */
public class RecopiladorDeDatosMOOD implements IRGenerales {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public RecopiladorDeDatosMOOD() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite recopilar un dato especifico de archivo del archivo.
     *
     * @param path la dirección del archivo.
     * @param id un entero que permite identificar el dato a recoilar.
     * @return un String que contiene el daato solicitado.
     */
    @Override
    public String recopilar(String path, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass().getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_id",
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_" + id))
                .collect(Collectors.toList()).get(0).invoke(this, path);

        //Se retornan los datos.
        return dato == null ? "-1" : dato;
    }

    /**
     * Método que permite recopilar un dato especifico de archivo del archivo.
     *
     * @param file un objeto de tipo File que contiene la dirección del archivo.
     * @param id un entero que permite identificar el dato a recopilar.
     * @return un String que contiene el dato solicitado.
     */
    @Override
    public String recopilar(File file, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.recopilar(file.getAbsolutePath(), id);
    }

    /**
     * Método encargado de contar el número total clases hijas que existen en el
     * diagrama.
     *
     * @param path un string con la ruta del archivo.
     * @return un entero que reresenta el número total de métodos en el
     * diagrama.
     */
    private String totalClasesHijas_MOOD_0(String path) {
        try {
            return this.getResult(path,
                    IVC.SG_CONTAR_N_TOTAL_CLASES_HIJAS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar el número total de grupos de hernecia
     * (clusters) que existen en el diagrama.
     *
     * @param path un string con la ruta del archivo.
     * @return un entero que reresenta el número total de métodos en el
     * diagrama.
     */
    private String totalClusters_MOOD_1(String path) {
        try {
            return "" + this.getResult(path,
                    IVC.SG_CONTAR_N_TOTAL_CLUSTERS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar el número total de relaciones, descartando la
     * herencia, que existen en el diagrama.
     *
     * @param path un string con la ruta del archivo.
     * @return un entero que reresenta el número total de métodos en el
     * diagrama.
     */
    private String totalRelacionesNH_MOOD_2(String path) {
        try {
            return this.getResult(path,
                    IVC.SG_CONTAR_N_TOTAL_RELACIONES_NH);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar el número total de atributos privados que
     * existen en el diagrama.
     *
     * @param path un string con la ruta del archivo.
     * @return un entero que reresenta el número total de métodos en el
     * diagrama.
     */
    private String totalAtributosPrivados_MOOD_3(String path) {
        try {
            return this.getResult(path,
                    IVC.SG_CONTAR_N_TOTAL_ATRIBUTOS_PRIVADOS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar el número total de métodos privados que
     * existen en el diagrama.
     *
     * @param path un string con la ruta del archivo.
     * @return un entero que reresenta el número total de métodos privados en el
     * diagrama.
     */
    private String totalMetodosPrivados_MOOD_4(String path) {
        try {
            return this.getResult(path,
                    IVC.SG_CONTAR_N_TOTAL_METODOS_PRIVADOS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método que envía los datos a la persistencia para ejecutar la sentencia y
     * retorna el resultado generado.
     *
     * @param nombre un String con el nombre de la clase que se va a evaluar.
     * @param path un String con la dirección del archivo XMI.
     * @param id un entero que especifica la id de la sentencia a ejecurtarse.
     * @return un String que contiene el resultado de la ejecución de la
     * sentencia. Se retornará "-1" si se produce algún error.
     * @throws XQException
     * @throws Throwable
     */
    private String getResult(String path, String id)
            throws XQException, Throwable {
        IVC validador = Validador.getInstance();
        IFP selecPersistencia = new SelectorDePersistencias();

        //Se obtiene la sentencia que se va a ejecutar.
        String Sentencia = validador.getVersionEnUso()
                .armarSentenciaGeneral(path, id);
        //Se accede a la persistencia de XQuery.
        IQuery pXQuery = selecPersistencia
                .persistenciasQuery()
                .getPersistencias(FPQuery.XQUERY);
        //Se ejecuta la sentencia.
        XQResultSequence result = (XQResultSequence) pXQuery
                .ejecutarQuery(Sentencia);

        //Se crea la lista que contendrá los datos
        List<String> listDatos = new ArrayList<>();
        //Se añaden los resultados de la sentencia a la lista de datos.
        while (result.next()) {
            listDatos.add(result.getItemAsString(null));
        }
        //Se cierra la conexión con XQuery.
        pXQuery.terminarConexion();
        //Se retorna el resutado.
        //Si la lista de datos está vacía se retorna -1, en caso contrario se 
        //juntan los resultados en un mismo string, separados por punto y coma.
        return listDatos.isEmpty()
                ? "-1"
                : listDatos.stream().collect(Collectors.joining(";"));
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
