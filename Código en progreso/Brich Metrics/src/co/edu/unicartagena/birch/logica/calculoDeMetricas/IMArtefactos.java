package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * Interface que provee los métodos para calcular métricas de artefactos.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.0
 * @since 15/09/2016
 */
public interface IMArtefactos {

    /**
     * Método que permite calcular una métrica de artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String calcularMetrica(String artifactId, String path, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;

    /**
     * Método que permite calcular una métrica de artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto que se desea
     * evaluar.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que representa la métrica que se desea evaluar.
     * @return un String que contiene el resultado de la métrica.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public String calcularMetrica(String artifactId, File file, int id) throws
            IllegalArgumentException, IllegalAccessException,
            InvocationTargetException;
}
