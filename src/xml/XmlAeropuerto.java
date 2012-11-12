/*
 * 
 * 
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jorgeCastañon
 */

public class XmlAeropuerto implements Serializable {

    private int idAeropuerto;
    private String nombre;
    private int capacidadMax;
    private int capacidadActual;
    private int coordX;
    private int coordY;
    private int pais;
    private int ciudad;
    
    private int estado;
   
    
    
   
    public XmlAeropuerto() {
    }

    public int getIdAeropuerto() {
        return idAeropuerto;
    }
    public int getPais() {
        return pais;
    }

    public int getCiudad() {
        return ciudad;
    }

    public int getEstado() {
        return estado;
    }


    public String getNombre() {
        return nombre;
    }

    public void setIdAeropuerto(int id) {
        this.idAeropuerto = id;
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

    

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    
    public void setPais(int pais) {
        this.pais = pais;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
