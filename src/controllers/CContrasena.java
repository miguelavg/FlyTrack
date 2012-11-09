/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import beans.seguridad.Contrasena;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author joao
 */
public class CContrasena {
    
    
    public void agregarContrasena(char[] contrasena, Usuario usuario, Parametro estado){

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Transaction tx = s.beginTransaction();

            Contrasena CContrasena = new Contrasena();

            CContrasena.setText(contrasena);
            CContrasena.setUsuario(usuario);
            CContrasena.setEstado(estado);

            int i = (Integer)s.save(CContrasena);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
    }
    
    public static Date calcularCaducidad(Calendar calendarioActual){
        Parametro parametro = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_CADUCIDAD_DIAS");
        int diasMover = Integer.parseInt(parametro.getValor());
        calendarioActual.add(Calendar.DATE, diasMover);   
        return calendarioActual.getTime();
    }
    
}
