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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

    public static Usuario verificarContrasenia(String user, char[] pass) {

        Session s = Sesion.openSessionFactory();

        try {

            //-Existe usuario
            //-Usuario activo
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("LoginUsuario").setMaxResults(1);
            q.setParameter("login", user);
            Usuario usuario = (Usuario) q.uniqueResult();

            if (usuario == null) {
                return null; //si el usuario no existe
            }
            usuario.getContrasenias().size();//LAZY QUERY: obtener las contrasenias
            List<Contrasena> contrasenias = usuario.getContrasenias();
            Contrasena passActiva = null;
            for (Contrasena passAnalizada : contrasenias) {
                if (passAnalizada.getEstado().getValorUnico().equals("ACTV")) {
                    passActiva = passAnalizada;
                    break;
                }
            }

            if (passActiva == null) {
                return null; //si el usuario no tiene password activa
            }
            if (!passwordCorrecta(passActiva.getText(), CContrasena.encriptarContrasena(pass))) {
                return null; //si la password no coincide
            }
            //Si el usuario tiene contrasenia activa y justa esa coincide con la ingresada
            usuario.getPerfil().getPermisos().size();//LAZY QUERY: obtener los permisos
            return usuario;
        } catch (Exception e) {
            System.out.println("CSeguridad.verificarContrasenia - ERROR: " + e.getMessage());
        } finally {
            System.out.println("CSeguridad.verificarContrasenia - INFO: Transaccion Terminada");
            s.close();
            Sesion.closeSessionFactory();
        }
        return null;
    }

    public static boolean passwordCorrecta(char[] passBD, char[] passRead) {
        boolean correcto = Boolean.TRUE;
        if (passBD.length != passRead.length) {
            correcto = Boolean.FALSE;
        } else {
            correcto = Arrays.equals(passRead, passBD);
        }
        //Arrays.fill(passBD, '0');
        System.out.println("CSeguridad.passwordCorrecta - INFO: resultado - " + correcto);
        return correcto;
    }

    public static Contrasena getContrasenaActiva(int idUsuario) {
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("ContrasenaActivaXidUsuario").setMaxResults(1);
            q.setParameter("idUsuario", idUsuario);
            return (Contrasena) q.uniqueResult();
        } catch (Exception e) {
            System.out.println("CSeguridad.getContrasenaActiva - ERROR: " + e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

        return null;
    }

    public static int getMaxIntentosFallidos() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("ParametrosSeguridad").setMaxResults(1);
            q.setParameter("valorUnico", "NUM_INTENTOS_FALLIDOS");
            Parametro p = (Parametro) q.uniqueResult();
            return Integer.parseInt(p.getValor());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return 0;
    }

    public static void bloquearCuenta(String usuario) {
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("Usuario");
            Filter f = s.enableFilter("UsuarioxLogin");
            f.setParameter("login", usuario);
            Usuario usuarioAux = (Usuario) q.uniqueResult();

            if (usuarioAux != null && 
                usuarioAux.getEstado().getValorUnico().equals("ACTV")) {
                q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
                q.setParameter("tipo", usuarioAux.getEstado().getTipo());
                q.setParameter("valorUnico", "INCTV");
                Parametro p = (Parametro) q.uniqueResult();
                usuarioAux.setEstado(p);

                s.update(usuarioAux);
                tx.commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            Sesion.openSessionFactory();
        }
    }

    public static boolean validarPermiso(int nivel, String nombreAccionPadre, String nombreAccion, List<Permiso> permisos) {
//        return true;
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

    public static char[] generaContraseniaAleatoria() {
        int num_min_car_num = Integer.parseInt(CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_NUM").getValor());
        int num_min_car_mayus = Integer.parseInt(CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MAYUS").getValor());
        int num_min_car_minus = Integer.parseInt(CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MINUS").getValor());
        int num_min_car_esp = Integer.parseInt(CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_ESP").getValor());
        
        ArrayList<Character> contrasenaNueva = new ArrayList<Character>();
        
        Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
        
        for(int i=0; i < num_min_car_num; i++){
            char valor = (char)('0' + rnd.nextInt('9' - '0' + 1));
            contrasenaNueva.add(valor);
        }
        
        for(int i=0; i < num_min_car_mayus; i++){
            char valor = (char)('A' + rnd.nextInt('Z' - 'A' + 1));
            contrasenaNueva.add(valor);
        }
        
        for(int i=0; i < num_min_car_minus; i++){
            char valor = (char)('a' + rnd.nextInt('z' - 'a' + 1));
            contrasenaNueva.add(valor);
        }
        
//        char[] car_esp = new char[]{'!','@','#','$','%','^','&','*','(',')','_',
//                                    '+','-','=','{','}','|','[',']',':',';',
//                                    '<','>','?',',','.','/','~','¡',
//                                    '?','°','¬'};
        char[] car_esp = new char[]{'!','@','#','$','%','^','&','*','(',')'};
        for(int i=0; i < num_min_car_esp; i++){
            char valor = car_esp[rnd.nextInt(car_esp.length)];
            contrasenaNueva.add(valor);
        }
        
        Collections.shuffle(contrasenaNueva);
        
        Character[] aux = contrasenaNueva.toArray(new Character[contrasenaNueva.size()]);
        char[] passNueva = new char[aux.length];
        for(int i=0; i < aux.length; i++){
            passNueva[i] = aux[i].charValue();
        }
        return passNueva;
        
    }

    public static List<Contrasena> getUltimasContrasenasXUsuario(int limite, int idUsuario) {
        Session s = Sesion.openSessionFactory();

        try {
            Transaction tx = s.beginTransaction();
            Query q = s.getNamedQuery("UltimasContrasenasXUsuarioParaValidar").setMaxResults(limite);
            q.setParameter("idUsuario", idUsuario);
            return (List<Contrasena>) q.list();

        } catch (Exception e) {
            System.out.println("CSeguridad.getUltimasContrasenasXUsuario - ERROR: " + e.getMessage());
        } finally {
            s.close();
            Sesion.closeSessionFactory();
        }

        return null;
    }

    public static boolean validarContrasena(char[] contrasenaAValidar, int idUsuario) {
        Parametro condicion;
        int count;

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_LONG_MINIMA");
        if (contrasenaAValidar.length < Integer.parseInt(condicion.getValor())) {
            return false;
        }

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_NUM");
        count = 0;
        for (char car : contrasenaAValidar) {
            if (Character.isDigit(car)) {
                count++;
            }
        }
        if (count < Integer.parseInt(condicion.getValor())) {
            return false;
        }

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MAYUS");
        count = 0;
        for (char car : contrasenaAValidar) {
            if (Character.isUpperCase(car)) {
                count++;
            }
        }
        if (count < Integer.parseInt(condicion.getValor())) {
            return false;
        }

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MINUS");
        count = 0;
        for (char car : contrasenaAValidar) {
            if (Character.isLowerCase(car)) {
                count++;
            }
        }
        if (count < Integer.parseInt(condicion.getValor())) {
            return false;
        }

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_ESP");
        count = 0;
        for (char car : contrasenaAValidar) {
            if (!Character.isDigit(car) && !Character.isLetter(car)) {
                count++;
            }
        }
        if (count < Integer.parseInt(condicion.getValor())) {
            return false;
        }

        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_CONT_HIST");
        List<Contrasena> contrasenasAContrastar = CSeguridad.getUltimasContrasenasXUsuario(Integer.parseInt(condicion.getValor()), idUsuario);
        String passAValidar = new String(contrasenaAValidar);

        if (contrasenasAContrastar != null) {
            for (Contrasena contrasenaAContrastar : contrasenasAContrastar) {
                String passAContrastar = new String(contrasenaAContrastar.getText());
                if (passAContrastar.equals(passAValidar)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean validarContrasenaHist(char[] contrasenaAValidar, int idUsuario) {

        Parametro condicion;
        boolean valor = true;
        condicion = CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_CONT_HIST");
        List<Contrasena> contrasenasAContrastar = CSeguridad.getUltimasContrasenasXUsuario(Integer.parseInt(condicion.getValor()), idUsuario);
        String passAValidar = new String(contrasenaAValidar);
        for (Contrasena contrasenaAContrastar : contrasenasAContrastar) {
            String passAContrastar = new String(contrasenaAContrastar.getText());
            if (passAContrastar.equals(passAValidar)) {
                valor = false;
                break;
            } else {
                valor = true;
            }
        }

        return valor;

    }
}
