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
    private int num_nuevo;
    private boolean completado;
    private boolean completado_nuevo;
    private int completados;
    private int completados_nuevo;
    private boolean pintar_rojo;
    private boolean pintar_rojo_nuevo;

    public EnvioLite(AeroLite origen, AeroLite destino, int num, int num_nuevo) {
        this.origen = origen;
        this.destino = destino;
        this.num = num;
        this.num_nuevo = num_nuevo;
        this.completado = false;
        this.completado_nuevo = false;
        this.completados = 0;
        this.completados_nuevo = 0;
        this.pintar_rojo = false;
        this.pintar_rojo_nuevo = false;
    }
    
        public EnvioLite(EnvioLite e) {
        this.origen = e.origen;
        this.destino = e.destino;
        this.num = e.num_nuevo;
        this.num_nuevo = e.num_nuevo;
        this.completado = false;
        this.completado_nuevo = false;
        this.completados = 0;
        this.completados_nuevo = 0;
        this.pintar_rojo = false;
        this.pintar_rojo_nuevo = false;
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

    public int getNum_nuevo() {
        return num_nuevo;
    }

    public void setNum_nuevo(int num_nuevo) {
        this.num_nuevo = num_nuevo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public boolean isCompletado_nuevo() {
        return completado_nuevo;
    }

    public void setCompletado_nuevo(boolean completado_nuevo) {
        this.completado_nuevo = completado_nuevo;
    }

    public int getCompletados() {
        return completados;
    }

    public void setCompletados(int completados) {
        this.completados = completados;
    }

    public int getCompletados_nuevo() {
        return completados_nuevo;
    }

    public void setCompletados_nuevo(int completados_nuevo) {
        this.completados_nuevo = completados_nuevo;
    }

    public boolean isPintar_rojo() {
        return pintar_rojo;
    }

    public void setPintar_rojo(boolean pintar_rojo) {
        this.pintar_rojo = pintar_rojo;
    }

    public boolean isPintar_rojo_nuevo() {
        return pintar_rojo_nuevo;
    }

    public void setPintar_rojo_nuevo(boolean pintar_rojo_nuevo) {
        this.pintar_rojo_nuevo = pintar_rojo_nuevo;
    }


    @Override
    public String toString() {
        return String.valueOf(this.getNum());
    }
}
