package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

import java.io.File;
import java.util.HashMap;

/**
 * Clase abstracta que contiene algunoas atributos y comportamientos comunes
 * entre las diferentes versiones.
 *
 * @author Ismael Sayas Arrieta
 */
public abstract class Version {

    /**
     * Variable que almacena el valor del la URL donde se especifica el
     * namespace uml utilizado en el archivo xmi.
     */
    protected String umlNamespace;

    /**
     * Variable que almacena el valor del la URL donde se especifica el
     * namespace xmi utilizado en el archivo.
     */
    protected String xmiNamespace;
    /**
     * Atributo que se utiliza para formar sentencias XQuery en tiempo de
     * ejecución, el prejijo es el mismo tanto para sentencias de artefactos
     * como para las sentencias que recopilan información de todo el diagrama.
     * Ésta es la primera parte de una sentencia y va antes de la ruta del
     * archivo.
     */
    protected String prefijo;

    /**
     * Atributo que se utiliza para formar sentencias XQuery en tiempo de
     * ejecución, que en este caso están diseñadas para recopilar información de
     * artefactos. Ésta es la tercera parte de la sentencia y va justo después
     * de la ruta del archivo, y antes del nombre del artefacto.
     */
    protected String intermedioArtefactos;

    /**
     * Los sufijos de artefactos se utilizan para formar sentencias XQuery en
     * tiempo de ejecución, que en este caso están diseñadas para recopilar
     * información de artefactos. Éstos son la última parte de la sentencia y
     * van justo después del nombre del artefacto.
     */
    protected HashMap sufijosArtefactos;

    /**
     * Los sufijos de diagrama se utilizan para formar sentencias XQuery en
     * tiempo de ejecución, en este caso las sentencias formadas son para
     * recopilar información de todo el diagrama. Éstos son la última parte de
     * la sentencia y van justo después de la ruta del archivo.
     */
    protected HashMap sufijosDiagrama;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public Version() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite armar una sentencia especifica para recopilar datos de
     * un artefacto especifico. La sentencia está compuesta por un prefijo, la
     * ruta del archivo, un intermedio, el nombre del artefacto y finalmente un
     * sufijo el cuál debe ser idenificado con un entero.
     *
     * @param nombre el nombre del artefacto.
     * @param path la dirección de archivo xmi.
     * @param id la id que identifica a la sentencia deseada.
     * @param isSearchAsId atributo que permite especificar si la sentencia se
     * armará a través de la id del artefacto o a través del nombre.
     * @return un String que contiene la sentencia armada.
     */
    public abstract String armarSentenciaArtefacto(String nombre, String path, String id, boolean isSearchAsId);

    /**
     * Método que permite armar una sentencia especifica para recopilar datos de
     * un artefacto especifico. La sentencia está compuesta por un prefijo, la
     * ruta del archivo, un intermedio, el nombre del artefacto y finalmente un
     * sufijo el cuál debe ser idenificado con un entero.
     *
     * @param nombre el nombre del artefacto.
     * @param file un objeto File que contiene la dirección de archivo xmi.
     * @param id la id que identifica a la sentencia deseada.
     * @param isSearchAsId atributo que permite especificar si la sentencia se
     * armará a través de la id del artefacto o a través del nombre.
     * @return un String que contiene la sentencia armada.
     */
    public abstract String armarSentenciaArtefacto(String nombre, File file, String id, boolean isSearchAsId);

    /**
     * Método que permite armar una sentencia especifica para recopilar datos
     * generales. La sentencia está compuesta por un prefijo, la ruta del
     * archivo y un sufijo que debe ser identificado por un entero.
     *
     * @param path la dirección del archivo xmi.
     * @param id la ide que identifica la sentencia deseada.
     * @return un String que contiene la sentencia armada.
     */
    public abstract String armarSentenciaGeneral(String path, String id);

    /**
     * Método que permite armar una sentencia especifica para recopilar datos
     * generales. La sentencia está compuesta por un prefijo, la ruta del
     * archivo y un sufijo que debe ser identificado por un entero.
     *
     * @param file un objeto File que contiene la dirección del archivo xmi.
     * @param id la ide que identifica la sentencia deseada.
     * @return un String que contiene la sentencia armada.
     */
    public abstract String armarSentenciaGeneral(File file, String id);
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================

    /**
     * Método que se encarga de llenar el vector que contiene todos los sufijos
     * de las sentencias de artefactos.
     */
    protected abstract void llenarSufijosArtefactos();

    /**
     * Método que se encarga de llenar el vector que contiene todos los sufijos
     * de las sentencias de diagrama.
     */
    protected abstract void llenarSufijosGenerales();
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public HashMap getSufijosArtefactos() {
        return sufijosArtefactos;
    }

    public void setSufijosArtefactos(HashMap sufijosArtefactos) {
        this.sufijosArtefactos = sufijosArtefactos;
    }

    public HashMap getSufijosDiagrama() {
        return sufijosDiagrama;
    }

    public void setSufijosDiagrama(HashMap sufijosDiagrama) {
        this.sufijosDiagrama = sufijosDiagrama;
    }

    public String getUmlNamespace() {
        return umlNamespace;
    }

    public void setUmlNamespace(String umlNamespace) {
        this.umlNamespace = umlNamespace;
    }

    public String getXmiNamespace() {
        return xmiNamespace;
    }

    public void setXmiNamespace(String xmiNamespace) {
        this.xmiNamespace = xmiNamespace;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public abstract String getIntermedioArtefactos(boolean isIdSearch);

    public void setIntermedioArtefactos(String intermedioArtefactos) {
        this.intermedioArtefactos = intermedioArtefactos;
    }

    /**
     * Método que permite armar una sentencia especial que permite verificar si
     * el archivo especificado en la ruta existe y está bien formado.
     *
     * @param path
     * @return
     */
    public String getXQ_VAL_DOC(String path) {
        return "doc-available(\"" + path + "\")";
    }

}
