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
    
    public static boolean agregarContrasenaActiva(char[] contrasenaNueva, Usuario usuario){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            Parametro paramContrasenaActiva = CParametro.buscarXValorUnicoyTipo("ESTADO_CONTRASENIA", "ACTV");
            
            Contrasena contrasena = new Contrasena();
            
            contrasena.setText(contrasenaNueva);
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
            return true;
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
    
}
