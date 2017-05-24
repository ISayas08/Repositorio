package co.edu.unicartagena.birch.persistencia;

/**
 * Interfaz que declara los servicios que ofrece el componente de persistencia.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.1
 * @since 26/01/2016
 */
public interface IFP {

    /**
     * Método que retorna las factoría de persistencias query.
     *
     * @return una instancias de FPQuery.
     */
    public FPQuery persistenciasQuery();

}
