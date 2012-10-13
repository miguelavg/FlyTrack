/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Filter;
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

            //Transaction tx = s.beginTransaction();

            Query q = s.getNamedQuery("ParametrosXTipo");
            q.setParameter("tipo", "PAIS");
            //q.setParameter("valorUnico", "PER");

            List<Parametro> paises = q.list();




            s.close();
            for (Parametro pais : paises) {
                System.out.println(pais.getHijos().size());
            }
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //s.close();
        }
    }
}
