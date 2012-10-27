/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Cliente;
import beans.Parametro;
import gui.aeropuerto.Aeropuerto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author joao
 */
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

//    private String nombres;
//    private String apellidos;
//    private String correoElectronico;
//    @ManyToOne
//    @JoinColumn(name = "tipoDoc")
//    private Parametro tipoDoc;
//
//    private String numDoc;

    private String logIn;
    
    
    @ManyToOne
    @JoinColumn(name = "estado")
    private Parametro estado;
    
    private int numAcceso;
    private boolean primerAcceso;
    
    @OneToMany(mappedBy="usuario")
    private List<Contrasena> contrasenias;
    
    
      public int getIdUsuario(){
      return idUsuario;
      }    
      
      public void setIdUsuario(int idUsuario){
      this.idUsuario=idUsuario;
      }    
    
//      public String getNombres(){
//      return nombres;
//      }    
//      
//      public void setNombres(String nombres){
//      this.nombres=nombres;
//      }    
//      
//      public String getApellidos(){
//      return apellidos;
//      }    
//      
//      public void setApellidos(String apellidos){
//      this.apellidos=apellidos;
//      }   
      
      public String getLogIn(){
      return logIn;
      }    
      
      public void setLogIn(String logIn){
      this.logIn=logIn;
      }   
      
//      public String getCorreoElectronico(){
//      return correoElectronico;
//      }    
//      
//      public void setCorreoElectronico(String correoElectronico){
//      this.correoElectronico=correoElectronico;
//      }   
//      
//      public String getNumDoc(){
//      return numDoc;
//      }    
//      
//      public void setNumDoc(String numDoc){
//      this.numDoc=numDoc;
//      }   
      
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
      
//      public Parametro getTipoDoc() {
//      return tipoDoc;
//      }
//
//      public void setTipoDoc(Parametro tipoDoc) {
//      this.tipoDoc = tipoDoc;
//      }
      
      
    
}
