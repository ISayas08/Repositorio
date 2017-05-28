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
 * Clase encargada de recopilar toda la información de las métricas generales.
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.5
 * @since 14/09/2016
 */
public final class RecopiladorDeDatosMG implements IRGenerales {

//==============================================================================
//  Constructores y métodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public RecopiladorDeDatosMG() {

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
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_" + id))
                .collect(Collectors.toList()).get(0).invoke(this, path);

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
     * Método encargado de contar las clases que tiene el archivo XMI.
     *
     * @param path la dirección del archivo.
     * @return un entero que representa el numero de clases encontradas.
     */
    private String totalClases_MG_0(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_CLASES);
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
     * Método encargado de contar las clases abstractas que tiene el archivo
     * XMI.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de clases abstractas
     * encontradas.
     */
    private String totalCAbstractas_MG_1(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_CLASES_ABSTRACTAS);
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
     * Método encargado de contar las iterfaces que existen en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de interfaces encontradas.
     */
    private String totalInterfaces_MG_2(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_INTERFACES);
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
     * Método encargado de contar los paquetes que hay en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de paquetes encontrados.
     */
    private String totalPaquetes_MG_3(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_PAQUETES);
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
     * Método encargado de contar los atributos que hay en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de atributos encontrados.
     */
    private String totalAtributos_MG_4(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_ATRIBUTOS);
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
     * Método encargado de contar los atributos públicos que hay en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de métodos públicos
     * encontrados.
     */
    private String totalAPublicos_MG_5(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_ATRIBUTOS_PUBLICOS);
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
     * Método encargado de contar los métodos que hay en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de métodos encontrados.
     */
    private String totalMetodos_MG_6(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_METODOS);
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
     * Método encargado de contar los metodos públicos que hay en el archivo.
     *
     * @param path la dirección del archivo XMI.
     * @return un entero que representa el número de métodos públicos
     * encontrados.
     */
    private String totalMPublicos_MG_7(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_CONTAR_N_TOTAL_METODOS_PUBLICOS);
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
     * Método que permite obtener los nombres de todas las clases e interfaces
     * en el diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene los nombres de todas las clases.
     */
    private String idesClases_MG_8(String path) {
        try {
            return this.getResult(path,
                    UtileriaRDD.SG_OBTENER_TODAS_LAS_IDS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
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
//  Metodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setter.
//==============================================================================
}
