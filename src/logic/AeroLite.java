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

    private int id;
    private String nombre;
    private int capacidadMax;
    private int capacidadActual;
    int necesidad;
    private ArrayList<VueloLite> vuelosSalida;
    private ArrayList<VueloLite> vuelosLlegada;

    public AeroLite() {
    }

    public AeroLite(int id, String nombre, int capacidadMax, int capacidadActual) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.capacidadActual = capacidadActual;
        this.necesidad = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<VueloLite> getVuelosSalida() {
        return vuelosSalida;
    }

    public void setVuelosSalida(ArrayList<VueloLite> vuelosSalida) {
        this.vuelosSalida = vuelosSalida;
    }

    public ArrayList<VueloLite> getVuelosLlegada() {
        return vuelosLlegada;
    }

    public void setVuelosLlegada(ArrayList<VueloLite> vuelosLlegada) {
        this.vuelosLlegada = vuelosLlegada;
    }

    
    public int getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(int necesidad) {
        this.necesidad = necesidad;
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
}
