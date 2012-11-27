/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author miguelavg
 */
public class VueloLite {
    private AeroLite origen;            //  origen del vuelo
    private AeroLite destino;           //  destino del vuelo
    private int evt;                    //  número de vuelo
    private int evt_nuevo;
    private int dur;                    //  duración de vuelo
    private int num;                    //  cantidad de vuelos
    private int num_nuevo;
    private int capacidad_actual;       //  capacidad actual del vuelo
    private int capacidad_actual_nuevo;
    private int capacidad_maxima;       //  capacidad máxima del vuelo
    private int capacidad_maxima_nuevo;
    private int envios_congestiona ;    //  envíos que congestiona
    private int envios_congestiona_nuevo;
    private int tiempo_congestiona;     //  tiempo congestionado
    private int tiempo_congestiona_nuevo;
    private boolean congestiona;        //  si está congestionado más del tiempo del umbral
    private boolean congestiona_nuevo;
    private double alquiler;            //  alquiler del vuelo
    private double plleno;              //  % de qué tan lleno está
    private double plleno_nuevo;
    private boolean pintar_rojo;        //  si se debe pintar de pintar_rojo
    private boolean pintar_rojo_nuevo;

    public VueloLite(AeroLite origen, AeroLite destino, int num, int num_nuevo, int capacidad_maxima, int capacidad_maxima_nuevo, double alquiler, double plleno) {
        this.origen = origen;
        this.destino = destino;
        this.num = num;
        this.num_nuevo = num_nuevo;
        this.capacidad_maxima = capacidad_maxima;
        this.capacidad_maxima_nuevo = capacidad_maxima_nuevo;
        this.plleno = plleno;
        this.plleno_nuevo = plleno * (capacidad_maxima_nuevo / capacidad_maxima);
        
        this.capacidad_actual = (int) (capacidad_maxima * plleno);
        this.capacidad_actual_nuevo = (int) (capacidad_maxima_nuevo * plleno_nuevo);
        
        this.alquiler = alquiler;
        
        this.envios_congestiona = 0;
        this.envios_congestiona_nuevo = 0;
        this.congestiona = false;
        this.congestiona_nuevo = false;
        this.tiempo_congestiona = 0;
        this.tiempo_congestiona_nuevo = 0;
        this.pintar_rojo = false;
        this.pintar_rojo_nuevo = false;
    }
    
        public VueloLite(VueloLite v) {
        this.origen = v.origen;
        this.destino = v.destino;
        this.num = v.num_nuevo;
        this.num_nuevo = v.num_nuevo;
        this.capacidad_maxima = v.capacidad_maxima_nuevo;
        this.capacidad_maxima_nuevo = v.capacidad_maxima_nuevo;
        this.plleno = v.plleno;
        this.plleno_nuevo = v.plleno_nuevo;
        
        this.capacidad_actual = v.capacidad_actual;
        this.capacidad_actual_nuevo = v.capacidad_actual_nuevo;
        
        this.alquiler = v.alquiler;
        
        this.envios_congestiona = 0;
        this.envios_congestiona_nuevo = 0;
        this.congestiona = false;
        this.congestiona_nuevo = false;
        this.tiempo_congestiona = 0;
        this.tiempo_congestiona_nuevo = 0;
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

    public int getEvt_nuevo() {
        return evt_nuevo;
    }

    public void setEvt_nuevo(int evt_nuevo) {
        this.evt_nuevo = evt_nuevo;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
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

    public int getCapacidad_actual() {
        return capacidad_actual;
    }

    public void setCapacidad_actual(int capacidad_actual) {
        this.capacidad_actual = capacidad_actual;
    }

    public int getCapacidad_actual_nuevo() {
        return capacidad_actual_nuevo;
    }

    public void setCapacidad_actual_nuevo(int capacidad_actual_nuevo) {
        this.capacidad_actual_nuevo = capacidad_actual_nuevo;
    }

    public int getCapacidad_maxima() {
        return capacidad_maxima;
    }

    public void setCapacidad_maxima(int capacidad_maxima) {
        this.capacidad_maxima = capacidad_maxima;
    }

    public int getCapacidad_maxima_nuevo() {
        return capacidad_maxima_nuevo;
    }

    public void setCapacidad_maxima_nuevo(int capacidad_maxima_nuevo) {
        this.capacidad_maxima_nuevo = capacidad_maxima_nuevo;
    }

    public int getEnvios_congestiona() {
        return envios_congestiona;
    }

    public void setEnvios_congestiona(int envios_congestiona) {
        this.envios_congestiona = envios_congestiona;
    }

    public int getEnvios_congestiona_nuevo() {
        return envios_congestiona_nuevo;
    }

    public void setEnvios_congestiona_nuevo(int envios_congestiona_nuevo) {
        this.envios_congestiona_nuevo = envios_congestiona_nuevo;
    }

    public int getTiempo_congestiona() {
        return tiempo_congestiona;
    }

    public void setTiempo_congestiona(int tiempo_congestiona) {
        this.tiempo_congestiona = tiempo_congestiona;
    }

    public int getTiempo_congestiona_nuevo() {
        return tiempo_congestiona_nuevo;
    }

    public void setTiempo_congestiona_nuevo(int tiempo_congestiona_nuevo) {
        this.tiempo_congestiona_nuevo = tiempo_congestiona_nuevo;
    }

    public boolean isCongestiona() {
        return congestiona;
    }

    public void setCongestiona(boolean congestiona) {
        this.congestiona = congestiona;
    }

    public boolean isCongestiona_nuevo() {
        return congestiona_nuevo;
    }

    public void setCongestiona_nuevo(boolean congestiona_nuevo) {
        this.congestiona_nuevo = congestiona_nuevo;
    }

    public double getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public double getPlleno() {
        return plleno;
    }

    public void setPlleno(double plleno) {
        this.plleno = plleno;
    }

    public double getPlleno_nuevo() {
        return plleno_nuevo;
    }

    public void setPlleno_nuevo(double plleno_nuevo) {
        this.plleno_nuevo = plleno_nuevo;
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
    public String toString(){
        return String.valueOf(this.getNum());
    }
}
