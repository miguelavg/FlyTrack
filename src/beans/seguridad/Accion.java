/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.seguridad;

import beans.Parametro;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @JoinColumn(name = "idAccionPadre")
    private Accion AccionPadre;

    private int nivel;

    private String nombre;
      
    public int getIdAccion(){
        return idAccion;
    }    

    public Accion getIdAccionPadre() {
        return AccionPadre;
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

}

