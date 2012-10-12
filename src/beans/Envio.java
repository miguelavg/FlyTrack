/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Envio")
public class Envio implements Serializable {

    @Id
    private int idEnvio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    private int numPaquetes;
    private double monto;
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
    @ManyToOne
    @JoinColumn(name = "idActual")
    private Aeropuerto actual;
    @ManyToOne
    @JoinColumn(name = "idRemitente")
    private Cliente remitente;
    @ManyToOne
    @JoinColumn(name = "idDestinatario")
    private Cliente destinatario;
    @OneToMany(mappedBy = "envio")
    private List<Escala> escalas;

    public Envio() {
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumPaquetes() {
        return numPaquetes;
    }

    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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

    public Aeropuerto getActual() {
        return actual;
    }

    public void setActual(Aeropuerto actual) {
        this.actual = actual;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public List<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<Escala> escalas) {
        this.escalas = escalas;
    }
}
