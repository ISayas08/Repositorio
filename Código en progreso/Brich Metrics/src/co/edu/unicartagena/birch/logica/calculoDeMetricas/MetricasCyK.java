package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import co.edu.unicartagena.birch.logica.recopiladorDeDatos.ControlDeRecopilaciones;
import co.edu.unicartagena.birch.logica.recopiladorDeDatos.IRD;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase encargada de calcular las métricas de Chidamber and Kemerer (Cyk). Para
 * éste componente sólo se tomaron en consideración cuatro de las métricas
 * pertenecientes a la familia de métricas de CyK, las cuales son:
 * <ul>
 * <li>Depth of Inheritance Tree.</li>
 * <li>Number of Chindren.</li>
 * <li>Coupling Between Object Classes.</li>
 * <li>Weighted Methods per Class..</li>
 * </ul>
 *
 * @author Ismael Sayas Arrieta
 * @version 1.3
 * @since 15/09/2016
 */
public final class MetricasCyK implements IMArtefactos {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public MetricasCyK() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite calcular una métrica de artefacto. Èste mètodo utiliza
     * reflexiòn para ejecutar un mètodo especifico de la clase el cuàl es
     * identificado gracias a una id. La id de cada mètodo se aprecia al final
     * del nombre del mismo.
     *
     * Por ejemplo para un mètodo "metodo_M0" la id correspondiente serà igual a
     * "0".
     *
     * Las id estàn especificadas en la interface IResultado.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    public String calcularMetrica(String artifactId, String path, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_" + id))
                .collect(Collectors.toList()).get(0).invoke(this, artifactId, path);

        return dato == null ? "NA" : dato;
    }

    /**
     * Método que permite calcular una métrica de artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    public String calcularMetrica(String artifactId, File file, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.calcularMetrica(artifactId, file.getAbsolutePath(), id);
    }

    /**
     * Método que realiza el cálculo de la métrica CyK, Profundidad del árbol de
     * herencias de una clase o interface especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evauar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String con el valor de la métrica que corresponde al artefacto
     * evaluado.
     */
    private String depthOfInheritanceTree_CyK_0(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path,
                IRD.PROFUNDIDAD_DEL_ARBOL_DE_HERENCIAS, IRD.FAMILIA_CYK);
    }

    /**
     * Método que realiza el cálculo de la métrica CyK, Hijos inmediatos de una
     * clase o interface especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la métrica que corresponde al
     * artefacto evaluado.
     */
    private String numbreOfChildren_CyK_1(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path, IRD.HIJOS_INMEDIATOS, IRD.FAMILIA_CYK);
    }

    /**
     * Método que realiza el cálculo de la métrica CyK, Acoplamiento entre
     * clases para una clase o interface especifica.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la métrica corresondiente al
     * artefacto evaluado.
     */
    private String couplingBetweenObjectClasses_CyK_2(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path, IRD.ACOPLAMIENTO, IRD.FAMILIA_CYK);
    }

    /**
     * Método que realiza el cálculo de la métrica CyK, Métodos ponderados por
     * clase para una clase o interface especifica. Ésta métrica calcula la
     * complejidad de una clase a través de la complejidad de sus métodos. Dado
     * que a través de un diagrama de clases (representado a través de un
     * archivo XMI) no es posible medir la complejidad de un método, ésta se
     * toma como la unidad, por lo que en última instancia el valor de la
     * métrica es el número total de métodos de la clase.
     *
     * @param artifactId un String que contiene la id del artefacto a evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor de la métrica correspondiente al
     * artefacto evaluado.
     */
    private String weightedMethodsPerClass_CyK_3(String artifactId, String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(artifactId, path, IRD.METODOS_TOTALTES, IRD.FAMILIA_LYK);
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
