/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Cliente;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import beans.Parametro;
import beans.Aeropuerto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario",
    query = "from Usuario"),
    @NamedQuery(name="LoginUsuario",
        query="from Usuario where login = :login and estado.valorUnico = 'ACTV'")
})

@FilterDefs({
    @FilterDef(name = "UsuarioxIdperfil",
    parameters =
    @ParamDef(name = ":idperfil", type = "integer")),
    @FilterDef(name = "UsuarioxIdaeropuerto",
    parameters =
    @ParamDef(name = ":idaeropuerto", type = "integer")),
    @FilterDef(name = "UsuarioxIdcliente",
    parameters =
    @ParamDef(name = ":idcliente", type = "integer")),
    @FilterDef(name = "UsuarioxLogin",
    parameters =
    @ParamDef(name = ":login", type = "string")),
    @FilterDef(name = "UsuarioxEstado",
    parameters =
    @ParamDef(name = ":estado", type = "integer"))    

})
@Filters({
    @Filter(name = "UsuarioxIdperfil", condition = "idPerfil = :idperfil"),
    @Filter(name = "UsuarioxIdaeropuerto", condition = "idAeropuerto = :idaeropuerto"),
    @Filter(name = "UsuarioxIdcliente", condition = "idCliente = :idcliente"),
    @Filter(name = "UsuarioxLogin", condition = "Login = :login"),
    @Filter(name = "UsuarioxEstado", condition = "Estado = :estado"),    
})

//select C.nombres, C.Apellidos,P.nombre, A.nombre, ,C.CorreoElectronico, U.estado,C.TipoDoc, C.NumDoc     
//from cliente C, usuario U, perfil P, aeropuerto A 
//where C.idcliente=U.idcliente and U.idPerfil=P.idPerfil and U.idAeropuerto=A.idAeropuerto
//and C.Nombres like nombres and C.Apellidos like apellidos and C.NumDoc like numdoc and C.TipoDoc like tipodoc       
// 

public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;
    
    @ManyToOne
    @JoinColumn(name = "idAeropuerto")
    private Aeropuerto aeropuerto;
    
    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    private String logIn;
    
    @ManyToOne
    @JoinColumn(name = "estado")
    private Parametro estado;
    
    private int numAcceso;
    private boolean primerAcceso;
    
    @OneToMany(mappedBy="usuario")
    private List<Contrasena> contrasenias;
    
    public List<Contrasena> getContrasenias() {
        return contrasenias;
    }

    public void setContrasenias(List<Contrasena> contrasenias) {
        this.contrasenias = contrasenias;
    }
    
    public int getIdUsuario(){
    return idUsuario;
    }    

    public void setIdUsuario(int idUsuario){
    this.idUsuario=idUsuario;
    }    

    public Perfil getPerfil() {
    return perfil;
    }

    public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
    }

    public String getLogIn(){
    return logIn;
    }    

    public void setLogIn(String logIn){
    this.logIn=logIn;
    }   

    public int getNumAcceso(){
    return numAcceso;
    }    

    public void setNumAcceso(int numAcceso){
    this.numAcceso=numAcceso;
    }   

    public boolean getPrimerAcceso(){
    return primerAcceso;
    }    

    public void setPrimerAcceso(boolean primerAcceso){
    this.primerAcceso=primerAcceso;
    }

    public Aeropuerto getIdAeropuerto() {
    return aeropuerto;
    }

    public void setIdAeropuerto(Aeropuerto aeropuerto) {
    this.aeropuerto = aeropuerto;
    }

    public Cliente getIdCliente() {
    return cliente;
    }

    public void setIdCliente(Cliente cliente) {
    this.cliente = cliente;
    }

    public Parametro getEstado() {
    return estado;
    }

    public void setEstado(Parametro estado) {
    this.estado = estado;
    }      
    
}
