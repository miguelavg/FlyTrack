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
import beans.Vuelo;
import controllers.CAeropuerto;
import controllers.CCliente;
import controllers.CParametro;
import controllers.CSerializer;
import controllers.CTarifa;
import controllers.CValidator;
import gui.ErrorDialog;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import xml.XmlAeropuertoString;
import xml.XmlEnvio;
import xml.XmlVuelo;
import xml.xmlVueloString;

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
    
    private static ArrayList<Vuelo> vuelos;
    
    public static ArrayList<Aeropuerto> cargarAeropuertos(){
        try{
            ArrayList<XmlAeropuertoString> xmlAeropuertos = CSerializer.deserializar("./cargaMasiva/CargaAeropuertos.xml");
            return pasaValores(xmlAeropuertos);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private static ArrayList<Aeropuerto> pasaValores(ArrayList<XmlAeropuertoString> xmlAeropuertos){
        
        ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
        
//        for (XmlAeropuertoString xmlaero : xmlAeropuertos){
        for (int i=0; i<xmlAeropuertos.size(); i++){
            
            XmlAeropuertoString xmlaero = xmlAeropuertos.get(i);
            
            Aeropuerto aero = new Aeropuerto();
            aero.setIdAeropuerto(i+1);

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

//    private static List<Vuelo> generarVuelos(int posAeroIni, int posAeroFin, ArrayList<Aeropuerto> aeropuertos){
//        Random rnd =  new Random();
//        int cantVuelos = rnd.nextInt(10) + 1;
//        
//        if(cantVuelos == 1){
//            Vuelo vuelo = new Vuelo();
//            vuelo.setAlquiler(500.0);
//            vuelo.setCapacidadActual(200);
//            vuelo.setCapacidadMax(500);
//            vuelo.setFechaSalida(null);
//        }
//        else{
//            
//        }
//        
//        
//        return null;
//    }
    
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
        vuelos = new ArrayList<Vuelo>();
        
        for (int i=0 ; i < numEnvios; i++){
            Envio envio = new Envio();
            
            
            
            int posAeroInicial = rnd.nextInt(cantAeropuertos);
            Aeropuerto aInicial = aeropuertos.get(posAeroInicial);
            envio.setOrigen(aInicial);
            
            int posAeroFinal;
            while( (posAeroFinal = rnd.nextInt(cantAeropuertos)) == posAeroInicial) ;
            Aeropuerto aFinal = aeropuertos.get(posAeroFinal);
            
//            Lista<Vuelo> vuelosDelEnvio = generarVuelos(posAeroInicial, posAeroFinal, aeropuertos);
            
            envio.setDestino(aFinal);
            envio.setActual(aFinal);
            envio.setRemitente(remit);
            envio.setDestinatario(desti);
            
            envio.setEstado(estado);
            int numPaquetes = 5 + rnd.nextInt(6);
            envio.setNumPaquetes(numPaquetes);
            envio.setMonto(1.2*numPaquetes*100.0);
            envio.setMoneda(moneda);

            Calendar cal = Calendar.getInstance();
            cal.roll(Calendar.MILLISECOND, -90000000);
            Date fecharecojo = cal.getTime();
            cal.roll(Calendar.MILLISECOND, -10564894);
            Date fechavuelollegada = cal.getTime();
            cal.roll(Calendar.MILLISECOND, -90561616);
            Date fechavuelosalida = cal.getTime();
            cal.roll(Calendar.MILLISECOND, -11651665);            
            Date fecharegistro = cal.getTime();
            
            
            envio.setFechaRecojo(fecharecojo);
            envio.setFechaRegistro(fecharegistro);
            envio.setTipoDocVenta(tipoDocVenta);
            envio.setNumDocVenta(i+1);
            envio.setImpuesto(numPaquetes*0.20*100.0);

            envio.setIva(0.20);
            envio.setUnitario(100.00);
            envio.setEstadoFactura(estadoFactura);
            
            Vuelo vuelo = new Vuelo();
            vuelo.setAlquiler(500);
            vuelo.setCapacidadActual(200);
            vuelo.setCapacidadMax(500);
            vuelo.setDestino(aFinal);
            vuelo.setEstado(CParametro.buscarXValorUnicoyTipo("ESTADO_VUELO", "FIN"));
            vuelo.setFechaLlegada(fechavuelollegada);
            vuelo.setFechaSalida(fechavuelosalida);
            vuelo.setOrigen(aInicial);
            
            vuelos.add(vuelo);
            
            envios.add(envio);
        }
        
        return envios;
    }
    
    public static List<Vuelo> generarVuelos(){
        return vuelos;
    }

    public static ArrayList<XmlEnvio> conversionAXML(List<Envio> envios){
        
        ArrayList<XmlEnvio> xmlenvios = new ArrayList<XmlEnvio>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        
        for (Envio envio : envios){
            
            XmlEnvio xmlenvio = new XmlEnvio();
            
            xmlenvio.setFecharegis(sdf.format(envio.getFechaRegistro()));
            xmlenvio.setFechaRecojo(sdf.format(envio.getFechaRecojo()));
            xmlenvio.setNumPaquetes(envio.getNumPaquetes());
            xmlenvio.setMonto(envio.getMonto());
            xmlenvio.setIva(envio.getIva());
            xmlenvio.setImpuesto(envio.getImpuesto());
            xmlenvio.setUnitario(envio.getUnitario());
            xmlenvio.setNumDocVenta(envio.getNumDocVenta());
            xmlenvio.setMoneda(envio.getMoneda() != null ? envio.getMoneda().getValorUnico() : null);
//            xmlenvio.setMoneda(null);
            xmlenvio.setTipodoc(envio.getTipoDocVenta() != null ? envio.getTipoDocVenta().getValorUnico() : null);
            xmlenvio.setEstado(envio.getEstado() != null ? envio.getEstado().getValorUnico() : null);
            xmlenvio.setEstadofac(envio.getEstadoFactura() != null ? envio.getEstadoFactura().getValorUnico() : null);
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
    
    public static ArrayList<xmlVueloString> conversionAXML2(List<Vuelo> vuelos){
        
        ArrayList<xmlVueloString> xmlvuelos = new ArrayList<xmlVueloString>();
        
        for (Vuelo vuelo : vuelos){
            
            xmlVueloString xmlvuelo = new xmlVueloString();
            
            xmlvuelo.setAlquiler(""+vuelo.getAlquiler());
            xmlvuelo.setCapacidadMax(""+vuelo.getCapacidadMax());
            xmlvuelo.setCapacidadActual(""+vuelo.getCapacidadActual());
            
//            Calendar cal = Calendar.getInstance().setTime(vuelo.getFechaSalida());
            
//            if(vuelo.getFechaLlegada() == null || vuelo.getFechaSalida() == null){
//                System.out.println("fdfsgsfgsfg");
//            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf = new SimpleDateFormat("yyyy");
            xmlvuelo.setAnoSal(sdf.format(vuelo.getFechaSalida()));
            sdf = new SimpleDateFormat("MM");
            xmlvuelo.setMesSal(sdf.format(vuelo.getFechaSalida()));
            sdf = new SimpleDateFormat("dd");
            xmlvuelo.setDiaSal(sdf.format(vuelo.getFechaSalida()));
            sdf = new SimpleDateFormat("HH");
            xmlvuelo.setHoraSal(sdf.format(vuelo.getFechaSalida()));
            sdf = new SimpleDateFormat("mm");
            xmlvuelo.setMinSal(sdf.format(vuelo.getFechaSalida()));
            sdf = new SimpleDateFormat("yyyy");
            xmlvuelo.setAnoLle(sdf.format(vuelo.getFechaLlegada()));
            sdf = new SimpleDateFormat("MM");
            xmlvuelo.setMesLle(sdf.format(vuelo.getFechaLlegada()));
            sdf = new SimpleDateFormat("dd");
            xmlvuelo.setDiaLle(sdf.format(vuelo.getFechaLlegada()));
            sdf = new SimpleDateFormat("HH");
            xmlvuelo.setHoraLle(sdf.format(vuelo.getFechaLlegada()));
            sdf = new SimpleDateFormat("mm");
            xmlvuelo.setMinLle(sdf.format(vuelo.getFechaLlegada()));
            
            
            xmlvuelo.setEstado(vuelo.getEstado() != null ? vuelo.getEstado().getValorUnico() : null);
            xmlvuelo.setOrigen(vuelo.getOrigen() != null ? vuelo.getOrigen().getNombre() : null);
            xmlvuelo.setDestino(vuelo.getDestino() != null ? vuelo.getDestino().getNombre() : null);
            
//            xmlvuelo.setAlquiler(vuelo.getAlquiler());
//            xmlvuelo.setCapacidadActual(vuelo.getCapacidadActual());
//            xmlvuelo.setCapacidadMax(vuelo.getCapacidadMax());
//            xmlvuelo.setDestino(vuelo.getDestino() != null ? vuelo.getDestino().getIdAeropuerto() : null);
//            xmlvuelo.setEstado(vuelo.getEstado() != null ? vuelo.getEstado().getIdParametro() : null);
//            xmlvuelo.setFechaLlegada(vuelo.getFechaLlegada());
//            xmlvuelo.setFechaSalida(vuelo.getFechaSalida());
//            xmlvuelo.setIdVuelo(vuelo.getIdVuelo());
//            xmlvuelo.setOrigen(vuelo.getOrigen() != null ? vuelo.getOrigen().getIdAeropuerto() : null);
            
            xmlvuelos.add(xmlvuelo);
        }
        
        return xmlvuelos;
    }
    
}
