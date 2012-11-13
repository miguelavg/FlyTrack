/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author miguelavg
 */
public class VueloHist {

    private int origen;
    private int destino;
    private double pLleno;

    public VueloHist(int origen, int destino, double pLleno) {
        this.origen = origen;
        this.destino = destino;
        this.pLleno = pLleno;
    }

    public VueloHist() {
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public double getpLleno() {
        return pLleno;
    }

    public void setpLleno(double pLleno) {
        this.pLleno = pLleno;
    }
}
