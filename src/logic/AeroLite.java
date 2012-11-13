/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;

/**
 *
 * @author miguelavg
 */
public class AeroLite {
    private String nombre;
    private int capacidadMax;
    private int capacidadActual;
    private ArrayList<VueloHist> vuelosSalida;
    private ArrayList<VueloHist> vuelosLlegada;
    
    public AeroLite(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public ArrayList<VueloHist> getVuelosSalida() {
        return vuelosSalida;
    }

    public void setVuelosSalida(ArrayList<VueloHist> vuelosSalida) {
        this.vuelosSalida = vuelosSalida;
    }

    public ArrayList<VueloHist> getVuelosLlegada() {
        return vuelosLlegada;
    }

    public void setVuelosLlegada(ArrayList<VueloHist> vuelosLlegada) {
        this.vuelosLlegada = vuelosLlegada;
    }
}
