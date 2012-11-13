/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;

import java.util.List;
import org.hibernate.*;

/**
 *
 * @author jorge
 */
public class CVuelo {

    private static List ListaTipoEst;

    public static List<Parametro> llenarComboEstado() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "ESTADO_VUELO");
            ListaTipoEst = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoEst;

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
    
     public static void cargarVuelo(Vuelo v){
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        
        try {
            
            Transaction tx = s.beginTransaction();
              
            int i = (Integer)s.save(v);
            
            tx.commit();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
         
     }
      public static List<Vuelo> BuscarVuelo(Aeropuerto PaisOrigen,Aeropuerto PaisDestino,Calendar fechini, Calendar fechfinal, Parametro Estado) {
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Vuelo> ListaVuelos = null;
        Date ini = null;
        Date fin = null;
        String inii = null;

        try {

            if (fechini != null) {
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
                inii = date_format.format(fechini.getTime());
                ini = fechini.getTime();
            }

            if (fechfinal != null) {
                fin = fechfinal.getTime();
            }


            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
            Query q = s.getNamedQuery("Volar");



//        if (isInteger(numEnvio)) {
//                Filter f_numEnvio = s.enableFilter("EnviosXNumEnvio");
//                f_numEnvio.setParameter("idEnvio", Integer.parseInt(numEnvio));
//            }

            if (PaisOrigen != null) {
                Filter f_origen = s.enableFilter("VueloXOrigen");
                f_origen.setParameter("idOrigen", PaisOrigen.getIdAeropuerto());
            }

            if (PaisDestino != null) {
                Filter f_destino = s.enableFilter("VueloXDestino");
                f_destino.setParameter("idDestino", PaisDestino.getIdAeropuerto());
            }

            if (fechini != null) {

//                ini = new Date();
//                ini.setDate(fechini.get(0));
//                ini.setMonth(fechini.get(1));
//                ini.setYear(fechini.get(2));
//                ini = formatoDelTexto.
                Filter f_inisalida = s.enableFilter("VueloXfechini");
                f_inisalida.setParameter("fechasalida", ini);
            }

            if (fechfinal != null) {
//                fin = new Date();
//                fin.setDate(fechfinal.get(0));
//                fin.setMonth(fechfinal.get(1));
//                fin.setYear(fechfinal.get(2));
                Filter f_finllega = s.enableFilter("VueloXfechfin");
                f_finllega.setParameter("fechallegada", fin);
            }
 
            if (Estado != null) {
                Filter f_estado = s.enableFilter("VueloXEstado");
                f_estado.setParameter("estado", Estado.getIdParametro());
            }
            
            ListaVuelos = q.list();
            
            for (int i=0; ListaVuelos.size()>i; i++){
                ListaVuelos.get(i).getIncidencias().size();
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return ListaVuelos;

    }

    public static void modificarVuelo(
            
            String idVuelo,
            Aeropuerto aeropuertoOrigen, 
            Aeropuerto aeropuertoDestino,
            Calendar ini,
            Calendar fin,
            Parametro Estado,
            String capacidad,
            String monto
            ) {


        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();


        try {
            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;
            Vuelo objVuelo = new Vuelo();

            objVuelo.setIdVuelo(Integer.parseInt(idVuelo)); 
            objVuelo.setOrigen(aeropuertoOrigen);
            objVuelo.setDestino(aeropuertoDestino);
            objVuelo.setFechaLlegada(ini.getTime());
            objVuelo.setFechaSalida(fin.getTime());
            objVuelo.setEstado(Estado);
            objVuelo.setCapacidadMax(Integer.parseInt(capacidad));
            objVuelo.setAlquiler(Double.valueOf(monto));
        
//            objVuelo.setPais(Pais);
//            objVuelo.setIdAeropuerto(idAeropuerto);

            
            /*
             *  int idVuelo,
            Aeropuerto aeropuertoOrigen, 
            Aeropuerto aeropuertoDestino,
            Calendar ini,
            Calendar fin,
            Parametro Estado,
            String capacidad
             */
            s.update(objVuelo);

            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
//      

    }

    public static void modificarVueloEstado(
            
           Vuelo objVuelo,
           String Estado
            ) {


        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();


        try {
            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;
            Parametro p = CParametro.buscarXValorUnicoyTipo("ESTADO_VUELO", Estado);
            objVuelo.setEstado(p);
            
            
            if(p.getValorUnico().equals("FIN")){
                for(Escala e : objVuelo.getEscalas()){
                    String valUnico;
                    if(objVuelo.getDestino().getIdAeropuerto() == e.getEnvio().getDestino().getIdAeropuerto()){
                        valUnico = "XREC";
                    } else {
                        valUnico = "ESC";
                    }
                          
                    p = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", valUnico);
                    e.getEnvio().setActual(objVuelo.getDestino());
                    
                    int cActual = e.getEnvio().getActual().getCapacidadActual();
                    e.getEnvio().getActual().setCapacidadActual(cActual + e.getEnvio().getNumPaquetes());
                    e.getEnvio().setEstado(p);
                }
            }
            
            if(p.getValorUnico().equals("VUE")){
                for(Escala e : objVuelo.getEscalas()){                       
                    p = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", "VUE");
                    int cActual = e.getEnvio().getActual().getCapacidadActual();
                    e.getEnvio().getActual().setCapacidadActual(cActual - e.getEnvio().getNumPaquetes());
                    e.getEnvio().setActual(null);
                    e.getEnvio().setEstado(p);
                }
            }
            
            if(p.getValorUnico().equals("CAN") || p.getValorUnico().equals("RET")){
                for(Escala e : objVuelo.getEscalas()){                       
                    CEnvio cenvio = new CEnvio();
                    String error = cenvio.calcularRuta(e.getEnvio());
                    
                    if(error == null || error.isEmpty()){
                        p = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", "IND");
                        e.getEnvio().setEstado(p);
                    }
                }
            }
        
//            objVuelo.setPais(Pais);
//            objVuelo.setIdAeropuerto(idAeropuerto);

            
            /*
             *  int idVuelo,
            Aeropuerto aeropuertoOrigen, 
            Aeropuerto aeropuertoDestino,
            Calendar ini,
            Calendar fin,
            Parametro Estado,
            String capacidad
             */
            s.update(objVuelo);

            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
//      

    }
    
    public static void agregarVuelo(
           
            Aeropuerto aeropuertoOrigen, 
            Aeropuerto aeropuertoDestino,
            Calendar ini,
            Calendar fin,
            Parametro Estado,
            String capacidad,
            String monto) {



        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();


        try {
            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;


              Vuelo objVuelo = new Vuelo();

     //       objVuelo.setIdVuelo(idVuelo); 
            objVuelo.setOrigen(aeropuertoOrigen);
            objVuelo.setDestino(aeropuertoDestino);
            objVuelo.setFechaLlegada(ini.getTime());
            objVuelo.setFechaSalida(fin.getTime());
            objVuelo.setEstado(Estado);
            objVuelo.setCapacidadMax(Integer.parseInt(capacidad));
            objVuelo.setAlquiler(Double.valueOf(monto));


            int i = (Integer) s.save(objVuelo);

            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }
}
