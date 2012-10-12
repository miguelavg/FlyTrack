/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
            
            
            
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Actual contact insertion will happen at this step
            s.flush();
            s.close();
        }
    }
}
