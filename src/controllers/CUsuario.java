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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Filter;
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
            CUsuario.setIdCliente(cliente);
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

        //El usuario exista y este activo
        //
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try{
         
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
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
    
     public List<Usuario> Buscar(Integer idperfil, Integer idaeropuerto,Integer idcliente,Integer Estado)
    {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Usuario> ListaUsuarios;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
           q = s.getNamedQuery("Usuario");
           Filter f = s.enableFilter("UsuarioxIdperfil");
           f.setParameter("idperfil",idperfil);
           Filter f2 = s.enableFilter("UsuarioxIdaeropuerto");
           f2.setParameter("idaeropuerto",idaeropuerto);
           Filter f3 = s.enableFilter("UsuarioxIdcliente");
           f3.setParameter("idcliente",idcliente);
//           Filter f4 = s.enableFilter("UsuarioxLogin");
//           f4.setParameter("login",Login);
           Filter f5 = s.enableFilter("UsuarioxEstado");
           f5.setParameter("estado",Estado);
           ListaUsuarios= q.list();
           
           
           return ListaUsuarios;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    }
     
         public void modificarUsuario(Integer idUsuario,Perfil perfil, Aeropuerto aeropuerto, Cliente cliente, String LogIn,
                Parametro estado){
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            Usuario CUsuario = (Usuario)s.load(Usuario.class, idUsuario);
            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            CUsuario.setIdCliente(cliente);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            
            s.update(CUsuario);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }
    
}
