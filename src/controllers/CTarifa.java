/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import beans.Sesion;
import beans.Tarifa;
import beans.TipoCambio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Filter;
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
         SessionFactory sf = Sesion.getSessionFactory();
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
    public String ValidarRuta(Aeropuerto aeroori,Aeropuerto aerodes){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String mensaje="";
        List<Tarifa> Lista;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("TarifaxRuta");
            q.setParameter("aeroori", aeroori);
            q.setParameter("aerodes", aerodes);
            Lista = q.list();
            if (Lista.size()>0){
               mensaje="Ya existe una tarifa para el origen y destino especificado"; 
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        
        return mensaje;
    }
    public List<Parametro> ListarEstadoMonedas(){
         SessionFactory sf = Sesion.getSessionFactory();
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

    public List<Tarifa> Buscar(Aeropuerto AeroOri,Aeropuerto AeroDes,String MontoIni,String MontoFin){
        
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Tarifa> ListaTarifas;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            
           q = s.getNamedQuery("Tarifas");
           
           if (AeroOri!=null )
           {
             Filter f = s.enableFilter("TarifaxAeroOri");
             f.setParameter("idaero",AeroOri.getIdAeropuerto());
           }    
           if (AeroDes!=null){
                Filter f2 = s.enableFilter("TarifaxAeroDes");
                f2.setParameter("idaero",AeroDes.getIdAeropuerto());

           }
           if (MontoIni!=null && !MontoIni.equals("")){
                Filter f3 = s.enableFilter("TarifaMayorA");
                f3.setParameter("tarifa",Double.parseDouble(MontoIni));
           }
           if (!MontoFin.equals("")){
                Filter f4 = s.enableFilter("TarifaMenorA");
                f4.setParameter("tarifa",Double.parseDouble(MontoFin));

           }
           
           
           ListaTarifas= q.list();
           
           
           return ListaTarifas;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    
    }
    
    public Aeropuerto BuscarAeropuertoXId (Aeropuerto Origen){
    
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Aeropuerto aeropuerto;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            
           q = s.getNamedQuery("AeropuertosxID");
           
           q.setParameter("idaero", Origen.getIdAeropuerto());
           aeropuerto = (Aeropuerto) q.uniqueResult();
           
           
           return aeropuerto;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
        
    }
    
    public TipoCambio BuscarCambio(int idmonori,int iddolares){
    
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        TipoCambio tipocambio;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            
           q = s.getNamedQuery("TiposCambioXMonedas");
           
           q.setParameter("idMonedaOrigen", idmonori);
           q.setParameter("idMonedaDestino", iddolares);
           
           tipocambio = (TipoCambio) q.uniqueResult();
           
           
           return tipocambio;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    }
    
    public Tarifa BuscarXid(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Tarifa tarifa= new Tarifa();
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("TarifaxId").setMaxResults(1);
            q.setParameter("id", id);
            tarifa=(Tarifa)q.uniqueResult();
            return tarifa;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
    public void ModificarTarifa(int idTarifa,Aeropuerto AeroOri,Aeropuerto AeroDes,String monto,
           String FechaAct,String FechaDes
                    ,Parametro Moneda,Parametro Estado){
        
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            Tarifa TarifaBE = new Tarifa(); 

            
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
            
            Date fechaact = null;
            Date fechades=null;
            try {

                fechaact = formatoDelTexto.parse(FechaAct);
                fechades = formatoDelTexto.parse(FechaDes);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            
            TarifaBE.setIdTarifa(idTarifa);
            TarifaBE.setOrigen(AeroOri);
            TarifaBE.setDestino(AeroDes);
            TarifaBE.setMoneda(Moneda);
            TarifaBE.setEstado(Estado);
            TarifaBE.setMonto(Double.parseDouble(monto));
            TarifaBE.setFechaActivacion(fechaact);
            TarifaBE.setFechaDesactivacion(fechades);
                        
            s.update(TarifaBE);
            
            tx.commit();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
        
    }
    public void agregarTarifa(Aeropuerto AeroOri,Aeropuerto AeroDes, String monto, Parametro Moneda, Parametro Estado,String FechaAct, String FechaDes)
    {
                    
        SessionFactory sf = Sesion.getSessionFactory();
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
