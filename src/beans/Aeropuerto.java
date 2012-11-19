/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.ParamDef;

/**
 *
 * @author jorgeCastañon
 */
@Entity
@Table(name = "Aeropuerto")
@NamedQueries({
    @NamedQuery(name = "AeroXNombre",
    query = "from Aeropuerto where nombre = :nombre"),
    @NamedQuery(name = "AeroTarifa",
    query = "select t.destino from Tarifa t where t.destino.estado.valorUnico = 'ACTV' and t.origen.idAeropuerto = :id order by t.destino"),
    @NamedQuery(name = "Aero",
    query = "from Aeropuerto where estado.valorUnico = 'ACTV' order by idAeropuerto"),
    @NamedQuery(name = "AeroXNombres",
    query = "from Aeropuerto where nombre = :aero"),
    @NamedQuery(name = "Aeropuertos",
    query = "from Aeropuerto where estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "AeropuertosXPais",
    query = "from Aeropuerto a where a.pais = :pais and estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "ParametrosXCiudad",
    query = "from Aeropuerto a where a.ciudad = :ciudad and estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "AeropuertosxID",
    query = "from Aeropuerto where idAeropuerto = :idaero  and estado.valorUnico = 'ACTV'")
})
@FilterDefs({
    @FilterDef(name = "AeropuertoXNombre",
    parameters =
    @ParamDef(name = "nombre", type = "string")),
    @FilterDef(name = "ParametroHijosXTipo",
    parameters =
    @ParamDef(name = "tipo", type = "string")),
    @FilterDef(name = "AeropuertoxPais",
    parameters =
    @ParamDef(name = "Pais", type = "integer")),
    @FilterDef(name = "AeropuertoxEstado",
    parameters =
    @ParamDef(name = "Estado", type = "integer")),
    @FilterDef(name = "AeropuertoxCiudad",
    parameters =
    @ParamDef(name = "Ciudad", type = "integer")),
    @FilterDef(name = "VuelosXAeropuertoSalida", parameters = {
        @ParamDef(name = "upper", type = "date"),
        @ParamDef(name = "lower", type = "date"),
        @ParamDef(name = "idEstado", type = "integer")
    }),
        @FilterDef(name = "VuelosXAeropuertoSalidaAux", parameters = {
        @ParamDef(name = "upper", type = "timestamp"),
        @ParamDef(name = "lower", type = "timestamp"),
        @ParamDef(name = "idEstado1", type = "integer"),
        @ParamDef(name = "idEstado2", type = "integer")
    }),     
    @FilterDef(name = "VuelosXAeropuertoLlegada", parameters = {
        @ParamDef(name = "upper", type = "timestamp"),
        @ParamDef(name = "lower", type = "timestamp"),
        @ParamDef(name = "idEstado", type = "integer")
    })
   
})
@Filters({
    @Filter(name = "AeropuertoxPais", condition = "UPPER(pais) = UPPER(:Pais)"),
    @Filter(name = "AeropuertoxEstado", condition = "estado = :Estado"),
    @Filter(name = "AeropuertoxCiudad", condition = "UPPER(ciudad) = UPPER(:Ciudad)"),})
public class Aeropuerto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAeropuerto;
    private String nombre;
    private int capacidadMax;
    private int capacidadActual;
    private int coordX;
    private int coordY;
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
    @Filters({
        @Filter(name = "VuelosXAeropuertoSalida", condition = ":lower < fechaSalida AND fechaSalida < :upper AND estado = :idEstado"),
        @Filter(name = "VuelosXAeropuertoSalidaAux", condition = ":lower < fechaSalida AND fechaSalida < :upper AND (estado <> :idEstado1 AND estado <> :idEstado2 )")
    })
    private List<Vuelo> vuelosSalida;
    @OneToMany(mappedBy = "destino")
    @Filters({
        @Filter(name = "VuelosXAeropuertoLlegada", condition = ":lower < fechaSalida AND fechaSalida < :upper AND estado = :idEstado")
    })
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
}
