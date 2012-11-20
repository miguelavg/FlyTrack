/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author miguelavg
 */
public class RecocidoLite {

    private double alfaGrasp;                   // coeficiente de relajación del grasp construcción
    private int intentos;                       // intentos de malos grasp
    private double vCostoAlmacen;               // costo de los almacenes
    private ArrayList<VueloLite> solucion;      // ruta solución
    private ArrayList<VueloLite> alterado;      // ruta alterada
    private ArrayList<EnvioLite> envios;

    public RecocidoLite(double alfaGrasp, int intentos, double vCostoAlmacen, ArrayList<EnvioLite> envios) {
        this.alfaGrasp = alfaGrasp;
        this.intentos = intentos;
        this.vCostoAlmacen = vCostoAlmacen;
        this.envios = envios;
    }

    private int estadoEnergia(ArrayList<VueloLite> vuelos, int evt) {
        try {
            double iCostoAlmacen;
            double iCostoEnvio;
            double costoAlmacen = 0;
            double costoEnvio = 0;
            double costo;
            double pLleno;
            double pCapacidad;
            VueloLite vuelo;

            for (int i = 0; i < vuelos.size(); i++) {
                vuelo = vuelos.get(i);

                iCostoAlmacen = this.vCostoAlmacen * (vuelo.getEvt() - evt);
                costoAlmacen = costoAlmacen + iCostoAlmacen;

                pCapacidad = Math.max(0.8 * vuelo.getCapacidadMax(), vuelo.getCapacidadActual() + 1);

                iCostoEnvio = (double) vuelo.getAlquiler() / pCapacidad;
                costoEnvio = costoEnvio + iCostoEnvio;
            }

            costo = costoEnvio + costoAlmacen;
            return (int) costo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private double boltzmann(double dEnergia, double temperatura) {
        return Math.exp(-1 * (dEnergia / temperatura));
    }

    private ArrayList<VueloLite> liteGrasp(AeroLite aOrigen, AeroLite aDestino, int evt, double alfa) {
        try {
            AeroLite aActual = aOrigen;
            Random rnd = new Random();

            int dActual = evt;
            int iActual = aActual.getId();
            int iFinal = aDestino.getId();

            ArrayList<VueloLite> posibles;
            ArrayList<VueloLite> construccion = new ArrayList<VueloLite>();
            ArrayList<VueloLite> rcl;
            VueloLite aleatorio;

            int beta = Integer.MAX_VALUE;
            int tau = 0;
            int e;

            // Mientras no hayamos llegado al final...

            while (iActual != iFinal && aActual.getCapacidadMax() > aActual.getCapacidadActual()) {
                posibles = new ArrayList<VueloLite>();

                // Calcular los vuelos posibles, el beta y el tau

                for (VueloLite vuelo : aActual.getVuelosSalida()) {

                    if (vuelo.getCapacidadMax() >= vuelo.getCapacidadActual()
                            && aActual.getCapacidadActual() <= aActual.getCapacidadMax()) {
                        posibles.add(vuelo);
                        ArrayList<VueloLite> wrap = new ArrayList<VueloLite>();
                        wrap.add(vuelo);
                        e = estadoEnergia(wrap, dActual);

                        if (e < beta && e >= 0) {
                            beta = e;
                        }
                        if (e > tau && e >= 0) {
                            tau = e;
                        }

                    } else {
                        if (vuelo.getCapacidadMax() < vuelo.getCapacidadActual()) {
                            vuelo.setNecesidad(vuelo.getNecesidad() + 1);
                        } else {
                            aActual.setNecesidad(aActual.getNecesidad() + 1);
                        }
                    }
                }

                rcl = new ArrayList<VueloLite>();

                for (VueloLite vuelo : posibles) {
                    ArrayList<VueloLite> wrap = new ArrayList<VueloLite>();
                    wrap.add(vuelo);
                    e = estadoEnergia(wrap, dActual);

                    if (beta <= e && e <= beta + alfa * (tau - beta)) {
                        rcl.add(vuelo);
                    }
                }

                if (rcl.isEmpty()) {
                    return null;
                }

                aleatorio = rcl.get(rnd.nextInt(rcl.size()));
                construccion.add(aleatorio);

                aActual = aleatorio.getDestino();
                iActual = aActual.getId();
                dActual = aleatorio.getEvt() + aleatorio.getDur();
                beta = Integer.MAX_VALUE;
                tau = 0;

            }

            if (iActual != iFinal) {
                aActual.setNecesidad(aActual.getNecesidad() + 1);
                return null;
            }

            return construccion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<VueloLite> grasp(EnvioLite envio) {

        int energia = Integer.MAX_VALUE;
        int iEnergia;
        this.solucion = null;


        for (int i = 0; i < this.intentos; i++) {
            this.alterado = liteGrasp(envio.getOrigen(), envio.getDestino(), envio.getEvt(), this.alfaGrasp);
            if (this.alterado != null) {
                iEnergia = estadoEnergia(this.alterado, 0);
                if (iEnergia <= energia) {
                    this.solucion = this.alterado;
                    energia = iEnergia;
                }
            }
        }

        return solucion;
    }
}
