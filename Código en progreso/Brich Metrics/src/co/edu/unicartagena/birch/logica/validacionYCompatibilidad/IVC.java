package co.edu.unicartagena.birch.logica.validacionYCompatibilidad;

/**
 * Interfaz que declara los servicios ofrecidos por el subcomponente "Validación
 * y compatibilidad".
 *
 * @author Ismael Sayas Arrieta.
 * @version 1.1
 * @since 27/07/2016
 */
public interface IVC {

    /**
     * Método que verifica la validez de un archivo XMI.
     *
     * @param path la ruta del archivo.
     *
     * @return returna true en caso de que el archivo corresponda con alguno de
     * las versiones de XMI que acepta el componente, o false en caso contrario.
     */
    public String verificarArchivoXMI(String path);

    /**
     * Método que permite comprobar si el artefacto al que se le desea calcular
     * métricas existe.
     *
     * @param nombre un String que representa el nombre del artefacto.
     * @param path la ruta del archivo.
     * @param isIdSearch atributoque permite especificar si el artefacto a
     * evaluar se busca a través de la id o del nombre.
     * @return Un String con el resultado de la verificación, en caso de que no
     * exista ningún problema con el nombre se retornará "Artifact available."
     */
    public boolean verificarArtefacto(String nombre, String path, boolean isIdSearch);

    /**
     * Método que retorna una instancia de la interfáz IVersionSentencias la
     * cuál contiene las sentencias necesarias para trabajar con cada versión en
     * particular.
     *
     * @return
     */
    public Version getVersionEnUso();

    /**
     * Método que libera el espacio en memoria utilizado por la clase.
     *
     * Nota: ëste método es llamado una vez que el proceso de cálculo de
     * métricas es llevado a cabo, con el propósito de liberar espacio en
     * memoria innecesario.
     *
     * @throws java.lang.Throwable
     */
    public void disposeSingleton() throws Throwable;
}
