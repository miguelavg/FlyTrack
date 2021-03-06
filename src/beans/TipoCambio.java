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
@Table(name = "TipoCambio")
@NamedQueries({
    @NamedQuery(name = "TiposCambio",
    query = "from TipoCambio"),
    @NamedQuery(name = "TiposCambioXId",
    query = "from TipoCambio where idTipoCambio = :id"),
    @NamedQuery(name = "TiposCambioXMoneda",
    query = "from TipoCambio where monedaOrigen.idParametro = :idMoneda"),
    @NamedQuery(name = "TiposCambioXMonedas",
    query = "from TipoCambio where monedaOrigen.idParametro = :idMonedaOrigen and monedaDestino.idParametro = :idMonedaDestino"),
    @NamedQuery(name = "TiposCambioXValosresUnicos",
    query = "from TipoCambio where monedaOrigen.valorUnico = :monedaOrigen and monedaDestino.valorUnico = :monedaDestino")
})
@FilterDefs({
    @FilterDef(name = "TiposCambioXOrigen",
    parameters =
    @ParamDef(name = "idMoneda", type = "integer")),
    @FilterDef(name = "TiposCambioXDestino",
    parameters =
    @ParamDef(name = "idMoneda", type = "integer"))
})
@Filters({
    @Filter(name = "TiposCambioXOrigen", condition = "MonedaOrigen = :idMoneda"),
    @Filter(name = "TiposCambioXDestino", condition = "MonedaDestino = :idMoneda")
})
public class TipoCambio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoCambio;
    
    private double tipoCambio;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    
    @ManyToOne
    @JoinColumn(name = "MonedaOrigen")
    private Parametro monedaOrigen;
    
    @ManyToOne
    @JoinColumn(name = "MonedaDestino")
    private Parametro monedaDestino;

    public TipoCambio() {
    
    }

    public TipoCambio(TipoCambio tipocambio) {
        this.idTipoCambio = tipocambio.idTipoCambio;
        this.tipoCambio = tipocambio.tipoCambio;
        this.fechaActualizacion = tipocambio.fechaActualizacion;
        this.monedaOrigen = tipocambio.monedaOrigen;
        this.monedaDestino = tipocambio.monedaDestino;
    }
    
    

    public int getIdTipoCambio() {
        return idTipoCambio;
    }

    public void setIdTipoCambio(int idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Parametro getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(Parametro monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public Parametro getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(Parametro monedaDestino) {
        this.monedaDestino = monedaDestino;
    }
    
    public String aString(){
    return "IdTipoCambio = " + idTipoCambio + 
            " Moneda Origen = " + (monedaOrigen != null ? monedaOrigen.getValor() : null ) +
            " Moneda Destino = " + (monedaDestino != null ? monedaDestino.getValor() : null )+
            " Tipo de Cambio = " + tipoCambio +
            " Fecha Actualización = " + CValidator.formatDate(fechaActualizacion);
    }
}
