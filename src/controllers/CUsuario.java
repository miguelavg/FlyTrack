/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Contrasena;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
/**
 *
 * @author joao
 */


public class CUsuario {
    public void agregarUsuario(Perfil perfil, Aeropuerto aeropuerto,String LogIn,
        Parametro estado,Integer numAcceso, boolean PrimerAcceso,
            String Nombre,String Apellidos, String correo, 
            String telefono,String NumeroDoc, 
            Parametro TipoDoc, Parametro Ciudad, Parametro Pais
            
            ){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            
            Usuario CUsuario = new Usuario();
            
            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            //CUsuario.setIdCliente(cliente);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            CUsuario.setNumAcceso(numAcceso);
            CUsuario.setPrimerAcceso(PrimerAcceso);
            
            
            CUsuario.setTipoDoc(TipoDoc);
            CUsuario.setApellidos(Apellidos);
            CUsuario.setNombres(Nombre);
            CUsuario.setNumDoc(NumeroDoc);
            CUsuario.setTelefono(telefono);
            CUsuario.seteMail( correo);
            CUsuario.setCiudad(Ciudad);
            CUsuario.setPais(Pais);
            
            int i = (Integer)s.save(CUsuario);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }
    
    public List<Usuario> Buscar(Perfil perfil, Aeropuerto aeropuerto,String nombre,Parametro Estado)
    {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Usuario");
           
           
            if (perfil!=null ){
                Filter f = s.enableFilter("UsuarioxIdperfil");
                f.setParameter("idperfil",perfil.getIdPerfil());
            }

            if (aeropuerto!=null ){
                Filter f2 = s.enableFilter("UsuarioxIdaeropuerto");
                f2.setParameter("idaeropuerto",aeropuerto.getIdAeropuerto());
            }

            if (nombre!=null ){
//                Filter f3 = s.enableFilter("UsuarioxIdcliente");
//                f3.setParameter("idcliente",cliente.getIdCliente());
//                
                Filter f3 = s.enableFilter("UsuarioxNombre");
                f3.setParameter("nombres",nombre);
                
            }

            //&& Estado.getIdParametro()!=0
            if (Estado!=null ){
                Filter f5 = s.enableFilter("UsuarioxEstado");
                f5.setParameter("estado",Estado.getIdParametro());
            }
            
            return (List<Usuario>)q.list();
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());            
        }
        finally {
            s.close();
        }
        
        return null;
    }
     
    public Usuario BuscarXid(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
          Usuario CUsuario = new Usuario();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("UsuarioxId").setMaxResults(1);
            q.setParameter("idusuario", id);
            CUsuario=(Usuario)q.uniqueResult();
            return CUsuario;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
     
    public static String ValidarContraseña(char[] contra){
        
        String mensaje="";
        List<Parametro> Condiciones ;
        Condiciones=CParametro.buscar(null, null,"SEGURIDAD",null);
        for (Parametro cond : Condiciones){
            if (cond.getValorUnico().equals("PASS_LONG_MINIMA")){
                if (contra.length<Integer.parseInt(cond.getValor())){
                   mensaje= "Password no tiene la longitud mínima\n";
                }
            }
            if (cond.getValorUnico().equals("PASS_NUM_MINIMO_CAR_ALFANUM")){
                int numlet=0;
                for (int s=0; s<contra.length;s++){
                    Character car=contra[s];
                    
                    if (car.isLetter(car)){
                        numlet++;
                    }
                    
                }
                if (numlet<Integer.parseInt( cond.getValor())){
                    mensaje = "Password no tiene cantidad mínima de letras\n";
                }
            }
            if (cond.getValorUnico().equals("PASS_NUM_MINIMO_CAR_NUM")){
                int numnum=0;
                for (int s=0; s<contra.length;s++){
                    Character car=contra[s];
                    
                    if (car.isDigit(car)){
                        numnum++;
                    }
                    
                }
                if (numnum<Integer.parseInt( cond.getValor())){
                    mensaje = "Password No tiene cantidad mínima de números\n";
                }
            }
            if (cond.getValorUnico().equals("PASS_NUM_MINIMO_CAR_ESP")){
                int numesp=0;
                for (int s=0; s<contra.length;s++){
                    Character car=contra[s];
                    
                    if ((!car.isDigit(car)&& (!car.isLetter(car)))){
                        numesp++;
                    }
                    
                }
                if (numesp<Integer.parseInt( cond.getValor())){
                    mensaje = "Password no tiene cantidad mínima de caracteres especiales\n";
                }
            }
        }
        
        return mensaje;
    }
    public void modificarUsuario(Integer idUsuario,Perfil perfil, Aeropuerto aeropuerto, String LogIn,
                Parametro estado,String Nombre, String Apellidos, String correo, 
            String telefono,String NumeroDoc, 
            Parametro TipoDoc, Parametro Ciudad, Parametro Pais
            
            ){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession( );
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            //Usuario CUsuario = (Usuario)s.load(Usuario.class, idUsuario);\
            Usuario CUsuario = new Usuario();
            CUsuario.setIdUsuario(idUsuario);
            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            //CUsuario.setIdCliente(cliente);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            CUsuario.setNumAcceso(0);
            CUsuario.setPrimerAcceso(false);
            
            CUsuario.setTipoDoc(TipoDoc);
            CUsuario.setApellidos(Apellidos);
            CUsuario.setNombres(Nombre);
            CUsuario.setNumDoc(NumeroDoc);
            CUsuario.setTelefono(telefono);
            CUsuario.seteMail( correo);
            CUsuario.setCiudad(Ciudad);
            CUsuario.setPais(Pais);
            
            s.update(CUsuario);
            tx.commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }
      
    public String validar(Integer idusuario, boolean isNuevo, String aeropuerto, String cliente, String logIn,Parametro estado, Perfil perfil) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        Usuario U;
        Usuario U2;
        try {

            if (idusuario==-1){
            
            if (aeropuerto.isEmpty()|| cliente.isEmpty()|| logIn.isEmpty() ||  estado==null || perfil==null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }
            if (aeropuerto.isEmpty()|| cliente.isEmpty()|| logIn.isEmpty() ||  estado==null || perfil==null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }
            
            else {
           Query q;
           Query q1;
           
           q = s.getNamedQuery("Usuario");
           Filter f = s.enableFilter("UsuarioxLogin");
           f.setParameter("login",logIn);
        
           List<Usuario> ListaUsuarios;
           List<Usuario> ListaAuxUsuarios;
          
           ListaUsuarios= q.list();
           s.disableFilter("UsuarioxLogin");
           
                if (ListaUsuarios.size() > 0) {
                    U = ListaUsuarios.get(0);
                    if (U!=null){
                        error_message = error_message + CValidator.buscarError("ERROR_FT006") + "\n";
                    }
                }
                

           //q = s.getNamedQuery("Usuario");
//           Filter f2 = s.enableFilter("UsuarioxIdcliente");
//           f2.setParameter("idcliente",idcliente);
        
//           q1 = s.getNamedQuery("UsuarioxIdClienteAux");
//           q1.setParameter("idcliente",idcliente );
//           
//           ListaAuxUsuarios= q1.list();
//                
//                if (ListaAuxUsuarios.size() > 0) {
//                    U2 = ListaAuxUsuarios.get(0);
//                    if (U2!=null){
//                        error_message = error_message + CValidator.buscarError("ERROR_FT007") + "\n";
//                    }
//                }
                
            }
            }
            
            if (idusuario!=-1){
            if ( aeropuerto.isEmpty()|| cliente.isEmpty() || logIn.isEmpty() ||  estado==null || perfil==null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return error_message;
    }
     
//    public Usuario BuscarXidCliente(int id){
//        SessionFactory sf = Sesion.getSessionFactory();
//        Session s = sf.openSession();
//        Usuario CUsuario = new Usuario();
//        
//        try {
//            Transaction tx = s.beginTransaction();
//            Query q;
//            q = s.getNamedQuery("UsuarioxIdClienteAux");
//            q.setParameter("idcliente", id);
//            
//            CUsuario=(Usuario)q.list().get(0);
//            //CUsuario=(Usuario)q.uniqueResult();
//            return CUsuario;
//            }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        finally {
//            s.close();
//        }
//        return null;
//    }
    
    public static Usuario buscarXNombreUsuario(String username){
        Session s = Sesion.openSessionFactory();
        try{
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("UsuarioxNombreUsuario").setMaxResults(1);
            q.setParameter("username", username);
            Usuario user = (Usuario)q.uniqueResult();
            user.getContrasenias().size();
            return user;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s.close();
            Sesion.closeSessionFactory();
        }
        
        return null;
    }
}
