package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

/**
 * Clase estática que contiene variables que permiten identificar fácilmente las
 * sentencias XQuery.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.0
 * @since 23/06/2016
 */
public final class UtileriaRDD {

//==============================================================================
//  Constantes para sentencias de artefactos. (LyK, CyK).    
//==============================================================================
    public static final int SA_CONTAR_ATRIBUTOS_TOTALES = 0;

    public static final int SA_CONTAR_ATRIBUTOS_PUBLICOS = 1;

    public static final int SA_CONTAR_ATRIBUTOS_ESTATICOS = 2;

    public static final int SA_CONTAR_METODOS_TOTALES = 3;

    public static final int SA_CONTAR_METODOS_PUBLICOS = 4;

    public static final int SA_CONTAR_METODOS_ESTATICOS = 5;

    public static final int SA_DATOS_METODOS_CLASE = 6;

    public static final int SA_IDES_CLASES_PADRE = 7;
    
    public static final int SA_CONTAR_ATRIBUTOS_HEREDADOS = 8;

    public static final int SA_CONTAR_METODOS_HEREDADOS = 9;

    public static final int SA_CONTAR_PARAMETROS_TOTALES = 10;

    public static final int SA_CONTAR_PROFUNDIDAD_ARBOL_HERENCIAS = 11;

    public static final int SA_CONTAR_HIJOS_INMEDIATOS = 12;

    public static final int SA_CONTAR_ACOPLAMIENTO = 13;

    public static final int SA_DATOS_HERENCIA_CLASES = 14;

    public static final int SA_VERIFICAR_ARTEFACTO = 15;
    
    public static final int SA_GET_IDS = 16;

//==============================================================================
//  Constantes para sentencias generales. (MG, MOOD).    
//==============================================================================
    public static final int SG_CONTAR_N_TOTAL_CLASES = 0;

    public static final int SG_CONTAR_N_TOTAL_CLASES_ABSTRACTAS = 1;

    public static final int SG_CONTAR_N_TOTAL_INTERFACES = 2;

    public static final int SG_CONTAR_N_TOTAL_PAQUETES = 3;

    public static final int SG_CONTAR_N_TOTAL_ATRIBUTOS = 4;

    public static final int SG_CONTAR_N_TOTAL_ATRIBUTOS_PUBLICOS = 5;

    public static final int SG_CONTAR_N_TOTAL_METODOS = 6;

    public static final int SG_CONTAR_N_TOTAL_METODOS_PUBLICOS = 7;

    public static final int SG_OBTENER_TODAS_LAS_IDS = 8;

    public static final int SG_CONTAR_N_TOTAL_CLASES_HIJAS = 9;

    public static final int SG_CONTAR_N_TOTAL_CLUSTERS = 10;

    public static final int SG_CONTAR_N_TOTAL_RELACIONES_NH = 11;

    public static final int SG_CONTAR_N_TOTAL_ATRIBUTOS_PRIVADOS = 12;

    public static final int SG_CONTAR_N_TOTAL_METODOS_PRIVADOS = 13;

}
