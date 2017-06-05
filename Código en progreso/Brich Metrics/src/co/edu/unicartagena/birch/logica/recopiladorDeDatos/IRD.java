package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import java.io.File;
import java.util.List;

/**
 * Interfaz que declara los servicios que ofrece el subcomonente "Recopilador de
 * datos"
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.3
 * @since 16/09/2016
 */
public interface IRD {

    //Familias de recopilaciones.
    public static final String FAMILIA_CYK = "CyK";
    public static final String FAMILIA_LYK = "LyK";
    public static final String FAMILIA_MG = "MG";
    public static final String FAMILIA_MOOD = "MOOD";
    //Ids para la recopilación de famila de métricas CyK.
    public static final String PROFUNDIDAD_DEL_ARBOL_DE_HERENCIAS = "CyK_0";
    public static final String HIJOS_INMEDIATOS = "CyK_1";
    public static final String ACOPLAMIENTO = "CyK_2";
    //Ids para la recopilación de la familia de métricas LyK.
    public static final String ATRIBUTOS_TOTALES = "LyK_0";
    public static final String ATRIBUTOS_PUBLICOS = "LyK_1";
    public static final String ATRIBUTOS_ESTATICOS = "LyK_2";
    public static final String METODOS_TOTALTES = "LyK_3";
    public static final String METODOS_PUBLICOS = "LyK_4";
    public static final String METODOS_ESTATICOS = "LyK_5";
    public static final String DATOS_METODOS_CLASE = "LyK_6";
    public static final String IDS_CLASES_E_INTEFACES_SOBRESCRITAS = "LyK_7";
    public static final String ATRIBUTOS_HEREDADOS = "LyK_8";
    public static final String METODOS_HEREDADOS = "LyK_9";
    public static final String PARAMETROS_TOTALES = "LyK_10";
    public static final String DATOS_HERENCIA_CLASE = "LyK_11";
    public static final String IDS_CLASES_PADRE = "LyK_12";
    public static final String IDS_INTERFACES_IMPLEMENTADAS = "LyK_13";
    //Ids para la recopilación de la familia de métricas MG.
    public static final String TOTAL_CLASES = "MG_0";
    public static final String TOTAL_CLASES_ABSTRACTAS = "MG_1";
    public static final String TOTAL_INTERFACES = "MG_2";
    public static final String TOTAL_PAQUETES = "MG_3";
    public static final String TOTAL_ATRIBUTOS = "MG_4";
    public static final String TOTAL_ATRIBUTOS_PUBLICOS = "MG_5";
    public static final String TOTAL_METODOS = "MG_6";
    public static final String TOTAL_METODOS_PUBLICOS = "MG_7";
    public static final String IDES_CLASES = "MG_8";
    public static final String NOMBRES_CLASES = "MG_9";
    //Ids para la recopilación de la familia de métricas MOOD.
    public static final String TOTAL_CLASES_HIJAS = "MOOD_0";
    public static final String TOTAL_CLUSTERS = "MOOD_1";
    public static final String TOTAL_RELACIONES_NH = "MOOD_2";
    public static final String TOTAL_ATRIBUTOS_PRIVADOS = "MOOD_3";
    public static final String TOTAL_METODOS_PRIVADOS = "MOOD_4";

    /**
     * Mètodo que permite calcular un dato especifico sobre una clase o
     * interface del diagrama.
     *
     * @param nombreArtefacto un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param ruta un String con la ruta donde se encuentra el archivo XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @param familia
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String nombreArtefacto, String ruta, String id, String familia);

    /**
     * Mètodo que permite calcular un dato especifico sobre una clase o
     * interface del diagrama.
     *
     * @param nombreArtefacto un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param file un objeto de la clase File que contiene la ruta del archivo
     * XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @param familia
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String nombreArtefacto, File file, String id, String familia);

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param ruta un String que contiene la ruta del archivo XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @param familia
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String ruta, String id, String familia);

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param file un objeto de la clase File que contiene la ruta del archivo.
     * @param id un entero que permite identificar el dato que se requiere.
     * @param familia
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(File file, String id, String familia);

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param path la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    public List<String> getArtifacId(String nombre, String path);

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param file la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    public List<String> getArtifacId(String nombre, File file);

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param path la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(String path, boolean isLookingForIds);

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param file una instancia de la clase File que contiene la ruta del
     * archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(File file, boolean isLookingForIds);

}
