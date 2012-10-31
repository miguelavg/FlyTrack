/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Contrasena;
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
        
        SessionFactory sf = Sesion.getSessionFactory();
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
    
    
    
     public List<Usuario> Buscar(Integer idperfil, Integer idaeropuerto,Integer idcliente,Integer Estado)
    {
        SessionFactory sf = Sesion.getSessionFactory();
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
     
         public Usuario BuscarXid(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
          Usuario CUsuario = new Usuario();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("UsuarioxId").setMaxResults(1);
            q.setParameter("idusuario", id);
            CUsuario=(Usuario)q.uniqueResult();
            return CUsuario;
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
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession( );
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            //Usuario CUsuario = (Usuario)s.load(Usuario.class, idUsuario);\
            Usuario CUsuario = new Usuario();
            CUsuario.setIdUsuario(idUsuario);
            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            CUsuario.setIdCliente(cliente);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            CUsuario.setNumAcceso(0);
            CUsuario.setPrimerAcceso(false);
            
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
