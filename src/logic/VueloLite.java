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
    int evt;
    int dur;
    int num;
    int capacidadActual;
    int capacidadMax;
    int necesidad;
    double alquiler;

    public VueloLite(AeroLite origen, AeroLite destino, int num, int capacidadMax, double alquiler) {
        this.origen = origen;
        this.destino = destino;
        this.num = num;
        this.capacidadMax = capacidadMax;
        this.alquiler = alquiler;
        this.necesidad = 0;
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
}
