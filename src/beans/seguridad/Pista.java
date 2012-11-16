/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author msolorzano
 */

@Entity
@Table(name="Pista")
public class Pista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPista;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuarioR;
    
    private String usuario;
    
    private String moduloPrincipal;
    
    private String moduloSecundario;
    
    private String clase;
    
    private String metodo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private String estadoAnterior;
    
    private String estadoActual;
    
    private String descripcion;

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public Usuario getUsuarioR() {
        return usuarioR;
    }

    public void setUsuarioR(Usuario usuarioR) {
        this.usuarioR = usuarioR;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getModuloPrincipal() {
        return moduloPrincipal;
    }

    public void setModuloPrincipal(String moduloPrincipal) {
        this.moduloPrincipal = moduloPrincipal;
    }

    public String getModuloSecundario() {
        return moduloSecundario;
    }

    public void setModuloSecundario(String moduloSecundario) {
        this.moduloSecundario = moduloSecundario;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
