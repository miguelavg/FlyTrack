/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Aeropuerto;

/**
 *
 * @author jugox
 */
public class Venta {
    private Aeropuerto aero;
    private String mes;
    private double monto;

    /**
     * @return the aero
     */
    public Aeropuerto getAero() {
        return aero;
    }

    /**
     * @param aero the aero to set
     */
    public void setAero(Aeropuerto aero) {
        this.aero = aero;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }
}
