package co.edu.unicartagena.birch.persistencia;

/**
 * Interfaz que declara los servicios que ofrece el componente de persistencia.
 *
 * @author Ismael Sayas Arrieta
 */
public interface IFP {

    /**
     * Método que retorna las factoría de persistencias query.
     *
     * @return una instancias de FPQuery.
     */
    public FPQuery persistenciasQuery();

}
