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
 * @author miguelavg
 */
@Entity
@Table(name = "Cliente")
@NamedQueries({
    @NamedQuery(name = "Clientes",
    query = "from Cliente "),
    @NamedQuery(name = "ClienteXID",
    query = "from Cliente where idCliente = :idcliente ")
})
@FilterDefs({
    @FilterDef(name = "ClientesXNombre",
    parameters =
    @ParamDef(name = "nombres", type = "string")),
    
    @FilterDef(name = "ClientesXApellido",
    parameters =
    @ParamDef(name = "apellidos", type = "string")),
    
    @FilterDef(name = "ClientesXNumDoc",
    parameters =
    @ParamDef(name = "numdoc", type = "string")),
    
    @FilterDef(name = "ClientesXTipoDoc",
    parameters =
    @ParamDef(name = "tipodoc", type = "integer"))
        
})
@Filters({
    
    @Filter(name = "ClientesXNombre", condition = "UPPER(Nombres) like UPPER(:nombres)"),
    
    @Filter(name = "ClientesXApellido", condition = "UPPER(Apellidos) like UPPER(%:apellidos%)"),
    
    @Filter(name = "ClientesXNumDoc", condition = "Numdoc = :numdoc"),
    
    @Filter(name = "ClientesXTipoDoc", condition = "tipodoc = :tipodoc"),
    
})

public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String Nombres;
    private String Apellidos;
    private String NumDoc;
    private String Telefono;
    private String CorreoElectronico;
    //private String Celular;
    //private String Direccion;
    @ManyToOne
    @JoinColumn(name = "TipoDoc")
    private Parametro tipoDoc;
    @ManyToOne
    @JoinColumn(name = "Ciudad")
    private Parametro ciudad;
    @ManyToOne
    @JoinColumn(name = "Pais")
    private Parametro pais;
    @OneToMany(mappedBy = "destinatario")
    private List<Envio> enviosRecibidos;
    @OneToMany(mappedBy = "remitente")
    private List<Envio> enviosRemitidos;

    public Cliente() {
    }

    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        this.Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String numDoc) {
        this.NumDoc = numDoc;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String geteMail() {
        return CorreoElectronico;
    }

    public void seteMail(String eMail) {
        this.CorreoElectronico = eMail;
    }

    public Parametro getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Parametro tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Parametro getCiudad() {
        return ciudad;
    }

    public void setCiudad(Parametro ciudad) {
        this.ciudad = ciudad;
    }

    public Parametro getPais() {
        return pais;
    }

    public void setPais(Parametro pais) {
        this.pais = pais;
    }

    public List<Envio> getEnviosRecibidos() {
        return enviosRecibidos;
    }

    public void setEnviosRecibidos(List<Envio> enviosRecibidos) {
        this.enviosRecibidos = enviosRecibidos;
    }

    public List<Envio> getEnviosRemitidos() {
        return enviosRemitidos;
    }

    public void setEnviosRemitidos(List<Envio> enviosRemitidos) {
        this.enviosRemitidos = enviosRemitidos;
    }
    
   
}

