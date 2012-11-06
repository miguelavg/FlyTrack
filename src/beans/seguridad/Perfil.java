/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Parametro;
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
 * @author joao
 */
@Entity
@Table(name = "Perfil")
@NamedQueries({
    @NamedQuery(name = "Perfil",
    query = "from Perfil"),
    @NamedQuery(name = "PerfilxId",
    query = "from Perfil where idPerfil = :idperfil "),
    @NamedQuery(name = "PerfilXNombre",
    query = "from Perfil where UPPER(Nombre) like UPPER(:nombre)")

})

@FilterDefs({
    @FilterDef(name = "PerfilxIdperfil",
    parameters =
    @ParamDef(name = "idperfil", type = "integer")),
    @FilterDef(name = "PerfilxNombre",
    parameters =
    @ParamDef(name = "nombre", type = "string")),
    @FilterDef(name = "PerfilxDescripcion",
    parameters =
    @ParamDef(name = "descripcion", type = "string")),
    @FilterDef(name = "PerfilxEstado",
    parameters =
    @ParamDef(name = "estado", type = "integer"))    

})
@Filters({
    @Filter(name = "PerfilxIdperfil", condition = "idperfil = :idperfil"),
    @Filter(name = "PerfilxNombre", condition = "nombre = :nombre"),
    @Filter(name = "PerfilxDescripcion", condition = "descripcion = :descripcion"),
    @Filter(name = "PerfilxEstado", condition = "estado = :estado"), 
})




public class Perfil implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerfil;
    
    private String nombre;
    
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;       
    
    @OneToMany(mappedBy="perfil")
    private List<Permiso> permisos;
    
    @OneToMany(mappedBy="perfil")
    private List<Usuario> usuarios;


    public int getIdPerfil(){
    return idPerfil;
    }    

    public void setIdPerfil(int idPerfil){
    this.idPerfil=idPerfil;
    }   

    public String getNombre(){
    return nombre;
    }    

    public void setNombre(String nombre){
    this.nombre=nombre;
    }   

    public String getDescripcion(){
    return descripcion;
    }    

    public void setDescripcion(String descripcion){
    this.descripcion=descripcion;
    }   

    public Parametro getEstado() {
    return estado;
    }

    public void setEstado(Parametro estado) {
    this.estado = estado;
    }

   @Override
    public String toString(){
        return nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }
    
}
