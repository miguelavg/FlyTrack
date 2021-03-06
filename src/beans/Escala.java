/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controllers.CValidator;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Escala")
public class Escala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEscala;
    private int numEscala;
    private boolean original;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;
    @ManyToOne
    @JoinColumn(name = "idVuelo")
    private Vuelo vuelo;
    @ManyToOne
    @JoinColumn(name = "idEnvio")
    private Envio envio;

    public Escala() {
    }

    public int getIdEscala() {
        return idEscala;
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Parametro getEstado() {
        return estado;
    }

    public void setEstado(Parametro estado) {
        this.estado = estado;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public int getNumEscala() {
        return numEscala;
    }

    public void setNumEscala(int numEscala) {
        this.numEscala = numEscala;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }
    
    public String aString(){
        return "IdEscala: " + idEscala +
                " Numero de Escala: " + numEscala +
                " Original: " + (original ? "Si" : "No") + 
                " Fecha de Inicio: " + CValidator.formatDate(fechaInicio) +
                " Estado: " + (estado != null ? estado.getValor() : null) +
                " Vuelo: " + (vuelo != null ? vuelo.getIdVuelo() : null) + 
                " Envio: " + (envio != null ? envio.getIdEnvio() : null);
    }
}
