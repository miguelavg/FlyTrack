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

    public static void guardarPista(String mPrincipal, String mSecundario, String clase, String metodo, 
            String eAnterior, String eActual, String descripcion){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            
            Pista pista = new Pista();
            
            Usuario user = Sesion.getUsuario();
            pista.setUsuarioR(user);
            pista.setUsuario(user.getLogIn());
            pista.setModuloPrincipal(mPrincipal);
            pista.setModuloSecundario(mSecundario);
            pista.setClase(clase);
            pista.setMetodo(metodo);
            pista.setFecha(Calendar.getInstance().getTime());
            pista.setEstadoAnterior(eAnterior);
            if(eActual != null) pista.setEstadoActual(eActual);
            if(descripcion != null) pista.setDescripcion(descripcion);
            
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
