/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import java.util.ArrayList;
import java.util.List;
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
            Query q = s.createQuery("select  count(e) from Envio e").setMaxResults(1);
            int nEnvios = (Integer) q.uniqueResult();
            
            q = s.createQuery("select  e.origen.nombre, e.destino.nombre,  count(e) from Envio e group by e.origen.nombre, e.destino.nombre");
            List<Object[]> envios = q.list();
            
            for(Object[] obj : envios){
                
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return null;
    }
}
