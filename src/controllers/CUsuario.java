/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author joao
 */


public class CUsuario {
    public void agregarUsuario(Perfil perfil, Aeropuerto aeropuerto, Cliente cliente, String LogIn,
                Parametro estado,Integer numAcceso, boolean PrimerAcceso){
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            Usuario CUsuario = new Usuario();
            
            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            //CUsuario.setIdCliente(cliente);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            CUsuario.setNumAcceso(numAcceso);
            CUsuario.setPrimerAcceso(PrimerAcceso);
            
            int i = (Integer)s.save(CUsuario);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }
    
    public boolean verificarContrasenia(String user, String pass){

        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try{
         
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("UsuarioXLogin").setFirstResult(0);
            q.setParameter("login", user);
            Usuario usuario = (Usuario)q.uniqueResult();
            
            System.out.println("Login :"+ usuario.getLogIn());
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }

        return true;
    }
}
