/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicartagena.birch.logica;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class TestBirchMetric {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rutaArchivo = "C:/Users/Ismael/Desktop/XMI de prueba/EA_XMI_2_4_2__UML_2_4_1.xml";
        //String rutaArchivo = "C:/Users/Ismael/Desktop/XMI de prueba/XMI/ICR.xml";
        List<String> nombres = Arrays.asList(
                "Clase Madre",
                "ClaseHija1",
                "ClaseHijaSub1",
                "ClaseParte",
                "ClaseDependencia",
                "ClaseHijaSub2",
                "ClaseHija2",
                "Interface1",
                "ClaseImplementacion",
                "ClaseAgregacion",
                "ClaseAgregada");
        //int metrica = ICalculaMetricas.NUMBER_OF_METHOD_OVERRIDDEN;
        int metrica = 14;
        ICalculaMetricas icm = new CalculaMetricas();

        //===========================================================Artefactos.
        nombres.stream().forEach(nombre -> {
            //System.err.println("Nombre artefacto: " + nombre);
            System.err.println("Calculando métrica: " + metrica + "...");
            icm.getArtifacId(nombre, rutaArchivo)
                    .stream()
                    .forEach(id -> {
                        //System.err.println("Id: " + id);
                        String result = icm.calcularMetricas(id, rutaArchivo, metrica);
                        System.err.println("Resultado métrica: " + result);
                        System.err.println("========================================================");
                    });
        });
        //============================================================Generales.
        /*
        for (int f = 0; f < 16; f++) {
            System.err.println("Métricas a nivel de sistema");
            System.err.println("Calculando métrica: " + f + "...");
            String result = icm.calcularMetricas(rutaArchivo, f);
            System.err.println("Resultado métrica: " + result);
            System.err.println("==================================================");
        }
         */
    }

}
