/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import beans.seguridad.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author msolorzano
 */
public class CPermiso {

    public static boolean buscarPermiso(List<Permiso> permisos, String nombre, int nivel, String nombrePadre){
        
        for(Permiso permiso : permisos){
            boolean coincideNombre  = permiso.getAccion().getNombre().equals(nombre);
            boolean coincideNivel   = permiso.getAccion().getNivel() == nivel;
            boolean coincidePadre;
            if(nombrePadre == null){
                if (permiso.getAccion().getPadre() == null){
                    coincidePadre = true;
                }
                else{
                    coincidePadre = false;
                }
            }
            else{
                if(permiso.getAccion().getPadre() == null){
                    coincidePadre = false;
                }
                else{
                    coincidePadre = permiso.getAccion().getPadre().getNombre().equals(nombrePadre);
                }
            }
            
            if(coincideNombre && coincideNivel && coincidePadre){
                return true;
            }
                
        }
        
        return false;
    }
    
    public static void crearPermiso(Perfil perfil, String nombreAccion, int nivelAccion, String nombreAccionPadre){
        Session s = Sesion.openSessionFactory();
        
        try{
            Transaction tx = s.beginTransaction();
            
            Query q = s.getNamedQuery("AccionXNombreXNivelXPadre").setMaxResults(1);
            q.setParameter("nivel", nivelAccion);
            q.setParameter("nombre", nombreAccion);
            q.setParameter("nombrePadre", nombreAccionPadre);
            Accion accion = (Accion)q.uniqueResult();
            
            Permiso permiso = new Permiso();
            permiso.setAccion(accion);
            permiso.setPerfil(perfil);
            s.save(permiso);
            tx.commit();
            
        }catch (Exception e){
            System.out.println(e.getMessage());            
        }finally{
            s.close();
            Sesion.closeSessionFactory();
        }
        
    }
}
