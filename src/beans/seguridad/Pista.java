/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
/**
 *
 * @author msolorzano
 */

@Entity
@Table(name="Pista")
@NamedQueries({
    @NamedQuery(name = "Pistas", query = "from Pista order by fecha desc"),
})
@FilterDefs({
    @FilterDef(name = "PistasXUsuario" , parameters = @ParamDef(name = "usuario", type = "String")),
    @FilterDef(name = "PistasXFechaIni", parameters = @ParamDef(name = "fecha"  , type = "Timestamp")),
    @FilterDef(name = "PistasXFechaFin", parameters = @ParamDef(name = "fecha"  , type = "Timestamp")),
    @FilterDef(name = "PistasXAccion"  , parameters = @ParamDef(name = "accion" , type = "String")),
})
@Filters({
    @Filter(name = "PistasXUsuario" , condition = "usuario  = :usuario"),
    @Filter(name = "PistasXFechaIni", condition = "fecha    > :fechaIni"),
    @Filter(name = "PistasXFechaFin", condition = "fecha    < :fechaFin"),
    @Filter(name = "PistasXAccion"  , condition = "accion   = :accion"),
})
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
    
    private String accion;
        
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private String mensaje;

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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
