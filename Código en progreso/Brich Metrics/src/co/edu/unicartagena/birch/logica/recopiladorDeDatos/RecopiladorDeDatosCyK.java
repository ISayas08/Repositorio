package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.IVC;
import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.Validador;
import co.edu.unicartagena.birch.persistencia.FPQuery;
import co.edu.unicartagena.birch.persistencia.IFP;
import co.edu.unicartagena.birch.persistencia.IQuery;
import co.edu.unicartagena.birch.persistencia.SelectorDePersistencias;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

/**
 * Clase encargada de obtener todos los datos necesarios para hacer el cálculo
 * de las métricas de CyK (Chidamber & Kemerer).
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.7
 * @since 14/09/2016
 */
public final class RecopiladorDeDatosCyK implements IRArtefactos {

//==============================================================================
//  Constructores y métodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public RecopiladorDeDatosCyK() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método encargado de recopilar un dato especifico neceario para calcular
     * una métrica.
     *
     * @param nombre el nombre del artefacto.
     * @param path la dirección del archivo.
     * @param id un entero utilizado para indicar el dato que se desea
     * recopilar.
     * @return un String que contiene los datos requeridos.
     */
    @Override
    public String recopilar(String nombre, String path, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {

        //Solicitamos todos los métodos de la clase.
        List<Method> metodos = Arrays.asList(this.getClass()
                .getDeclaredMethods());

        //Ejecutamos el método cuyo nombre termine con "_Mi",
        //donde i es el valor de la id.
        String dato = (String) metodos.stream()
                .filter(m -> m.getName().endsWith("_M" + id))
                .collect(Collectors.toList()).get(0).invoke(this, nombre, path);

        return dato == null ? "-1" : dato;
    }

    /**
     * Método encargado de recopilar un dato especifico neceario para calcular
     * una métrica.
     *
     * @param nombre el nombre del artefacto.
     * @param file una instancia de tipo FILE que contiene la dirección del
     * archivo.
     * @param id un entero utilizado para indicar el dto que se desea recopilar.
     * @return un String que contiene los datos requeridos.
     */
    @Override
    public String recopilar(String nombre, File file, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        return this.recopilar(nombre, file.getAbsolutePath(), id);
    }

    /**
     * Método encargado de contar la profundidad del árbol de herencias de una
     * clase especifica..
     *
     * @param nombre el nombre de la clase a evaluar.
     * @param path la dirección del archivo XMI.
     * @return un entero que representa la profundidad del arbol de herencias de
     * la clase.
     */
    private String profArbolHerencias_M0(String nombre, String path) {
        try {
            return this.getResult(nombre, path,
                    UtileriaRDD.SA_CONTAR_PROFUNDIDAD_ARBOL_HERENCIAS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar la cantidad de clases que heredan de una clase
     * especifica.
     *
     * @param nombre el nombre de la clase a evaluar.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa la cantidad de hijos de la clase.
     */
    private String hijosInmediatos_M1(String nombre, String path) {
        try {
            return this.getResult(nombre, path,
                    UtileriaRDD.SA_CONTAR_HIJOS_INMEDIATOS);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método encargado de contar la cantidad de relaciones eferentes y
     * aferentes de una clase especifica.
     *
     * @param nombre el nombre de la clase a evaluar.
     * @param path la dirección del archivo XMI.
     * @return Un entero que representa el acoplamiento de la clase.
     */
    private String recopilarAcoplamiento_M2(String nombre, String path) {
        try {
            return this.getResult(nombre, path,
                    UtileriaRDD.SA_CONTAR_ACOPLAMIENTO);
        } catch (XQException ex) {
            Logger.getLogger(RecopiladorDeDatosMG.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        } catch (Throwable ex) {
            Logger.getLogger(RecopiladorDeDatosCyK.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "-1";
        }
    }

    /**
     * Método que envía los datos a la persistencia para ejecutar la sentencia y
     * retorna el resultado generado.
     *
     * @param idArtifact un String con el nombre de la clase que se va a evaluar.
     * @param path un String con la dirección del archivo XMI.
     * @param id un entero que especifica la id de la sentencia a ejecurtarse.
     * @return un String que contiene el resultado de la ejecución de la
     * sentencia. Se retornará "-1" si se produce algún error.
     * @throws XQException
     * @throws Throwable
     */
    private String getResult(String idArtifact, String path, int id)
            throws XQException, Throwable {
        IVC validador = Validador.getInstance();
        IFP selecPersistencia = new SelectorDePersistencias();

        //Se obtiene la sentencia que se va a ejecutar.
        String Sentencia = validador.getVersionEnUso()
                .armarSentenciaArtefacto(idArtifact, path, id, true);
        //Se accede a la persistencia de XQuery.
        IQuery pXQuery = selecPersistencia
                .persistenciasQuery()
                .getPersistencias(FPQuery.XQUERY);
        //Se ejecuta la sentencia.
        XQResultSequence result = (XQResultSequence) pXQuery
                .ejecutarQuery(Sentencia);

        //Se crea la lista que contendrá los datos
        List<String> listDatos = new ArrayList<>();
        //Se añaden los resultados de la sentencia a la lista de datos.
        while (result.next()) {
            listDatos.add(result.getItemAsString(null));
        }
        //Se cierra la conexión con XQuery.
        pXQuery.terminarConexion();
        //Se retorna el resutado.
        //Si la lista de datos está vacía se retorna -1, en caso contrario se 
        //juntan los resultados en un mismo string, separados por punto y coma.
        return listDatos.isEmpty()
                ? "-1"
                : listDatos.stream().collect(Collectors.joining(";"));
    }
//==============================================================================
//  Metodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setter.
//==============================================================================
}
