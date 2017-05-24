package co.edu.unicartagena.birch.persistencia;

import javax.xml.xquery.XQException;

/**
 * Clase que proporciona las instancias de persistencias query.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.1
 * @since 17/07/2016
 */
public final class FPQuery {

    /**
     * Una constante empleada para especificar la instancia que retornará el
     * método getInstances(), en este caso retornará una instancia de XQuery.
     */
    public final static int XQUERY = 0;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public FPQuery() {

    }

//==============================================================================
//  Otros métodos.
//==============================================================================
    /**
     * Método que retorna un objeto de tipo IArchivo, la instancia dependerá del
     * valor del entero que se pase por parámetro.
     *
     * @return XQuery
     * @param tipo, un entero que determina la instancia del objeto de retorno.
     * @throws javax.xml.xquery.XQException
     */
    @SuppressWarnings("null")
    public IQuery getPersistencias(int tipo) throws XQException {
        IQuery instance = null;

        switch (tipo) {
            case FPQuery.XQUERY:
                instance = XQuery.getInstance();
                break;
        }

        return instance;
    }
//==============================================================================
//  Metodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
