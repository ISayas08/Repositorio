package co.edu.unicartagena.birch.logica;

import co.edu.unicartagena.birch.logica.calculoDeMetricas.IResultado;
import java.io.File;
import java.util.List;

/**
 * Interface que ofrece los servicios del componente.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 29/07/2015
 */
public interface ICalculaMetricas {
//==============================================================================
//  Atributos generales.
//==============================================================================

    public static final String FAMILIA_CYK = "CyK";
    public static final String FAMILIA_LYK = "LyK";
    public static final String FAMILIA_MG = "MG";
    public static final String FAMILIA_MOOD = "MOOD";

    public static final int NUMERO_DE_MA_CYK = 4;
    public static final int NUMERO_DE_MA_LYK = 11;
    public static final int NUMERO_DE_MS_MG = 8;
    public static final int NUMERO_DE_MS_MOOD = 8;
    public static final int NUMERO_DE_M_ARTEFACTO = NUMERO_DE_MA_CYK
            + NUMERO_DE_MA_LYK;
    public static final int NUMERO_DE_M_SISTEMA = NUMERO_DE_MS_MG
            + NUMERO_DE_MS_MOOD;
    public static final int NUMERO_DE_METRICAS = NUMERO_DE_M_ARTEFACTO
            + NUMERO_DE_M_SISTEMA;

//==============================================================================
//  Ides para las mátricas.
//==============================================================================
    //Métricas de la familia CyK.
    public static final String DEPTH_OF_INHERITANCE_TREE = "CyK_0";
    public static final String NUMBER_OF_CHILDREN = "CyK_1";
    public static final String COUPLING_BETWEEN_OBJECT_CLASSES = "CyK_2";
    public static final String WEIGHTED_METHODS_PER_CLASS = "CyK_3";

    //Métricas para la familia LyK.
    public static final String NUMBER_OF_PUBLIC_METHODS = "LyK_0";
    public static final String NUMBER_OF_METHODS = "LyK_1";
    public static final String NUMBER_OF_PUBLIC_VARIABLES = "LyK_2";
    public static final String NUMBER_OF_VARIABLES = "LyK_3";
    public static final String NUMBER_OF_CLASS_VARIABLES = "LyK_4";
    public static final String NUMBER_OF_CLASS_METHODS = "LyK_5";
    public static final String NUMBER_OF_METHODS_INHERITED = "LyK_6";
    public static final String NUMBER_OF_METHODS_OVERRIDDEN = "LyK_7";
    public static final String NUMBER_OF_NEW_METHODS = "LyK_8";
    public static final String AVERAGE_PARAMETER_PER_METHOD = "LyK_9";
    public static final String SPECIALIZATION_INDEX = "LyK_10";

    //Métricas para la familia MG.
    public static final String NUMBER_OF_CLASSES = "MG_0";
    public static final String NUMBER_OF_ABSTRACT_CLASSES = "MG_1";
    public static final String NUMBER_OF_INTERFACES = "MG_2";
    public static final String NUMBER_OF_PACKAGES = "MG_3";
    public static final String AVERAGE_METHODS_ARTIFACT = "MG_4";
    public static final String AVERAGE_PUBLIC_METHODS_ARTIFACT = "MG_5";
    public static final String AVERAGE_ATTRIBUTES_ARTIFACT = "MG_6";
    public static final String AVERAGE_PUBLIC_ATTRIBUTE_ARTIFACT = "MG_7";

    //Métricas para la familia MOOD.
    public static final String METHOD_HIDING_FACTOR = "MOOD_0";
    public static final String ATTRIBUTE_HIDING_FACTOR = "MOOD_1";
    public static final String METHOD_INHERITANCE_FACTOR = "MOOD_2";
    public static final String ATTRIBUTE_INHERITANCE_FACTOR = "MOOD_3";
    public static final String POLYMORPHISM_FACTOR = "MOOD_4";
    public static final String COUPLING_FACTOR = "MOOD_5";
    public static final String CLUSTERING_FACTOR = "MOOD_6";
    public static final String REUSE_FACTOR = "MOOD_7";

//==============================================================================
//  Declaración de métodos.
//==============================================================================
    /**
     * Método que permite hacer el calculo de todas las métricas, a nivel de
     * artefacto, a un único artefacto perteneciente a un diagrama. Es necesario
     * especificar la id del artefacto y la ruta del archivo XMI donde se
     * encuentran los datos del mismo.
     *
     * @param artifactId un String con la id del artefacto.
     * @param path un String con la ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido, o se produzca algún error, se
     * retornará null y se mostrará un mensaje por consola.
     */
    public IResultado calcularMetricas(String artifactId, String path);

    /**
     * Método que permite hacer el calculo de todas las métricas, a nivel de
     * artefacto, a un único artefacto perteneciente a un diagrama. Es necesario
     * especificar la id del artefacto y la ruta del archivo XMI donde se
     * encuentran los datos del mismo.
     *
     * @param artifacId un String con la id del artefacto.
     * @param file un objeto File con la ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido, o se produzca algún error, se
     * retornará null y se mostrará un mensaje por consola.
     */
    public IResultado calcularMetricas(String artifacId, File file);

    /**
     * Método que permite calcular métricas que miden aspectos generales a nivel
     * de sistema, como la cantidad de clases, el número de paquetes, entre
     * otros. Es necesario proporcionar la ruta donde se encuentra el archivo
     * XMI que contiene los datos del diagrama.
     *
     * @param path un String que contiene La ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido o se produzca un error, se retornará
     * null.
     */
    public IResultado calcularMetricas(String path);

    /**
     * Método que permite calcular métricas que miden aspectos generales a nivel
     * de sistema, como la cantidad de clases, el número de paquetes, entre
     * otros. Es necesario proporcionar la ruta donde se encuentra el archivo
     * XMI que contiene los datos del diagrama.
     *
     * @param file un objeto File que contiene La ruta para acceder al archivo.
     * @return Retorna una instancia de tipo IResultado, la cual contiene el
     * resultado del cálculo de las métricas y los métodos necesarios para
     * acceder a la información. En caso de que no se encuentre el archivo, que
     * el mismo no sea considerado válido o se produzca un error, se retornará
     * null.
     */
    public IResultado calcularMetricas(File file);

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica de aquellas que miden las caracteristicas de un artefacto. Es
     * necesario proporcionar la ruta del archivo, el nombre del artefacto y la
     * id de la métrica que se desea obtener. Todas las IDs están especificadas
     * en la interface ICalculaMetricas.
     *
     * @param artifactId un String con el nombre del artefacto a evaluar.
     * @param path un String con la ruta para acceder al archivo.
     * @param metricId un String con la id de la métrica.
     * @return un String que contiene el valor de la métrica, o "NA" en caso de
     * que haya habido un error. De resultar el últmo evento se informará por
     * consola.
     */
    public String calcularMetrica(String artifactId, String path, String metricId);

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica de aquellas que miden las caracteristicas de un artefacto. Es
     * necesario proporcionar la ruta del archivo, la id del artefacto y la id
     * de la métrica que se desea obtener. Todas las IDs están especificadas en
     * la interface ICalculaMetricas.
     *
     * @param artifactId un String con la id del artefacto a evaluar.
     * @param file un objeto File con la ruta para acceder al archivo.
     * @param metricId un String con la id de la métrica.
     * @return un String que contiene el valor de la métrica, o "NA" en caso de
     * que haya habido un error. De resultar el últmo evento se informará por
     * consola.
     */
    public String calcularMetrica(String artifactId, File file, String metricId);

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica a nivel de diagrama. Es necesario proporcionar la ruta del
     * archivo y la id de la métrica que se desea obtener. Todas las IDs están
     * especificadas en la interface ICalculaMetricas.
     *
     * @param path un String con la ruta del archivo.
     * @param metricId un String con la id de la métrica.
     * @return un String que contiene el valor de la métrica o "NA" en caso de
     * que se haya producido un error, en cuyo caso se informará por consola.
     */
    public String calcularMetrica(String path, String metricId);

    /**
     * Método que permite obtener el resultado de calcular una métrica
     * especifica a nivel de diagrama. Es necesario proporcionar la ruta del
     * archivo y la id de la métrica que se desea obtener. Todas las IDs están
     * especificadas en la interface ICalculaMetricas.
     *
     * @param file un objeto File con la ruta del archivo.
     * @param metricId un String con la id de la métrica.
     * @return un String que contiene el valor de la métrica o "NA" en caso de
     * que se haya producido un error, en cuyo caso se informará por consola.
     */
    public String calcularMetrica(File file, String metricId);

    /**
     * Método que permite obtener los id de las clases con un nombre especifico.
     * Es necesario proporcionar la ruta del archivo y el nombre del(los)
     * artefacto(s).
     *
     * @param path la ruta del archivo XMI.
     * @param name el nombre del los artefactos a los que se desea conocer la
     * id.
     * @return Una lista de String que contiene las ids de los artefactos que
     * tienen el nombre indicado.
     */
    public List<String> getArtifacId(String name, String path);

    /**
     * Método que permite obtener los id de las clases con un nombre especifico.
     * Es necesario proporcionar la ruta del archivo y el nombre del(los)
     * artefacto(s).
     *
     * @param file un objeto de la clase File que contien la ruta del archivo
     * XMI.
     * @param name el nombre del los artefactos a los que se desea conocer la
     * id.
     * @return Una lista de String que contiene las ids de los artefactos que
     * tienen el nombre indicado.
     */
    public List<String> getArtifacId(String name, File file);

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param path la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres. (true = id, false = nombres)
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(String path, boolean isLookingForIds);

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param file una instancia de la clase File que contiene la ruta del
     * archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres. (true = id, false = nombres)
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(File file, boolean isLookingForIds);
}
