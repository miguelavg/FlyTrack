/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Parametro;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author miguelavg
 */
public class CAeropuerto {
    
    List<Parametro> ListaTipoDoc;
     List<Parametro> ListaTipoEst;
    
    public void agregarAeropuerto() {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        try {
            
            Transaction tx = s.beginTransaction();
            Query q;
            Parametro p;
            
            Aeropuerto a = new Aeropuerto();
            
            a.setNombre("Arturo Merino Benítez");
            a.setCapacidadMax(500);
            a.setCapacidadActual(0);
            
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico").setFirstResult(0);
            q.setParameter("valorUnico", "ACTV");
            q.setParameter("tipo", "ESTADO_AEROPUERTO");
            p = (Parametro) q.uniqueResult();
            a.setEstado(p);
            
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", "SANTIAGO");
            q.setParameter("tipo", "CIUDAD");
            p = (Parametro) q.uniqueResult();
            a.setCiudad(p);
            
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
    }
    
      public List<Parametro> llenarComboPais(){
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "PAIS");
            ListaTipoDoc = q.list();
            
            
            
            }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            
            s.close();
        }
        
        return ListaTipoDoc;
        
    }
      
     public List<Aeropuerto> Buscar() {
                  
     
     }
             
      
      
       public List<Parametro> llenarComboEstado(){
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            
            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "ESTADO_AEROPUERTO");
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
}
