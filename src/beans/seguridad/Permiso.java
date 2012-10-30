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
import javax.persistence.Table;

/**
 *
 * @author joao
 */
@Entity
@Table(name = "Permiso")

public class Permiso implements Serializable {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idPerfil;
      private int idAccion;

      public int getIdPerfil(){
      return idPerfil;
      }    
      
      public void setIdPerfil(int idPerfil){
      this.idPerfil=idPerfil;
      }    
      
      public int getIdAccion(){
      return idAccion;
      }    
      
      public void setIdAccion(int idAccion){
      this.idAccion=idAccion;
      }    
      
}
