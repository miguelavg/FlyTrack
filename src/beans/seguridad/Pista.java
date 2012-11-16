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
}
