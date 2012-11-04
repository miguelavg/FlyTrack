/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
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

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try{
            
            //-Existe usuario
            //-Usuario activo
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
            q.setParameter("login", user);
            Usuario usuario = (Usuario)q.uniqueResult();
            
            if(usuario == null) return Boolean.FALSE; //si el usuario no existe
            
            //-Existe contrasenia
            //-Contrasenia activa
            Query q2 = s.getNamedQuery("ContraseniaActivaXUsuario").setMaxResults(1);
            q2.setParameter("usuario", usuario);
            Contrasena contrasenaActiva = (Contrasena)q2.uniqueResult();
            
            if(contrasenaActiva != null && passwordCorrecta(contrasenaActiva.getText(), pass)) 
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
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try{
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("ParametrosSeguridad").setMaxResults(1);
            q.setParameter("valorUnico", "NUM_INTENTOS_FALLIDOS");
            Parametro p = (Parametro) q.uniqueResult();
            return Integer.parseInt(p.getValor());
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
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try{
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Usuario");
            Filter f = s.enableFilter("UsuarioxLogin");
            f.setParameter("login", usuario);
            Usuario usuarioAux = (Usuario)q.uniqueResult(); 
            
            if( usuarioAux != null && 
                usuarioAux.getEstado().getValorUnico().equals("ACTV")){
                q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
                q.setParameter("tipo", usuarioAux.getEstado().getTipo());
                q.setParameter("valorUnico", "INCTV");
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
