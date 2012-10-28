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
@NamedQueries({
    @NamedQuery(name = "Parametros",
    query = "from Parametro where estado = true"),
    @NamedQuery(name = "ParametrosXTipoXValorUnico",
    query = "from Parametro where tipo = :tipo and valorUnico = :valorUnico"),
    @NamedQuery(name = "ParametrosXTipo",
    query = "from Parametro where tipo = :tipo and estado = true"),
    @NamedQuery(name = "ParametrosAdmin",
    query = "from Parametro"),
        
})
@FilterDefs({
    @FilterDef(name = "ParametroHijosXTipo",
    parameters =
    @ParamDef(name = "tipo", type = "string"))
})
public class Parametro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParametro;
    private String tipo;
    private String valor;
    private String valorUnico;
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "idPadre")
    private Parametro padre;
    @OneToMany(mappedBy = "padre")
    @Filters({
        @Filter(name = "ParametroHijosXTipo", condition = "tipo = :tipo and estado = true")
    })
    private List<Parametro> hijos;

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Parametro getPadre() {
        return padre;
    }

    public void setPadre(Parametro padre) {
        this.padre = padre;
    }

    public List<Parametro> getHijos() {
        return hijos;
    }

    public void setHijos(List<Parametro> hijos) {
        this.hijos = hijos;
    }
    
    @Override
    public String toString(){
        return valor;
    }
}
