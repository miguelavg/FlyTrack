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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author joao
 */
public class CContrasena {

    //Manolin
    public static void actualizarFechaUltimoUso(Contrasena passIngresada) {
        Session s = Sesion.openSessionFactory();
        
        try{
            Transaction tx = s.beginTransaction();
            passIngresada.setFechaUltimoUso(new Date());
            s.update(passIngresada);
            tx.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());            
        } finally{
            s.close();
            Sesion.closeSessionFactory();
        }
    }
    
    public void agregarContrasena(char[] contrasena, Usuario usuario, Parametro estado){

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Transaction tx = s.beginTransaction();

            Contrasena CContrasena = new Contrasena();

            CContrasena.setText(encriptarContrasena(contrasena));
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
    
    //Manolin
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
    
    //Manolin
    public static boolean desactivarContrasena(Contrasena contrasena){
        Session s = Sesion.openSessionFactory();
        
        try{
            Transaction tx = s.beginTransaction();
            Parametro paramContrasenaInactiva = CParametro.buscarXValorUnicoyTipo("ESTADO_CONTRASENIA", "INCTV");
            contrasena.setEstado(paramContrasenaInactiva);
            s.update(contrasena);
            tx.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
            return true;
        }
    }
    
    //Manolin
    public static boolean agregarContrasenaActiva(char[] contrasenaNueva, Usuario usuario){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            Parametro paramContrasenaActiva = CParametro.buscarXValorUnicoyTipo("ESTADO_CONTRASENIA", "ACTV");
            
            Contrasena contrasena = new Contrasena();
            
            contrasena.setText(encriptarContrasena(contrasenaNueva));
            contrasena.setEstado(paramContrasenaActiva);
            Calendar cal = Calendar.getInstance();
            contrasena.setFechaActivacion(cal.getTime());
            contrasena.setFechaCaducidad(calcularCaducidad(cal));
            contrasena.setFechaUltimoUso(null);
            contrasena.setUsuario(usuario);
            
            s.save(contrasena);
            tx.commit();
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
            return true;
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
    
    public Contrasena buscarContrasenaActivaPorUsuario(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Contrasena Contrasena = new Contrasena();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("ContrasenaActivaXidUsuario");
            q.setParameter("idUsuario", id);
            
            Contrasena=(Contrasena)q.uniqueResult();
            return Contrasena;
            
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        return null;
    }
    
    //Manolin
    private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public static char[] encriptarContrasena(char[] contrasenaAEncriptar){
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(new String(contrasenaAEncriptar).getBytes("UTF-8"));
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString().toCharArray();
        } catch (UnsupportedEncodingException ex) {
            return null; //cuidado cuando sea null
        } catch (NoSuchAlgorithmException e) {
            return null; //cuidado cuando sea null
        }
    }
    
}
