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
    private int capacidad_maxima;
    private int capacidad_maxima_nuevo;
    private int capacidad_actual;
    private int capacidad_actual_nuevo;
    private boolean congestiona;
    private boolean congestiona_nuevo;
    private int tiempo_congestiona;
    private int tiempo_congestiona_nuevo;
    private int envios_congestiona;
    private int envios_congestiona_nuevo;
    private boolean pintar_rojo;
    private boolean pintar_rojo_nuevo;
    private int x;
    private int y;
    private ArrayList<VueloLite> vuelos_salida = new ArrayList<VueloLite>();
    private ArrayList<VueloLite> vuelos_llegada= new ArrayList<VueloLite>();
    private ArrayList<VueloLite> vuelos_salida_nuevo = new ArrayList<VueloLite>();
    private ArrayList<VueloLite> vuelos_llegada_nuevo= new ArrayList<VueloLite>();

    public AeroLite() {
    }

    public AeroLite(int id, String nombre, int capacidad_maxima, int capacidad_maxima_nuevo, int capacidad_actual, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad_maxima = capacidad_maxima;
        this.capacidad_maxima_nuevo = capacidad_maxima_nuevo;
        this.capacidad_actual = capacidad_actual;
        this.capacidad_actual_nuevo = capacidad_actual;
        
        this.tiempo_congestiona = 0;
        this.tiempo_congestiona_nuevo = 0;
        this.envios_congestiona = 0;
        this.envios_congestiona_nuevo = 0;
        this.congestiona = false;
        this.congestiona_nuevo = false;
        this.x = x;
        this.y = y;
        this.pintar_rojo = false;
        this.pintar_rojo_nuevo = false;
    }
    
        public AeroLite(AeroLite a) {
        this.id = a.id;
        this.nombre = a.nombre;
        this.capacidad_maxima = a.capacidad_maxima_nuevo;
        this.capacidad_maxima_nuevo = a.capacidad_maxima_nuevo;
        this.capacidad_actual = a.capacidad_actual;
        this.capacidad_actual_nuevo = a.capacidad_actual;
        
        this.tiempo_congestiona = 0;
        this.tiempo_congestiona_nuevo = 0;
        this.envios_congestiona = 0;
        this.envios_congestiona_nuevo = 0;
        this.congestiona = false;
        this.congestiona_nuevo = false;
        this.x = x;
        this.y = y;
        this.pintar_rojo = false;
        this.pintar_rojo_nuevo = false;
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

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public int getCapacidad_maxima_nuevo() {
        return capacidad_maxima_nuevo;
    }

    public void setCapacidad_maxima_nuevo(int capacidad_maxima_nuevo) {
        this.capacidad_maxima_nuevo = capacidad_maxima_nuevo;
    }

    public int getCapacidad_actual() {
        return capacidad_actual;
    }

    public void setCapacidad_actual(int capacidad_actual) {
        this.capacidad_actual = capacidad_actual;
    }

    public int getCapacidad_actual_nuevo() {
        return capacidad_actual_nuevo;
    }

    public void setCapacidad_actual_nuevo(int capacidad_actual_nuevo) {
        this.capacidad_actual_nuevo = capacidad_actual_nuevo;
    }

    public boolean isCongestiona() {
        return congestiona;
    }

    public void setCongestiona(boolean congestiona) {
        this.congestiona = congestiona;
    }

    public boolean isCongestiona_nuevo() {
        return congestiona_nuevo;
    }

    public void setCongestiona_nuevo(boolean congestiona_nuevo) {
        this.congestiona_nuevo = congestiona_nuevo;
    }

    public int getTiempo_congestiona() {
        return tiempo_congestiona;
    }

    public void setTiempo_congestiona(int tiempo_congestiona) {
        this.tiempo_congestiona = tiempo_congestiona;
    }

    public int getTiempo_congestiona_nuevo() {
        return tiempo_congestiona_nuevo;
    }

    public void setTiempo_congestiona_nuevo(int tiempo_congestiona_nuevo) {
        this.tiempo_congestiona_nuevo = tiempo_congestiona_nuevo;
    }

    public int getEnvios_congestiona() {
        return envios_congestiona;
    }

    public void setEnvios_congestiona(int envios_congestiona) {
        this.envios_congestiona = envios_congestiona;
    }

    public int getEnvios_congestiona_nuevo() {
        return envios_congestiona_nuevo;
    }

    public void setEnvios_congestiona_nuevo(int envios_congestiona_nuevo) {
        this.envios_congestiona_nuevo = envios_congestiona_nuevo;
    }

    public boolean isPintar_rojo() {
        return pintar_rojo;
    }

    public void setPintar_rojo(boolean pintar_rojo) {
        this.pintar_rojo = pintar_rojo;
    }

    public boolean isPintar_rojo_nuevo() {
        return pintar_rojo_nuevo;
    }

    public void setPintar_rojo_nuevo(boolean pintar_rojo_nuevo) {
        this.pintar_rojo_nuevo = pintar_rojo_nuevo;
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

    public ArrayList<VueloLite> getVuelos_salida() {
        return vuelos_salida;
    }

    public void setVuelos_salida(ArrayList<VueloLite> vuelos_salida) {
        this.vuelos_salida = vuelos_salida;
    }

    public ArrayList<VueloLite> getVuelos_llegada() {
        return vuelos_llegada;
    }

    public void setVuelos_llegada(ArrayList<VueloLite> vuelos_llegada) {
        this.vuelos_llegada = vuelos_llegada;
    }

    public ArrayList<VueloLite> getVuelos_salida_nuevo() {
        return vuelos_salida_nuevo;
    }

    public void setVuelos_salida_nuevo(ArrayList<VueloLite> vuelos_salida_nuevo) {
        this.vuelos_salida_nuevo = vuelos_salida_nuevo;
    }

    public ArrayList<VueloLite> getVuelos_llegada_nuevo() {
        return vuelos_llegada_nuevo;
    }

    public void setVuelos_llegada_nuevo(ArrayList<VueloLite> vuelos_llegada_nuevo) {
        this.vuelos_llegada_nuevo = vuelos_llegada_nuevo;
    }

    
    @Override
    public String toString(){
        return this.getNombre();
    }
}
