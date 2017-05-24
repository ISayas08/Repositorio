package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import java.io.File;
import java.util.List;

/**
 * Interfaz que declara los servicios que ofrece el subcomonente "Recopilador de
 * datos"
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.3
 * @since 16/09/2016
 */
public interface IRD {

    public static final int PROFUNDIDAD_DEL_ARBOL_DE_HERENCIAS = 0;

    public static final int HIJOS_INMEDIATOS = 1;

    public static final int ACOPLAMIENTO = 2;
//
    public static final int ATRIBUTOS_TOTALES = 3;

    public static final int ATRIBUTOS_PUBLICOS = 4;

    public static final int ATRIBUTOS_ESTATICOS = 5;
    
    public static final int METODOS_TOTALTES = 6;

    public static final int METODOS_PUBLICOS = 7;

    public static final int METODOS_ESTATICOS = 8;

    public static final int DATOS_METODOS_CLASE = 9;

    public static final int IDS_CLASES_PADRE = 10;
    
    public static final int ATRIBUTOS_HEREDADOS = 11;
    
    public static final int METODOS_HEREDADOS = 12;

    public static final int PARAMETROS_TOTALES = 13;

    public static final int DATOS_HERENCIA_CLASE = 14;

    //==========================================================================
    public static final int TOTAL_CLASES = 0;

    public static final int TOTAL_CLASES_ABSTRACTAS = 1;

    public static final int TOTAL_INTERFACES = 2;

    public static final int TOTAL_PAQUETES = 3;

    public static final int TOTAL_ATRIBUTOS = 4;

    public static final int TOTAL_ATRIBUTOS_PUBLICOS = 5;

    public static final int TOTAL_METODOS = 6;

    public static final int TOTAL_METODOS_PUBLICOS = 7;

    public static final int IDES_CLASES = 8;

    public static final int TOTAL_CLASES_HIJAS = 9;

    public static final int TOTAL_CLUSTERS = 10;

    public static final int TOTAL_RELACIONES_NH = 11;

    public static final int TOTAL_ATRIBUTOS_PRIVADOS = 12;

    public static final int TOTAL_METODOS_PRIVADOS = 13;

    /**
     * Mètodo que permite calcular un dato especifico sobre una clase o
     * interface del diagrama.
     *
     * @param nombreArtefacto un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param ruta un String con la ruta donde se encuentra el archivo XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String nombreArtefacto, String ruta, int id);

    /**
     * Mètodo que permite calcular un dato especifico sobre una clase o
     * interface del diagrama.
     *
     * @param nombreArtefacto un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param file un objeto de la clase File que contiene la ruta del archivo
     * XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String nombreArtefacto, File file, int id);

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param ruta un String que contiene la ruta del archivo XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(String ruta, int id);

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param file un objeto de la clase File que contiene la ruta del archivo.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    public String recopilarDatos(File file, int id);

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param path la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    public List<String> getArtifacId(String nombre, String path);

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param file la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    public List<String> getArtifacId(String nombre, File file);
    
    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param path la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(String path, boolean isLookingForIds);

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param file una instancia de la clase File que contiene la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    public List<String> getAllIdentifier(File file, boolean isLookingForIds);

}
