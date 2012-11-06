/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Calendar;

import java.util.List;
import org.hibernate.*;


/**
 *
 * @author jorge
 */
public class CVuelo {
    private static List ListaTipoEst;
    
    
    
    
     public static List<Parametro> llenarComboEstado(){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            q = s.getNamedQuery("ParametrosVuelo");
            q.setParameter("tipo", "ESTADO_VUELO");
            ListaTipoEst = q.list();
            
            
            
            }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            
            s.close();
        }
        
        return ListaTipoEst;
        
    }
     
     
      public static List<Vuelo> BuscarVuelo(Aeropuerto PaisOrigen,Aeropuerto PaisDestino,Calendar fechini, Calendar fechfinal, Parametro Estado) {
         
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Vuelo> ListaVuelos = null;
        
        try {
 
            Query q= s.getNamedQuery("Aero");
           
//        if (isInteger(numEnvio)) {
//                Filter f_numEnvio = s.enableFilter("EnviosXNumEnvio");
//                f_numEnvio.setParameter("idEnvio", Integer.parseInt(numEnvio));
//            }

            if (PaisOrigen != null) {
                Filter f_origen = s.enableFilter("VueloXOrigen");
                f_origen.setParameter("idAeropuerto", PaisOrigen.getIdAeropuerto());
            }

            if (PaisDestino != null) {
                Filter f_destino = s.enableFilter("VueloXDestino");
                f_destino.setParameter("idAeropuerto", PaisDestino.getIdAeropuerto());
            }

            if (fechini != null) {
                Filter f_inisalida = s.enableFilter("VueloXfechini");
                f_inisalida.setParameter("idAeropuerto", fechini);
            }

            if (fechfinal != null) {
                Filter f_finllega = s.enableFilter("VueloXfechfin");
                f_finllega.setParameter("idEstado", fechfinal);
            }

            if (Estado != null) {
                Filter f_estado = s.enableFilter("VueloXEstado");
                f_estado.setParameter("estado", Estado.getIdParametro());
            }

            ListaVuelos = q.list();

          
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return ListaVuelos;      
     
     }
}
