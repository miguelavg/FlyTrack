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
    @NamedQuery(name = "LoginUsuario",
                query = "from Usuario where login = :login and estado.valorUnico = 'ACTV'"),
    @NamedQuery(name = "UsuarioxId",
                query = "from Usuario where idUsuario = :idusuario "),
    @NamedQuery(name = "UsuarioxNombreUsuario",
                query = "from Usuario where logIn = :username "),
    @NamedQuery(name = "UsuarioxNumDoc",
                query = "from Usuario where numdoc = :documento "),
    @NamedQuery(name = "UsuarioxIdentidad",
                query = "from Usuario where tipodoc = :tipodoc and numdoc = :numdoc ")
})
@FilterDefs({
    @FilterDef( name = "UsuarioxIdperfil",
                parameters = @ParamDef(name = "idperfil", type = "integer")),
    @FilterDef( name = "UsuarioxIdaeropuerto",
                parameters = @ParamDef(name = "idaeropuerto", type = "integer")),
    @FilterDef( name = "UsuarioxTipoDoc",
                parameters = @ParamDef(name = "tipodoc", type = "integer")),
    @FilterDef( name = "UsuarioxNombre",
                parameters = @ParamDef(name = "nombres", type = "string")),
    @FilterDef( name = "UsuarioxLogin",
                parameters = @ParamDef(name = "login", type = "string")),
    @FilterDef( name = "UsuarioxApellido",
                parameters = @ParamDef(name = "apellidos", type = "string")),
    @FilterDef( name = "UsuarioxNumDoc",
                parameters = @ParamDef(name = "numdoc", type = "string")),
    @FilterDef( name = "UsuarioxEstado",
                parameters = @ParamDef(name = "estado", type = "integer"))
})
@Filters({
    @Filter(name = "UsuarioxIdperfil", condition = "idPerfil = :idperfil"),
    @Filter(name = "UsuarioxIdaeropuerto", condition = "idAeropuerto = :idaeropuerto"),
    @Filter(name = "UsuarioxNombre", condition = "nombres like :nombres"),
    @Filter(name = "UsuarioxLogin", condition = "Login like :login"),
    @Filter(name = "UsuarioxNumDoc", condition = "numdoc like :numdoc"),
    @Filter(name = "UsuarioxApellido", condition = "apellidos like :apellidos"),
    @Filter(name = "UsuarioxEstado", condition = "Estado = :estado"),
    @Filter(name = "UsuarioxTipoDoc", condition = "tipodoc = :tipodoc")
})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;
    
    @ManyToOne
    @JoinColumn(name = "idAeropuerto")
    private Aeropuerto aeropuerto;
    
    private String logIn;
    
    @ManyToOne
    @JoinColumn(name = "estado")
    private Parametro estado;
    
    private int numAcceso;
    private boolean primerAcceso;
    
    @OneToMany(mappedBy = "usuario")
    private List<Contrasena> contrasenias;
    
    private String Nombres;
    private String Apellidos;
    
    @ManyToOne
    @JoinColumn(name = "TipoDoc")
    private Parametro tipoDoc;
    private String NumDoc;
    
    private String Telefono;
    
    @ManyToOne
    @JoinColumn(name = "Ciudad")
    private Parametro ciudad;
    
    @ManyToOne
    @JoinColumn(name = "Pais")
    private Parametro pais;
    
    private String CorreoElectronico;

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

    //se puede mapear la contrasenia activa haciendo un mapeo de uno a muchos
    //donde a continuacion se indique un filtro para detectar la contrasenia activa
    //y sacarla como un atributo
    public List<Contrasena> getContrasenias() {
        return contrasenias;
    }

    public void setContrasenias(List<Contrasena> contrasenias) {
        this.contrasenias = contrasenias;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public int getNumAcceso() {
        return numAcceso;
    }

    public void setNumAcceso(int numAcceso) {
        this.numAcceso = numAcceso;
    }

    public boolean getPrimerAcceso() {
        return primerAcceso;
    }

    public void setPrimerAcceso(boolean primerAcceso) {
        this.primerAcceso = primerAcceso;
    }

    public Aeropuerto getIdAeropuerto() {
        return aeropuerto;
    }

    public void setIdAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public Parametro getEstado() {
        return estado;
    }

    public void setEstado(Parametro estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return logIn;
    }
    
    public String aString(){
        return "IdUsuario = " + idUsuario + 
                " Perfil = " + (perfil != null ? perfil.getNombre() : null) +
                " Aeropuerto = " + (aeropuerto != null ? aeropuerto.getNombre() : null) +
                " Login = " + logIn +
                " Estado = " + (estado != null ? estado.getValor() : null) +
                " Numero de accesos = " + numAcceso +
                " Primer Acceso = " + (primerAcceso ? "Realizado" : "Por realizar") +
                " Nombres = " + Nombres +
                " Apellidos = " + Apellidos +
                " Tipo de Documento = " + (tipoDoc != null ? tipoDoc.getValor() : null) +
                " Numero del Documento = " + NumDoc +
                " Email = " + CorreoElectronico +
                " Telefono = " + Telefono +
                " Ciudad = " + (ciudad != null ? ciudad.getValor() : null) +
                " Pais = " + (pais != null ? pais.getValor() : null);
    }
}
