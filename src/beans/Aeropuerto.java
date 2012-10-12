/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Aeropuerto")
public class Aeropuerto implements Serializable {

    @Id
    private int idAeropuerto;
    private String nombre;
    private int almacen;
    private int almacenUsado;
    @ManyToOne
    @JoinColumn(name = "Pais")
    private Parametro pais;
    @ManyToOne
    @JoinColumn(name = "Ciudad")
    private Parametro ciudad;
    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;
    @OneToMany(mappedBy = "origen")
    private List<Vuelo> vuelosSalida;
    @OneToMany(mappedBy = "destino")
    private List<Vuelo> vuelosLlegada;
    @OneToMany(mappedBy = "actual")
    private List<Envio> enviosAlmacen;
    @OneToMany(mappedBy = "origen")
    private List<Tarifa> tarifas;

    public Aeropuerto() {
    }

    public int getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(int idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public int getAlmacenUsado() {
        return almacenUsado;
    }

    public void setAlmacenUsado(int almacenUsado) {
        this.almacenUsado = almacenUsado;
    }

    public Parametro getPais() {
        return pais;
    }

    public void setPais(Parametro pais) {
        this.pais = pais;
    }

    public Parametro getCiudad() {
        return ciudad;
    }

    public void setCiudad(Parametro ciudad) {
        this.ciudad = ciudad;
    }

    public Parametro getEstado() {
        return estado;
    }

    public void setEstado(Parametro estado) {
        this.estado = estado;
    }

    public List<Vuelo> getVuelosSalida() {
        return vuelosSalida;
    }

    public void setVuelosSalida(List<Vuelo> vuelosSalida) {
        this.vuelosSalida = vuelosSalida;
    }

    public List<Vuelo> getVuelosLlegada() {
        return vuelosLlegada;
    }

    public void setVuelosLlegada(List<Vuelo> vuelosLlegada) {
        this.vuelosLlegada = vuelosLlegada;
    }

    public List<Envio> getEnviosAlmacen() {
        return enviosAlmacen;
    }

    public void setEnviosAlmacen(List<Envio> enviosAlmacen) {
        this.enviosAlmacen = enviosAlmacen;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
}
