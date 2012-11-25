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
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Tarifa")
@NamedQueries({
    @NamedQuery(name = "Tarifa",
    query = "from Tarifa where origen.idAeropuerto = :idorigen and destino.idAeropuerto = :iddestino"),
    @NamedQuery(name = "Tarifas",
    query = "from Tarifa"  ),
    @NamedQuery(name = "TarifasActuales", query = "from Tarifa where estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "TarifaxId",
    query = "from Tarifa where idTarifa= :id"  ),
    @NamedQuery(name = "TarifaxRuta",
    query = "from Tarifa where origen= :aeroori and destino=:aerodes"  ),
})
@FilterDefs({
    @FilterDef(name = "TarifaxAeroOri",
    parameters =
    @ParamDef(name = "idaero", type = "integer")),
    
    @FilterDef(name = "TarifaxAeroDes",
    parameters =
    @ParamDef(name = "idaero", type = "integer")),
    
    @FilterDef(name = "TarifaMayorA",
    parameters =
    @ParamDef(name = "tarifa", type = "double")),
    
    @FilterDef(name = "TarifaMenorA",
    parameters =
    @ParamDef(name = "tarifa", type = "double"))
        
})
@Filters({
    
    @Filter(name = "TarifaxAeroOri", condition = "idOrigen = :idaero"),
    
    @Filter(name = "TarifaxAeroDes", condition = "idDestino = :idaero"),
    
    @Filter(name = "TarifaMayorA", condition = "monto > :tarifa"),
    
    @Filter(name = "TarifaMenorA", condition = "monto < :tarifa"),
    
})
public class Tarifa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarifa;
    
    private double monto;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesactivacion;
    
    @ManyToOne
    @JoinColumn(name = "Moneda")
    private Parametro moneda;
    
    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;
    
    @ManyToOne
    @JoinColumn(name = "idOrigen")
    private Aeropuerto origen;
    
    @ManyToOne
    @JoinColumn(name = "idDestino")
    private Aeropuerto destino;

    public Tarifa() {
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public Date getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    public void setFechaDesactivacion(Date fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }

    public Parametro getMoneda() {
        return moneda;
    }

    public void setMoneda(Parametro moneda) {
        this.moneda = moneda;
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
    
    public String aString(){
        return "Idtarfia = " + idTarifa + 
                " Aeropuerto Origen = " + (origen != null ? origen.getNombre() : null) +
                " Aeropuerto Destino = " + (destino != null ? destino.getNombre() : null) +
                " Monto = " + monto +
                " Moneda = " + (moneda != null ? moneda.getValor() : null) +
                " Estado = "+ (estado != null ? estado.getValor() : null) +
                " Fecha Activacion = " + CValidator.formatDate(fechaActivacion) + 
                " Fecha Desactivacion = " + CValidator.formatDate(fechaDesactivacion);
    }
}
