/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
import org.hibernate.annotations.*;

/**
 *
 * @author jorge
 */


/*
 * 
 * @Entity
@Table(name = "Vuelo")
@NamedQueries({
    @NamedQuery(name = "Volar",  query = "from Vuelo"),
    @NamedQuery(name = "Vuelos", query = "from Vuelo where estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "VuelosXFecha", query = "from Vuelo where :fechaRegistro < fechaSalida")
})
@FilterDefs({
    @FilterDef(name = "VueloXOrigen", parameters = @ParamDef(name = "idOrigen", type = "integer")),
    @FilterDef(name = "VueloXDestino", parameters = @ParamDef(name = "idDestino", type = "integer")),
    @FilterDef(name = "VueloXfechini", parameters = @ParamDef(name = "fechasalida", type = "timestamp")),
    @FilterDef(name = "VueloXfechfin", parameters = @ParamDef(name = "fechallegada", type = "timestamp")),
    @FilterDef(name = "VueloXEstado", parameters = @ParamDef(name = "estado", type = "integer")),
})
@Filters({
    @Filter(name = "VueloXOrigen", condition = "idOrigen = :idOrigen"),
    @Filter(name = "VueloXDestino", condition = "idDestino = :idDestino"),
    @Filter(name = "VueloXfechini", condition = "fechaSalida > :fechasalida"), 
    @Filter(name = "VueloXfechfin", condition = "fechaLlegada < :fechallegada"),
    @Filter(name = "VueloXEstado", condition = "estado = :estado")
})
 */
@Entity
@Table(name = "Incidencia")
@NamedQueries({
    @NamedQuery(name = "Incidencia",  query = "from Incidencia")
})
@FilterDefs({
    @FilterDef(name = "IncidenciaXTipo", parameters = @ParamDef(name = "tipo", type = "integer")),
    @FilterDef(name = "IncidenciaXFechaini", parameters = @ParamDef(name = "fechai", type = "timestamp")),
    
    @FilterDef(name = "IncidenciaXVuelo", parameters = @ParamDef(name = "idvuelo", type = "integer")),
    @FilterDef(name = "IncidenciaXFechafin", parameters = @ParamDef(name = "fechaf", type = "timestamp"))
})
@Filters({
    @Filter(name = "IncidenciaXOrigen", condition = "tipo = :tipo"),
    @Filter(name = "IncidenciaXFechaini", condition = "fecha > :fechai"),
    @Filter(name = "IncidenciaXVuelo", condition = "idvuelo=:idvuelo"),
    @Filter(name = "IncidenciaXFechafin", condition = "fecha < :fechaf")
})

public class Incidencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIncidencia;
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "tipo")
    private Parametro estado;
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
}
