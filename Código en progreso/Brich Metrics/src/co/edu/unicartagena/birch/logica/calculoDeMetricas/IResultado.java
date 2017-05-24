package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Interface que rovee los metodos a las clases que contienen los resultados del
 * cálculo de métricas.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 26/07/2016
 */
public interface IResultado {

    public static final int DEPTH_OF_INHERITANCE_TREE = 0;
    public static final int NUMBER_OF_CHILDREN = 1;
    public static final int COUPLING_BETWEEN_OBJECT_CLASSES = 2;
    public static final int WEIGHTED_METHODS_PER_CLASS = 3;
    //
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
    public static final int NAME = 15;

    public static final int NUMBER_OF_CLASSES_D = 0;
    public static final int N_ABSTRACT_CLASSES_D = 1;
    public static final int N_INTERFACES_D = 2;
    public static final int N_PACKAGE_D = 3;
    public static final int AVERAGE_METHODS_CLASS = 4; 
    public static final int AVERAGE_PUBLIC_METHODS_CLASS = 5; 
    public static final int AVERAGE_ATTRIBUTES_CLASS = 6; 
    public static final int AVERAGE_PUBLIC_ATTRIBUTE_CLASS = 7; 
    //
    public static final int METHOD_HIDING_FACTOR = 8;
    public static final int ATTRIBUTE_HIDING_FACTOR = 9;
    public static final int METHOD_INHERITANCE_FACTOR = 10;
    public static final int ATTRIBUTE_INHERITANCE_FACTOR = 11;
    public static final int POLYMORPHISM_FACTOR = 12; 
    public static final int COUPLING_FACTOR = 13;
    public static final int CLUSTERING_FACTOR = 14;
    public static final int REUSE_FACTOR = 15;

    public static final int WITHOUT_NAME = 1;
    public static final int CyK_ONLY = 2;
    public static final int LyK_ONLY = 3;
    public static final int GM_ONLY = 4;
    public static final int MOOD_ONLY = 5;
    public static final int INTEGERS_ONLY = 6;
    public static final int DECIMALS_ONLY = 7;

    /**
     * Método que permite obtener los datos como una lista de String,
     * dependiendo del entero que se le pase como parámetro.
     *
     * @param filtro es un entero que permite especificar la información que se
     * desea obtener. la interface IResultado tiene variables estáticas con los
     * valores que se admiten como filtro. Cualquier otro valor diferente a los
     * especificados por dichas variables retornará toda la información.
     * @return una lista de String.
     */
    public List<String> getDatosAsListString(int filtro);

    /**
     * Método que permite obtener los datos como un Iterator, dependiendo del
     * entero que se le pase como parámetro.
     *
     * @param filtro es un entero que permite especificar la información que se
     * desea obtener. la interface IResultado tiene variables estáticas con los
     * valores que se admiten como filtro. Cualquier otro valor diferente a los
     * especificados por dichas variables retornará toda la información.
     * @return una instancia de Iterator.
     */
    public Iterator getDatosAsIterator(int filtro);

    /**
     * Método que permite obtener los datos como una instancia de HashMap,
     * dependiendo del entero que se le pase como parámetro.
     *
     * @param filtro es un entero que permite especificar la información que se
     * desea obtener. la interface IResultado tiene variables estáticas con los
     * valores que se admiten como filtro. Cualquier otro valor diferente a los
     * especificados por dichas variables retornará toda la información.
     * @return una instancia de HashMap.
     */
    public HashMap getDatosAsHashMap(int filtro);

    /**
     * Método que permite obtener un valor especifico dara una determinada
     * métrica.
     *
     * @param idDato un entero que sirve para especificar el valor de la métrica
     * que se quiere obtener. Están todos especificados en la Interface
     * IResultado.
     * @return un String que contiene el valor de la métrica.
     */
    public String get(int idDato);
}
