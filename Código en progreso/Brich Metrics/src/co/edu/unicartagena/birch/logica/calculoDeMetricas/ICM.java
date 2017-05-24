package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.io.File;

/**
 * Interfaz que declara los servicios ofrecidos por el subcomonente "Calculo de
 * métricas".
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.3
 * @since 14/09/2016
 */
public interface ICM {
    
    public static final int DEPTH_OF_INHERITANCE_TREE = 0;
    public static final int NUMBER_OF_CHILDREN = 1;
    public static final int COUPLING_BETWEEN_OBJECT_CLASSES = 2;
    public static final int WEIGHTED_METHODS_PER_CLASS = 3;
    public static final int NUMBER_OF_PUBLIC_METHODS = 4;
    public static final int NUMBER_OF_METHODS = 5;
    public static final int NUMBER_OF_PUBLIC_VARIABLES = 6;
    public static final int NUMBER_OF_VARIABLES = 7;
    public static final int NUMBER_OF_CLASS_VARIABLES = 8;
    public static final int NUMBER_OF_CLASS_METHOD = 9;
    public static final int NUMBER_OF_METHODS_INHERITED = 10;
    public static final int NUMBER_OF_METHOD_OVERRIDDEN = 11;
    public static final int NUMBER_OF_NEW_METHOD = 12;
    public static final int AVERAGE_PARAMETER_PER_METHOD = 13;
    public static final int SPECIALIZATION_INDEX = 14;
    public static final int ID = 15;

    public static final int NUMBER_OF_CLASSES = 0;
    public static final int NUMBER_OF_ABSTRACT_CLASSES = 1;
    public static final int NUMBER_OF_INTERFACES = 2;
    public static final int NUMBER_OF_PACKAGES = 3;
    public static final int AVERAGE_METHODS_CLASS = 4;
    public static final int AVERAGE_PUBLIC_METHODS_CLASS = 5;
    public static final int AVERAGE_ATTRIBUTES_CLASS = 6;
    public static final int AVERAGE_PUBLIC_ATTRIBUTE_CLASS = 7;
    public static final int METHOD_HIDING_FACTOR = 8;
    public static final int ATTRIBUTE_HIDING_FACTOR = 9;
    public static final int METHOD_INHERITANCE_FACTOR = 10;
    public static final int ATTRIBUTE_INHERITANCE_FACTOR = 11;
    public static final int POLYMORPHISM_FACTOR = 12;
    public static final int COUPLING_FACTOR = 13;
    public static final int CLUSTERING_FACTOR = 14;
    public static final int REUSE_FACTOR = 15;

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
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    public double calcularMetricasArtefacto(String artifactId, String path, int id);

    /**
     * Método que permite calcular una métrica especifica sobre un artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto sobre el
     * cual se calculará la métrica.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que identifica la métrica a calcular.
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    public double calcularMetricasArtefacto(String artifactId, File file, int id);

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica.
     */
    public double calcularMetricasGenerales(String path, int id);

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica.
     */
    public double calcularMetricasGenerales(File file, int id);
}
