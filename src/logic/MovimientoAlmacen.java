/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Date;

/**
 *
 * @author miguelavg
 */
public class MovimientoAlmacen {
    private Date fecha;
    private int cantidad;
    private String tipo;

    public MovimientoAlmacen(Date fecha, String tipo, int cantidad) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
