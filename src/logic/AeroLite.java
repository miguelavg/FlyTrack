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
    private boolean congestiona;
    private int tCongestiona;
    private int necesidad;
    private boolean rojo;
    private int x;
    private int y;
    private ArrayList<VueloLite> vuelosSalida = new ArrayList<VueloLite>();
    private ArrayList<VueloLite> vuelosLlegada= new ArrayList<VueloLite>();

    public AeroLite() {
    }

    public AeroLite(int id, String nombre, int capacidadMax, int capacidadActual, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMax = capacidadMax;
        this.capacidadActual = capacidadActual;
        this.necesidad = 0;
        this.congestiona = false;
        this.x = x;
        this.y = y;
        this.rojo = false;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    @Override
    public String toString(){
        return this.getNombre();
    }
}
