/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Contrasena;
import beans.seguridad.Permiso;
import beans.seguridad.Usuario;
import java.util.Arrays;
import java.util.List;
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
    
    public static Usuario verificarContrasenia(String user, char[] pass){

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try{
            
            //-Existe usuario
            //-Usuario activo
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
            q.setParameter("login", user);
            Usuario usuario = (Usuario)q.uniqueResult();
            
            if(usuario == null) return null; //si el usuario no existe
            
            usuario.getContrasenias().size();
            List<Contrasena> contrasenias = usuario.getContrasenias();
            boolean findPassActiva = Boolean.FALSE;
            for(Contrasena passAnalizada : contrasenias){
                if(passAnalizada.getEstado().getValorUnico().equals("ACTV")){
                    findPassActiva = Boolean.TRUE;
                     usuario.getPerfil().getPermisos().size();//para jalar en el query los permisos
                    break;
                }
            }
            
            return findPassActiva ? usuario : null;
            
        }
        catch(Exception e){
            System.out.println("CSeguridad.verificarContrasenia - ERROR: " + e.getMessage());
        }
        finally {
            System.out.println("CSeguridad.verificarContrasenia - INFO: Transaccion Terminada");
            s.close();
        }
        return null;
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
        System.out.println("CSeguridad.passwordCorrecta - INFO: resultado - " + correcto);
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
    
    public static boolean validarPermiso(int nivel, String nombreAccionPadre, String nombreAccion, List<Permiso> permisos){
        for(Permiso permiso : permisos){
            boolean verificarNivel = permiso.getAccion().getNivel() == nivel;
            boolean verificarAccion = permiso.getAccion().getNombre().equals(nombreAccion);
            boolean verificarAccionPadre;
            if(nombreAccionPadre == null){
                verificarAccionPadre = Boolean.TRUE;
            }
            else{
                if(permiso.getAccion().getPadre() != null){
                    verificarAccionPadre = permiso.getAccion().getPadre().getNombre().equals(nombreAccionPadre);
                }
                else{
                    verificarAccionPadre = Boolean.FALSE;
                }
            }
            
            if(verificarNivel && verificarAccion && verificarAccionPadre) return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
