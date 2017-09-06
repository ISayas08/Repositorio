package co.edu.unicartagena.birch.logica.recopiladorDeDatos;

import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.IVC;
import co.edu.unicartagena.birch.logica.validacionYCompatibilidad.Validador;
import co.edu.unicartagena.birch.persistencia.FPQuery;
import co.edu.unicartagena.birch.persistencia.IFP;
import co.edu.unicartagena.birch.persistencia.IQuery;
import co.edu.unicartagena.birch.persistencia.SelectorDePersistencias;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

/**
 * Clase que se encarga de gestionar la recopilación de datos.
 *
 * @author Ismael Sayas Arrieta
 */
public final class ControlDeRecopilaciones implements IRD {

    private int numeroDeRecopilaciones;
    private int numeroDeRArtefacto;
    private int numeroDeRACyK;
    private int numeroDeRALyK;
    private int numeroDeRSistema;
    private int numeroDeRSMG;
    private int numeroDeRSMOOD;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ControlDeRecopilaciones() {
        this.numeroDeRACyK = 3;
        this.numeroDeRALyK = 14;
        this.numeroDeRSMG = 10;
        this.numeroDeRSMOOD = 5;
        this.numeroDeRArtefacto = numeroDeRACyK + numeroDeRALyK;
        this.numeroDeRSistema = numeroDeRSMG + numeroDeRSMOOD;
        this.numeroDeRecopilaciones = numeroDeRArtefacto + numeroDeRSistema;
    }

//==============================================================================
//  Métodos principales.
//==============================================================================
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
    @Override
    public String recopilarDatos(String nombreArtefacto, String ruta, 
            String id, String familia) {
        int tipo = familia.equals(IRD.FAMILIA_CYK)
                ? FactoriaDeRecopiladores.RECOPILADOR_CYK
                : FactoriaDeRecopiladores.RECOPILADOR_LYK;

        try {
            return new FactoriaDeRecopiladores()
                    .getIRArtefactos(tipo)
                    .recopilar(nombreArtefacto, ruta, id);
        } catch (IllegalArgumentException | IllegalAccessException
                | InvocationTargetException ex) {
            Logger.getLogger(ControlDeRecopilaciones.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
        }
    }

    /**
     * Mètodo que permite calcular un dato especifico sobre una clase o
     * interface del diagrama.
     *
     * @param idArtifact un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param file un objeto de la clase File que contiene la ruta del archivo
     * XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    @Override
    public String recopilarDatos(String idArtifact, File file, String id, 
            String familia) {
        return this.recopilarDatos(
                idArtifact,
                file.getAbsolutePath(),
                id,
                familia);
    }

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param ruta un String que contiene la ruta del archivo XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    @Override
    public String recopilarDatos(String ruta, String id, String familia) {
        int tipo = familia.equals(IRD.FAMILIA_MG)
                ? FactoriaDeRecopiladores.RECOPILADOR_MG
                : FactoriaDeRecopiladores.RECOPILADOR_MOOD;

        try {
            return new FactoriaDeRecopiladores()
                    .getIRGenerales(tipo)
                    .recopilar(ruta, id);
        } catch (IllegalArgumentException | IllegalAccessException
                | InvocationTargetException ex) {
            Logger.getLogger(ControlDeRecopilaciones.class.getName())
                    .log(Level.SEVERE, null, ex);
            return "NA";
        }
    }

    /**
     * Método que permite recopilar un dato especifico relacionado con todo el
     * diagrama. Éste método está diseñado para recopilar datos generales tal
     * como el número de clases en el diagrama o el número de paquetes.
     *
     * @param file un objeto de la clase File que contiene la ruta del archivo.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    @Override
    public String recopilarDatos(File file, String id, String familia) {
        return this.recopilarDatos(file.getAbsolutePath(), id, familia);
    }

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param path la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    @Override
    public List<String> getArtifactId(String nombre, String path) {
        List<String> ids = new ArrayList<>();
        try {
            String idss = this.getResult(nombre, path, IVC.SA_GET_IDS);
            ids = Arrays.asList(idss.split(";"));
        } catch (Throwable ex) {
            Logger.getLogger(ControlDeRecopilaciones.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return ids;
    }

    /**
     * Método que permite obtener las id de los artefactos que tienen un nombre
     * especifico.
     *
     * @param nombre el nombre del artefacto.
     * @param file la ruta del archivo.
     * @return un lista de Strings donde cada string es una id.
     */
    @Override
    public List<String> getArtifactId(String nombre, File file) {
        return this.getArtifactId(nombre, file.getAbsolutePath());
    }

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param path la ruta del archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    @Override
    public List<String> getAllIdentifiers(String path, boolean isLookingForIds) {
        try {
            String identifiers = isLookingForIds
                    ? this.recopilarDatos(path, IRD.IDES_ARTEFACTOS, FAMILIA_MG)
                    : this.recopilarDatos(path, IRD.NOMBRES_CLASES, FAMILIA_MG);

            return Arrays.asList(identifiers.split(";"));
        } catch (Throwable ex) {
            Logger.getLogger(ControlDeRecopilaciones.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    /**
     * Método que ermite obtener todos las id o todos los nombres del diagrama.
     *
     * @param file una instancia de la clase File que contiene la ruta del
     * archivo XMI.
     * @param isLookingForIds un boolean que especifica si se quiere obtener las
     * id o los nombres.
     * @return una lista de Strings que contiene todos los datos.
     */
    @Override
    public List<String> getAllIdentifiers(File file, boolean isLookingForIds) {
        return this.getAllIdentifiers(file.getAbsolutePath(), isLookingForIds);
    }

//==============================================================================
//  Métodos secundarios.
//==============================================================================
    private String getResult(String idArtifact, String path, String id)
            throws XQException, Throwable {
        IVC validador = Validador.getInstance();
        IFP selecPersistencia = new SelectorDePersistencias();

        //Se obtiene la sentencia que se va a ejecutar.
        String Sentencia = validador.getVersionEnUso()
                .armarSentenciaArtefacto(idArtifact, path, id, false);
        //Se accede a la persistencia de XQuery.
        IQuery pXQuery = selecPersistencia
                .persistenciasQuery()
                .getPersistencias(FPQuery.XQUERY);
        //Se ejecuta la sentencia.
        XQResultSequence result = (XQResultSequence) pXQuery
                .ejecutarQuery(Sentencia);

        List<String> s = new ArrayList<>();
        while (result.next()) {
            s.add(result.getItemAsString(null));
        }
        //Se cierra la conexión con XQuery.
        pXQuery.terminarConexion();
        //Se retorna el resutado.
        return s.isEmpty() ? "-1" : s.stream().collect(Collectors.joining(";"));
    }
//==============================================================================
//  Getters and Setters.
//==============================================================================

    public int getNumeroDeRecopilaciones() {
        return numeroDeRecopilaciones;
    }

    public void setNumeroDeRecopilaciones(int numeroDeRecopilaciones) {
        this.numeroDeRecopilaciones = numeroDeRecopilaciones;
    }

    public int getNumeroDeRArtefacto() {
        return numeroDeRArtefacto;
    }

    public void setNumeroDeRArtefacto(int numeroDeRArtefacto) {
        this.numeroDeRArtefacto = numeroDeRArtefacto;
    }

    public int getNumeroDeRACyK() {
        return numeroDeRACyK;
    }

    public void setNumeroDeRACyK(int numeroDeRACyK) {
        this.numeroDeRACyK = numeroDeRACyK;
    }

    public int getNumeroDeRALyK() {
        return numeroDeRALyK;
    }

    public void setNumeroDeRALyK(int numeroDeRALyK) {
        this.numeroDeRALyK = numeroDeRALyK;
    }

    public int getNumeroDeRSistema() {
        return numeroDeRSistema;
    }

    public void setNumeroDeRSistema(int numeroDeRSistema) {
        this.numeroDeRSistema = numeroDeRSistema;
    }

    public int getNumeroDeRSMG() {
        return numeroDeRSMG;
    }

    public void setNumeroDeRSMG(int numeroDeRSMG) {
        this.numeroDeRSMG = numeroDeRSMG;
    }

    public int getNumeroDeRSMOOD() {
        return numeroDeRSMOOD;
    }

    public void setNumeroDeRSMOOD(int numeroDeRSMOOD) {
        this.numeroDeRSMOOD = numeroDeRSMOOD;
    }

}
