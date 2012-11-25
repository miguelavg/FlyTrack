/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Accion;
import beans.seguridad.Perfil;
import beans.seguridad.Permiso;
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
    
    public List<Perfil> Buscar(){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Perfil");
            return (List<Perfil>)q.list();           
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    }
         
    public List<Accion> BuscarAcciones (){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Accion> ListaAccion;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("ArbolAccion");
            ListaAccion= q.list();
                      
           return ListaAccion;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        return null;
    }
         
    public static Perfil agregarPerfil(String nombre, String descripcion, Parametro estado){
        
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();

            Perfil perfilNuevo = new Perfil();
            perfilNuevo.setNombre(nombre);
            perfilNuevo.setDescripcion(descripcion);
            perfilNuevo.setEstado(estado);

            s.save(perfilNuevo);
            tx.commit();
            return perfilNuevo;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
            Sesion.closeSessionFactory();
        }
        return null;
    }   
     
    public static Perfil modificarPerfil(Integer idPerfil,String nombre, String descripcion, Parametro estado){
        
        Session s = Sesion.openSessionFactory();
        
        try {
            Transaction tx = s.beginTransaction();
            
            Perfil perfilModificado = new Perfil();
            perfilModificado.setIdPerfil(idPerfil);
            perfilModificado.setNombre(nombre);
            perfilModificado.setDescripcion(descripcion);
            perfilModificado.setEstado(estado);
            
            s.update(perfilModificado);
            tx.commit();
            return perfilModificado;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
            Sesion.closeSessionFactory();
        }
        return null;
    }   
         
    public static Perfil BuscarXid(int id){
    
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("PerfilxId").setMaxResults(1);
            q.setParameter("idperfil", id);
            Perfil perfilAux = (Perfil)q.uniqueResult();
            perfilAux.getPermisos().size();
            return  perfilAux;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
                
    public static String validar(Integer idperfil, String nombre, String descripcion, Parametro estado) {
        Session s = Sesion.openSessionFactory();
        String error_message = "";
        try {

//            if (nombre.isEmpty()|| descripcion.isEmpty()|| estado==null ) { //deberia aceptar descripcion nula
            if (nombre.isEmpty()|| estado==null ) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }
            
            if(!nombre.isEmpty()){
                Query q = s.getNamedQuery("PerfilXNombre").setMaxResults(1);
                q.setParameter("nombre", nombre);
                Perfil perfilEncontrado = (Perfil)q.uniqueResult();
                if (perfilEncontrado != null) {
                    error_message = error_message + CValidator.buscarError("ERROR_FT005") + "\n";                    
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }
        return error_message;
    }
    
    public static List<Permiso> listarPermisosXPerfil(int idperfil){
        Session s = Sesion.openSessionFactory();
        
        try{
            
            Query q = s.getNamedQuery("PermisosXPerfil");
            q.setParameter("idperfil", idperfil);
            return (List<Permiso>)q.list();

        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }
        
        return null;
    }
        
         
}
