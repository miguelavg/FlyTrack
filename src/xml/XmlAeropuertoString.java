/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.Serializable;

/**
 *
 * @author joao
 */
public class XmlAeropuertoString implements Serializable{
    
    private String idAeropuerto;
    private String nombre;
    private String capacidadMax;
    private String capacidadActual;
    private String coordX;
    private String coordY;
    private String pais;
    private String ciudad;
    
    private String estado;
    
    public XmlAeropuertoString() {
    }

    public String getIdAeropuerto() {
        return idAeropuerto;
    }
    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }


    public String getNombre() {
        return nombre;
    }

    public void setIdAeropuerto(String id) {
        this.idAeropuerto = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(String capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(String capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
