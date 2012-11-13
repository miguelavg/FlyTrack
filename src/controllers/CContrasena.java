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
import java.util.List;
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
            CContrasena.setFechaActivacion(new Date());
            CContrasena.setFechaCaducidad(calcularCaducidad(Calendar.getInstance()));
            
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
    
    public void desactivarUltimaContrasena(Contrasena contrasena, Parametro estado){

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession( );
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            Contrasena CContrasena = new Contrasena();
            CContrasena.setIdContrasena(contrasena.getIdContrasena());
            CContrasena.setUsuario(contrasena.getUsuario());
            CContrasena.setText(contrasena.getText());
            CContrasena.setFechaActivacion(contrasena.getFechaActivacion());
            CContrasena.setFechaUltimoUso(contrasena.getFechaUltimoUso());
            CContrasena.setFechaCaducidad(contrasena.getFechaCaducidad());
            CContrasena.setEstado(estado);
            
            s.update(CContrasena);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
    }
    
    public List<Contrasena> buscarContrasena(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Contrasena> listContrasenas = null;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("ContrasenaxId");
            q.setParameter("idusuario", id);
            
            listContrasenas=q.list();
            
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        return listContrasenas;
    }
    
    public static boolean validarContrasena(char[] contrasenaAValidar, int idUsuario){
        Parametro condicion;
        int count;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_LONG_MINIMA");
        if(contrasenaAValidar.length < Integer.parseInt(condicion.getValor()))
            return false;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_NUM");
        count = 0;
        for(char car : contrasenaAValidar){
            if(Character.isDigit(car)) count++;
        }
        if(count < Integer.parseInt(condicion.getValor()))
            return false;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MAYUS");
        count = 0;
        for(char car : contrasenaAValidar){
            if(Character.isUpperCase(car)) count++;
        }
        if(count < Integer.parseInt(condicion.getValor()))
            return false;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MINUS");
        count = 0;
        for(char car : contrasenaAValidar){
            if(Character.isLowerCase(car)) count++;
        }
        if(count < Integer.parseInt(condicion.getValor()))
            return false;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_ESP");
        count = 0;
        for(char car : contrasenaAValidar){
            if(!Character.isDigit(car) && !Character.isLetter(car)) count++;
        }
        if(count < Integer.parseInt(condicion.getValor()))
            return false;
        
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_CONT_HIST");
        List<Contrasena> contrasenasAContrastar = CSeguridad.getUltimasContrasenasXUsuario(Integer.parseInt(condicion.getValor()), idUsuario);
        String passAValidar = new String(contrasenaAValidar);
        for(Contrasena contrasenaAContrastar : contrasenasAContrastar){
            String passAContrastar = new String(contrasenaAContrastar.getText());
            if(passAContrastar.equals(passAValidar)) return false;
        }
        
        return true;
    }
    
}
