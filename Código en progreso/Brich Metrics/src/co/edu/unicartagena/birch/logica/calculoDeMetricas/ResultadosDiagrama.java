package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Clase que almecena los datos generales o del diagrama, resultantes del
 * proceso de cáculo de métricas. La clase implementa tambien los métodos
 * declarados por la interface IResultado, los cuales permiten acceder a la
 * información con facilidad.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 26/07/2016
 */
public class ResultadosDiagrama implements IResultado {

    private int nClasesD;
    private int nClasesAbstractasD;
    private int nInterfacesD;
    private int nPaquetesD;
    private double promedioMetodosClase;
    private double promedioMetodosPClase;
    private double promedioAtributosClase;
    private double promedioAtributosPClase;

    private double methodHidingFactor;
    private double attributeHidingFactor;
    private double methodInheritanceFactor;
    private double attributeInheritanceFactor;
    private double polymorphismFactor;
    private double couplingFactor;
    private double clusteringFactor;
    private double reuseFactor;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param a
     */
    public ResultadosDiagrama(List<Double> a) {
        this.nClasesD = (int) (double) a.get(NUMBER_OF_CLASSES_D);
        this.nClasesAbstractasD = (int) (double) a.get(N_ABSTRACT_CLASSES_D);
        this.nInterfacesD = (int) (double) a.get(N_INTERFACES_D);
        this.nPaquetesD = (int) (double) a.get(N_PACKAGE_D);

        this.promedioMetodosClase = a.get(AVERAGE_METHODS_CLASS);
        this.promedioMetodosPClase = a.get(AVERAGE_PUBLIC_METHODS_CLASS);
        this.promedioAtributosClase = a.get(AVERAGE_ATTRIBUTES_CLASS);
        this.promedioAtributosPClase = a.get(AVERAGE_PUBLIC_ATTRIBUTE_CLASS);

        this.methodHidingFactor = a.get(METHOD_HIDING_FACTOR);
        this.attributeHidingFactor = a.get(ATTRIBUTE_HIDING_FACTOR);
        this.methodInheritanceFactor = a.get(METHOD_INHERITANCE_FACTOR);
        this.attributeInheritanceFactor = a.get(ATTRIBUTE_INHERITANCE_FACTOR);
        this.polymorphismFactor = a.get(POLYMORPHISM_FACTOR);
        this.couplingFactor = a.get(COUPLING_FACTOR);
        this.clusteringFactor = a.get(CLUSTERING_FACTOR);
        this.reuseFactor = a.get(REUSE_FACTOR);
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
            case GM_ONLY:
                return Arrays.asList(
                        "" + nClasesD,
                        "" + nClasesAbstractasD,
                        "" + nInterfacesD,
                        "" + nPaquetesD,
                        "" + promedioMetodosClase,
                        "" + promedioMetodosPClase,
                        "" + promedioAtributosClase,
                        "" + promedioAtributosPClase);
            case MOOD_ONLY:
                return Arrays.asList(
                        "" + methodHidingFactor,
                        "" + attributeHidingFactor,
                        "" + methodInheritanceFactor,
                        "" + attributeInheritanceFactor,
                        "" + polymorphismFactor,
                        "" + couplingFactor,
                        "" + clusteringFactor,
                        "" + reuseFactor);
            case INTEGERS_ONLY:
                return Arrays.asList(
                        "" + nClasesD,
                        "" + nClasesAbstractasD,
                        "" + nInterfacesD,
                        "" + nPaquetesD);
            case DECIMALS_ONLY:
                return Arrays.asList(
                        "" + promedioMetodosClase,
                        "" + promedioMetodosPClase,
                        "" + promedioAtributosClase,
                        "" + promedioAtributosPClase,
                        "" + methodHidingFactor,
                        "" + attributeHidingFactor,
                        "" + methodInheritanceFactor,
                        "" + attributeInheritanceFactor,
                        "" + polymorphismFactor,
                        "" + couplingFactor,
                        "" + clusteringFactor,
                        "" + reuseFactor);
            default:
                return Arrays.asList(
                        "" + nClasesD,
                        "" + nClasesAbstractasD,
                        "" + nInterfacesD,
                        "" + nPaquetesD,
                        "" + promedioMetodosClase,
                        "" + promedioMetodosPClase,
                        "" + promedioAtributosClase,
                        "" + promedioAtributosPClase,
                        "" + methodHidingFactor,
                        "" + attributeHidingFactor,
                        "" + methodInheritanceFactor,
                        "" + attributeInheritanceFactor,
                        "" + polymorphismFactor,
                        "" + couplingFactor,
                        "" + clusteringFactor,
                        "" + reuseFactor);
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
            case IResultado.GM_ONLY:
                datos.put("Number of Classes", nClasesD);
                datos.put("Número de clases", nClasesD);
                datos.put(IResultado.NUMBER_OF_CLASSES_D, nClasesD);
                datos.put("Number of Abstracts Class", nClasesAbstractasD);
                datos.put("Número de clases astractas", nClasesAbstractasD);
                datos.put(IResultado.N_ABSTRACT_CLASSES_D, nClasesAbstractasD);
                datos.put("Number of Interfaces", nInterfacesD);
                datos.put("Número de interfaces", nInterfacesD);
                datos.put(IResultado.N_INTERFACES_D, nInterfacesD);
                datos.put("Number of Packages", nPaquetesD);
                datos.put("Número de paquetes", nPaquetesD);
                datos.put(IResultado.N_PACKAGE_D, nPaquetesD);
                datos.put("Average Methods per Class",
                        promedioMetodosClase);
                datos.put("Promedio de métodos por clases",
                        promedioMetodosClase);
                datos.put(IResultado.AVERAGE_METHODS_CLASS,
                        promedioMetodosClase);
                datos.put("Average Public Methods per Class",
                        promedioMetodosPClase);
                datos.put("Promedio de métodos públicos por clases",
                        promedioMetodosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_METHODS_CLASS,
                        promedioMetodosPClase);
                datos.put("Average Attributes per Class",
                        promedioAtributosClase);
                datos.put("Promedio de atributos por clases",
                        promedioAtributosClase);
                datos.put(IResultado.AVERAGE_ATTRIBUTES_CLASS,
                        promedioAtributosClase);
                datos.put("Average Public Attributes per Class",
                        promedioAtributosPClase);
                datos.put("Promedio de atributos públicos por clases",
                        promedioAtributosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_ATTRIBUTE_CLASS,
                        promedioAtributosPClase);
                break;
            case IResultado.MOOD_ONLY:
                datos.put("Method Hiding Factor", methodHidingFactor);
                datos.put("Factor de ocultamiento de métodos",
                        methodHidingFactor);
                datos.put(IResultado.METHOD_HIDING_FACTOR, methodHidingFactor);
                datos.put("Attribute Hiding Factor", attributeHidingFactor);
                datos.put("Factor de ocultamiento de atributos",
                        attributeHidingFactor);
                datos.put(IResultado.ATTRIBUTE_HIDING_FACTOR,
                        attributeHidingFactor);
                datos.put("Method Inheritance Factor",
                        methodInheritanceFactor);
                datos.put("Factor de herencia de métodos",
                        methodInheritanceFactor);
                datos.put(IResultado.METHOD_INHERITANCE_FACTOR,
                        methodInheritanceFactor);
                datos.put("Attribute Inheritance Factor",
                        attributeInheritanceFactor);
                datos.put("Factor de herencia de atributos",
                        attributeInheritanceFactor);
                datos.put(IResultado.ATTRIBUTE_INHERITANCE_FACTOR,
                        attributeInheritanceFactor);
                datos.put("Polymorphism Factor", polymorphismFactor);
                datos.put("Factor de polimorfismo", polymorphismFactor);
                datos.put(IResultado.POLYMORPHISM_FACTOR, polymorphismFactor);
                datos.put("Coupling Factor", couplingFactor);
                datos.put("Factor de acoplamiento", couplingFactor);
                datos.put(IResultado.COUPLING_FACTOR, couplingFactor);
                datos.put("Clustering Factor", clusteringFactor);
                datos.put("Factor de agrupamiento", clusteringFactor);
                datos.put(IResultado.CLUSTERING_FACTOR, clusteringFactor);
                datos.put("Reuse Factor", reuseFactor);
                datos.put("Factor de reutilización", reuseFactor);
                datos.put(IResultado.REUSE_FACTOR, reuseFactor);
                break;
            case IResultado.INTEGERS_ONLY:
                datos.put("Number of Classes", nClasesD);
                datos.put("Número de clases", nClasesD);
                datos.put(IResultado.NUMBER_OF_CLASSES_D, nClasesD);
                datos.put("Number of Abstracts Class", nClasesAbstractasD);
                datos.put("Número de clases astractas", nClasesAbstractasD);
                datos.put(IResultado.N_ABSTRACT_CLASSES_D, nClasesAbstractasD);
                datos.put("Number of Interfaces", nInterfacesD);
                datos.put("Número de interfaces", nInterfacesD);
                datos.put(IResultado.N_INTERFACES_D, nInterfacesD);
                datos.put("Number of Packages", nPaquetesD);
                datos.put("Número de paquetes", nPaquetesD);
                datos.put(IResultado.N_PACKAGE_D, nPaquetesD);
                break;
            case IResultado.DECIMALS_ONLY:
                datos.put("Average Methods per Class", promedioMetodosClase);
                datos.put("Promedio de métodos por clases",
                        promedioMetodosClase);
                datos.put(IResultado.AVERAGE_METHODS_CLASS,
                        promedioMetodosClase);
                datos.put("Average Public Methods per Class",
                        promedioMetodosPClase);
                datos.put("Promedio de métodos públicos por clases",
                        promedioMetodosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_METHODS_CLASS,
                        promedioMetodosPClase);
                datos.put("Average Attributes per Class",
                        promedioAtributosClase);
                datos.put("Promedio de atributos por clases",
                        promedioAtributosClase);
                datos.put(IResultado.AVERAGE_ATTRIBUTES_CLASS,
                        promedioAtributosClase);
                datos.put("Average Public Attributes per Class",
                        promedioAtributosPClase);
                datos.put("Promedio de atributos públicos por clases",
                        promedioAtributosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_ATTRIBUTE_CLASS,
                        promedioAtributosPClase);
                //
                datos.put("Method Hiding Factor", methodHidingFactor);
                datos.put("Factor de ocultamiento de métodos",
                        methodHidingFactor);
                datos.put(IResultado.METHOD_HIDING_FACTOR, methodHidingFactor);
                datos.put("Attribute Hiding Factor", attributeHidingFactor);
                datos.put("Factor de ocultamiento de atributos",
                        attributeHidingFactor);
                datos.put(IResultado.ATTRIBUTE_HIDING_FACTOR,
                        attributeHidingFactor);
                datos.put("Method Inheritance Factor", methodInheritanceFactor);
                datos.put("Factor de herencia de métodos",
                        methodInheritanceFactor);
                datos.put(IResultado.METHOD_INHERITANCE_FACTOR,
                        methodInheritanceFactor);
                datos.put("Attribute Inheritance Factor",
                        attributeInheritanceFactor);
                datos.put("Factor de herencia de atributos",
                        attributeInheritanceFactor);
                datos.put(IResultado.ATTRIBUTE_INHERITANCE_FACTOR,
                        attributeInheritanceFactor);
                datos.put("Polymorphism Factor", polymorphismFactor);
                datos.put("Factor de polimorfismo", polymorphismFactor);
                datos.put(IResultado.POLYMORPHISM_FACTOR, polymorphismFactor);
                datos.put("Coupling Factor", couplingFactor);
                datos.put("Factor de acoplamiento", couplingFactor);
                datos.put(IResultado.COUPLING_FACTOR, couplingFactor);
                datos.put("Clustering Factor", clusteringFactor);
                datos.put("Factor de agrupamiento", clusteringFactor);
                datos.put(IResultado.CLUSTERING_FACTOR, clusteringFactor);
                datos.put("Reuse Factor", reuseFactor);
                datos.put("Factor de reutilización", reuseFactor);
                datos.put(IResultado.REUSE_FACTOR, reuseFactor);
                break;
            default:
                datos.put("Number of Classes", nClasesD);
                datos.put("Número de clases", nClasesD);
                datos.put(IResultado.NUMBER_OF_CLASSES_D, nClasesD);
                datos.put("Number of Abstracts Class", nClasesAbstractasD);
                datos.put("Número de clases astractas", nClasesAbstractasD);
                datos.put(IResultado.N_ABSTRACT_CLASSES_D, nClasesAbstractasD);
                datos.put("Number of Interfaces", nInterfacesD);
                datos.put("Número de interfaces", nInterfacesD);
                datos.put(IResultado.N_INTERFACES_D, nInterfacesD);
                datos.put("Number of Packages", nPaquetesD);
                datos.put("Número de paquetes", nPaquetesD);
                datos.put(IResultado.N_PACKAGE_D, nPaquetesD);
                datos.put("Average Methods per Class", promedioMetodosClase);
                datos.put("Promedio de métodos por clases",
                        promedioMetodosClase);
                datos.put(IResultado.AVERAGE_METHODS_CLASS,
                        promedioMetodosClase);
                datos.put("Average Public Methods per Class",
                        promedioMetodosPClase);
                datos.put("Promedio de métodos públicos por clases",
                        promedioMetodosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_METHODS_CLASS,
                        promedioMetodosPClase);
                datos.put("Average Attributes per Class",
                        promedioAtributosClase);
                datos.put("Promedio de atributos por clases",
                        promedioAtributosClase);
                datos.put(IResultado.AVERAGE_ATTRIBUTES_CLASS,
                        promedioAtributosClase);
                datos.put("Average Public Attributes per Class",
                        promedioAtributosPClase);
                datos.put("Promedio de atributos públicos por clases",
                        promedioAtributosPClase);
                datos.put(IResultado.AVERAGE_PUBLIC_ATTRIBUTE_CLASS,
                        promedioAtributosPClase);
                //
                datos.put("Method Hiding Factor", methodHidingFactor);
                datos.put("Factor de ocultamiento de métodos",
                        methodHidingFactor);
                datos.put(IResultado.METHOD_HIDING_FACTOR, methodHidingFactor);
                datos.put("Attribute Hiding Factor", attributeHidingFactor);
                datos.put("Factor de ocultamiento de atributos",
                        attributeHidingFactor);
                datos.put(IResultado.ATTRIBUTE_HIDING_FACTOR,
                        attributeHidingFactor);
                datos.put("Method Inheritance Factor", methodInheritanceFactor);
                datos.put("Factor de herencia de métodos",
                        methodInheritanceFactor);
                datos.put(IResultado.METHOD_INHERITANCE_FACTOR,
                        methodInheritanceFactor);
                datos.put("Attribute Inheritance Factor",
                        attributeInheritanceFactor);
                datos.put("Factor de herencia de atributos",
                        attributeInheritanceFactor);
                datos.put(IResultado.ATTRIBUTE_INHERITANCE_FACTOR,
                        attributeInheritanceFactor);
                datos.put("Polymorphism Factor", polymorphismFactor);
                datos.put("Factor de polimorfismo", polymorphismFactor);
                datos.put(IResultado.POLYMORPHISM_FACTOR, polymorphismFactor);
                datos.put("Coupling Factor", couplingFactor);
                datos.put("Factor de acoplamiento", couplingFactor);
                datos.put(IResultado.COUPLING_FACTOR, couplingFactor);
                datos.put("Clustering Factor", clusteringFactor);
                datos.put("Factor de agrupamiento", clusteringFactor);
                datos.put(IResultado.CLUSTERING_FACTOR, clusteringFactor);
                datos.put("Reuse Factor", reuseFactor);
                datos.put("Factor de reutilización", reuseFactor);
                datos.put(IResultado.REUSE_FACTOR, reuseFactor);
                break;
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
        switch (idDato) {
            case NUMBER_OF_CLASSES_D:
                return "" + nClasesD;
            case N_ABSTRACT_CLASSES_D:
                return "" + nClasesAbstractasD;
            case N_INTERFACES_D:
                return "" + nInterfacesD;
            case N_PACKAGE_D:
                return "" + nPaquetesD;
            case AVERAGE_METHODS_CLASS:
                return "" + promedioMetodosClase;
            case AVERAGE_PUBLIC_METHODS_CLASS:
                return "" + promedioMetodosPClase;
            case AVERAGE_ATTRIBUTES_CLASS:
                return "" + promedioAtributosClase;
            case AVERAGE_PUBLIC_ATTRIBUTE_CLASS:
                return "" + promedioAtributosPClase;
            case METHOD_HIDING_FACTOR:
                return "" + methodHidingFactor;
            case ATTRIBUTE_HIDING_FACTOR:
                return "" + attributeHidingFactor;
            case METHOD_INHERITANCE_FACTOR:
                return "" + methodInheritanceFactor;
            case ATTRIBUTE_INHERITANCE_FACTOR:
                return "" + attributeInheritanceFactor;
            case POLYMORPHISM_FACTOR:
                return "" + polymorphismFactor;
            case COUPLING_FACTOR:
                return "" + couplingFactor;
            case CLUSTERING_FACTOR:
                return "" + clusteringFactor;
            case REUSE_FACTOR:
                return "" + reuseFactor;
            default:
                return "NA";
        }
    }

    /**
     * Método que transforma el objeto en un String.
     *
     * @return un String con los datos del objeto.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");

        if (Locale.getDefault().getDisplayLanguage().equals("español")) {
            return "Mediciones generales.\n\n"
                    + "Número de clases en el diagrama: " + nClasesD + ".\n"
                    + "Número de clases abstractas en el diagrama: "
                    + nClasesAbstractasD + ".\n"
                    + "Número de interfaces en el diagrama: "
                    + nInterfacesD + ".\n"
                    + "Número de paquetes en el diagrama: " + nPaquetesD + ".\n"
                    + "Promedio de metodos por clases: "
                    + df.format(promedioMetodosClase) + ".\n"
                    + "Promedio de metodos públicos por clases: "
                    + df.format(promedioMetodosPClase) + ".\n"
                    + "Promedio de atributos por clases: "
                    + df.format(promedioAtributosClase) + ".\n"
                    + "Promedio de atributos públicos por clases: "
                    + df.format(promedioAtributosPClase) + ".\n"
                    + "\nMétricas MOOD.\n\n"
                    + "Factor de ocultamiento de métodos: "
                    + df.format(methodHidingFactor) + ".\n"
                    + "Factor de ocultamiento de atributos: "
                    + df.format(attributeHidingFactor) + ".\n"
                    + "Factor de herencia de métodos: "
                    + df.format(methodInheritanceFactor) + ".\n"
                    + "Factor de herencia de atributos: "
                    + df.format(attributeInheritanceFactor) + ".\n"
                    + "Factor de polimorfismo: "
                    + df.format(polymorphismFactor) + ".\n"
                    + "Factor de acoplamiento: "
                    + df.format(couplingFactor) + ".\n"
                    + "Factor de agrupamiento: "
                    + df.format(clusteringFactor) + ".\n"
                    + "Factor de reutilización: "
                    + df.format(reuseFactor) + ".\n";
        } else {
            return "General Measurements.\n\n"
                    + "Number of classes in the diagram: " + nClasesD + ".\n"
                    + "Number of abstract classes in the diagram: "
                    + nClasesAbstractasD + ".\n"
                    + "Number of interfaces in the diagraman: "
                    + nInterfacesD + ".\n"
                    + "Number of packages in the diagraman: "
                    + nPaquetesD + ".\n"
                    + "Average Methods per Class: "
                    + df.format(promedioMetodosClase) + ".\n"
                    + "Average Public Methods per Class: "
                    + df.format(promedioMetodosPClase) + ".\n"
                    + "Average Attribute per Class: "
                    + df.format(promedioAtributosClase) + ".\n"
                    + "Average Public Attribute per Class: "
                    + df.format(promedioAtributosPClase) + ".\n"
                    + "\nMOOD Metrics.\n\n"
                    + "Method hiding factor: "
                    + df.format(methodHidingFactor) + ".\n"
                    + "Attribute hiding factor: "
                    + df.format(attributeHidingFactor) + ".\n"
                    + "Method inheritance factor: "
                    + df.format(methodInheritanceFactor) + ".\n"
                    + "Attribute inheritance factor: "
                    + df.format(attributeInheritanceFactor) + ".\n"
                    + "Polymorphism factor: "
                    + df.format(polymorphismFactor) + ".\n"
                    + "Coupling factor: " + df.format(couplingFactor) + ".\n"
                    + "Clustering factor: "
                    + df.format(clusteringFactor) + ".\n"
                    + "Reuse factor: " + df.format(reuseFactor) + ".\n";
        }
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
    public int getnClasesD() {
        return nClasesD;
    }

    public void setnClasesD(int nClasesD) {
        this.nClasesD = nClasesD;
    }

    public int getnClasesAbstractasD() {
        return nClasesAbstractasD;
    }

    public void setnClasesAbstractasD(int nClasesAbstractasD) {
        this.nClasesAbstractasD = nClasesAbstractasD;
    }

    public int getnInterfacesD() {
        return nInterfacesD;
    }

    public void setnInterfacesD(int nInterfacesD) {
        this.nInterfacesD = nInterfacesD;
    }

    public int getnPaquetesD() {
        return nPaquetesD;
    }

    public void setnPaquetesD(int nPaquetesD) {
        this.nPaquetesD = nPaquetesD;
    }

    public double getPromedioMetodosClase() {
        return promedioMetodosClase;
    }

    public void setPromedioMetodosClase(double promedioMetodosClase) {
        this.promedioMetodosClase = promedioMetodosClase;
    }

    public double getPromedioMetodosPClase() {
        return promedioMetodosPClase;
    }

    public void setPromedioMetodosPClase(double promedioMetodosPClase) {
        this.promedioMetodosPClase = promedioMetodosPClase;
    }

    public double getPromedioAtributosClase() {
        return promedioAtributosClase;
    }

    public void setPromedioAtributosClase(double promedioAtributosClase) {
        this.promedioAtributosClase = promedioAtributosClase;
    }

    public double getPromedioAtributosPClase() {
        return promedioAtributosPClase;
    }

    public void setPromedioAtributosPClase(double promedioAtributosPClase) {
        this.promedioAtributosPClase = promedioAtributosPClase;
    }

    public double getMethodHidingFactor() {
        return methodHidingFactor;
    }

    public void setMethodHidingFactor(double methodHidingFactor) {
        this.methodHidingFactor = methodHidingFactor;
    }

    public double getAttributeHidingFactor() {
        return attributeHidingFactor;
    }

    public void setAttributeHidingFactor(double attributeHidingFactor) {
        this.attributeHidingFactor = attributeHidingFactor;
    }

    public double getMethodInheritanceFactor() {
        return methodInheritanceFactor;
    }

    public void setMethodInheritanceFactor(double methodInheritanceFactor) {
        this.methodInheritanceFactor = methodInheritanceFactor;
    }

    public double getAttributeInheritanceFactor() {
        return attributeInheritanceFactor;
    }

    public void setAttributeInheritanceFactor(double attributeInheritanceFactor) {
        this.attributeInheritanceFactor = attributeInheritanceFactor;
    }

    public double getPolymorphismFactor() {
        return polymorphismFactor;
    }

    public void setPolymorphismFactor(double polymorphismFactor) {
        this.polymorphismFactor = polymorphismFactor;
    }

    public double getCouplingFactor() {
        return couplingFactor;
    }

    public void setCouplingFactor(double couplingFactor) {
        this.couplingFactor = couplingFactor;
    }

    public double getClusteringFactor() {
        return clusteringFactor;
    }

    public void setClusteringFactor(double clusteringFactor) {
        this.clusteringFactor = clusteringFactor;
    }

    public double getReuseFactor() {
        return reuseFactor;
    }

    public void setReuseFactor(double reuseFactor) {
        this.reuseFactor = reuseFactor;
    }

}
