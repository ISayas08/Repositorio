package co.edu.unicartagena.birch.logica.calculoDeMetricas;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que controla el cálculo de métricas. ësta clase implementa los
 * servicios ofrecidos por el subcomponente "Calculo de métricas".
 *
 * @author Ismael Sayas Arrieta
 * @version 1.2
 * @since 22/09/2016
 */
public class ControlDeCalculoDeMetricas implements ICM {

    private int numeroDeMetricas;
    private int numeroDeMArtefacto;
    private int numeroDeMACyK;
    private int numeroDeMALyK;
    private int numeroDeMSistema;
    private int numeroDeMSMG;
    private int numeroDeMSMOOD;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ControlDeCalculoDeMetricas() {
        this.numeroDeMetricas = 31;
        this.numeroDeMArtefacto = 15;
        this.numeroDeMACyK = 4;
        this.numeroDeMALyK = 11;
        this.numeroDeMSistema = 16;
        this.numeroDeMSMG = 8;
        this.numeroDeMSMOOD = 8;
    }

    /**
     * Constructor general.
     *
     * @param nCyK número de métricas de la familia CyK.
     * @param nLyK número de métricas de la familia LyK.
     * @param nMG número de métricas de la familia MG.
     * @param nMOOD número de métricas de la familia MOOD.
     */
    public ControlDeCalculoDeMetricas(int nCyK, int nLyK, int nMG, int nMOOD) {
        this.numeroDeMACyK = nCyK;
        this.numeroDeMALyK = nLyK;
        this.numeroDeMSMG = nMG;
        this.numeroDeMSMOOD = nMOOD;
        this.numeroDeMArtefacto = nCyK + nLyK;
        this.numeroDeMSistema = nMG + nMOOD;
        this.numeroDeMetricas = nCyK + nLyK + nMG + nMOOD;

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    /**
     * Método que permite calcular las métricas para un artefacto especifico.
     *
     * @param artifactId la id del artefacto.
     * @param path la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    @Override
    public IResultado calcularMetricasArtefacto(String artifactId, String path) {
        List<Double> datos = new ArrayList<>();

        for (int f = 0; f < this.getNumeroDeMACyK(); f++) {
            datos.add(this.calcularMetricasArtefacto(
                    artifactId,
                    path,
                    f,
                    ICM.FAMILIA_CYK));
        }

        for (int f = 0; f < this.getNumeroDeMALyK(); f++) {
            datos.add(this.calcularMetricasArtefacto(
                    artifactId,
                    path,
                    f,
                    ICM.FAMILIA_LYK));
        }

        return new ResultadosArtefacto(artifactId, datos);
    }

    /**
     * Método que permite calcular las métricas para un artefacto especifico.
     *
     * @param artifactId la id del artefacto.
     * @param file un objeto File que contiene la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    @Override
    public IResultado calcularMetricasArtefacto(String artifactId, File file) {
        return this.calcularMetricasArtefacto(artifactId, file.getAbsolutePath());
    }

    /**
     * Método que permite calcular las métricas generales del diagrama.
     *
     * @param path la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    @Override
    public IResultado calcularMetricasGenerales(String path) {
        List<Double> datos = new ArrayList<>();

        for (int f = 0; f < this.getNumeroDeMSMG(); f++) {
            datos.add(this.calcularMetricasGenerales(path, f, ICM.FAMILIA_MG));
        }

        for (int f = 0; f < this.getNumeroDeMSMOOD(); f++) {
            datos.add(this.calcularMetricasGenerales(path, f, ICM.FAMILIA_MOOD));
        }

        return new ResultadosDiagrama(datos);
    }

    /**
     * Método que permite calcular las métricas generales del diagrama.
     *
     * @param file un objeto File que contiene la ruta del archivo.
     * @return una instancia de IResultado que contiene todos los datos.
     */
    @Override
    public IResultado calcularMetricasGenerales(File file) {
        return this.calcularMetricasGenerales(file.getAbsolutePath());
    }

    /**
     * Método que permite calcular una métrica especifica sobre un artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto sobre el
     * cual se calculará la métrica.
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que identifica la métrica a calcular.
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    @Override
    public double calcularMetricasArtefacto(String artifactId, String path, int id, String familia) {
        //Declaración de variables.
        //Si el valor del parámetro "id" está entre 0 y 3, quiere decir que la
        //métrica a calcular es una métrica perteneciente a la familia de 
        //métricas CyK. En caso de ser mayor que 4 significa que la métrica a 
        //calcular es una métrica perteneciente a la familia de métricas LyK.

        int tipo = familia.equals(ICM.FAMILIA_CYK)
                ? FactoriaDeFamiliasDeMetricas.METRICAS_CyK
                : FactoriaDeFamiliasDeMetricas.METRICAS_LyK;

        try {
            //En primer lugar, se crea un objeto de la clase 
            //FactoriaDeFamiliasDeMetricas, la cuál contiene los métodos 
            //necesarios para acceder a las interfaces que declaran los métodos
            //necesarios para realizar el cálculo de las métricas.
            //Se solicita la interfaz para métricas de artefactos y se 
            //especifica el tipo de familia al que pertenece la métrica a calcular.
            //Se llama al método encargado de calcular las metricas.
            //Y por último, se convierte el resultado obtenido, el cuál es un 
            //String, a double y se retorna.
            return Double.parseDouble(new FactoriaDeFamiliasDeMetricas()
                    .getIMArtefactos(tipo).calcularMetrica(artifactId, path, id));
        } catch (IllegalArgumentException
                | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ControlDeCalculoDeMetricas.class.getName())
                    .log(Level.SEVERE, null, ex);
            //En caso de producirse un error, se retorna el valor de -1.
            return -1;
        }

    }

    /**
     * Método que permite calcular una métrica especifica sobre un artefacto.
     *
     * @param artifactId un String que contiene la id del artefacto sobre el
     * cual se calculará la métrica.
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que identifica la métrica a calcular.
     * @return un double que contiene los datos resultantes de calcular la
     * métrica.
     */
    @Override
    public double calcularMetricasArtefacto(String artifactId, File file, int id, String familia) {
        return this.calcularMetricasArtefacto(artifactId, file.getAbsolutePath(), id, familia);
    }

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param path un String que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica o el valor de -1 si se ha encontrado un error.
     */
    @Override
    public double calcularMetricasGenerales(String path, int id, String familia) {
        //Declaración de variables.
        //Si el valor del parámetro id es mayor o igual a 0 y menor que 8, 
        //quiere decir que la métrica a calcular es una métrica MG. 
        //De ser mayor que 7, quiere decir que la métrica a calcular es una 
        //Métrica de la familia de métricas MOOD.
        int tipo = familia.equals(ICM.FAMILIA_MG)
                ? FactoriaDeFamiliasDeMetricas.METRICAS_MG
                : FactoriaDeFamiliasDeMetricas.METRICAS_MOOD;

        try {
            //En primer lugar, se crea un objeto de la clase 
            //FactoriaDeFamiliasDeMetricas, la cuál contiene los métodos 
            //necesarios para acceder a las interfaces que declaran los métodos
            //necesarios para realizar el cálculo de las métricas.
            //Se solicita la interfaz para métricas generales y se 
            //especifica el tipo de familia al que pertenece la métrica a calcular.
            //Se llama al método encargado de calcular las metricas.
            //Y por último, se convierte el resultado obtenido, el cuál es un 
            //String, a double y se retorna.
            return Double.parseDouble(new FactoriaDeFamiliasDeMetricas()
                    .getIMGenerales(tipo).calcularMetrica(path, id));
        } catch (IllegalArgumentException
                | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(ControlDeCalculoDeMetricas.class.getName())
                    .log(Level.SEVERE, null, ex);
            //Se retorna -1 en caso de que se produzca un error.
            return -1;
        }
    }

    /**
     * Método que permite calcular una métrica especifica sobre todo el
     * diagrama.
     *
     * @param file un objeto File que contiene la ruta del archivo XMI.
     * @param id un entero que indentifica la métrica que se calculará.
     * @return un double que contiene los datos esultantes de calcular la
     * métrica.
     */
    @Override
    public double calcularMetricasGenerales(File file, int id, String familia) {
        return this.calcularMetricasGenerales(file.getAbsolutePath(), id, familia);
    }
//==============================================================================
//  Métodos sin cuerpo.
//==============================================================================
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public int getNumeroDeMetricas() {
        return numeroDeMetricas;
    }

    public void setNumeroDeMetricas(int numeroDeMetricas) {
        this.numeroDeMetricas = numeroDeMetricas;
    }

    public int getNumeroDeMArtefacto() {
        return numeroDeMArtefacto;
    }

    public void setNumeroDeMArtefacto(int numeroDeMArtefacto) {
        this.numeroDeMArtefacto = numeroDeMArtefacto;
    }

    public int getNumeroDeMSistema() {
        return numeroDeMSistema;
    }

    public void setNumeroDeMSistema(int numeroDeMSistema) {
        this.numeroDeMSistema = numeroDeMSistema;
    }

    public int getNumeroDeMACyK() {
        return numeroDeMACyK;
    }

    public void setNumeroDeMACyK(int numeroDeMACyK) {
        this.numeroDeMACyK = numeroDeMACyK;
    }

    public int getNumeroDeMALyK() {
        return numeroDeMALyK;
    }

    public void setNumeroDeMALyK(int numeroDeMALyK) {
        this.numeroDeMALyK = numeroDeMALyK;
    }

    public int getNumeroDeMSMG() {
        return numeroDeMSMG;
    }

    public void setNumeroDeMSMG(int numeroDeMSMG) {
        this.numeroDeMSMG = numeroDeMSMG;
    }

    public int getNumeroDeMSMOOD() {
        return numeroDeMSMOOD;
    }

    public void setNumeroDeMSMOOD(int numeroDeMSMOOD) {
        this.numeroDeMSMOOD = numeroDeMSMOOD;
    }
}
