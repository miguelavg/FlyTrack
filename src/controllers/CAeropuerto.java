/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Sesion;
import gui.reportes.Movimientoalmacen;
import java.awt.Cursor;
import java.util.List;
import org.hibernate.*;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author miguelavg
 */
public class CAeropuerto {

    static List<Parametro> ListaTipoDoc;
    static List<Parametro> ListaTipoEst;
    static List<Aeropuerto> ListaAeropuerto;

    public static void modificarAeropuerto(
            int idAeropuerto,
            int Capacidad,
            int CapacidadAct,
            Parametro Ciudad,
            int X,
            int Y,
            Parametro Estado,
            String Nombre,
            Parametro Pais) {


        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();



        try {


            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;
            Aeropuerto objAeropuerto = new Aeropuerto();

            objAeropuerto.setCapacidadMax(Capacidad);
            objAeropuerto.setCapacidadActual(CapacidadAct);
            objAeropuerto.setCiudad(Ciudad);
            objAeropuerto.setCoordX(X);
            objAeropuerto.setCoordY(Y);
            objAeropuerto.setEstado(Estado);
            objAeropuerto.setNombre(Nombre);
            objAeropuerto.setPais(Pais);
            objAeropuerto.setIdAeropuerto(idAeropuerto);

            
            CPista.guardarPista("Administración", "Tarifa", "Modificar", new CAeropuerto().BuscarId(idAeropuerto).aString(), objAeropuerto.aString());

            s.update(objAeropuerto);

            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
//      

    }

    public static void agregarAeropuerto(
            int Capacidad,
            int CapacidadAct,
            Parametro Ciudad,
            int X,
            int Y,
            Parametro Estado,
            String Nombre,
            Parametro Pais) {



        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();


        try {
            Transaction tx = s.beginTransaction();
            Query q;


            Aeropuerto objAeropuerto = new Aeropuerto();

            objAeropuerto.setCapacidadMax(Capacidad);
            objAeropuerto.setCapacidadActual(CapacidadAct);
            objAeropuerto.setCiudad(Ciudad);
            objAeropuerto.setCoordX(X);
            objAeropuerto.setCoordY(Y);
            objAeropuerto.setEstado(Estado);
            objAeropuerto.setNombre(Nombre);
            objAeropuerto.setPais(Pais);


            int i = (Integer) s.save(objAeropuerto);

            tx.commit();
            
            CPista.guardarPista("Administración", "Aeropuerto", "Crear", objAeropuerto.aString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }

    public static void cargarAeropuerto(
            Aeropuerto a) {



        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();


        try {
            Transaction tx = s.beginTransaction();
            Query q;

            int i = (Integer) s.save(a);

            tx.commit();
            
            CPista.guardarPista("Administración", "Aeropuerto", "Crear", a.aString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }
//    public void agregarAeropuerto() {
//        SessionFactory sf = Sesion.getSessionFactory();
//        Session s = sf.openSession();
//        
//        try {
//            
//            Transaction tx = s.beginTransaction();
//            Query q;
//            Parametro p;
//            
//            Aeropuerto a = new Aeropuerto();
//            
//            a.setNombre("Arturo Merino Benítez");
//            a.setCapacidadMax(500);
//            a.setCapacidadActual(0);
//            
//            
//            q = s.getNamedQuery("ParametrosXTipoXValorUnico").setFirstResult(0);
//            q.setParameter("valorUnico", "ACTV");
//            q.setParameter("tipo", "ESTADO_AEROPUERTO");
//            p = (Parametro) q.uniqueResult();
//            a.setEstado(p);
//            
//            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
//            q.setParameter("valorUnico", "SANTIAGO");
//            q.setParameter("tipo", "CIUDAD");
//            p = (Parametro) q.uniqueResult();
//            a.setCiudad(p);
//            
//            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
//            q.setParameter("valorUnico", "CHI");
//            q.setParameter("tipo", "PAIS");
//            p = (Parametro) q.uniqueResult();
//            a.setCiudad(p);
//            
//            int i = (Integer)s.save(a);
//            
//            System.out.println(i);
//            
//            tx.commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            s.close();
//        }
//    }
    public static ArrayList<Aeropuerto> GenerarListaAeropuerto2() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<Aeropuerto> listafinal = new ArrayList<Aeropuerto>();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("Aero");

            ListaAeropuerto = q.list();
            listafinal.addAll(ListaAeropuerto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return listafinal;

    }
    public static List<Aeropuerto> GenerarListaAeropuerto() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("Aero");

            ListaAeropuerto = q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaAeropuerto;

    }

    public static Aeropuerto esUsado(String text) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        beans.Aeropuerto aero = null;

        try {

            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("AeroXNombre");
            q.setParameter("nombre", text);

            aero = (beans.Aeropuerto) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return aero;


    }

    public beans.Aeropuerto BuscarId(int id) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        beans.Aeropuerto aero = null;

        try {

            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("AeropuertosxID");
            q.setParameter("idaero", id);

            aero = (beans.Aeropuerto) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return aero;
    }

    public static beans.Aeropuerto BuscarNombre(String nombre) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        beans.Aeropuerto aero = null;

        try {

            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("AeroXNombres");
            q.setParameter("aero", nombre);

            aero = (beans.Aeropuerto) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return aero;
    }

    public static List<Parametro> llenarComboPais() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "PAIS");
            ListaTipoDoc = q.list();

            for (Parametro tipoDoc : ListaTipoDoc) {
                tipoDoc.getHijos().size();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoDoc;

    }

    public static List<Aeropuerto> BuscarAeropuerto(
            Parametro Pais,
            Parametro Ciudad,
            Parametro Estado,
            int idActual) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Aeropuerto> ListaAeropuertos;

        try {
            Transaction tx = s.beginTransaction();
            Query q;

            if (idActual > 0) {
                q = s.getNamedQuery("AeroTarifa");
                q.setParameter("idActual", idActual);
            } else {
            q = s.getNamedQuery("Aero");
            }

            if (Pais != null) {
                Filter f = s.enableFilter("AeropuertoxPais");
                f.setParameter("Pais", Pais.getIdParametro());
            }
            if (Ciudad != null) {
                Filter f2 = s.enableFilter("AeropuertoxCiudad");
                f2.setParameter("Ciudad", Ciudad.getIdParametro());

            }

            if (Estado != null) {
                Filter f4 = s.enableFilter("AeropuertoxEstado");
                f4.setParameter("Estado", Estado.getIdParametro());

            }


            ListaAeropuertos = q.list();


            return ListaAeropuertos;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return null;

    }

    public static List<Aeropuerto> BuscarAeropuertoXEnvioXFechas(
            Aeropuerto aeropuerto,
            Calendar fechini,
            Calendar fechfinal,
            //int idActual
            Integer idparentrada,
            Integer idparsalida1,
            Integer idparsalida2) {

        Date ini = null;
        Date fin = null;
        String inii = null;

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Aeropuerto> ListaAeropuertos;

        try {
            Transaction tx = s.beginTransaction();
            Query q;

            if (fechini != null) {
                inii = CValidator.formatDate(fechini.getTime());
                ini = fechini.getTime();
            }

            if (fechfinal != null) {
                fin = fechfinal.getTime();
            }

            //if (aeropuerto != null) {
//            Filter f_origen = s.enableFilter("VueloXOrigen");
//            f_origen.setParameter("idOrigen", aeropuerto.getIdAeropuerto());

            q = s.getNamedQuery("AeropuertosxID");
            q.setParameter("idaero", aeropuerto.getIdAeropuerto());
            if (fechini != null) {
                Filter f_vuelos_s = s.enableFilter("VuelosXAeropuertoSalidaAux");
                f_vuelos_s.setParameter("lower", ini);
                f_vuelos_s.setParameter("upper", fin);
                f_vuelos_s.setParameter("idEstado1", idparsalida1);
                f_vuelos_s.setParameter("idEstado2", idparsalida2);
            }
            if (fechfinal != null) {
                Filter f_vuelos_l = s.enableFilter("VuelosXAeropuertoLlegada");
                f_vuelos_l.setParameter("lower", ini);
                f_vuelos_l.setParameter("upper", fin);
                f_vuelos_l.setParameter("idEstado", idparentrada);
            }

            ListaAeropuertos = q.list();

            for (int i = 0; ListaAeropuertos.size() > i; i++) {
                ListaAeropuertos.get(i).getVuelosLlegada().size();
                ListaAeropuertos.get(i).getVuelosSalida().size();
            };

            return ListaAeropuertos;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return null;
    }

    public static List<Parametro> llenarComboEstado() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "ESTADO_AEROPUERTO");
            ListaTipoEst = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoEst;

    }
    
    public class MovimientosOrdenadosAsc implements Comparator<Movimientoalmacen> {

        @Override
        public int compare(Movimientoalmacen e1, Movimientoalmacen e2) {
            if (e1.getFecha().after(e2.getFecha())){
                return -1;
            }
            if (e1.getFecha().before(e2.getFecha())|| e1.getFecha().equals(e2.getFecha())) {
                return 1;
            }
            return 0;
        }
    }
    public class MovimientosOrdenadosDesc implements Comparator<Movimientoalmacen> {

        @Override
        public int compare(Movimientoalmacen e1, Movimientoalmacen e2) {
            if (e1.getFecha().before(e2.getFecha())|| e1.getFecha().equals(e2.getFecha())) {
                return 1;
            }
            if (e1.getFecha().after(e2.getFecha())) {
                return -1;
            }
            return 0;
        }
    }
    
    public List<Movimientoalmacen> OrdenadasAsc(ArrayList<Movimientoalmacen> movimientos) {
        Collections.sort(movimientos, new MovimientosOrdenadosAsc());
        return movimientos;
    }
    
    public List<Movimientoalmacen> OrdenadasDesc(ArrayList<Movimientoalmacen> movimientos) {
        Collections.sort(movimientos, new MovimientosOrdenadosDesc());
        return movimientos;
    }
}
