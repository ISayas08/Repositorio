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
 * Clase que realiza los cálculos de las métricas generales. las métricas
 * generales son métricas que miden caracteristicas generales del diagrama como
 * un todo. Las métricas generales son las siguientes:
 * <ul>
 * <li>Número de clases en el diagrama.</li>
 * <li>Número de clases abstractas en el diagrama.</li>
 * <li>Número de interfaces en el diagrama.</li>
 * <li>Número de paquetes en el diagrama.</li>
 * <li>Promedio de métodos por clase.</li>
 * <li>Promedio de métodos públicos por clase.</li>
 * <li>Promedio de atributos por clase.</li>
 * <li>Promedio de atributos públicos por clase.</li>
 * </ul>
 *
 * @author Ismael Sayas Arrieta
 * @version 1.4
 * @since 16/09/2016
 */
public class MetricasMG implements IMGenerales {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public MetricasMG() {

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
     */
    @Override
    public String calcularMetrica(String path, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_M" + id))
                .collect(Collectors.toList()).get(0).invoke(this, path);

        return dato == null ? "NA" : dato;
    }

    /**
     * Método que permite calcular una métrica general.
     *
     * @param file una objeto File que contiene la ruta del archivo.
     * @param id un entero que representa la métrica que se desea calcular.
     * @return un String con el resultado de la métrica.
     */
    @Override
    public String calcularMetrica(File file, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.calcularMetrica(file.getAbsolutePath(), id);
    }

    /**
     * Método que cuenta el número total de clases en el diagrama (Incluyendo
     * clases abstractas).
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número total de clases en el diagrama.
     */
    private String totalClases_M0(String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(path, IRD.TOTAL_CLASES);
    }

    /**
     * Método que cuenta el número total de clases abstractas
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número total de clases abstractas.
     */
    private String totalCAbstractas_M1(String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(path, IRD.TOTAL_CLASES_ABSTRACTAS);
    }

    /**
     * Método que cuenta el número total de interfaces en el diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de interfaces en el diagrama.
     */
    private String totalInterfaces_M2(String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(path, IRD.TOTAL_INTERFACES);
    }

    /**
     * Método que cuenta el número total de paquetes en el diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el número de paquetes en el diagrama.
     */
    private String totalPaquetes_M3(String path) {
        IRD cdr = new ControlDeRecopilaciones();
        return cdr.recopilarDatos(path, IRD.TOTAL_PAQUETES);
    }

    /**
     * Método que realiza el cálculo del promedio de métodos por clase.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor del cálculo solicitado.
     */
    private String promedioMetodosClase_M4(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int NTM = Integer.parseInt(cdr.recopilarDatos(path, IRD.TOTAL_METODOS));
        int NC = Integer.parseInt(this.totalClases_M0(path));
        int NI = Integer.parseInt(this.totalInterfaces_M2(path));

        return Double.toString((double) NTM / ((double) NC + (double) NI));
    }

    /**
     * Método que realiza el cáluclo del promedio de métodos públicos por clase.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor del cálculo solicitado.
     */
    private String promedioMetodosPClase_M5(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int NTMP = Integer.parseInt(cdr.recopilarDatos(path,
                IRD.TOTAL_METODOS_PUBLICOS));
        int NC = Integer.parseInt(this.totalClases_M0(path));
        int NI = Integer.parseInt(this.totalInterfaces_M2(path));

        return Double.toString((double) NTMP / ((double) NC + (double) NI));
    }

    /**
     * Método que realiza el cálculo del promedio de atributos por clase.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor del cálculo solicitado.
     */
    private String promedioAtributosClase_M6(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int NTA = Integer.parseInt(cdr.recopilarDatos(path,
                IRD.TOTAL_ATRIBUTOS));
        int NC = Integer.parseInt(this.totalClases_M0(path));
        int NI = Integer.parseInt(this.totalInterfaces_M2(path));

        return Double.toString((double) NTA / ((double) NC + (double) NI));
    }

    /**
     * Método que realiza el cálculo del promedio de atributos públicos por
     * clase.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @return un String que contiene el valor del cálculo solicitado.
     */
    private String promedioAtributosPClase_M7(String path) {
        IRD cdr = new ControlDeRecopilaciones();

        int NTAP = Integer.parseInt(cdr.recopilarDatos(path,
                IRD.TOTAL_ATRIBUTOS_PUBLICOS));
        int NC = Integer.parseInt(this.totalClases_M0(path));
        int NI = Integer.parseInt(this.totalInterfaces_M2(path));

        return Double.toString((double) NTAP / ((double) NC + (double) NI));
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
