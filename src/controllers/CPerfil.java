/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import java.util.List;
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
public class CPerfil {
    
         public List<Perfil> Buscar()
    {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Perfil> ListaPerfiles;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
           q = s.getNamedQuery("Perfil");
           ListaPerfiles= q.list();
           
           
           return ListaPerfiles;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    }
         
     public void agregarPerfil(String nombre, String descripcion, Parametro estado){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            Perfil CPerfil = new Perfil();
            
            CPerfil.setNombre(nombre);
            CPerfil.setDescripcion(descripcion);
            CPerfil.setEstado(estado);
            
            int i = (Integer)s.save(CPerfil);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }   
     
     
         public void modificarPerfil(Integer idPerfil,String nombre, String descripcion, Parametro estado){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession( );
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            Perfil CPerfil = new Perfil();
            CPerfil.setIdPerfil(idPerfil);
            CPerfil.setNombre(nombre);
            CPerfil.setDescripcion(descripcion);
            CPerfil.setEstado(estado);
            
            
            s.update(CPerfil);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }   
         
        public Perfil BuscarXid(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
          Perfil CPerfil = new Perfil();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("PerfilxId").setMaxResults(1);
            q.setParameter("idperfil", id);
            CPerfil=(Perfil)q.uniqueResult();
            return CPerfil;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
         
}
