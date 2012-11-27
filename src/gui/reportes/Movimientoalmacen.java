/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Envio;
import beans.Escala;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jugox
 */
public class Movimientoalmacen {
    private Date fecha;
    private String Movimiento;
    private String Motivo;
    private int numpaq;

    /**
     * @return the fecha
     */
    
    
    
    
    
    
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the Movimiento
     */
    public String getMovimiento() {
        return Movimiento;
    }

    /**
     * @param Movimiento the Movimiento to set
     */
    public void setMovimiento(String Movimiento) {
        this.Movimiento = Movimiento;
    }

    /**
     * @return the Motivo
     */
    public String getMotivo() {
        return Motivo;
    }

    /**
     * @param Motivo the Motivo to set
     */
    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    /**
     * @return the numpaq
     */
    public int getNumpaq() {
        return numpaq;
    }

    /**
     * @param numpaq the numpaq to set
     */
    public void setNumpaq(int numpaq) {
        this.numpaq = numpaq;
    }
    
    
    
    
}
