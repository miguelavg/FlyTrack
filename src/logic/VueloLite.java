/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author miguelavg
 */
public class VueloLite {
    private AeroLite origen;
    private AeroLite destino;
    private int evt;
    private int dur;
    private int num;
    private int capacidadActual;
    private int capacidadMax;
    private int necesidad;
    private int tCongestiona;
    private boolean congestiona;
    private double alquiler;
    private double plleno;
    private boolean rojo;

    public VueloLite(AeroLite origen, AeroLite destino, int num, int capacidadMax, double alquiler, double plleno) {
        this.origen = origen;
        this.destino = destino;
        this.num = num;
        this.capacidadMax = capacidadMax;
        this.alquiler = alquiler;
        this.necesidad = 0;
        this.plleno = plleno;
        this.congestiona = false;
        this.tCongestiona = 0;
        this.rojo = false;
    }
    
    public AeroLite getOrigen() {
        return origen;
    }

    public void setOrigen(AeroLite origen) {
        this.origen = origen;
    }

    public AeroLite getDestino() {
        return destino;
    }

    public void setDestino(AeroLite destino) {
        this.destino = destino;
    }

    public int getEvt() {
        return evt;
    }

    public void setEvt(int evt) {
        this.evt = evt;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }

    public int getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(int necesidad) {
        this.necesidad = necesidad;
    }

    public double getPlleno() {
        return plleno;
    }

    public void setPlleno(double plleno) {
        this.plleno = plleno;
    }

    public boolean isCongestiona() {
        return congestiona;
    }

    public void setCongestiona(boolean congestiona) {
        this.congestiona = congestiona;
    }

    public int gettCongestiona() {
        return tCongestiona;
    }

    public void settCongestiona(int tCongestiona) {
        this.tCongestiona = tCongestiona;
    }

    public boolean isRojo() {
        return rojo;
    }

    public void setRojo(boolean rojo) {
        this.rojo = rojo;
    }
    
    
}
