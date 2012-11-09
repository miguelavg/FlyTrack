/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.seguridad.Permiso;
import java.util.List;

/**
 *
 * @author msolorzano
 */
public class CPermiso {

    public static boolean buscarPermiso(List<Permiso> permisos, String nombre, int nivel, String nombrePadre){
        
        for(Permiso permiso : permisos){
            boolean coincideNombre  = permiso.getAccion().getNombre().equals(nombre);
            boolean coincideNivel   = permiso.getAccion().getNivel() == nivel;
            boolean coincidePadre;
            if(nombrePadre == null){
                if (permiso.getAccion().getPadre() == null){
                    coincidePadre = true;
                }
                else{
                    coincidePadre = false;
                }
            }
            else{
                if(permiso.getAccion().getPadre() == null){
                    coincidePadre = false;
                }
                else{
                    coincidePadre = permiso.getAccion().getPadre().getNombre().equals(nombrePadre);
                }
            }
            
            if(coincideNombre && coincideNivel && coincidePadre){
                return true;
            }
                
        }
        
        return false;
    }
}
