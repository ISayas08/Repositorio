package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

/**
 * Clase que permite elegir un tipo determinado de Versión.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.3
 * @since 20/07/2016
 */
public final class FactoriaDeVersiones {

    /**
     * Representa el valor correspondiente a la versión 2.4.1 de UML.
     */
    public final String XMI_2_4_2 = "2.4.2";

    /**
     * Representa el valor correspondiente a la versión 2.1 de UML.
     */
    public final String XMI_2_1 = "2.1";

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public FactoriaDeVersiones() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite obtener una versión especifica que contiene las
     * sentencias apropiadas para trabajar con cada versión admitida por el
     * componente.
     *
     * @param version un string que define el tipo de versión que será
     * retornada.
     * @return
     */
    public Version getIntances(String version) {
        Version instance = null;

        switch (version) {
            case XMI_2_4_2:
                instance = new Version2_4_2();
                break;
            case XMI_2_1:
                instance = new Version2_1();
            default:
                //Versión no disponible. retorna null.
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
