/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Tarifa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author jugox
 */
public class CTarifa {
    
    public List<Parametro> ListarMonedas(){
         SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Parametro> Lista;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("ParametrosXTipo");
            q.setParameter("tipo", "TIPO_MONEDA");
            Lista = q.list();
            if (Lista.size()>0){
               return Lista; 
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
    
    public List<Parametro> ListarEstadoMonedas(){
         SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Parametro> Lista;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("ParametrosXTipo");
            q.setParameter("tipo", "ESTADO_TARIFA");
            Lista = q.list();
            if (Lista.size()>0){
               return Lista; 
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
    
    public void agregarTarifa(Aeropuerto AeroOri,Aeropuerto AeroDes, String monto, Parametro Moneda, Parametro Estado,String FechaAct, String FechaDes)
    {
                    
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;
            
            Tarifa TarifaBE = new Tarifa(); 
//            
            
//            
//            q = s.getNamedQuery("ParametrosXTipoValorUnico").setFirstResult(0);
//            q.setParameter("tipo", "CIUDAD");
//            q.setParameter("valorUnico", ""+Ciudad.toString()+"");
//            pCiudad = (Parametro) q.uniqueResult();
//            
//            q = s.getNamedQuery("ParametrosXTipoValorUnico").setFirstResult(0);
//            q.setParameter("tipo", "PAIS");
//            q.setParameter("valorUnico", ""+Pais.toString()+"");
//            pPais = (Parametro) q.uniqueResult();
            
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
            
            Date fechaact = null;
            Date fechades=null;
            try {

                fechaact = formatoDelTexto.parse(FechaAct);
                fechades = formatoDelTexto.parse(FechaDes);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            
            
            TarifaBE.setOrigen(AeroOri);
            TarifaBE.setDestino(AeroDes);
            TarifaBE.setMoneda(Moneda);
            TarifaBE.setEstado(Estado);
            TarifaBE.setMonto(Double.parseDouble(monto));
            TarifaBE.setFechaActivacion(fechaact);
            TarifaBE.setFechaDesactivacion(fechades);
                        
            int i = (Integer)s.save(TarifaBE);
            
            tx.commit();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
    }
    
}
