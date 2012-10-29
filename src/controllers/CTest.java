/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author miguelavg
 */
public class CTest {

    private SessionFactory sf;
    private Session s;

    public CTest() {
        this.sf = new AnnotationConfiguration().configure().buildSessionFactory();
        this.s = sf.openSession();
    }

    public void chevere() {
        try {

            Transaction tx = s.beginTransaction();
            Query q;            
            q = s.getNamedQuery("Aeropuertos").setFirstResult(0).setMaxResults(1);
            
            Aeropuerto aeropuerto = (Aeropuerto) q.uniqueResult(); //List<Aeropuerto> aeropuertos = q.list();
  
            //for(Aeropuerto aeropuerto : aeropuertos){
                System.out.println(aeropuerto.getNombre());
            //}
            
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //s.close();
        }
    }
}
