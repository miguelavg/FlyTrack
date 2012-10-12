package beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Parametro")
public class Parametro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParametro;
    private String tipo;
    private String valor;
    private String valorUnico;
    @ManyToOne
    @JoinColumn(name = "idPadre")
    private Parametro parametro;

    public Parametro() {
    }

    public int getIdParametro() {
        return idParametro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorUnico() {
        return valorUnico;
    }

    public void setValorUnico(String valorUnico) {
        this.valorUnico = valorUnico;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }
}
