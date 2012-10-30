/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.util.ArrayList;
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
            Parametro Moneda,
            int numEnvio,
            int numPaq,
            Parametro docPago,
            int numDocPago,
            float impuesto,        
            String fechaRegistro,
            String fechaRocojo
            ) {
      
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        try {
            
            Transaction tx = s.beginTransaction();
            Query q;
            Parametro p;
            Envio e = new Envio();
            
            Aeropuerto a = null;
            
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aOrigen));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aDestino));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aActual));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", registrado.getValorUnico());
            q.setParameter("tipo", "CIUDAD");
            p = (Parametro) q.uniqueResult();
            e.setEstado(p);
            
            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aActual));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", "CHI");
            q.setParameter("tipo", "PAIS");
            p = (Parametro) q.uniqueResult();
            a.setCiudad(p);
            
            int i = (Integer)s.save(a);
            
            System.out.println(i);
            
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return null;
    }
}
