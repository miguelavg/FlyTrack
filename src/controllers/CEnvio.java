/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author ronald
 */
public class CEnvio {
    public ArrayList<Parametro> llenarCombo(String tipo){
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        ArrayList<Parametro>  p = null;
        try {
            
            
            Query q; 
            q = s.getNamedQuery("ParametrosXTipo");
           
            q.setParameter("tipo", tipo);
            p = (ArrayList<Parametro>) q.list();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return p;
    }
    public Envio agregarEnvio(
            String aOrigen,
            String aDestino,
            String aActual,
            Parametro registrado,
            String remitente,
            String destinatario,
            float monto,
            Parametro moneda,
            int numEnvio,
            int numPaq,
            Parametro docPago,
            int numDocPago,
            float impuesto,        
            String fechaRegistro,
            String fechaRecojo
            ) {
      
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        Envio e = new Envio();
        try {
            
            Transaction tx = s.beginTransaction();
            Query q;
            Parametro p;
            
            Aeropuerto a = null;
            
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aOrigen));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aDestino));
            a = (Aeropuerto) q.uniqueResult();
            e.setDestino(a);
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aActual));
            a = (Aeropuerto) q.uniqueResult();
            e.setActual(a);
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", registrado.getValorUnico());
            q.setParameter("tipo", registrado.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setEstado(p);
            
            q = s.getNamedQuery("ClienteXID").setFirstResult(0);
            q.setParameter("idcliente", Integer.parseInt(remitente));
            e.setRemitente((Cliente) q.uniqueResult());
            
            q = s.getNamedQuery("ClienteXID").setFirstResult(0);
            q.setParameter("idcliente", Integer.parseInt(destinatario));
            e.setDestinatario((Cliente) q.uniqueResult());
            
            e.setMonto(monto*(1+impuesto));
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", moneda.getValorUnico());
            q.setParameter("tipo", moneda.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setMoneda(p);
            
            e.setNumPaquetes(numPaq);
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", docPago.getValorUnico());
            q.setParameter("tipo", docPago.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setTipoDocVenta(p);
            
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaRegistroDate = null;
            Date fechaRecojoDate = null;
            try {

                fechaRegistroDate= formatoDelTexto.parse(fechaRegistro);
                fechaRecojoDate= formatoDelTexto.parse(fechaRecojo);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            e.setFechaRegistro(fechaRegistroDate);
            e.setFechaRecojo(fechaRecojoDate);
            numEnvio = (Integer)s.save(e);
            e.setIdEnvio(numEnvio);
            numDocPago = numEnvio;
            e.setNumDocVenta(numDocPago);
            //s.saveOrUpdate(e);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            s.close();
        }
        return e;
    }
}
