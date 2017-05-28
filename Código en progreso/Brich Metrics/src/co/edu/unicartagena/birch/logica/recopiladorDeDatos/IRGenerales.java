package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Interfaz que provee los métodos a los recopiladores generales.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 14/09/2016
 */
public interface IRGenerales {

    /**
     * Método que permite recopilar un dato especifico de archivo del archivo.
     *
     * @param path la dirección del archivo.
     * @param id un entero que permite identificar el dato a recoilar.
     * @return un String que contiene el daato solicitado.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String recopilar(String path, String id) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException;

    /**
     * Método que permite recopilar un dato especifico de archivo del archivo.
     *
     * @param file un objeto de tipo File que contiene la dirección del archivo.
     * @param id un entero que permite identificar el dato a recopilar.
     * @return un String que contiene el dato solicitado.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String recopilar(File file, String id) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException;
;

}
