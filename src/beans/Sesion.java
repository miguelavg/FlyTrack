/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.seguridad.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author msolorzano
 */
public class Sesion {
    
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void openSessionFactory(){
        sessionFactory.openSession();
    }
    
    public static void closeSessionFactory(){
        sessionFactory.close();
    }
    
    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }
    
    
    
    public void crearSesion(String nombreUsuario){
        SessionFactory sf = getSessionFactory();
        Session s = sf.openSession();
        
        try{
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
            q.setParameter("Login", nombreUsuario);
            setUsuario((Usuario)q.uniqueResult());
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
    }
    
}
