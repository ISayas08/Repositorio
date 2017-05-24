package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Interfaz que provee los métodos para calcular métricas generales.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.1
 * @since 15/09/2016
 */
public interface IMGenerales {

    /**
     * Método que permite calcular una métrica general.
     *
     * @param path un String con la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea calcular.
     * @return un String con el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String calcularMetrica(String path, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;

    /**
     * Método que permite calcular una métrica general.
     *
     * @param file una objeto File que contiene la ruta del archivo.
     * @param id un entero que representa la métrica que se desea calcular.
     * @return un String con el resultado de la métrica.
     * @throws java.lang.reflect.InvocationTargetException
     * @throws java.lang.IllegalAccessException
     */
    public String calcularMetrica(File file, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;
}
