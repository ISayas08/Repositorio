package co.edu.unicartagena.birch.persistencia;

import com.saxonica.xqj.SaxonXQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

/**
 * Clase donde se implementan los métodos necesarios para trabajar archivos XMI
 * utilizando XQuery.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.1
 * @since 17/07/2016
 */
public final class XQuery implements IQuery<XQResultSequence> {

    /**
     * Una instancia de la clase para el patrón SIngletón.
     */
    private static XQuery instance;

    /**
     * Una instancia de la conexción con XQuery.
     */
    private XQConnection conexión;

//==============================================================================
//  Constructores y métodos de inicialización
//==============================================================================
    /**
     * Constructor general. También establece la conexión con XQuery.
     *
     * @throws XQException
     */
    private XQuery() throws XQException {
        this.establecerConexion();
    }

    /**
     * Metodo que retorna una única instancia de la clase.
     *
     * @return
     * @throws XQException
     */
    public static XQuery getInstance() throws XQException {
        if (instance == null) {
            instance = new XQuery();
        }

        return instance;
    }
//==============================================================================
//  Métodos principales.
//==============================================================================

    /**
     * Método que establece la conexión.
     *
     * @throws XQException
     */
    private void establecerConexion() throws XQException {
        XQDataSource ds = new SaxonXQDataSource();
        conexión = ds.getConnection();
    }

    /**
     * Método que finaliza la conexión.
     *
     * @throws XQException
     * @throws Throwable
     */
    @Override
    @SuppressWarnings("FinalizeCalledExplicitly")
    public void terminarConexion() throws XQException, Throwable {
        conexión.close();
        instance = null;
        this.finalize();
    }

    /**
     * Método que ejecuta una sntencia XQuery.
     *
     * @param sentencia
     * @return
     * @throws XQException
     */
    @Override
    public XQResultSequence ejecutarQuery(String sentencia) throws XQException {
        XQPreparedExpression exp = conexión.prepareExpression(sentencia);

        return exp.executeQuery();
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
}
