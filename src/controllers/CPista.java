/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import beans.seguridad.Pista;
import beans.seguridad.Usuario;
import java.util.Calendar;
import org.hibernate.*;

/**
 *
 * @author msolorzano
 */
public class CPista {

    public static void guardarPista(String modPrincipal, String modSecundario, 
                                    String clase, String metodo, 
                                    String estadoAnt, String estadoAct, String descripcion){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            
            Pista pista = new Pista();
            
            Usuario user = Sesion.getUsuario();
            pista.setUsuarioR(user);
            pista.setUsuario(user.getLogIn());
            pista.setModuloPrincipal(modPrincipal);
            pista.setModuloSecundario(modSecundario);
            pista.setClase(clase);
            pista.setMetodo(metodo);
            pista.setFecha(Calendar.getInstance().getTime());
            pista.setEstadoAnterior(estadoAnt);
            pista.setEstadoActual(estadoAct);
            pista.setDescripcion(descripcion);
            
            s.save(pista);
            
            tx.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            s.close();
            Sesion.closeSessionFactory();
        }
    }
}
