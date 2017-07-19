package co.edu.unicartagena.birch.vista;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ismael
 */
public class TestBirchMetric {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Principal - Error con el LookAndFeel.");
        }

        VistaDePrueba vTest = new VistaDePrueba();
        vTest.abrirArchivo();
        JOptionPane.showMessageDialog(null, "Cálculo terminado",
                "Birch Metrics",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
