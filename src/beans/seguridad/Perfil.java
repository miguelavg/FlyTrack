/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Parametro;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author joao
 */
public class Perfil implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerfil;
    
    private String nombre;
    
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Estado")
    private Parametro estado;       

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

}
