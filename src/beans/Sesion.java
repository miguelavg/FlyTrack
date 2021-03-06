/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.seguridad.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

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
    
    public static Session openSessionFactory(){
        return sessionFactory.openSession();
    }
    
    public static void closeSessionFactory(){
        sessionFactory.close();
    }
    
    private static Usuario usuario = null;
    private static boolean cambioPerfil;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

    public static boolean huboCambioPerfil() {
        return cambioPerfil;
    }

    public static void setCambioPerfil(boolean CambioPerfil) {
        cambioPerfil = CambioPerfil;
    }
    
}
