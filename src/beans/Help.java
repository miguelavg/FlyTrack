/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author miguelavg
 */
public class Help {
    ArrayList<Vuelo> vh;
    int fallos;
    int exitos;

    public Help() {
        this.fallos = 0;
        this.exitos = 0;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getExitos() {
        return exitos;
    }

    public void setExitos(int exitos) {
        this.exitos = exitos;
    }
    
    public void sumaFallo(){
        this.fallos = this.fallos + 1;
    }
    
    public void sumaExito(){
        this.exitos = this.exitos + 1;
    }
    
    
}
