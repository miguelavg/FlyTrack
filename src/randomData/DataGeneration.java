/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randomData;

import beans.Aeropuerto;
import beans.Parametro;
import controllers.CAeropuerto;
import controllers.CParametro;
import controllers.CSerializer;
import controllers.CValidator;
import gui.ErrorDialog;
import java.util.ArrayList;
import xml.XmlAeropuertoString;

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
}
