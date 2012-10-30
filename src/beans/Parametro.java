package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
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
    query = "from Parametro where tipo = :tipo and valorUnico = :valorUnico and estado = true"),
    @NamedQuery(name = "ParametrosXTipo",
    query = "from Parametro where tipo = :tipo and estado = true"),
    @NamedQuery(name = "ParametrosAdmin",
    query = "from Parametro"),
    @NamedQuery(name = "ParametrosAeropuerto",
    query = "from Parametro where tipo = :tipo"),
    @NamedQuery(name = "ParametrosSeguridad",
    query = "from Parametro where tipo = 'SEGURIDAD' and valorUnico = :valorUnico and estado = true")
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
    
    @ManyToOne
    @JoinColumn(name = "idPadre")
    private Parametro padre;
    
    
    private String valor;
    private String valorUnico;
    private String tipo;
    private boolean estado;
    
    @OneToMany(mappedBy = "padre", fetch = FetchType.EAGER)
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
