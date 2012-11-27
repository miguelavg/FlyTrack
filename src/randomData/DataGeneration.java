/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randomData;

import beans.Aeropuerto;
import beans.Cliente;
import beans.Envio;
import beans.Parametro;
import beans.Tarifa;
import controllers.CAeropuerto;
import controllers.CCliente;
import controllers.CParametro;
import controllers.CSerializer;
import controllers.CTarifa;
import controllers.CValidator;
import gui.ErrorDialog;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import xml.XmlAeropuertoString;
import xml.XmlEnvio;

/**
 *
 * @author msolorzano
 */
public class DataGeneration {
    private static int numMinAeropuertos = 60;
    private static int numMaxAeropuertos = 100;
    
    private static int probAeropuertoPrincipal = 5; 
    
    private static int capacMaxAvion = 300;
    private static int capacMinAvion = 200;
    
    private static int capacMaxAlmacenAeropuerto = 1000;
    private static int capacMinAlmacenAeropuerto = 600;
    
    private static int costoMaxAvion = 600;
    private static int costoMinAvion = 400;
    
    private static int maxH = 800;
    private static int maxV = 600;
    
    private static int distanciaMaxDeVuelo;
    private static int tiempoMaxDeVuelo = 24;
    
    private static int numeroCasosAProbar = 40;
    
    public static ArrayList<Aeropuerto> cargarAeropuertos(){
        try{
            ArrayList<XmlAeropuertoString> xmlAeropuertos = CSerializer.deserializar("./cargaMasiva/CargaAeropuertos.xml");
            System.out.println("fsioghsiog");
            return pasaValores(xmlAeropuertos);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private static ArrayList<Aeropuerto> pasaValores(ArrayList<XmlAeropuertoString> xmlAeropuertos){
        
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
        
        for (XmlAeropuertoString xmlaero : xmlAeropuertos){
            
            Aeropuerto aero = new Aeropuerto();

            if (CValidator.isInteger(xmlaero.getCapacidadActual())&&(Integer.parseInt(xmlaero.getCapacidadActual())>=0)){
                aero.setCapacidadActual(Integer.parseInt(xmlaero.getCapacidadActual()));
            }
            else {
                aeropuertos=null;
                break;        
            }
            
            if (CValidator.isInteger(xmlaero.getCapacidadMax())&&(Integer.parseInt(xmlaero.getCapacidadMax())>=0)){
                aero.setCapacidadMax(Integer.parseInt(xmlaero.getCapacidadMax()));
            }
            else {
                aeropuertos=null;
                break;        
            }
            
            if (CValidator.isInteger(xmlaero.getCoordX())&&(Integer.parseInt(xmlaero.getCoordX())>=0)){
                aero.setCoordX(Integer.parseInt(xmlaero.getCoordX()));
            }
            else {
                aeropuertos=null;
                break;        
            }
            
            if (CValidator.isInteger(xmlaero.getCoordY())&&(Integer.parseInt(xmlaero.getCoordY())>=0)){
                aero.setCoordY(Integer.parseInt(xmlaero.getCoordY()));
            }
            else {
                aeropuertos=null;
                break;        
            }
            
            aero.setNombre(xmlaero.getNombre());
            
            Parametro pais = CParametro.buscarXValorUnicoyTipo("PAIS",xmlaero.getPais());
            if (pais!=null){
                aero.setPais(pais);
            }
            else {
                aeropuertos = null;
                break;        
            }
            
            Parametro ciudad = CParametro.buscarXValorUnicoyTipo("CIUDAD",xmlaero.getCiudad());
            if (ciudad!=null){
                aero.setCiudad(ciudad);
            }
            else {
                aeropuertos=null;
                break;        
            }
            
            Parametro estado=CParametro.buscarXValorUnicoyTipo("ESTADO_AEROPUERTO",xmlaero.getEstado());
            if (estado!=null){
                aero.setEstado(estado);
            }
            else {
                aeropuertos=null;
                break;        
            }

            aeropuertos.add(aero);
        }
        return aeropuertos;
        
    }

    public static List<Envio> generarEnvios(int numEnvios){
        
        ArrayList<Aeropuerto> aeropuertos  = cargarAeropuertos();
        int cantAeropuertos = aeropuertos.size();
        Random rnd = new Random();
        
        Cliente remit = new CCliente().BuscarXid(1);
        Cliente desti = new CCliente().BuscarXid(2);
        Parametro estado = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", "REC");
        Parametro moneda = CParametro.buscarXValorUnicoyTipo("TIPO_MONEDA", "DOL");
        Parametro tipoDocVenta = CParametro.buscarXValorUnicoyTipo("TIPO_DOC_PAGO_ENVIO", "BOL");
        Parametro estadoFactura = CParametro.buscarXValorUnicoyTipo("ESTADO_FACTURA", "EM");

        ArrayList<Envio> envios = new ArrayList<Envio>();
        
        for (int i=0 ; i < numEnvios; i++){
            Envio envio = new Envio();
            
            Aeropuerto aInicial = aeropuertos.get(rnd.nextInt(cantAeropuertos));
            envio.setOrigen(aInicial);
            Aeropuerto aFinal = aeropuertos.get(rnd.nextInt(cantAeropuertos));
            envio.setDestino(aFinal);
            envio.setActual(aFinal);
            envio.setRemitente(remit);
            envio.setDestinatario(desti);
            
            envio.setEstado(estado);
            int numPaquetes = 5 + rnd.nextInt(6);
            envio.setNumPaquetes(numPaquetes);
            envio.setMonto(1.2*numPaquetes*100*0);
            envio.setMoneda(moneda);

            Calendar cal = Calendar.getInstance();
            cal.roll(Calendar.DAY_OF_YEAR, -1);
            envio.setFechaRecojo(cal.getTime());
            cal.roll(Calendar.DAY_OF_YEAR, -3);            
            envio.setFechaRegistro(cal.getTime());
            
            envio.setTipoDocVenta(tipoDocVenta);
            envio.setNumDocVenta(i+1);
            envio.setImpuesto(numPaquetes*0.20*100.0);

            envio.setIva(0.20);
            envio.setUnitario(100.00);
            envio.setEstadoFactura(estadoFactura);
            
            
            envios.add(envio);
        }
        
        return envios;
    }

    public static ArrayList<XmlEnvio> conversionAXML(List<Envio> envios){
        
        ArrayList<XmlEnvio> xmlenvios = new ArrayList<XmlEnvio>();
        
        for (Envio envio : envios){
            
            XmlEnvio xmlenvio = new XmlEnvio();
            
            xmlenvio.setFecharegis(CValidator.formatDate(envio.getFechaRegistro()));
            xmlenvio.setFechaRecojo(CValidator.formatDate(envio.getFechaRecojo()));
            xmlenvio.setNumPaquetes(envio.getNumPaquetes());
            xmlenvio.setMonto(envio.getMonto());
            xmlenvio.setIva(envio.getIva());
            xmlenvio.setImpuesto(envio.getImpuesto());
            xmlenvio.setUnitario(envio.getUnitario());
            xmlenvio.setNumDocVenta(envio.getNumDocVenta());
            xmlenvio.setMoneda(envio.getMoneda() != null ? envio.getMoneda().getValor() : null);
            xmlenvio.setTipodoc(envio.getTipoDocVenta() != null ? envio.getTipoDocVenta().getValor() : null);
            xmlenvio.setEstado(envio.getEstado() != null ? envio.getEstado().getValor() : null);
            xmlenvio.setEstadofac(envio.getEstadoFactura() != null ? envio.getEstadoFactura().getValor() : null);
            xmlenvio.setOrigen(envio.getOrigen() != null ? envio.getOrigen().getNombre() : null);
            xmlenvio.setDestino(envio.getDestino() != null ? envio.getDestino().getNombre() : null);
            xmlenvio.setActual(envio.getActual() != null ? envio.getActual().getNombre() : null);
            
//            xmlenvio.setEstadoescala(null);
//            xmlenvio.setVuelo(null);
//            xmlenvio.setEnvio(null);
            
            
            
            xmlenvios.add(xmlenvio);
        }
        
        return xmlenvios;
    }
    
}
