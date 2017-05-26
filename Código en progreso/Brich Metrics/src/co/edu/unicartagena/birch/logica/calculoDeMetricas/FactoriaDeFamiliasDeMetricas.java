package co.edu.unicartagena.birch.logica.calculoDeMetricas;

/**
 * Clase que permite crear objetos con los métodos necesarios para el cálculo de
 * familias de métricas..
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 16/09/2016
 */
public final class FactoriaDeFamiliasDeMetricas {

    /**
     * Variable que se utiliza para especificar al método getInstances el tipo
     * de calculador que se desea obtener. En este caso es para obtener una
     * instancia que la clase encargada de realizar el cálculo de las mediciones
     * generales.
     */
    public static final int METRICAS_MG = 0;

    /**
     * Variable que se utiliza para especificar al método getInstances el tipo
     * de calculador que se desea obtener. En este caso es para obtener una
     * instancia que la clase encargada de realizar el cálculo de la familia de
     * métricas MOOD.
     */
    public static final int METRICAS_MOOD = 1;

    /**
     * Variable que se utiliza para especificar al método getInstances el tipo
     * de calculador que se desea obtener. En este caso es para obtener una
     * instancia que la clase encargada de realizar el cálculo de la familia de
     * métricas CyK.
     */
    public static final int METRICAS_CYK = 2;

    /**
     * Variable que se utiliza para especificar al método getInstances el tipo
     * de calculador que se desea obtener. En este caso es para obtener una
     * instancia que la clase encargada de realizar el cálculo de la familia de
     * métricas LyK.
     */
    public static final int METRICAS_LYK = 3;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public FactoriaDeFamiliasDeMetricas() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite construir objetos con los métodos necesarios para
     * calcular una familia de métricas de artefactos.
     *
     * @param tipo un entero que especifica el tipo de calculador que se desea
     * construir.
     * @return una instancia de tipo IMArtefactos.
     */
    public IMArtefactos getIMArtefactos(int tipo) {
        IMArtefactos instance = null;

        switch (tipo) {
            case FactoriaDeFamiliasDeMetricas.METRICAS_CYK:
                instance = new MetricasCyK();
                break;

            case FactoriaDeFamiliasDeMetricas.METRICAS_LYK:
                instance = new MetricasLyK();
                break;
        }

        return instance;
    }

    /**
     * Método que permite construir objetos con los métodos necesarios para
     * calcular una familia de métricas de sistema.
     *
     * @param tipo un entero que especifica el tipo de calculador que se desea
     * construir.
     * @return una instancia de tipo IMGenerales.
     */
    public IMGenerales getIMGenerales(int tipo) {
        IMGenerales instance = null;

        switch (tipo) {
            case FactoriaDeFamiliasDeMetricas.METRICAS_MG:
                instance = new MetricasMG();
                break;

            case FactoriaDeFamiliasDeMetricas.METRICAS_MOOD:
                instance = new MetricasMOOD();
                break;
        }

        return instance;
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

}
