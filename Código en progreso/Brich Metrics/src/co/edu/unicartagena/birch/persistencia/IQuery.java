package co.edu.unicartagena.birch.persistencia;

import javax.xml.xquery.XQException;

/**
 * Interface que provee los métodos para trabajar persistencias basadas en
 * peticiones Query.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.0
 * @param <T>
 * @since 26/01/2016
 */
public interface IQuery<T> {

    /**
     * Método encargado de finalizar la conexión.
     *
     * @throws XQException
     * @throws Throwable
     */
    public void terminarConexion() throws XQException, Throwable;

    /**
     * Método que ejecuta una sentencia y retorna el resultado.
     *
     * @param sentencia la sentencia que va a ser ejecutada.
     * @return T
     * @throws XQException
     */
    public T ejecutarQuery(String sentencia) throws XQException;
}
