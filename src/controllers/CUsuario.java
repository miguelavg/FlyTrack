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

    
    public Usuario agregarUsuarioNuevo(Perfil perfil, Aeropuerto aeropuerto, String LogIn,
                                        Parametro estado,String Nombre,String Apellidos, String correo, 
                                        String telefono, String NumeroDoc, Parametro TipoDoc, Parametro Ciudad, Parametro Pais){
        
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();

            Usuario CUsuario = new Usuario();

            CUsuario.setPerfil(perfil);
            CUsuario.setIdAeropuerto(aeropuerto);
            CUsuario.setLogIn(LogIn);
            CUsuario.setEstado(estado);
            CUsuario.setNumAcceso(0);
            CUsuario.setPrimerAcceso(false);
            
            
            CUsuario.setTipoDoc(TipoDoc);
            CUsuario.setApellidos(Apellidos);
            CUsuario.setNombres(Nombre);
            CUsuario.setNumDoc(NumeroDoc);
            CUsuario.setTelefono(telefono);
            CUsuario.seteMail(correo);
            CUsuario.setCiudad(Ciudad);
            CUsuario.setPais(Pais);

            int i = (Integer) s.save(CUsuario);
            tx.commit();
            
            CPista.guardarPista("Seguridad", "Usuario", "Crear", CUsuario.aString());
            
            return CUsuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

    }

    public static List<Usuario> buscar(Perfil perfil, Aeropuerto aeropuerto, Parametro tipodoc, String nombre, String apellido, String numdoc, String login, Parametro estado) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        List<Usuario> usuarios = null;

        try {
            Query q = s.getNamedQuery("Usuario");
            
            if(perfil != null){
                Filter f_perfil = s.enableFilter("UsuarioxIdperfil");
                f_perfil.setParameter("idperfil", perfil.getIdPerfil());
            }
            
            if(aeropuerto != null){
                Filter f_aeropuerto = s.enableFilter("UsuarioxIdaeropuerto");
                f_aeropuerto.setParameter("idaeropuerto", aeropuerto.getIdAeropuerto());
            }
            
            if(tipodoc != null){
                Filter f_tipodoc = s.enableFilter("UsuarioxTipoDoc");
                f_tipodoc.setParameter("tipodoc", tipodoc.getIdParametro());
            }
            
            if(nombre != null && !nombre.isEmpty()){
                Filter f_nombre = s.enableFilter("UsuarioxNombre");
                f_nombre.setParameter("nombres", "%" + nombre + "%");
            }
            
            if(apellido != null && !apellido.isEmpty()){
                Filter f_apellidos = s.enableFilter("UsuarioxApellido");
                f_apellidos.setParameter("apellidos", "%" + apellido + "%");
            }
            
            if(numdoc != null && !numdoc.isEmpty()){
                Filter f_numdoc = s.enableFilter("UsuarioxNumDoc");
                f_numdoc.setParameter("numdoc", "%" + numdoc + "%");
            }
            
            if(login != null && !login.isEmpty()){
                Filter f_login = s.enableFilter("UsuarioxLogin");
                f_login.setParameter("login", "%" + login + "%");
            }
            
            if(estado != null){
                Filter f_estado = s.enableFilter("UsuarioxEstado");
                f_estado.setParameter("estado", estado.getIdParametro());
            }            
            
            usuarios = q.list();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return usuarios;
    }

    public List<Usuario> Buscar(Perfil perfil, Aeropuerto aeropuerto, String nombre, Parametro Estado) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Usuario");


            if (perfil != null) {
                Filter f = s.enableFilter("UsuarioxIdperfil");
                f.setParameter("idperfil", perfil.getIdPerfil());
            }

            if (aeropuerto != null) {
                Filter f2 = s.enableFilter("UsuarioxIdaeropuerto");
                f2.setParameter("idaeropuerto", aeropuerto.getIdAeropuerto());
            }

            if (!nombre.trim().equals("")) {
//                Filter f3 = s.enableFilter("UsuarioxIdcliente");
//                f3.setParameter("idcliente",cliente.getIdCliente());
//                
                Filter f3 = s.enableFilter("UsuarioxNombre");
                f3.setParameter("nombres", nombre);

            }

            //&& Estado.getIdParametro()!=0
            if (Estado != null) {
                Filter f5 = s.enableFilter("UsuarioxEstado");
                f5.setParameter("estado", Estado.getIdParametro());
            }

            return (List<Usuario>) q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return null;
    }

    public Usuario BuscarXid(int id) {
        Session s = Sesion.openSessionFactory();
        Usuario CUsuario = new Usuario();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("UsuarioxId").setMaxResults(1);
            q.setParameter("idusuario", id);
            CUsuario = (Usuario) q.uniqueResult();
            CUsuario.getContrasenias().size();//LAZY QUERY
            CUsuario.getPerfil().getPermisos().size();//LAZY QUERY
            return CUsuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

        return null;
    }
    
    public Usuario modificarUsuario(Integer idUsuario,Perfil perfil, Aeropuerto aeropuerto, String LogIn,
                                Parametro estado,String Nombre, String Apellidos, String correo, 
                                String telefono,String NumeroDoc, Parametro TipoDoc, Parametro Ciudad, 
                                Parametro Pais){
        
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();
            
            Usuario usuario = new CUsuario().BuscarXid(idUsuario);
            
            usuario.setIdUsuario(idUsuario);
            usuario.setPerfil(perfil);
            usuario.setIdAeropuerto(aeropuerto);
            usuario.setLogIn(LogIn);
            usuario.setEstado(estado);
            
            usuario.setTipoDoc(TipoDoc);
            usuario.setApellidos(Apellidos);
            usuario.setNombres(Nombre);
            usuario.setNumDoc(NumeroDoc);
            usuario.setTelefono(telefono);
            usuario.seteMail(correo);
            usuario.setCiudad(Ciudad);
            usuario.setPais(Pais);

            s.update(usuario);
            tx.commit();
            return usuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

    }
      
    public String validar(String login_actual,Integer idusuario, boolean isNuevo, String aeropuerto, String logIn,Parametro estado, Perfil perfil) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        Usuario U;
        try {

            if (aeropuerto.isEmpty()||  logIn.isEmpty() ||  estado==null || perfil==null ) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }
            
            else {
                Query q;

                if (!login_actual.equals(logIn) ){

                q = s.getNamedQuery("Usuario");
                Filter f = s.enableFilter("UsuarioxLogin");
                f.setParameter("login",logIn);

                List<Usuario> ListaUsuarios;

                ListaUsuarios= q.list();
                s.disableFilter("UsuarioxLogin");

                     if (ListaUsuarios.size() > 0) {
                         U = ListaUsuarios.get(0);
                         if (U!=null){
                             error_message = error_message + CValidator.buscarError("ERROR_FT006") + "\n";
                         }
                     }                

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
    public static Usuario buscarXNombreUsuario(String username) {
        //Si no lo encuentra te devuelve null
        Session s = Sesion.openSessionFactory();
        try {
            Query q = s.getNamedQuery("UsuarioxNombreUsuario").setMaxResults(1);
            q.setParameter("username", username);
            Usuario user = (Usuario) q.uniqueResult();
            if (user != null) {
                user.getContrasenias().size();
                //user.getPerfil().getPermisos().size(); <-- no es necesario pues solo se usara para el Olvido Contrasenia
            }
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

        return null;
    }

    public static Usuario buscarXNumDocumento(String documento) {
        Session s = Sesion.openSessionFactory();
        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("UsuarioxNumDoc").setMaxResults(1);
            q.setParameter("documento", documento);
            Usuario user = (Usuario) q.uniqueResult();
            user.getContrasenias().size();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

        return null;
    }

    public String ValidarDocumento(Parametro tipodoc, String numero) {
        String error = "";

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Usuario usuario = null;

        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("UsuarioxIdentidad").setMaxResults(1);
            q.setParameter("tipodoc", tipodoc);
            q.setParameter("numdoc", numero);

            usuario = (Usuario) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        if (usuario != null) {
            error = "Ya existe un usuario registrado con ese número de documento";
        }

        return error;
    }

    public static Usuario inicializarAccesos(Usuario usuario) {
        //Actualiza los acceso a inmediatamente despues del primer acceso donde cambia la contrasena
        Session s = Sesion.openSessionFactory();
        try {
            Transaction tx = s.beginTransaction();
//            usuario.setNumAcceso(1);
            usuario.setPrimerAcceso(true);
            s.update(usuario);
            tx.commit();
            return usuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return usuario;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }
    }

    public static Usuario incrementarAccesos(Usuario usuario) {
        Session s = Sesion.openSessionFactory();
        try {
            Transaction tx = s.beginTransaction();
            usuario.setNumAcceso(usuario.getNumAcceso() + 1);
            s.update(usuario);
            tx.commit();
            return usuario;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return usuario;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }
    }

    public static Usuario actualizarUsuario(Usuario usuario){
        Session s = Sesion.openSessionFactory();
        try{
            
            Query q = s.getNamedQuery("UsuarioxId").setMaxResults(1);
            q.setParameter("idusuario", usuario.getIdUsuario());
            usuario = (Usuario)q.uniqueResult();
            usuario.getContrasenias().size();
            usuario.getPerfil().getPermisos().size();
            return usuario;
        } catch (Exception e){
            return usuario;
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }
    }
}
