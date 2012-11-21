/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import beans.seguridad.Pista;
import beans.seguridad.Usuario;
import java.util.Calendar;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author msolorzano
 */
public class CPista {

    public static void guardarPista(String modPrincipal, String modSecundario, 
                                    String accion, String mensaje){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            
            Pista pista = new Pista();
            
            Usuario user = Sesion.getUsuario();
            if(user != null){
                pista.setUsuarioR(user);
                pista.setUsuario(user.getLogIn());
            }else{
                pista.setUsuarioR(null);
                pista.setUsuario(null);
            }
            pista.setModuloPrincipal(modPrincipal);
            pista.setModuloSecundario(modSecundario);
            pista.setAccion(accion);
            pista.setFecha(Calendar.getInstance().getTime());
            pista.setMensaje(mensaje);
            
            s.save(pista);            
            tx.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            s.close();
            Sesion.closeSessionFactory();
        }
    }
    
    public static void guardarPista(Usuario user, String modPrincipal, String modSecundario, 
                                    String accion, String mensaje){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            
            Pista pista = new Pista();
            
            if(user != null){
                pista.setUsuarioR(user);
                pista.setUsuario(user.getLogIn());
            }else{
                pista.setUsuarioR(null);
                pista.setUsuario(null);
            }
            pista.setModuloPrincipal(modPrincipal);
            pista.setModuloSecundario(modSecundario);
            pista.setAccion(accion);
            pista.setFecha(Calendar.getInstance().getTime());
            pista.setMensaje(mensaje);
            
            s.save(pista);            
            tx.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            s.close();
            Sesion.closeSessionFactory();
        }
    }

    public static List<Pista> obtenerPistas(){
        //Esta cuestion se debe filtrar por usuario
        //por fecha mayor y menor
        //por accion (Control de Sesion - Insercion - Modificacion - Buscar)
        Session s = Sesion.openSessionFactory();
        try{
            Query q = s.getNamedQuery("Pistas");
            return (List<Pista>)q.list();
        } catch(Exception e){
//            System.out.println("CSeguridad.verificarContrasenia - ERROR: " + e.getMessage());
            return null;
        } finally{
            s.close();
            Sesion.closeSessionFactory();
        }
    }
}
