/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import beans.Aeropuerto;

/**
 *
 * @author miguelavg
 */
public class VueloLite {

    private Aeropuerto origen;
    private Aeropuerto destino;
    private double pLleno;

    public VueloLite(Aeropuerto origen, Aeropuerto destino, double pLleno) {
        this.origen = origen;
        this.destino = destino;
        this.pLleno = pLleno;
    }

    public VueloLite() {
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public double getpLleno() {
        return pLleno;
    }

    public void setpLleno(double pLleno) {
        this.pLleno = pLleno;
    }
}
