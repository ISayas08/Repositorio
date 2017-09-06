package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Clase que almacena los datos generados luego de realizar el cálculo de
 * métricas a un artefacto.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 26/07/2016
 */
public class ResultadosArtefacto implements IResultado {

    private String artifactId;

    private int depthOfInheritanceTree;
    private int numberOfChildren;
    private int couplingBetweenObjectClasses;
    private int weightedMethodsPerClass;

    private int numberOfPublicMethods;
    private int numberOfMethods;
    private int numberOfPublicVariables;
    private int numberOfVariables;
    private int numberOfClassVariables;
    private int numberofClassMethods;
    private int numberOfMethodsInherited;
    private int numberOfMethodsOverridden;
    private int numberOfNewMethods;
    private double avarageParametersPerMethod;
    private double specializationIndex;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param artifactId la id del artefacto
     * @param a una lista con todos los datos, ordenados
     */
    public ResultadosArtefacto(String artifactId, List<Double> a) {
        this.artifactId = artifactId;

        this.depthOfInheritanceTree = (int) (double) a
                .get(DEPTH_OF_INHERITANCE_TREE);
        this.numberOfChildren = (int) (double) a
                .get(NUMBER_OF_CHILDREN);
        this.couplingBetweenObjectClasses = (int) (double) a
                .get(COUPLING_BETWEEN_OBJECT_CLASSES);
        this.weightedMethodsPerClass = (int) (double) a
                .get(WEIGHTED_METHODS_PER_CLASS);

        this.numberOfPublicMethods = (int) (double) a
                .get(NUMBER_OF_PUBLIC_METHODS);
        this.numberOfMethods = (int) (double) a
                .get(NUMBER_OF_METHODS);
        this.numberOfPublicVariables = (int) (double) a
                .get(NUMBER_OF_PUBLIC_VARIABLES);
        this.numberOfVariables = (int) (double) a
                .get(NUMBER_OF_VARIABLES);
        this.numberOfClassVariables = (int) (double) a
                .get(NUMBER_OF_CLASS_VARIABLES);
        this.numberofClassMethods = (int) (double) a
                .get(NUMBER_OF_CLASS_METHOD);
        this.numberOfMethodsInherited = (int) (double) a
                .get(NUMBER_OF_METHODS_INHERITED);
        this.numberOfMethodsOverridden = (int) (double) a
                .get(NUMBER_OF_METHOD_OVERRIDDEN);
        this.numberOfNewMethods = (int) (double) a
                .get(NUMBER_OF_NEW_METHOD);
        this.avarageParametersPerMethod = a
                .get(AVERAGE_PARAMETER_PER_METHOD);
        this.specializationIndex = a
                .get(SPECIALIZATION_INDEX);
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
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
    @Override
    public List<String> getDatosAsListString(int filtro) {
        switch (filtro) {
            case WITHOUT_NAME:
                return Arrays.asList("" + this.depthOfInheritanceTree,
                        "" + this.numberOfChildren,
                        "" + this.couplingBetweenObjectClasses,
                        "" + this.weightedMethodsPerClass,
                        "" + this.numberOfPublicMethods,
                        "" + this.numberOfMethods,
                        "" + this.numberOfPublicVariables,
                        "" + this.numberOfVariables,
                        "" + this.numberOfClassVariables,
                        "" + this.numberofClassMethods,
                        "" + this.numberOfMethodsInherited,
                        "" + this.numberOfMethodsOverridden,
                        "" + this.numberOfNewMethods,
                        "" + this.avarageParametersPerMethod,
                        "" + this.specializationIndex);
            case CYK_ONLY:
                return Arrays.asList(
                        "" + this.depthOfInheritanceTree,
                        "" + this.numberOfChildren,
                        "" + this.couplingBetweenObjectClasses,
                        "" + this.weightedMethodsPerClass);
            case LYK_ONLY:
                return Arrays.asList("" + this.numberOfPublicMethods,
                        "" + this.numberOfMethods,
                        "" + this.numberOfPublicVariables,
                        "" + this.numberOfVariables,
                        "" + this.numberOfClassVariables,
                        "" + this.numberofClassMethods,
                        "" + this.numberOfMethodsInherited,
                        "" + this.numberOfMethodsOverridden,
                        "" + this.numberOfNewMethods,
                        "" + this.avarageParametersPerMethod,
                        "" + this.specializationIndex);
            case INTEGERS_ONLY:
                return Arrays.asList("" + this.depthOfInheritanceTree,
                        "" + this.numberOfChildren,
                        "" + this.couplingBetweenObjectClasses,
                        "" + this.weightedMethodsPerClass,
                        "" + this.numberOfPublicMethods,
                        "" + this.numberOfMethods,
                        "" + this.numberOfPublicVariables,
                        "" + this.numberOfVariables,
                        "" + this.numberOfClassVariables,
                        "" + this.numberofClassMethods,
                        "" + this.numberOfMethodsInherited,
                        "" + this.numberOfMethodsOverridden,
                        "" + this.numberOfNewMethods);
            case DECIMALS_ONLY:
                return Arrays.asList(
                        "" + this.avarageParametersPerMethod,
                        "" + this.specializationIndex);
            default:
                return Arrays.asList("" + this.artifactId,
                        "" + this.depthOfInheritanceTree,
                        "" + this.numberOfChildren,
                        "" + this.couplingBetweenObjectClasses,
                        "" + this.weightedMethodsPerClass,
                        "" + this.numberOfPublicMethods,
                        "" + this.numberOfMethods,
                        "" + this.numberOfPublicVariables,
                        "" + this.numberOfVariables,
                        "" + this.numberOfClassVariables,
                        "" + this.numberofClassMethods,
                        "" + this.numberOfMethodsInherited,
                        "" + this.numberOfMethodsOverridden,
                        "" + this.numberOfNewMethods,
                        "" + this.avarageParametersPerMethod,
                        "" + this.specializationIndex);
        }
    }

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
    @Override
    public Iterator getDatosAsIterator(int filtro) {
        return this.getDatosAsListString(filtro).iterator();
    }

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
    @Override
    public HashMap getDatosAsHashMap(int filtro) {
        HashMap datos = new HashMap();

        switch (filtro) {
            case WITHOUT_NAME:
                datos.put("Depth of Inheritance Tree",
                        this.depthOfInheritanceTree);
                datos.put(IResultado.DEPTH_OF_INHERITANCE_TREE,
                        this.depthOfInheritanceTree);
                datos.put("Profundidad del árbol de herencias",
                        this.depthOfInheritanceTree);
                datos.put("Number of Children",
                        this.numberOfChildren);
                datos.put(IResultado.NUMBER_OF_CHILDREN,
                        this.numberOfChildren);
                datos.put("Número de hijos",
                        this.numberOfChildren);
                datos.put("Coupling Between Object Classes",
                        this.couplingBetweenObjectClasses);
                datos.put(IResultado.COUPLING_BETWEEN_OBJECT_CLASSES,
                        this.couplingBetweenObjectClasses);
                datos.put("Acoplamiento entre clases",
                        this.couplingBetweenObjectClasses);
                datos.put("Weighted Methods Per Class",
                        this.weightedMethodsPerClass);
                datos.put(IResultado.WEIGHTED_METHODS_PER_CLASS,
                        this.weightedMethodsPerClass);
                datos.put("Promedio de métodos por clases",
                        this.weightedMethodsPerClass);
                //
                datos.put("Number of Public Methods",
                        this.numberOfPublicMethods);
                datos.put(IResultado.NUMBER_OF_PUBLIC_METHODS,
                        this.numberOfPublicMethods);
                datos.put("Número de métodos públicos",
                        this.numberOfPublicMethods);
                datos.put("Number of Methods",
                        this.numberOfMethods);
                datos.put(IResultado.NUMBER_OF_METHODS,
                        this.numberOfMethods);
                datos.put("Número de métodos",
                        this.numberOfMethods);
                datos.put("Number of Public Variables",
                        this.numberOfPublicVariables);
                datos.put(IResultado.NUMBER_OF_PUBLIC_VARIABLES,
                        this.numberOfPublicVariables);
                datos.put("Número de variables públicas",
                        this.numberOfPublicVariables);
                datos.put("Number of Variables",
                        this.numberOfVariables);
                datos.put(IResultado.NUMBER_OF_VARIABLES,
                        this.numberOfVariables);
                datos.put("Número de variables",
                        this.numberOfVariables);
                datos.put("Number of Class Variables",
                        this.numberOfClassVariables);
                datos.put(IResultado.NUMBER_OF_CLASS_VARIABLES,
                        this.numberOfClassVariables);
                datos.put("Número de variables de clase",
                        this.numberOfClassVariables);
                datos.put("Number of Class Methods",
                        this.numberofClassMethods);
                datos.put(IResultado.NUMBER_OF_CLASS_METHOD,
                        this.numberofClassMethods);
                datos.put("Número de métodos de clase",
                        this.numberofClassMethods);
                datos.put("Number of Methods Inherited",
                        this.numberOfMethodsInherited);
                datos.put(IResultado.NUMBER_OF_METHODS_INHERITED,
                        this.numberOfMethodsInherited);
                datos.put("Número de métodos heredados",
                        this.numberOfMethodsInherited);
                datos.put("Number of Methods Overridden",
                        this.numberOfMethodsOverridden);
                datos.put(IResultado.NUMBER_OF_METHOD_OVERRIDDEN,
                        this.numberOfMethodsOverridden);
                datos.put("Número de métodos sobrescritos",
                        this.numberOfMethodsOverridden);
                datos.put("Number of New Methods",
                        this.numberOfNewMethods);
                datos.put(IResultado.NUMBER_OF_NEW_METHOD,
                        this.numberOfNewMethods);
                datos.put("Número de métodos nuevos",
                        this.numberOfNewMethods);
                datos.put("Average Parameters Per Method",
                        this.avarageParametersPerMethod);
                datos.put(IResultado.AVERAGE_PARAMETER_PER_METHOD,
                        this.avarageParametersPerMethod);
                datos.put("Promedio de parámetros por método",
                        this.avarageParametersPerMethod);
                datos.put("Specialization Index",
                        this.specializationIndex);
                datos.put(IResultado.SPECIALIZATION_INDEX,
                        this.specializationIndex);
                datos.put("Indice de especialización",
                        this.specializationIndex);
                break;
            case CYK_ONLY:
                datos.put("Depth of Inheritance Tree",
                        this.depthOfInheritanceTree);
                datos.put(IResultado.DEPTH_OF_INHERITANCE_TREE,
                        this.depthOfInheritanceTree);
                datos.put("Profundidad del árbol de herencias",
                        this.depthOfInheritanceTree);
                datos.put("Number of Children",
                        this.numberOfChildren);
                datos.put(IResultado.NUMBER_OF_CHILDREN,
                        this.numberOfChildren);
                datos.put("Número de hijos",
                        this.numberOfChildren);
                datos.put("Coupling Between Object Classes",
                        this.couplingBetweenObjectClasses);
                datos.put(IResultado.COUPLING_BETWEEN_OBJECT_CLASSES,
                        this.couplingBetweenObjectClasses);
                datos.put("Acoplamiento entre clases",
                        this.couplingBetweenObjectClasses);
                datos.put("Weighted Methods Per Class",
                        this.weightedMethodsPerClass);
                datos.put(IResultado.WEIGHTED_METHODS_PER_CLASS,
                        this.weightedMethodsPerClass);
                datos.put("Promedio de métodos por clases",
                        this.weightedMethodsPerClass);
                break;
            case LYK_ONLY:
                datos.put("Number of Public Methods",
                        this.numberOfPublicMethods);
                datos.put(IResultado.NUMBER_OF_PUBLIC_METHODS,
                        this.numberOfPublicMethods);
                datos.put("Número de métodos públicos",
                        this.numberOfPublicMethods);
                datos.put("Number of Methods",
                        this.numberOfMethods);
                datos.put(IResultado.NUMBER_OF_METHODS,
                        this.numberOfMethods);
                datos.put("Número de métodos",
                        this.numberOfMethods);
                datos.put("Number of Public Variables",
                        this.numberOfPublicVariables);
                datos.put(IResultado.NUMBER_OF_PUBLIC_VARIABLES,
                        this.numberOfPublicVariables);
                datos.put("Número de variables públicas",
                        this.numberOfPublicVariables);
                datos.put("Number of Variables",
                        this.numberOfVariables);
                datos.put(IResultado.NUMBER_OF_VARIABLES,
                        this.numberOfVariables);
                datos.put("Número de variables",
                        this.numberOfVariables);
                datos.put("Number of Class Variables",
                        this.numberOfClassVariables);
                datos.put(IResultado.NUMBER_OF_CLASS_VARIABLES,
                        this.numberOfClassVariables);
                datos.put("Número de variables de clase",
                        this.numberOfClassVariables);
                datos.put("Number of Class Methods",
                        this.numberofClassMethods);
                datos.put(IResultado.NUMBER_OF_CLASS_METHOD,
                        this.numberofClassMethods);
                datos.put("Número de métodos de clase",
                        this.numberofClassMethods);
                datos.put("Number of Methods Inherited",
                        this.numberOfMethodsInherited);
                datos.put(IResultado.NUMBER_OF_METHODS_INHERITED,
                        this.numberOfMethodsInherited);
                datos.put("Número de métodos heredados",
                        this.numberOfMethodsInherited);
                datos.put("Number of Methods Overridden",
                        this.numberOfMethodsOverridden);
                datos.put(IResultado.NUMBER_OF_METHOD_OVERRIDDEN,
                        this.numberOfMethodsOverridden);
                datos.put("Número de métodos sobrescritos",
                        this.numberOfMethodsOverridden);
                datos.put("Number of New Methods",
                        this.numberOfNewMethods);
                datos.put(IResultado.NUMBER_OF_NEW_METHOD,
                        this.numberOfNewMethods);
                datos.put("Número de métodos nuevos",
                        this.numberOfNewMethods);
                datos.put("Average Parameters Per Method",
                        this.avarageParametersPerMethod);
                datos.put(IResultado.AVERAGE_PARAMETER_PER_METHOD,
                        this.avarageParametersPerMethod);
                datos.put("Promedio de parámetros por método",
                        this.avarageParametersPerMethod);
                datos.put("Specialization Index",
                        this.specializationIndex);
                datos.put(IResultado.SPECIALIZATION_INDEX,
                        this.specializationIndex);
                datos.put("Indice de especialización",
                        this.specializationIndex);
                break;
            case INTEGERS_ONLY:
                datos.put("Depth of Inheritance Tree",
                        this.depthOfInheritanceTree);
                datos.put(IResultado.DEPTH_OF_INHERITANCE_TREE,
                        this.depthOfInheritanceTree);
                datos.put("Profundidad del árbol de herencias",
                        this.depthOfInheritanceTree);
                datos.put("Number of Children",
                        this.numberOfChildren);
                datos.put(IResultado.NUMBER_OF_CHILDREN,
                        this.numberOfChildren);
                datos.put("Número de hijos",
                        this.numberOfChildren);
                datos.put("Coupling Between Object Classes",
                        this.couplingBetweenObjectClasses);
                datos.put(IResultado.COUPLING_BETWEEN_OBJECT_CLASSES,
                        this.couplingBetweenObjectClasses);
                datos.put("Acoplamiento entre clases",
                        this.couplingBetweenObjectClasses);
                datos.put("Weighted Methods Per Class",
                        this.weightedMethodsPerClass);
                datos.put(IResultado.WEIGHTED_METHODS_PER_CLASS,
                        this.weightedMethodsPerClass);
                datos.put("Promedio de métodos por clases",
                        this.weightedMethodsPerClass);
                //
                datos.put("Number of Public Methods",
                        this.numberOfPublicMethods);
                datos.put(IResultado.NUMBER_OF_PUBLIC_METHODS,
                        this.numberOfPublicMethods);
                datos.put("Número de métodos públicos",
                        this.numberOfPublicMethods);
                datos.put("Number of Methods",
                        this.numberOfMethods);
                datos.put(IResultado.NUMBER_OF_METHODS,
                        this.numberOfMethods);
                datos.put("Número de métodos",
                        this.numberOfMethods);
                datos.put("Number of Public Variables",
                        this.numberOfPublicVariables);
                datos.put(IResultado.NUMBER_OF_PUBLIC_VARIABLES,
                        this.numberOfPublicVariables);
                datos.put("Número de variables públicas",
                        this.numberOfPublicVariables);
                datos.put("Number of Variables",
                        this.numberOfVariables);
                datos.put(IResultado.NUMBER_OF_VARIABLES,
                        this.numberOfVariables);
                datos.put("Número de variables",
                        this.numberOfVariables);
                datos.put("Number of Class Variables",
                        this.numberOfClassVariables);
                datos.put(IResultado.NUMBER_OF_CLASS_VARIABLES,
                        this.numberOfClassVariables);
                datos.put("Número de variables de clase",
                        this.numberOfClassVariables);
                datos.put("Number of Class Methods",
                        this.numberofClassMethods);
                datos.put(IResultado.NUMBER_OF_CLASS_METHOD,
                        this.numberofClassMethods);
                datos.put("Número de métodos de clase",
                        this.numberofClassMethods);
                datos.put("Number of Methods Inherited",
                        this.numberOfMethodsInherited);
                datos.put(IResultado.NUMBER_OF_METHODS_INHERITED,
                        this.numberOfMethodsInherited);
                datos.put("Número de métodos heredados",
                        this.numberOfMethodsInherited);
                datos.put("Number of Methods Overridden",
                        this.numberOfMethodsOverridden);
                datos.put(IResultado.NUMBER_OF_METHOD_OVERRIDDEN,
                        this.numberOfMethodsOverridden);
                datos.put("Número de métodos sobrescritos",
                        this.numberOfMethodsOverridden);
                datos.put("Number of New Methods",
                        this.numberOfNewMethods);
                datos.put(IResultado.NUMBER_OF_NEW_METHOD,
                        this.numberOfNewMethods);
                datos.put("Número de métodos nuevos",
                        this.numberOfNewMethods);
                break;
            case DECIMALS_ONLY:
                datos.put("Average Parameters Per Method",
                        this.avarageParametersPerMethod);
                datos.put(IResultado.AVERAGE_PARAMETER_PER_METHOD,
                        this.avarageParametersPerMethod);
                datos.put("Promedio de parámetros por método",
                        this.avarageParametersPerMethod);
                datos.put("Specialization Index",
                        this.specializationIndex);
                datos.put(IResultado.SPECIALIZATION_INDEX,
                        this.specializationIndex);
                datos.put("Indice de especialización",
                        this.specializationIndex);
                break;
            default:
                datos.put("Name",
                        this.artifactId);
                datos.put(IResultado.ID,
                        this.artifactId);
                datos.put("Nombre",
                        this.artifactId);
                datos.put("Depth of Inheritance Tree",
                        this.depthOfInheritanceTree);
                datos.put(IResultado.DEPTH_OF_INHERITANCE_TREE,
                        this.depthOfInheritanceTree);
                datos.put("Profundidad del árbol de herencias",
                        this.depthOfInheritanceTree);
                datos.put("Number of Children",
                        this.numberOfChildren);
                datos.put(IResultado.NUMBER_OF_CHILDREN,
                        this.numberOfChildren);
                datos.put("Número de hijos",
                        this.numberOfChildren);
                datos.put("Coupling Between Object Classes",
                        this.couplingBetweenObjectClasses);
                datos.put(IResultado.COUPLING_BETWEEN_OBJECT_CLASSES,
                        this.couplingBetweenObjectClasses);
                datos.put("Acoplamiento entre clases",
                        this.couplingBetweenObjectClasses);
                datos.put("Weighted Methods Per Class",
                        this.weightedMethodsPerClass);
                datos.put(IResultado.WEIGHTED_METHODS_PER_CLASS,
                        this.weightedMethodsPerClass);
                datos.put("Promedio de métodos por clases",
                        this.weightedMethodsPerClass);
                //
                datos.put("Number of Public Methods",
                        this.numberOfPublicMethods);
                datos.put(IResultado.NUMBER_OF_PUBLIC_METHODS,
                        this.numberOfPublicMethods);
                datos.put("Número de métodos públicos",
                        this.numberOfPublicMethods);
                datos.put("Number of Methods",
                        this.numberOfMethods);
                datos.put(IResultado.NUMBER_OF_METHODS,
                        this.numberOfMethods);
                datos.put("Número de métodos",
                        this.numberOfMethods);
                datos.put("Number of Public Variables",
                        this.numberOfPublicVariables);
                datos.put(IResultado.NUMBER_OF_PUBLIC_VARIABLES,
                        this.numberOfPublicVariables);
                datos.put("Número de variables públicas",
                        this.numberOfPublicVariables);
                datos.put("Number of Variables",
                        this.numberOfVariables);
                datos.put(IResultado.NUMBER_OF_VARIABLES,
                        this.numberOfVariables);
                datos.put("Número de variables",
                        this.numberOfVariables);
                datos.put("Number of Class Variables",
                        this.numberOfClassVariables);
                datos.put(IResultado.NUMBER_OF_CLASS_VARIABLES,
                        this.numberOfClassVariables);
                datos.put("Número de variables de clase",
                        this.numberOfClassVariables);
                datos.put("Number of Class Methods",
                        this.numberofClassMethods);
                datos.put(IResultado.NUMBER_OF_CLASS_METHOD,
                        this.numberofClassMethods);
                datos.put("Número de métodos de clase",
                        this.numberofClassMethods);
                datos.put("Number of Methods Inherited",
                        this.numberOfMethodsInherited);
                datos.put(IResultado.NUMBER_OF_METHODS_INHERITED,
                        this.numberOfMethodsInherited);
                datos.put("Número de métodos heredados",
                        this.numberOfMethodsInherited);
                datos.put("Number of Methods Overridden",
                        this.numberOfMethodsOverridden);
                datos.put(IResultado.NUMBER_OF_METHOD_OVERRIDDEN,
                        this.numberOfMethodsOverridden);
                datos.put("Número de métodos sobrescritos",
                        this.numberOfMethodsOverridden);
                datos.put("Number of New Methods",
                        this.numberOfNewMethods);
                datos.put(IResultado.NUMBER_OF_NEW_METHOD,
                        this.numberOfNewMethods);
                datos.put("Número de métodos nuevos",
                        this.numberOfNewMethods);
                datos.put("Average Parameters Per Method",
                        this.avarageParametersPerMethod);
                datos.put(IResultado.AVERAGE_PARAMETER_PER_METHOD,
                        this.avarageParametersPerMethod);
                datos.put("Promedio de parámetros por método",
                        this.avarageParametersPerMethod);
                datos.put("Specialization Index",
                        this.specializationIndex);
                datos.put(IResultado.SPECIALIZATION_INDEX,
                        this.specializationIndex);
                datos.put("Indice de especialización",
                        this.specializationIndex);
        }

        return datos;
    }

    /**
     * Método que permite obtener un valor especifico dara una determinada
     * métrica.
     *
     * @param idDato un entero que sirve para especificar el valor de la métrica
     * que se quiere obtener. Están todos especificados en la Interface
     * IResultado.
     * @return un String que contiene el valor de la métrica.
     */
    @Override
    public String get(int idDato) {
        String dato = "NA";

        switch (idDato) {
            case IResultado.ID:
                dato = artifactId;
                break;
            case IResultado.DEPTH_OF_INHERITANCE_TREE:
                dato = "" + this.depthOfInheritanceTree;
                break;
            case IResultado.NUMBER_OF_CHILDREN:
                dato = "" + this.numberOfChildren;
                break;
            case IResultado.COUPLING_BETWEEN_OBJECT_CLASSES:
                dato = "" + this.couplingBetweenObjectClasses;
                break;
            case IResultado.WEIGHTED_METHODS_PER_CLASS:
                dato = "" + this.weightedMethodsPerClass;
                break;
            case IResultado.NUMBER_OF_PUBLIC_METHODS:
                dato = "" + this.numberOfPublicMethods;
                break;
            case IResultado.NUMBER_OF_METHODS:
                dato = "" + this.numberOfMethods;
                break;
            case IResultado.NUMBER_OF_PUBLIC_VARIABLES:
                dato = "" + this.numberOfPublicVariables;
                break;
            case IResultado.NUMBER_OF_VARIABLES:
                dato = "" + this.numberOfVariables;
                break;
            case IResultado.NUMBER_OF_CLASS_VARIABLES:
                dato = "" + this.numberOfClassVariables;
                break;
            case IResultado.NUMBER_OF_CLASS_METHOD:
                dato = "" + this.numberofClassMethods;
                break;
            case IResultado.NUMBER_OF_METHODS_INHERITED:
                dato = "" + this.numberOfMethodsInherited;
                break;
            case IResultado.NUMBER_OF_METHOD_OVERRIDDEN:
                dato = "" + this.numberOfMethodsOverridden;
                break;
            case IResultado.NUMBER_OF_NEW_METHOD:
                dato = "" + this.numberOfNewMethods;
                break;
            case IResultado.AVERAGE_PARAMETER_PER_METHOD:
                dato = "" + this.avarageParametersPerMethod;
                break;
            case IResultado.SPECIALIZATION_INDEX:
                dato = "" + this.specializationIndex;
                break;
        }
        return dato;
    }

    /**
     * Método que transforma en String el objeto.
     *
     * @return un String con los datos del objeto.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");

        if (Locale.getDefault().getDisplayLanguage().equals("español")) {
            return "\tMÉTRICAS ARTEFACTO.\n\n"
                    + "Id del artefacto: " + artifactId + ".\n\n"
                    + "Métricas CyK.\n\n"
                    + "Profundidad del árbol de herencias: "
                    + depthOfInheritanceTree + ".\n"
                    + "Número de hijos: " + numberOfChildren + ".\n"
                    + "Acoplamiento entre clases: "
                    + couplingBetweenObjectClasses + ".\n"
                    + "Métodos ponderados por clase: "
                    + weightedMethodsPerClass + ".\n"
                    + "\nMétricas LyK.\n\n"
                    + "Número de métodos públicos: "
                    + numberOfPublicMethods + ".\n"
                    + "Número de métodos: " + numberOfMethods + ".\n"
                    + "Número de variables públlicas: "
                    + numberOfPublicVariables + ".\n"
                    + "Número de variables: " + numberOfVariables + ".\n"
                    + "Número de variables estáticas: "
                    + numberOfClassVariables + ".\n"
                    + "Número de métodos estáticos: "
                    + numberofClassMethods + ".\n"
                    + "Número de métodos heredados: "
                    + numberOfMethodsInherited + ".\n"
                    + "Número de métodos sobrescritos: "
                    + numberOfMethodsOverridden + ".\n"
                    + "Número de métodos añadidos: "
                    + numberOfNewMethods + ".\n"
                    + "Promedio de parámetros por clase: "
                    + df.format(avarageParametersPerMethod) + ".\n"
                    + "Indice de especialización: "
                    + df.format(specializationIndex) + ".\n";
        } else {
            return "\tMetrics Artifact.\n\n"
                    + "Artifact Id: " + artifactId + ".\n\n"
                    + "CyK Metrics.\n\n"
                    + "Depth of inheritance tree: "
                    + depthOfInheritanceTree + ".\n"
                    + "Number of chindren: " + numberOfChildren + ".\n"
                    + "Coupling between object classes: "
                    + couplingBetweenObjectClasses + ".\n"
                    + "Weighted Methods per Class: "
                    + weightedMethodsPerClass + ".\n"
                    + "\nLyK Metrics.\n\n"
                    + "Number of public methods: "
                    + numberOfPublicMethods + ".\n"
                    + "Number of methods: " + numberOfMethods + ".\n"
                    + "Number of public variables: "
                    + numberOfPublicVariables + ".\n"
                    + "Number of variables: " + numberOfVariables + ".\n"
                    + "Number of class variables: "
                    + numberOfClassVariables + ".\n"
                    + "Number of class methods: " + numberofClassMethods + ".\n"
                    + "Number of methods inherited: "
                    + numberOfMethodsInherited + ".\n"
                    + "Number of method overridden: "
                    + numberOfMethodsOverridden + ".\n"
                    + "Number of new method: "
                    + numberOfNewMethods + ".\n"
                    + "Average parameter per method: "
                    + df.format(avarageParametersPerMethod) + ".\n"
                    + "Especialization index: "
                    + df.format(specializationIndex) + ".\n";
        }
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public String getNombre() {
        return artifactId;
    }

    public void setNombre(String nombre) {
        this.artifactId = nombre;
    }

    public int getDepthOfInheritanceTree() {
        return depthOfInheritanceTree;
    }

    public void setDepthOfInheritanceTree(int depthOfInheritanceTree) {
        this.depthOfInheritanceTree = depthOfInheritanceTree;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getCouplingBetweenObjectClasses() {
        return couplingBetweenObjectClasses;
    }

    public void setCouplingBetweenObjectClasses(int couplingBetweenObjectClasses) {
        this.couplingBetweenObjectClasses = couplingBetweenObjectClasses;
    }

    public int getWeightedMethodsPerClass() {
        return weightedMethodsPerClass;
    }

    public void setWeightedMethodsPerClass(int weightedMethodsPerClass) {
        this.weightedMethodsPerClass = weightedMethodsPerClass;
    }

    public int getNumberOfPublicMethods() {
        return numberOfPublicMethods;
    }

    public void setNumberOfPublicMethods(int numberOfPublicMethods) {
        this.numberOfPublicMethods = numberOfPublicMethods;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public void setNumberOfMethods(int numberOfMethods) {
        this.numberOfMethods = numberOfMethods;
    }

    public int getNumberOfPublicVariables() {
        return numberOfPublicVariables;
    }

    public void setNumberOfPublicVariables(int numberOfPublicVariables) {
        this.numberOfPublicVariables = numberOfPublicVariables;
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public void setNumberOfVariables(int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }

    public int getNumberOfClassVariables() {
        return numberOfClassVariables;
    }

    public void setNumberOfClassVariables(int numberOfClassVariables) {
        this.numberOfClassVariables = numberOfClassVariables;
    }

    public int getNumberofClassMethod() {
        return numberofClassMethods;
    }

    public void setNumberofClassMethod(int numberofClassMethod) {
        this.numberofClassMethods = numberofClassMethod;
    }

    public int getNumberOfMethodsInherited() {
        return numberOfMethodsInherited;
    }

    public void setNumberOfMethodsInherited(int numberOfMethodsInherited) {
        this.numberOfMethodsInherited = numberOfMethodsInherited;
    }

    public int getNumberOfMethodsOverridden() {
        return numberOfMethodsOverridden;
    }

    public void setNumberOfMethodsOverridden(int numberOfMethodsOverridden) {
        this.numberOfMethodsOverridden = numberOfMethodsOverridden;
    }

    public int getNumberOfNewMethods() {
        return numberOfNewMethods;
    }

    public void setNumberOfNewMethods(int numberOfNewMethods) {
        this.numberOfNewMethods = numberOfNewMethods;
    }

    public double getAvarageParametersPerMethod() {
        return avarageParametersPerMethod;
    }

    public void setAvarageParametersPerMethod(double avarageParametersPerMethod) {
        this.avarageParametersPerMethod = avarageParametersPerMethod;
    }

    public double getSpecializationIndex() {
        return specializationIndex;
    }

    public void setSpecializationIndex(double specializationIndex) {
        this.specializationIndex = specializationIndex;
    }

}
