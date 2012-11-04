/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author miguelavg
 */
public class ErrorDialog {
    public static void mostrarError(String error_message, Component parent){
        JOptionPane.showMessageDialog(parent, "Se produjeron los siguientes errores:\n" + error_message, "Error",  JOptionPane.ERROR_MESSAGE);
        
    }
}
