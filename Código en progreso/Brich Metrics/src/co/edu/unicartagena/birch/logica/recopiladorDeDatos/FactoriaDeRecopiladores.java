package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

/**
 * Clase encargada de crear los objetos que recopilan la información.
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.5
 * @since 16/09/2016
 */
public final class FactoriaDeRecopiladores {

    /**
     * Atributo utilizado para especificar el valor que debe recibir el método
     * getIRArtefactos para retornar un recopilador de datos para la familia de
     * métricas de CyK.
     */
    public static final int RECOPILADOR_CYK = 0;

    /**
     * Atributo utilizado para especificar el valor que debe recibir el método
     * getIRArtefactos para retornar un recopilador de datos para la familia de
     * métricas de LyK.
     */
    public static final int RECOPILADOR_LYK = 1;

    /**
     * Atributo utilizado para especificar el valor que debe recibir el método
     * getIRGenerales para retornar un recopilador de datos para las mediciones
     * generales.
     */
    public static final int RECOPILADOR_MG = 0;

    /**
     * Atributo utilizado para especificar el valor que debe recibir el método
     * getIRGenerales para retornar un recopilador de datos para la familia de
     * métricas MOOD.
     */
    public static final int RECOPILADOR_MOOD = 1;

//==============================================================================
//  Constructores y métodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public FactoriaDeRecopiladores() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método encargado de crear objetos que recopilan la información de los
     * artefactos.
     *
     * @param tipo un entero que especifica el tipo de recopilador que se desea
     * construir.
     * @return una instancia de la interfaz IRArtefactos.
     */
    public IRArtefactos getIRArtefactos(int tipo) {
        IRArtefactos instance = null;

        switch (tipo) {
            case FactoriaDeRecopiladores.RECOPILADOR_CYK:
                instance = new RecopiladorDeDatosCyK();
                break;
            case FactoriaDeRecopiladores.RECOPILADOR_LYK:
                instance = new RecopiladorDeDatosLyK();
                break;
        }

        return instance;
    }

    /**
     * Método encargado de crear objetos que recopilan la información general
     * del diagrama.
     *
     * @param tipo un entero que especifica el tipo de recopilador que se desea
     * construir.
     * @return una instancia de la interfaz IRGenerales.
     */
    public IRGenerales getIRGenerales(int tipo) {
        IRGenerales instance = null;

        switch (tipo) {
            case FactoriaDeRecopiladores.RECOPILADOR_MG:
                instance = new RecopiladorDeDatosMG();
                break;
            case FactoriaDeRecopiladores.RECOPILADOR_MOOD:
                instance = new RecopiladorDeDatosMOOD();
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
