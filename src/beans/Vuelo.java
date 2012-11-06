/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.*;

/**
 *
 * @author jcastanon
 */
@Entity
@Table(name = "Vuelo")
@NamedQueries({
    @NamedQuery(name = "Volar",  query = "from Vuelo"),
    @NamedQuery(name = "Vuelos", query = "from Vuelo where estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "VuelosXFecha", query = "from Vuelo where :fechaRegistro < fechaSalida")
})
@FilterDefs({
    @FilterDef(name = "VueloXOrigen", parameters = @ParamDef(name = "idAeropuerto", type = "integer")),
    @FilterDef(name = "VueloXDestino", parameters = @ParamDef(name = "idAeropuerto", type = "integer")),
    @FilterDef(name = "VueloXfechini", parameters = @ParamDef(name = "fechasalida", type = "timestamp")),
    @FilterDef(name = "VueloXfechfin", parameters = @ParamDef(name = "fechallegada", type = "timestamp")),
    @FilterDef(name = "VueloXEstado", parameters = @ParamDef(name = "estado", type = "integer")),
})
@Filters({
    @Filter(name = "VueloXOrigen", condition = "idAeropuerto = :idAeropuerto"),
    @Filter(name = "VueloXDestino", condition = "idAeropuerto = :idAeropuerto"),
    @Filter(name = "VueloXfechini", condition = "fechasalida = :fechasalida"),
     @Filter(name = "VueloXfechfin", condition = "fechallegada = :fechallegada"),
    @Filter(name = "VueloXEstado", condition = "estado = :estado")
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
