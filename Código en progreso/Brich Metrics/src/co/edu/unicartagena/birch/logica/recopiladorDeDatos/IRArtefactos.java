package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Interfaz que provee los métodos a los recopiladores de artefactos.
 *
 * @author Ismael Sayas Arrieta
 */
public interface IRArtefactos {

    /**
     * Método encargado de recopilar una dato especifico neceario para calcular
     * una métrica.
     *
     * @param nombre el nombre del artefacto.
     * @param path la dirección del archivo.
     * @param id un entero utilizado para indicar el dato que se desea
     * recopilar.
     * @return un String que contiene los datos requeridos.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String recopilar(String nombre, String path, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;

    /**
     * Método encargado de recopilar una dato especifico neceario para calcular
     * una métrica.
     *
     * @param nombre el nombre del artefacto.
     * @param file una instancia de tipo FILE que contiene la dirección del
     * archivo.
     * @param id un entero utilizado para indicar el dto que se desea recopilar.
     * @return un String que contiene los datos requeridos.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String recopilar(String nombre, File file, String id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;

}
