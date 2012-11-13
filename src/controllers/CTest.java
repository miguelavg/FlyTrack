/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.util.ArrayList;
import java.util.List;
import logic.VueloHist;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projections;

/**
 *
 * @author miguelavg
 */
public class CTest {

    public static void chevere() {
        try {
            SessionFactory sf = Sesion.getSessionFactory();
            Session s = sf.openSession();

            
            Query q = s.createQuery("select  v.origen.idAeropuerto, v.destino.idAeropuerto, avg(v.capacidadActual/v.capacidadMax) from Vuelo v group by v.origen, v.destino");
            List<Object[]> lista = q.list();
            ArrayList<VueloHist> vuelosL = new ArrayList <VueloHist>();
            for(Object[] o : lista){
                vuelosL.add(new VueloHist((Integer)o[0], (Integer)o[1], (Double)o[2]));
            }
            
            for(VueloHist v : vuelosL){
                System.out.println(v.getDestino() + " " + v.getOrigen() + " " + v.getpLleno());
            }
            
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //s.close();
        }
    }
}
