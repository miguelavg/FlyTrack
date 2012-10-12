/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Incidencia")
public class Incidencia implements Serializable {

    @Id
    private int idIncidencia;
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "Tipo")
    private Parametro tipo;
    @ManyToOne
    @JoinColumn(name = "idVuelo")
    private Vuelo vuelo;

    public Incidencia() {
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Parametro getTipo() {
        return tipo;
    }

    public void setTipo(Parametro tipo) {
        this.tipo = tipo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
