/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.seguridad.Contrasena;
import beans.seguridad.Usuario;
import java.util.Arrays;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Filter;

/**
 *
 * @author msolorzano
 */
public class CSeguridad {
    
    public static boolean verificarContrasenia(String user, char[] pass){

        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try{
            
            /*1. Se busca la existencia del nombre del nombre de usuario y de existir
             verificar que este activado*/
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
            q.setParameter("login", user);
            Usuario usuario = (Usuario)q.uniqueResult();
            
            if(usuario == null) return Boolean.FALSE; //si el usuario no existe
            
            /*2. Detectado el usuario se busca su contrasenia activa en este momento
             para verificar si tiene o no alguna activa*/
            Query q2 = s.getNamedQuery("ContraseniaActivaXUsuario").setMaxResults(1);
            q2.setParameter("usuario", usuario);
            Contrasena contrasenaActiva = (Contrasena)q2.uniqueResult();
            
            if(contrasenaActiva != null && passwordCorrecta(contrasenaActiva.getText(), pass)) 
                //si no tiene contrasenia activa o la contrasena activa es diferente a la ingresada
                return Boolean.TRUE;
            else 
                return Boolean.FALSE;
                        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }

        return true;
    }
    
    private static boolean passwordCorrecta(char[] passBD, char[] passRead){
        boolean correcto = Boolean.TRUE;
        if(passBD.length != passRead.length){
            correcto = Boolean.FALSE;
        }
        else{
            correcto = Arrays.equals(passRead, passBD);
        }
        Arrays.fill(passBD, '0');
        return correcto;
    }
    
    public static int getMaxIntentosFallidos(){
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try{
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("ParametrosSeguridad").setMaxResults(1);
            q.setParameter("valorUnico", "NUM_INTENTOS_FALLIDOS");
            Parametro p = (Parametro) q.uniqueResult();
            return Integer.parseInt(p.getValor());
        }
        catch(NumberFormatException nfe){
            System.out.println(nfe.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s.close();
        }
        return 0;
    }
    
    public static void bloquearCuenta(String usuario){
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try{
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Usuario");
            Filter f = s.enableFilter("UsuarioxLogin");
            f.setParameter("login", usuario);
            Usuario usuarioAux = (Usuario)q.uniqueResult(); 
            
            if(usuarioAux.getEstado().getValorUnico().equals("ACTV")){
                q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
                q.setParameter("tipo", usuarioAux.getEstado().getTipo());
                q.setParameter("valorUnico", "INCT");
                Parametro p = (Parametro) q.uniqueResult();
                usuarioAux.setEstado(p);
                
                s.update(usuarioAux);
                tx.commit();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s.close();
        }
    }
}
