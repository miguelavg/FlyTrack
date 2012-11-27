/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flytrack;

import beans.Aeropuerto;
import gui.principal.Login;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import randomData.DataGeneration;

/**
 *
 * @author miguelavg
 */
public class FlyTrack {

    /**
     * @param args the command line arguments
     */
    public static String leerSentencias(String archivo){
        String content = "";
        try{
            content = new Scanner(new File(archivo)).useDelimiter("\\Z").next();
        }
        catch(Exception e){
            
        }
        return content;
        
    }
    
    public static void main(String[] args) {
        /*String content = "no";
        content = leerSentencias("/home/gg/gg.txt");
        if (content.contains("no")){
            Login login = new Login();
            login.setVisible(true);
        }
        else if (content.contains("si")) {
            wizardbd.WizardFrame0 wf0 = new wizardbd.WizardFrame0();
            wf0.setVisible(true);
        }*/
        
        for(Aeropuerto aero : DataGeneration.cargarAeropuertos()){
            System.out.println(aero.aString());
        }
        
        Login login = new Login();
       login.setVisible(true);
    }

  
}
