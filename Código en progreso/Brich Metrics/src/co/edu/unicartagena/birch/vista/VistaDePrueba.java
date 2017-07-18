package co.edu.unicartagena.birch.vista;

import co.edu.unicartagena.birch.logica.CalculaMetricas;
import co.edu.unicartagena.birch.logica.ICalculaMetricas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ismael Sayas Arrieta
 * @version 1.0
 * @since 9/07/2017
 */
public class VistaDePrueba {
    
    private String state;
    private String progress;
    private String totalProgress;

//==============================================================================
//  Constructores y metodos de inicialización.
//==============================================================================
    /**
     * Constructor por defecto.
     */
    public VistaDePrueba() {

    }

//==============================================================================
//  Métodos principales.
//==============================================================================
    public void abrirArchivo() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XMI", "xmi", "xml");
        fc.setFileFilter(filter);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            calcularMetricasArtefactos(archivo.getAbsolutePath(), true);
            calcularMetricasArtefactos(archivo.getAbsolutePath(), false);
            calcularMetricasSistema(archivo.getAbsolutePath());
            /*System.err.println(new CalculaMetricas().calcularMetrica(
                    archivo.getAbsolutePath(),
                    ICalculaMetricas.POLYMORPHISM_FACTOR));*/
        }
    }

    private void calcularMetricasArtefactos(String path, boolean isId) {
        ICalculaMetricas icm = new CalculaMetricas();
        String timeLog = new SimpleDateFormat("yyyy MM dd - HH mm ss")
                .format(Calendar.getInstance().getTime());
        String fileName = isId
                ? "Artifact-level (Id version) - " + timeLog + ".txt"
                : "Artifact-level (Name version) - " + timeLog + ".txt";
        File XMIFile = new File(path);
        String outPutPath = XMIFile.getParent() + "/Output/" + fileName;
        String line = "";

        List<String> identificadores = icm.getAllIdentifier(path, isId);

        if (!identificadores.isEmpty()) {

            line = "File: " + XMIFile.getName();
            saveLog(line, outPutPath);
            line = "Calculating artifact-level metrics...";
            saveLog(line, outPutPath);

            for (int f = 0; f < ICalculaMetricas.NUMERO_DE_MA_CYK; f++) {
                line = "==================================================";
                saveLog(line, outPutPath);
                line = getMetricName("CyK_" + f);
                saveLog(line, outPutPath);
                for (int c = 0; c < identificadores.size(); c++) {
                    if (isId) {
                        String result = icm.calcularMetrica(
                                identificadores.get(c),
                                path,
                                "CyK_" + f);
                        line = identificadores.get(c) + ":\t\t " + result;
                        saveLog(line, outPutPath);
                    } else {
                        List<String> ids = icm.getArtifacId(identificadores.get(c), path);
                        for (String id : ids) {
                            String result = icm.calcularMetrica(
                                    id,
                                    path,
                                    "CyK_" + f);
                            line = identificadores.get(c) + ":\t\t " + result;
                            saveLog(line, outPutPath);
                        }
                    }

                }

            }

            for (int f = 0; f < ICalculaMetricas.NUMERO_DE_MA_LYK; f++) {
                line = "==================================================";
                saveLog(line, outPutPath);
                line = getMetricName("LyK_" + f);
                saveLog(line, outPutPath);
                for (int c = 0; c < identificadores.size(); c++) {
                    if (isId) {
                        String result = icm.calcularMetrica(
                                identificadores.get(c),
                                path,
                                "LyK_" + f);
                        line = identificadores.get(c) + ":\t\t " + result;
                        saveLog(line, outPutPath);
                    } else {
                        List<String> ids = icm.getArtifacId(identificadores.get(c), path);
                        for (String id : ids) {
                            String result = icm.calcularMetrica(
                                    id,
                                    path,
                                    "LyK_" + f);
                            line = identificadores.get(c) + ":\t\t " + result;
                            saveLog(line, outPutPath);
                        }
                    }
                }
            }
            line = "==================================================";
            saveLog(line, outPutPath);
            line = "Process completed";
            saveLog(line, outPutPath);
        }
    }

    private void calcularMetricasSistema(String path) {
        ICalculaMetricas icm = new CalculaMetricas();
        String timeLog = new SimpleDateFormat("yyyy MM dd - HH mm ss")
                .format(Calendar.getInstance().getTime());
        File XMIFile = new File(path);
        String outPutPath = XMIFile.getParent()
                + "/Output/System-level - " + timeLog + ".txt";
        String line = "";
        line = "File: " + XMIFile.getName();
        saveLog(line, outPutPath);
        line = "Calculating system-level metrics...";
        saveLog(line, outPutPath);
        line = "==================================================";
        saveLog(line, outPutPath);

        for (int f = 0; f < ICalculaMetricas.NUMERO_DE_MS_MG; f++) {
            String result = icm.calcularMetrica(path, "MG_" + f);
            line = getMetricName("MG_" + f) + ":\t\t" + result;
            saveLog(line, outPutPath);
        }
        for (int f = 0; f < ICalculaMetricas.NUMERO_DE_MS_MOOD; f++) {
            String result = icm.calcularMetrica(path, "MOOD_" + f);
            line = getMetricName("MOOD_" + f) + ":\t\t" + result;
            saveLog(line, outPutPath);
        }
        line = "==================================================";
        saveLog(line, outPutPath);
        line = "Process completed";
        saveLog(line, outPutPath);
    }

//==============================================================================
//  Métodos secundarios.
//==============================================================================
    public static void saveLog(String line, String path) {
        BufferedWriter writer = null;
        try {
            File logFile = new File(path);
            new File(logFile.getParent()).mkdirs();

            writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.append(line);
            writer.newLine();
            System.out.println(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    private String getMetricName(String id) {
        switch (id) {
            case ICalculaMetricas.DEPTH_OF_INHERITANCE_TREE:
                return "Depth of inheritance tree";
            case ICalculaMetricas.NUMBER_OF_CHILDREN:
                return "Number of children";
            case ICalculaMetricas.COUPLING_BETWEEN_OBJECT_CLASSES:
                return "Coupling between object classes";
            case ICalculaMetricas.WEIGHTED_METHODS_PER_CLASS:
                return "Weighted methods per class";
            case ICalculaMetricas.NUMBER_OF_PUBLIC_METHODS:
                return "Number of public methods";
            case ICalculaMetricas.NUMBER_OF_METHODS:
                return "Number of methods";
            case ICalculaMetricas.NUMBER_OF_PUBLIC_VARIABLES:
                return "Number of public variables";
            case ICalculaMetricas.NUMBER_OF_VARIABLES:
                return "Number of variables";
            case ICalculaMetricas.NUMBER_OF_CLASS_VARIABLES:
                return "Number of class variables";
            case ICalculaMetricas.NUMBER_OF_CLASS_METHODS:
                return "Number of class methods";
            case ICalculaMetricas.NUMBER_OF_METHODS_INHERITED:
                return "Number of methods inherited";
            case ICalculaMetricas.NUMBER_OF_METHODS_OVERRIDDEN:
                return "Number of methods overridden";
            case ICalculaMetricas.NUMBER_OF_NEW_METHODS:
                return "Number of new methods";
            case ICalculaMetricas.AVERAGE_PARAMETER_PER_METHOD:
                return "Average parameter per method";
            case ICalculaMetricas.SPECIALIZATION_INDEX:
                return "Specialization index";
            case ICalculaMetricas.NUMBER_OF_CLASSES:
                return "Number of clases";
            case ICalculaMetricas.NUMBER_OF_ABSTRACT_CLASSES:
                return "Number of abstract clases";
            case ICalculaMetricas.NUMBER_OF_INTERFACES:
                return "Number of interfaces";
            case ICalculaMetricas.NUMBER_OF_PACKAGES:
                return "Number of packages";
            case ICalculaMetricas.AVERAGE_METHODS_ARTIFACT:
                return "Average methods artifact";
            case ICalculaMetricas.AVERAGE_PUBLIC_METHODS_ARTIFACT:
                return "Average public methods artifact";
            case ICalculaMetricas.AVERAGE_ATTRIBUTES_ARTIFACT:
                return "Average attributes artifact";
            case ICalculaMetricas.AVERAGE_PUBLIC_ATTRIBUTE_ARTIFACT:
                return "Average public attribute artifact";
            case ICalculaMetricas.METHOD_HIDING_FACTOR:
                return "Method hiding factor";
            case ICalculaMetricas.ATTRIBUTE_HIDING_FACTOR:
                return "Attribute hiding factor";
            case ICalculaMetricas.METHOD_INHERITANCE_FACTOR:
                return "Method inheritance factor";
            case ICalculaMetricas.ATTRIBUTE_INHERITANCE_FACTOR:
                return "Attribute inheritance factor";
            case ICalculaMetricas.POLYMORPHISM_FACTOR:
                return "Polymorphism factor";
            case ICalculaMetricas.COUPLING_FACTOR:
                return "Coupling factor";
            case ICalculaMetricas.CLUSTERING_FACTOR:
                return "Clustering factor";
            case ICalculaMetricas.REUSE_FACTOR:
                return "Reuse factor";
            default:
                return "No valida";
        }
    }

//==============================================================================
//  Getters and Setters.
//==============================================================================
}
