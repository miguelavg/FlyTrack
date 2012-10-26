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
public class Accion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idAccion;
    
      @ManyToOne
      @JoinColumn(name = "idAccionPadre")
      private Parametro AccionPadre;

      @ManyToOne
      @JoinColumn(name = "Nivel")
      private Parametro nivel;

      private String nombre;
      
      public Accion() {
      } 
      
      public int getIdAccion(){
      return idAccion;
      }    
      
      public void setIdAccion(int accion){
      this.idAccion=accion;
      }    
      
      public Parametro getIdAccionPadre() {
      return AccionPadre;
      }

      public void setidAccionPadre(Parametro AccionPadre) {
      this.AccionPadre = AccionPadre;
      }
      
      public Parametro getNivel() {
      return nivel;
      }

      public void setNivel(Parametro nivel) {
      this.nivel = nivel;
      }
      
            
      public String getNombre(){
      return nombre;
      }    
      
      public void setNombre(String nombre){
      this.nombre=nombre;
      }    
      
      
}

