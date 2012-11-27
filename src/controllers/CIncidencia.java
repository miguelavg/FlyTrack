/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author jorge
 */
public class CIncidencia {
    
    public static List<Parametro> ListaTipoInci ;
    
     public static List<Parametro> llenarComboEstado() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "ESTADO_INCIDENCIA");
            ListaTipoInci = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoInci;

    }
     
     public static List<Incidencia> BuscarIncidencia(
                         Parametro TipoIncidencia,
                         Calendar fechini,
                         Calendar fechfin 
                         ) {
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Incidencia> ListaIncidencia = null;
        Date ini = null;
        Date fin = null;
        String inii;

        try {

            if (fechini != null) {
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
                inii = date_format.format(fechini.getTime());
                ini = fechini.getTime();
            }

            if (fechfin != null) {
                fin = fechfin.getTime();
            }


            Query q = s.getNamedQuery("Incidencia");


         

            if (ini != null) {

                Filter f_inisalida = s.enableFilter("IncidenciaXFechaini");
                f_inisalida.setParameter("fechai", ini);
            }

            if (fin != null) {

                Filter f_finllega = s.enableFilter("IncidenciaXFechafin");
                f_finllega.setParameter("fechaf", fin);
            }
 
            if (TipoIncidencia != null) {
                Filter f_estado = s.enableFilter("IncidenciaXTipo");
                f_estado.setParameter("tipo", TipoIncidencia.getIdParametro());
            }

            ListaIncidencia = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return ListaIncidencia;

    }
     public static List<Incidencia> BuscarIncidenciasVuelo(
                         Vuelo vuelo,
                         Calendar fechini,
                         Calendar fechfin 
                         ) {
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Incidencia> ListaIncidencia = null;
        Date ini = null;
        Date fin = null;
        String inii = null;

        try {

            if (fechini != null) {
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
                inii = date_format.format(fechini.getTime());
                ini = fechini.getTime();
            }

            if (fechfin != null) {
                fin = fechfin.getTime();
            }


            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
            Query q = s.getNamedQuery("Incidencia");


         

            if (ini != null) {

                Filter f_inisalida = s.enableFilter("IncidenciaXFechaini");
                f_inisalida.setParameter("fechai", ini);
            }

            if (fin != null) {

                Filter f_finllega = s.enableFilter("IncidenciaXFechafin");
                f_finllega.setParameter("fechaf", fin);
            }
 
            if (vuelo !=null) {
                Filter f_estado = s.enableFilter("IncidenciaXVuelo");
                f_estado.setParameter("idvuelo", vuelo.getIdVuelo());
            }

            ListaIncidencia = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return ListaIncidencia;

    }
     
      public static String validar(
            Aeropuerto aeropuertoOrigen, 
            Aeropuerto aeropuertoDestino,
            Calendar ini,
            Calendar fin,
            Parametro Estado,
            String capacidad,
            String monto) 
      {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        Parametro p;
        try {
             
            if (aeropuertoOrigen == null || 
                aeropuertoDestino == null || 
                ini == null || 
                fin == null || 
                Estado == null || 
                (capacidad == null || capacidad.compareTo("")==0 )  ||
                (monto == null || monto.compareTo("")==0 )) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return error_message;
    }
      
      public static void cargarIncidencia(Incidencia Inc){
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        
        try {
            
            Transaction tx = s.beginTransaction();
              
            int i = (Integer)s.save(Inc);
            
            tx.commit();
            CPista.guardarPista("Administración", "Vuelos - Incidencias", "Crear", Inc.aString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
         
     }
    
}
