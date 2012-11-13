/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Parametro;
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
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "Contrasena")
@NamedQueries({
    @NamedQuery(name = "ContraseniaActivaXUsuario",
                query = "from Contrasena where usuario = :usuario and estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "ContrasenaxId",
                query = "from Contrasena where idUsuario = :idusuario"),
    @NamedQuery(name = "ContrasenaActivaXidUsuario",
                query = "from Contrasena where usuario.idUsuario = :idUsuario and estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "UltimasContrasenasXUsuarioParaValidar",
                query = "from Contrasena "
        + "where usuario.idUsuario = :idUsuario and estado.valorUnico = 'INCTV'"
        + "order by fechaactivacion desc limit :limite"),
})
public class Contrasena implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrasena;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
            
    private char[] text;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoUso;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    
    @ManyToOne
    @JoinColumn(name = "estado")
    private Parametro estado;
    
    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
    
    public Date getFechaUltimoUso() {
        return fechaUltimoUso;
    }

    public void setFechaUltimoUso(Date fechaUltimoUso) {
        this.fechaUltimoUso = fechaUltimoUso;
    }
    
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public int getIdContrasena(){
    return idContrasena;
    }    

    public void setIdContrasena(int idContrasena){
    this.idContrasena=idContrasena;
    }    

    public char[] getText(){
    return text;
    }    

    public void setText(char[] text ){
    this.text=text;
    }  
    
    public Parametro getEstado() {
    return estado;
    }

    public void setEstado(Parametro estado) {
    this.estado = estado;
    }

  public Usuario getUsuario() {
      return usuario;
  }
        
      public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
    }
  
}
