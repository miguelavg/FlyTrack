/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import java.util.ArrayList;
import logic.AeroLite;
import logic.EnvioLite;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author miguelavg
 */
public class CSimulator {

    public static ArrayList<EnvioLite> calcularEnvios() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Query q = s.createQuery("select  e.origen.nombre, e.destino.nombre,  count(*) from Envio");
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return null;
    }
}
