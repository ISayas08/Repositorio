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
 * Clase encargada de reunir los datos necesarios para hacer el càlculo de las
 * mètricas de Lorenz y kidd.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.6
 * @since 14/09/2016
 */
public final class RecopiladorDeDatosLyK implements IRArtefactos {

//==============================================================================
//  Constructores y métodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public RecopiladorDeDatosLyK() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método encargado de recopilar una dato especifico neceario para calcular
     * una métrica.
     *
     * @param iDArtifact el identificador del artefacto.
     * @param path la dirección del archivo.
     * @param id un entero utilizado para indicar el dato que se desea
     * recopilar.
     * @return un String que contiene los datos requeridos.
     */
    @Override
    public String recopilar(String iDArtifact, String path, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_" + id))
                .collect(Collectors.toList()).get(0).invoke(this, iDArtifact, path);

        return dato == null ? "-1" : dato;
    }

    /**
     * Método encargado de recopilar una dato especifico neceario para calcular
     * una métrica.
     *
     * @param iDAritifact el identificador del artefacto.
     * @param file una instancia de tipo FILE que contiene la dirección del
     * archivo.
     * @param id un entero utilizado para indicar el dto que se desea recopilar.
     * @return un String que contiene los datos requeridos.
     */
    @Override
    public String recopilar(String iDAritifact, File file, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.recopilar(iDAritifact, file.getAbsolutePath(), id);
    }

    /**
     * Método encargado de contar los atributos totales de una cada clase.
     *
     * @param identifier el identificador del artrfacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de atributos en la clase.
     */
    private String atributosTotales_LyK_0(String identifier, String path) {
        try {
            return this.getResult(identifier, path,
                    UtileriaRDD.SA_CONTAR_ATRIBUTOS_TOTALES);
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
     * Método encargado de contar los atributos públicos de una cada clase.
     *
     * @param iDArtifact el identificador de la clase que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de atributos públicos en la
     * clase.
     */
    private String atributosPublicos_LyK_1(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_ATRIBUTOS_PUBLICOS);
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
     * Método encargado de contar los atributos estáticos de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de atributos estáticos en la
     * clase.
     */
    private String atributosEstaticos_LyK_2(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_ATRIBUTOS_ESTATICOS);
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
     * Método encargado de contar los métodos totales de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos totales en la
     * clase.
     */
    private String metodosTotales_LyK_3(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_METODOS_TOTALES);
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
     * Método encargado de contar los métodos públicos de una cada clase.
     *
     * @param iDArtifact el dentificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos públicos en la
     * clase.
     */
    private String metodosPublicos_LyK_4(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_METODOS_PUBLICOS);
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
     * Método encargado de contar los métodos estáticos de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos estáticos en la
     * clase.
     */
    private String metodosEstaticos_LyK_5(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_METODOS_ESTATICOS);
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
     * Método encargado de contar los métodos añadidos de una cada clase.
     *
     * @param idArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos añadidos en la
     * clase.
     */
    private String datosMetodosClase_LyK_6(String idArtifact, String path) {
        try {
            return this.getResult(idArtifact, path,
                    UtileriaRDD.SA_DATOS_METODOS_CLASE);
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
     * Método encargado de contar los métodos sobrescritos de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos sobrescritos en
     * la clase.
     */
    private String nombresClasesPadres_LyK_7(String iDArtifact, String path) {
        try {
            String dato = this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_IDES_CLASES_PADRE);
            return dato.equals("") ? "NA" : dato;
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
     *
     * @param idArtifact el identificador del artefacto que se está evaluando.
     * @param path la ruta del archivo XMI.
     * @return un String que contiene el dato recopialdo.
     */
    private String atributosHeredados_LyK_8(String idArtifact, String path) {
        try {
            return this.getResult(idArtifact, path,
                    UtileriaRDD.SA_CONTAR_ATRIBUTOS_HEREDADOS);
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
     * Método encargado de contar los métodos heredados de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de métodos heredados en la
     * clase.
     */
    private String metodosHeredados_LyK_9(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_METODOS_HEREDADOS);
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
     * Método encargado de contar los parámetros totales de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de parámetros totales en la
     * clase.
     */
    private String parametrosTotales_LyK_10(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_CONTAR_PARAMETROS_TOTALES);
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
     * Método encargado de contar los parámetros totales de una cada clase.
     *
     * @param iDArtifact el identificador del artefacto que se está evaluando.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de parámetros totales en la
     * clase.
     */
    private String datosDeHerencia_LyK_11(String iDArtifact, String path) {
        try {
            return this.getResult(iDArtifact, path,
                    UtileriaRDD.SA_DATOS_HERENCIA_CLASES);
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
     * @param idArtifact un String con el nombre de la clase que se va a
     * evaluar.
     * @param path un String con la dirección del archivo XMI.
     * @param id un entero que especifica la id de la sentencia a ejecurtarse.
     * @return un String que contiene el resultado de la ejecución de la
     * sentencia. Se retornará "-1" si se produce algún error.
     * @throws XQException
     * @throws Throwable
     */
    private String getResult(String idArtifact, String path, String id)
            throws XQException, Throwable {
        IVC validador = Validador.getInstance();
        IFP selecPersistencia = new SelectorDePersistencias();

        //Se obtiene la sentencia que se va a ejecutar.
        String Sentencia = validador.getVersionEnUso()
                .armarSentenciaArtefacto(idArtifact, path, id, true);

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
