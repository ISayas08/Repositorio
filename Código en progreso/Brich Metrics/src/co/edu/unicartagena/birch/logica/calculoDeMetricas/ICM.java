package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.io.File;

/**
 * Interfaz que declara los servicios ofrecidos por el subcomonente "Calculo de
 * métricas".
 *
 * @author Ismael Sayas Arrieta.
 */
public interface ICM {
    
    public static final String FAMILIA_CYK = "CyK";
    public static final String FAMILIA_LYK = "LyK";
    public static final String FAMILIA_MG = "MG";
    public static final String FAMILIA_MOOD = "MOOD";

    //Métricas de la familia CyK.
    public static final String DEPTH_OF_INHERITANCE_TREE = "CyK_0";
    public static final String NUMBER_OF_CHILDREN = "CyK_1";
    public static final String COUPLING_BETWEEN_OBJECT_CLASSES = "CyK_2";
    public static final String WEIGHTED_METHODS_PER_CLASS = "CyK_3";

    //Métricas para la familia LyK.
    public static final String NUMBER_OF_METHOD_OVERRIDDEN_NO_REALIZATION = "LyK__1";
    public static final String NUMBER_OF_PUBLIC_METHODS = "LyK_0";
    public static final String NUMBER_OF_METHODS = "LyK_1";
    public static final String NUMBER_OF_PUBLIC_VARIABLES = "LyK_2";
    public static final String NUMBER_OF_VARIABLES = "LyK_3";
    public static final String NUMBER_OF_CLASS_VARIABLES = "LyK_4";
    public static final String NUMBER_OF_CLASS_METHOD = "LyK_5";
    public static final String NUMBER_OF_METHOD_INHERITED = "LyK_6";
    public static final String NUMBER_OF_METHOD_OVERRIDDEN = "LyK_7";
    public static final String NUMBER_OF_NEW_METHOD = "LyK_8";
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

    /**
     * Método que permite calcular las métricas para un artefacto especifico.
     *
     * @param artifactId la id del artefacto.
     * @param path la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    public IResultado calcularMetricasArtefacto(String artifactId, String path);

    /**
     * Método que permite calcular las métricas para un artefacto especifico.
     *
     * @param artifactId la id del artefacto.
     * @param file un objeto File que contiene la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    public IResultado calcularMetricasArtefacto(String artifactId, File file);

    /**
     * Método que permite calcular las métricas generales del diagrama.
     *
     * @param path la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    public IResultado calcularMetricasGenerales(String path);

    /**
     * Método que permite calcular las métricas generales del diagrama.
     *
     * @param file un objeto File que contiene la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    public IResultado calcularMetricasGenerales(File file);

    /**
     * Método que permite calcular una métrica especifica sobre un artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto sobre el
     * cual se calculará la métrica.
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que identifica la métrica a calcular.
     * @param familia un string que especifica la familia de métricas a la que
     * pertenece la métrica.
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    public double calcularMetricasArtefacto(String artifactId, String path, String id, String familia);

    /**
     * Método que permite calcular una métrica especifica sobre un artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto sobre el
     * cual se calculará la métrica.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que identifica la métrica a calcular.
     * @param familia un string que especifica la familia de métricas a la que
     * pertenece la métrica.
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    public double calcularMetricasArtefacto(String artifactId, File file, String id, String familia);

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @param familia un string que especifica la familia de métricas a la que
     * pertenece la métrica.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica.
     */
    public double calcularMetricasGenerales(String path, String id, String familia);

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @param familia un string que especifica la familia de métricas a la que
     * pertenece la métrica.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica.
     */
    public double calcularMetricasGenerales(File file, String id, String familia);
}
