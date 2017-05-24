package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los datos de un artefacto. Esta clase se utiliza para
 * calular algunas métricas como:
 *
 * Métodos sobrescritos. Métodos añadidos. Indice de especialización. Factor de
 * polimosrfismo.
 *
 * @author Ismael Sayas Arrieta
 * @version 1.0
 * @since 18/09/2016
 */
public class Artefacto {

    private String id;
    private String idPadre;
    private List<Metodo> metodos;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor general.
     *
     * @param id el id del artefacto.
     */
    public Artefacto(String id) {
        this.id = id;
        this.idPadre = "NA";
        this.metodos = new ArrayList<>();
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método qu transforma el objeto en un String.
     *
     * @return un String que contiene los datos del objeto.
     */
    @Override
    public String toString() {
        return "Artefacto|" + id + ", " + metodos + "|";
    }

    /**
     * Metodo que añade un método al artefacto.
     *
     * @param ms un String con los datos del método.
     */
    void addNuewMethod(String ms) {
        this.getMetodos().add(new Metodo(ms));
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public List<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<Metodo> metodos) {
        this.metodos = metodos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

}
