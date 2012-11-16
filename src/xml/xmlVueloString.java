/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jugox
 */
public class xmlVueloString implements Serializable{
    
       
    private String costoAlquiler;
    
    private String capacidadMax;
    
    private String capacidadActual;
    
    private String anosal;
    private String messal;
    private String diasal;
    private String horasal;
    private String minsal;
    
    //private Date fechaSalida;
    
    
    private String anolle;
    private String meslle;
    private String dialle;
    private String horalle;
    private String minlle;
    
    //private Date fechaLlegada;
    
    private String estado;

    private String origen;
    
    private String destino;
    
    
    
    public void XmlVueloString() {
        
    }

   

    public String getAlquiler() {
        return costoAlquiler;
    }

    public void setAlquiler(String alquiler) {
        this.costoAlquiler = alquiler;
    }

    public String getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(String capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(String capacidadActual) {
        this.capacidadActual = capacidadActual;
    }


    public String getEstado() {
        return estado;
    }
    
    
    public String getHoraSal() {
        return horasal;
    }
    
    public String getMinSal() {
        return minsal;
    }
    
    public String getAnoSal() {
        return anosal;
    }
    
    public String getMesSal() {
        return messal;
    }
    
    public String getDiaSal() {
        return diasal;
    }
    
    
    public void setHoraSal(String estado) {
        this.horasal = horasal;
    }
    
    public void setMinSal(String estado) {
        this.minsal = minsal;
    }
    
    public void setAnoSal(String estado) {
        this.anosal = anosal;
    }
    
    public void setMesSal(String estado) {
        this.messal = messal;
    }
    
    public void setDiaSal(String estado) {
        this.diasal = diasal;
    }
    
    
    public String getHoraLle() {
        return horalle;
    }
    
    public String getMinLle() {
        return minlle;
    }
    
    public String getAnoLle() {
        return anolle;
    }
    
    public String getMesLle() {
        return meslle;
    }
    
    public String getDiaLle() {
        return dialle;
    }
    
    
    public void setHoraLle(String estado) {
        this.horalle = horalle;
    }
    
    public void setMinLle(String estado) {
        this.minlle = minlle;
    }
    
    public void setAnoLle(String estado) {
        this.anolle = anolle;
    }
    
    public void setMesLle(String estado) {
        this.meslle = meslle;
    }
    
    public void setDiaLle(String estado) {
        this.dialle = dialle;
    }
    
    

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
