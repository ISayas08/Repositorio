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
 * @version 1.2
 * @since 14/09/2016
 */
public final class ControlDeRecopilaciones implements IRD {

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public ControlDeRecopilaciones() {

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
    public String recopilarDatos(String nombreArtefacto, String ruta, int id) {
        int tipo = (id < 3) // Las recopilaciones de CyK van de 0 a 2.
                ? FactoriaDeRecopiladores.RECOPILADOR_CyK
                : FactoriaDeRecopiladores.RECOPILADOR_LyK;

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
     * @param nombreArtefacto un String que contiene el nombre de la clase o
     * interface a evaluar.
     * @param file un objeto de la clase File que contiene la ruta del archivo
     * XMI.
     * @param id un entero que permite identificar el dato que se requiere.
     * @return un String que contiene el dato solicitado.
     */
    @Override
    public String recopilarDatos(String nombreArtefacto, File file, int id) {
        return this.recopilarDatos(nombreArtefacto, file.getAbsolutePath(), id);
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
    public String recopilarDatos(String ruta, int id) {
        int tipo = (id < 9) // Las recopilaciones MG van de 0 a 8.
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
    public String recopilarDatos(File file, int id) {
        return this.recopilarDatos(file.getAbsolutePath(), id);
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
    public List<String> getArtifacId(String nombre, String path) {
        List<String> ids = new ArrayList<>();
        try {
            String idss =this.getResult(nombre, path, UtileriaRDD.SA_GET_IDS);
            ids = Arrays.asList(idss.split(";"));
        } catch (Throwable ex) {
            Logger.getLogger(ControlDeRecopilaciones.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<String> getArtifacId(String nombre, File file) {
        return this.getArtifacId(nombre, file.getAbsolutePath());
    }
    
    @Override
    public List<String> getAllIdentifier(String path, boolean isLookingForIds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllIdentifier(File file, boolean isLookingForIds) {
        return this.getAllIdentifier(file.getAbsolutePath(), isLookingForIds);
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

    
}
