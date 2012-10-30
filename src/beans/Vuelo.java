/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Vuelo")
@NamedQueries({
    @NamedQuery(name = "Vuelos",
    query = "from Vuelo where estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "VuelosXFecha",
    query = "from Vuelo where :fechaRegistro < fechaSalida"), 
    @NamedQuery(name = "VuelosHistoricos",
    query = "select new logic.VueloLite(v.origen, v.destino, avg(v.capacidadActual/v.capacidadMax)) from Vuelo v where :fecha < v.fechaSalida group by v.origen, v.destino")
})
public class Vuelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVuelo;
    private double costoAlquiler;
    private int capacidadMax;
    private int capacidadActual;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLlegada;
    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;
    @ManyToOne
    @JoinColumn(name = "idOrigen")
    private Aeropuerto origen;
    @ManyToOne
    @JoinColumn(name = "idDestino")
    private Aeropuerto destino;
    @OneToMany(mappedBy = "vuelo")
    private List<Incidencia> incidencias;
    @OneToMany(mappedBy = "vuelo")
    private List<Escala> escalas;

    public Vuelo() {
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public double getAlquiler() {
        return costoAlquiler;
    }

    public void setAlquiler(double alquiler) {
        this.costoAlquiler = alquiler;
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

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public Parametro getEstado() {
        return estado;
    }

    public void setEstado(Parametro estado) {
        this.estado = estado;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
    }
}
