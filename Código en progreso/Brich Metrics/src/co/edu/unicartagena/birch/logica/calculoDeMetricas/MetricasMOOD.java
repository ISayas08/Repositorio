package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import co.edu.unicartagena.birch.logica.recopiladorDeDatos.ControlDeRecopilaciones;
import co.edu.unicartagena.birch.logica.recopiladorDeDatos.IRD;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que realiza el cálculo de las métricas MOOD de Fernando Brito y Rogerio
 * Carpuca. Las métricas MOOD, al igual que las métricas generales, se centran
 * en medir cracteristicas del diagrama oomo un todo. Las métricas que conforman
 * ésta familia son:
 * <ul>
 * <li>Method Hiding Factor.</li>
 * <li>Attribute Hiding Factor.</li>
 * <li>Method Inheritance Factor.</li>
 * <li>Attribute Inheritance Factor.</li>
 * <li>Polymorphism Factor</li>
 * <li>Coupling Factor.</li>
 * <li>Clustering Factor.</li>
 * </ul>
 *
 * @author Ismael Sayas Arrieta
 * @version 1.3
 * @since 16/09/2016
 */
public class MetricasMOOD implements IMGenerales {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public MetricasMOOD() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite calcular una métrica general.
     *
     * @param path un String con la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea calcular.
     * @return un String con el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @Override
    public String calcularMetrica(String path, String id) throws
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
     * Método que permite calcular una métrica general.
     *
     * @param file una objeto File que contiene la ruta del archivo.
     * @param id un entero que representa la métrica que se desea calcular.
     * @return un String con el resultado de la métrica.
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.IllegalAccessException
     */
    @Override
    public String calcularMetrica(File file, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.calcularMetrica(file.getAbsolutePath(), id);
    }

    /**
     * Métodoq ue realiza el cálculo de la métrica MOOD, Method Hiding Factor.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor calculado de la métrica.
     */
    private String methodHidingFactor_MOOD_0(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nMPrivadosD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_METODOS_PRIVADOS, IRD.FAMILIA_MOOD));
        int nMetodosD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_METODOS, IRD.FAMILIA_MG));

        return nMetodosD > 0 //Se vaida división por cero.
                ? Double.toString((double) nMPrivadosD / (double) nMetodosD)
                : "0";
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD, Attribute Hiding Factor
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor calculado de la métrica.
     */
    private String attributeHidingFactor_MOOD_1(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nAPrivadosD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_ATRIBUTOS_PRIVADOS, IRD.FAMILIA_MOOD));
        int nAtributosD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_ATRIBUTOS, IRD.FAMILIA_MG));

        return nAtributosD > 0 //Se vaida división por cero.
                ? Double.toString((double) nAPrivadosD / (double) nAtributosD)
                : "0";
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD, Method Inheritance
     * Factor.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor resultante del cálculo de la
     * métrica.
     */
    private String methodInheritanceFactor_MOOD_2(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        //Se solicitan todas las ides de las clases del diagrama y se almacenan
        //en un arreglo. dado que los datos vienen unidos en un mismo string,
        //hay que separarlos (el elemento que une un string y otro es el ";").
        int sumatoriaMH = 0;
        sumatoriaMH = Arrays.asList(cdr.recopilarDatos(
                path, IRD.IDES_CLASES, IRD.FAMILIA_MG).split(";"))
                .stream() //Se recorre el arreglo con el método stream()
                //Se transforman los datos usando el método map() al resuldado
                //obtenido de calcular los métodos heredados para cada id,
                //y convertir el resultado a entero.
                .map(id -> Integer.parseInt(cdr.recopilarDatos(
                id, path, IRD.METODOS_HEREDADOS, IRD.FAMILIA_LYK)))
                //Se suman todos los datos usando el método reduce().
                //.reduce(0, (sum, nmh) -> sum += nmh);
                .reduce(sumatoriaMH, Integer::sum);

        //Se obtienen el total de métodos en el diagrama.
        int nMetodosD = Integer.parseInt(cdr.recopilarDatos(
                path, IRD.TOTAL_METODOS, IRD.FAMILIA_MG));

        int sumNM_MH = nMetodosD + sumatoriaMH;

        //Se divide el número de métodos heredado sobre el número de métodos totales.
        return sumNM_MH > 0 //Se vaida división por cero.
                ? Double.toString((double) sumatoriaMH / (double) sumNM_MH)
                : "0";
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD, Attribute Inheritance
     * Factor.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el resultado de la métrica calculada.
     */
    private String attributeInheritanceFactor_MOOD_3(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        //Se solicitan todas las ides de las clases del diagrama y se almacenan
        //en un arreglo. dado que los datos vienen unidos en un mismo string,
        //hay que separarlos (el elemento que une un string y otro es el ";").
        int sumatoriaAH = 0;
        sumatoriaAH = Arrays.asList(cdr.recopilarDatos(
                path, IRD.IDES_CLASES, IRD.FAMILIA_MG).split(";"))
                .stream() //Se recorre el arreglo con el método stream()
                //Se transforman los datos, usando el método map(), al resuldado
                //obtenido de calcular los atributos heredados para cada id,
                //y convertir el resultado a entero.
                .map(id -> Integer.parseInt(cdr.recopilarDatos(
                id, path, IRD.ATRIBUTOS_HEREDADOS, IRD.FAMILIA_LYK)))
                //Se suman todos los datos usando el método reduce().
                .reduce(sumatoriaAH, Integer::sum);

        //Se obtiene el total de atributos en el diagrama.
        int nAtributosD = Integer.parseInt(cdr.recopilarDatos(
                path, IRD.TOTAL_ATRIBUTOS, IRD.FAMILIA_MG));

        int sumNA_AH = nAtributosD + sumatoriaAH;

        //Se divide el número de atributos heredados 
        //sobre el número de atributos totales.
        return sumNA_AH > 0 //Se vaida división por cero.
                ? Double.toString((double) sumatoriaAH / (double) sumNA_AH)
                : "0";
    }

    /**
     * Método que calcula la métrica Factor de polimorfismo.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor resultante del cálculo de la
     * métrica.
     */
    private String polymorphismFactor_MOOD_4(String path) {
        IRD cdr = new ControlDeRecopilaciones();
        List<Artefacto> artefactos = new ArrayList<>();

        //1. Se solicitan todas las ides de las clases del diagrama.
        Arrays.asList(cdr.recopilarDatos(
                path, IRD.IDES_CLASES, IRD.FAMILIA_MG).split(";"))
                .stream()
                //Con cada id se crea un artefacto.
                .forEach(id -> {
                    Artefacto a = new Artefacto(id);
                    //Se solicitan la id de la clase padre.
                    String idCP = cdr.recopilarDatos(
                            id, path, IRD.DATOS_HERENCIA_CLASE, IRD.FAMILIA_LYK);
                    //Se cambia la id de la clase padre.
                    a.setIdPadre(idCP);

                    //Se obtienen todos los métodos de la clase y se añaden.
                    Arrays.asList(cdr.recopilarDatos(
                            id, path, IRD.DATOS_METODOS_CLASE, IRD.FAMILIA_LYK)
                            .split(";"))
                            .stream()
                            .forEach(ms -> {
                                //Se asignan los datos del método al artefacto
                                a.addNuewMethod(ms);
                            });
                    //se agrega el artefacto a la lista.
                    artefactos.add(a);
                });

        //2. Se realiza la sumatoria de métodos sobrescritos.
        //3. Se realiza la sumatoria de los métodos añadidos 
        //por el número de descendientes.
        int sumatoriaMO = 0;
        int sumatoriaMAxDC = 0;
        ICM ccm = new ControlDeCalculoDeMetricas();

        for (Artefacto artefacto : artefactos) {
            int mO = (int) ccm.calcularMetricasArtefacto(
                    artefacto.getId(),
                    path,
                    ICM.NUMBER_OF_METHOD_OVERRIDDEN,
                    ICM.FAMILIA_LYK);
            int mAñadidos = (int) ccm.calcularMetricasArtefacto(
                    artefacto.getId(),
                    path,
                    ICM.NUMBER_OF_NEW_METHOD,
                    ICM.FAMILIA_LYK);
            int nDescendientes = this.hijosTotales(artefacto, artefactos);

            sumatoriaMO += mO;
            sumatoriaMAxDC += (mAñadidos * nDescendientes);
        }
        return sumatoriaMAxDC > 0 //Se vaida división por cero.
                ? Double.toString((double) sumatoriaMO / (double) sumatoriaMAxDC)
                : "0";
    }

    /**
     * Método que cuenta el número total de hijos que tiene una clase.
     *
     * @param arfAEvaluar un objeto Artefacto con los datos de la clase a
     * evaluar.
     * @param artefactos una lista de objetos Artefacto que contiene los datos
     * de todas las clases en el diagra,a.
     * @return
     */
    private int hijosTotales(Artefacto arfAEvaluar, List<Artefacto> artefactos) {
        //Buscamos las claes que son inmediatamente hijas del atefacto a evaluar.
        List<Artefacto> hijosInmediatos = artefactos.stream()
                .filter(a -> a.getIdPadre().equals(arfAEvaluar.getId()))
                .collect(Collectors.toList());

        //Contamos los hijos inmediatos de la clase a evaluar.
        int con = hijosInmediatos.size();

        //Contamos los hijos inmediatos de cada hijo inmediato de la clase a evaluar.
        con = hijosInmediatos.stream()
                .map((hijoInmediato) -> hijosTotales(hijoInmediato, artefactos))
                .reduce(con, Integer::sum);

        return con;
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD: Coupling Factor.
     *
     * @param path un String que contiene la ruta del archivo.
     * @return un String que contiene el valor resultadod el cálculo de la
     * métrica.
     */
    private String couplingFactor_MOOD_5(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nAsociacionesNHD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_RELACIONES_NH, IRD.FAMILIA_MOOD));
        int nClasesD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_CLASES, IRD.FAMILIA_MG));

        return nClasesD > 0 //Se vaida división por cero.
                ? Double.toString((double) nAsociacionesNHD
                        / (Math.pow((double) nClasesD, 2)
                        - (double) nClasesD))
                : "0";
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD, Clustering Factor.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor resultante de calcular la
     * métrica.
     */
    private String clusteringFactor_MOOD_6(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nClusterD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_CLUSTERS, IRD.FAMILIA_MOOD));
        int nClasesD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_CLASES, IRD.FAMILIA_MG));

        return nClasesD > 0 //Se vaida división por cero.
                ? Double.toString((double) nClusterD / (double) nClasesD)
                : "0";
    }

    /**
     * Método que realiza el cálculo de la métrica MOOD, Reuse Factor.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el resultado de la métrica calculada.
     */
    private String reuseFactor_MOOD_7(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int nCHijosD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_CLASES_HIJAS, IRD.FAMILIA_MOOD));
        int nClasesD = Integer.parseInt(cdr.
                recopilarDatos(path, IRD.TOTAL_CLASES, IRD.FAMILIA_MG));

        return nClasesD > 0 //Se vaida división por cero.
                ? Double.toString((double) nCHijosD / (double) nClasesD)
                : "0";
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
