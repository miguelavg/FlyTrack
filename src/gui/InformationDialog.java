/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author jugox
 */
public class InformationDialog {
    public static void mostrarInformacion(String mensaje,Component parent){
        JOptionPane.showMessageDialog(parent, mensaje, "Confirmación",  JOptionPane.INFORMATION_MESSAGE);
        
    }
}
