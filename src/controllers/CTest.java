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
            
            Parametro par1 = new Parametro();
            par1.setTipo("PAIS");
            par1.setValorUnico("VZLA");
            par1.setValor("Venezuela");
            
            Parametro par2 = new Parametro();
            par2.setTipo("PAIS");
            par2.setValorUnico("BOL");
            par2.setValor("Bolivia");         
           
            Transaction tx = s.beginTransaction();
            
            s.save(par1);
            s.save(par2);

            
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }
}
