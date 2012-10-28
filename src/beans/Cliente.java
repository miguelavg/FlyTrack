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

/**
 *
 * @author miguelavg
 */
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String nombres;
    private String apellidos;
    private String numDoc;
    private String telefono;
    private String eMail;
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
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

