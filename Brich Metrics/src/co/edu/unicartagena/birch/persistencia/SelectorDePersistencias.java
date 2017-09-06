package co.edu.unicartagena.birch.persistencia;

/**
 * Clase que permite seleccionar el grupo de persistencias que se desea.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.1
 * @since 17/07/2016
 */
public final class SelectorDePersistencias implements IFP {

//==============================================================================
//  Constructores y métodos de inicialización
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public SelectorDePersistencias() {

    }
//==============================================================================
//  Otros métodos.
//==============================================================================

    /**
     * Método que permite obtener la factoría de persistencias query.
     *
     * @return FPQuery.
     */
    @Override
    public FPQuery persistenciasQuery() {
        return new FPQuery();
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
