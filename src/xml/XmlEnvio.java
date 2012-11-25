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
public class XmlEnvio implements Serializable {
    
    //datos envio
    private String fecharegis;
     
    private String fechaRecojo;
    
    private int numPaquetes;
    private double monto;
    private double iva;
    private double impuesto;
    private double unitario;
    private int numDocVenta;
    
    private String moneda;
    private String tipodoc;
    private String estado;
    private String estadofac;
    private String origen;
    private String destino;
    private String actual;

    
    
    // datos escala
    
    private String estadoescala;
    private int vuelo;
    private int envio;

    /**
     * @return the fecharegis
     */
    public String getFecharegis() {
        return fecharegis;
    }

    /**
     * @param fecharegis the fecharegis to set
     */
    public void setFecharegis(String fecharegis) {
        this.fecharegis = fecharegis;
    }

    /**
     * @return the fechaRecojo
     */
    public String getFechaRecojo() {
        return fechaRecojo;
    }

    /**
     * @param fechaRecojo the fechaRecojo to set
     */
    public void setFechaRecojo(String fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    /**
     * @return the numPaquetes
     */
    public int getNumPaquetes() {
        return numPaquetes;
    }

    /**
     * @param numPaquetes the numPaquetes to set
     */
    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the iva
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * @return the impuesto
     */
    public double getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the unitario
     */
    public double getUnitario() {
        return unitario;
    }

    /**
     * @param unitario the unitario to set
     */
    public void setUnitario(double unitario) {
        this.unitario = unitario;
    }

    /**
     * @return the numDocVenta
     */
    public int getNumDocVenta() {
        return numDocVenta;
    }

    /**
     * @param numDocVenta the numDocVenta to set
     */
    public void setNumDocVenta(int numDocVenta) {
        this.numDocVenta = numDocVenta;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the tipodoc
     */
    public String getTipodoc() {
        return tipodoc;
    }

    /**
     * @param tipodoc the tipodoc to set
     */
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the estadofac
     */
    public String getEstadofac() {
        return estadofac;
    }

    /**
     * @param estadofac the estadofac to set
     */
    public void setEstadofac(String estadofac) {
        this.estadofac = estadofac;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the actual
     */
    public String getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(String actual) {
        this.actual = actual;
    }

    /**
     * @return the remitente
     */
   

    /**
     * @return the numescala
     */
    

    /**
     * @return the estadoescala
     */
    public String getEstadoescala() {
        return estadoescala;
    }

    /**
     * @param estadoescala the estadoescala to set
     */
    public void setEstadoescala(String estadoescala) {
        this.estadoescala = estadoescala;
    }

    /**
     * @return the vuelo
     */
    public int getVuelo() {
        return vuelo;
    }

    /**
     * @param vuelo the vuelo to set
     */
    public void setVuelo(int vuelo) {
        this.vuelo = vuelo;
    }

    /**
     * @return the envio
     */
    public int getEnvio() {
        return envio;
    }

    /**
     * @param envio the envio to set
     */
    public void setEnvio(int envio) {
        this.envio = envio;
    }

    /**
     * @return the fechaInicioEscala
     */
   
    
    
    
}
