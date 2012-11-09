/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jugox
 */
public class CSerializer {
    
    public static void serializar(ArrayList lista, String arch) {

        XStream xstream = new XStream();
        String xml = xstream.toXML(lista);

        try {
            FileWriter fw = new FileWriter(arch);
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public static ArrayList deserializar(String nombreArch) {

        ArrayList lista = null;

        try {
            XStream xstream = new XStream();
            FileReader fr = new FileReader(nombreArch);           
            lista = (ArrayList) xstream.fromXML(fr);
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return lista ;
    }
    
}
