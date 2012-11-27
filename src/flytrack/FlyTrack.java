/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flytrack;

import beans.Aeropuerto;
import beans.Envio;
import beans.Vuelo;
import controllers.CSerializer;
import gui.principal.Login;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import randomData.DataGeneration;
import xml.XmlEnvio;
import xml.XmlVuelo;
import xml.xmlVueloString;

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

        List<Envio> envios = DataGeneration.generarEnvios(60);
        ArrayList<XmlEnvio> xmlEnvios = DataGeneration.conversionAXML(envios);
        CSerializer.serializar(xmlEnvios, "envios.xml");
        List<Vuelo> vuelos = DataGeneration.generarVuelos();
        ArrayList<xmlVueloString> xmlVuelos = DataGeneration.conversionAXML2(vuelos);
        CSerializer.serializar(xmlVuelos, "vuelos.xml");
//        
//        for(Envio envio : envios){
//            System.out.println(envio.aString());
//        }
        
//        Login login = new Login();
//        login.setVisible(true);
    }

  
}
