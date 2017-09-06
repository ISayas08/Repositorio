package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que contiene los datos de un método. Esta clase es utilizada para
 * calcular ciertas métricas como: El factor de polimorfismo de la familia de
 * métricas MOOD o Número de métodos sobrescritos, número de métodos añadidos y
 * el indice de especialización de la familia de métricas de LyK.
 *
 * @author Ismael Sayas Arrieta
 */
public class Metodo {

    private String tipoRetorno;
    private String nombre;
    private List<String> parametros;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param datos
     */
    public Metodo(String datos) {
        this.parametros = new ArrayList<>();
        List<String> listaDatos = Arrays.asList(datos.split("%"));

        if (listaDatos.size() > 1) {
            this.tipoRetorno = listaDatos.get(0);
            this.nombre = listaDatos.get(1);
        } else {
            this.tipoRetorno = "NA";
            this.nombre = listaDatos.get(0);
        }

        if (listaDatos.size() == 3) {
            this.parametros = Arrays.asList(listaDatos.get(2).split("#"));
        }
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que compara el método con otro, con el fin de discernir si son
     * iguales.
     *
     * @param obj el método con el que se realizará la comparación
     * @return true si los métodos son iguales.
     */
    public boolean comparar(Object obj) {
        Metodo m = (Metodo) obj;

        int con = 0;
        if (this.tipoRetorno.equals(m.tipoRetorno)) {
            if (this.nombre.equals(m.nombre)) {
                con++;
                if (this.parametros.size() == m.getParametros().size()) {
                    if (this.parametros.equals(m.getParametros())) {
                        con++;
                    }
                }
            }
        }

        return con == 2;
    }

    /**
     * Método que transforma el objeto en un String.
     *
     * @return un String que contiene los datos del objeto.
     */
    @Override
    public String toString() {
        return "Metodo[" + tipoRetorno + ", " + nombre + ", " + parametros + "]";
    }

//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================
    public String getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }

}
