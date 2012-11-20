/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author miguelavg
 */
public class EnvioLite {

    private AeroLite origen;
    private AeroLite destino;
    private int evt;
    private int num;
    private boolean completado;
    private int completados;

    public EnvioLite(AeroLite origen, AeroLite destino, int num) {
        this.origen = origen;
        this.destino = destino;
        this.num = num;
        this.completado = false;
        this.completados = 0;
    }
    
    public AeroLite getOrigen() {
        return origen;
    }

    public void setOrigen(AeroLite origen) {
        this.origen = origen;
    }

    public AeroLite getDestino() {
        return destino;
    }

    public void setDestino(AeroLite destino) {
        this.destino = destino;
    }

    public int getEvt() {
        return evt;
    }

    public void setEvt(int evt) {
        this.evt = evt;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public int getCompletados() {
        return completados;
    }

    public void setCompletados(int completados) {
        this.completados = completados;
    }
    
    
}
