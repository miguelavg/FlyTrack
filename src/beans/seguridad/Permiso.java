/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "Permiso")
@NamedQueries({
    @NamedQuery(name = "PermisosXPerfil",
                query = "from Permiso where perfil.idPerfil = :idperfil")
})

public class Permiso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermiso;
    
    @ManyToOne
    @JoinColumn(name="idAccion")
    private Accion accion;
    
    @ManyToOne
    @JoinColumn(name="idPerfil")
    private Perfil perfil;

    public int getIdPermiso() {
        return idPermiso;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    
      
}
