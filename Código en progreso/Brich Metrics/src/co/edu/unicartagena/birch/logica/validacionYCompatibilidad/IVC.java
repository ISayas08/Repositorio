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

    //==============================================================================
//  Constantes para sentencias de artefactos. (LyK, CyK).    
//==============================================================================
    public static final String SA_CONTAR_ATRIBUTOS_TOTALES = "MA Atributos totales";

    public static final String SA_CONTAR_ATRIBUTOS_PUBLICOS = "MA Atributos públicos";

    public static final String SA_CONTAR_ATRIBUTOS_ESTATICOS = "MA Atributos estáticos";

    public static final String SA_CONTAR_METODOS_TOTALES = "MA Métodos totales";

    public static final String SA_CONTAR_METODOS_PUBLICOS = "MA Métodos públicos";

    public static final String SA_CONTAR_METODOS_ESTATICOS = "MA Métodos estáticos";

    public static final String SA_DATOS_METODOS_CLASE = "MA Datos métodos";

    public static final String SA_IDES_CLASES_PADRE = "MA Ides clases padre";

    public static final String SA_IDES_INTERFACES_IMPLEMENTADAS = "MA Ides interfaces implementadas";

    public static final String SA_CONTAR_ATRIBUTOS_HEREDADOS = "MA Atributos heredados";

    public static final String SA_CONTAR_METODOS_HEREDADOS = "MA Métodos heredados";

    public static final String SA_CONTAR_PARAMETROS_TOTALES = "MA Parámetros totales";

    public static final String SA_CONTAR_PROFUNDIDAD_ARBOL_HERENCIAS = "MA DHT";

    public static final String SA_CONTAR_HIJOS_INMEDIATOS = "MA Hijos inmediatos";

    public static final String SA_CONTAR_ACOPLAMIENTO = "MA Acoplamiento";

    public static final String SA_DATOS_HERENCIA_CLASES = "MA Datos de herencia";

    public static final String SA_VERIFICAR_ARTEFACTO = "MA Verificar artefacto";

    public static final String SA_GET_IDS = "MA Get id";

//==============================================================================
//  Constantes para sentencias generales. (MG, MOOD).    
//==============================================================================
    public static final String SG_CONTAR_N_TOTAL_CLASES = "MS Total clases";

    public static final String SG_CONTAR_N_TOTAL_CLASES_ABSTRACTAS = "MS Total clases abstractas";

    public static final String SG_CONTAR_N_TOTAL_INTERFACES = "MS Total interfaces";

    public static final String SG_CONTAR_N_TOTAL_PAQUETES = "MS Total paquetes";

    public static final String SG_CONTAR_N_TOTAL_ATRIBUTOS = "MS Total atributos";

    public static final String SG_CONTAR_N_TOTAL_ATRIBUTOS_PUBLICOS = "MS Total atributos públicos";

    public static final String SG_CONTAR_N_TOTAL_METODOS = "MS Total métodos";

    public static final String SG_CONTAR_N_TOTAL_METODOS_PUBLICOS = "MS Total métodos públicos";

    public static final String SG_OBTENER_TODAS_LAS_IDS = "MS Get Ids";

    public static final String SG_OBTENER_TODOS_LOS_NOMBRES = "MS Get Names";

    public static final String SG_CONTAR_N_TOTAL_CLASES_HIJAS = "MS Total clases hijas";

    public static final String SG_CONTAR_N_TOTAL_CLUSTERS = "MS Total clusters";

    public static final String SG_CONTAR_N_TOTAL_RELACIONES_NH = "MS Total relaciones noH";

    public static final String SG_CONTAR_N_TOTAL_ATRIBUTOS_PRIVADOS = "MS Total atributos privados";

    public static final String SG_CONTAR_N_TOTAL_METODOS_PRIVADOS = "MS Total métodos privados";

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
