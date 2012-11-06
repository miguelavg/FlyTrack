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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author joao
 */

@Entity
@Table(name = "Accion")
public class Accion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccion;

    @ManyToOne
    @JoinColumn(name = "idPadre")
    private Accion accionPadre;

    private int nivel;

    private String nombre;
    
    //En esta variable estan los perfiles que ejecutan dicha accion, encapsulado
    //a traves de la clase Permiso
    @OneToMany(mappedBy="accion")
    private List<Permiso> permisos;
      
    public int getIdAccion(){
        return idAccion;
    }    

    public Integer getNivel() {
        return nivel;
    }

    public String getNombre(){
        return nombre;
    }    

    public void setNombre(String nombre){
        this.nombre=nombre;
    }    

    public Accion getAccionPadre() {
        return accionPadre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

}

