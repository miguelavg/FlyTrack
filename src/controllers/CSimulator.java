/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Parametro;
import beans.Sesion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import logic.AeroLite;
import logic.EnvioLite;
import logic.RecocidoLite;
import logic.VueloLite;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author miguelavg
 */
public class CSimulator {

    private static AeroLite buscarAeroLite(int id, ArrayList<AeroLite> aeroLites) {

        int lo = 0;
        int hi = aeroLites.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            AeroLite a = aeroLites.get(mid);
            if (id < a.getId()) {
                hi = mid - 1;
            } else if (id > a.getId()) {
                lo = mid + 1;
            } else {
                return aeroLites.get(mid);
            }
        }
        return null;
    }

    public static ArrayList<AeroLite> calcularAeropuertos() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<AeroLite> aeroLites = null;
        Random rnd = new Random();
        
        try {

            Query q = s.getNamedQuery("Aero");
            List<Aeropuerto> aeros = q.list();
            aeroLites = new ArrayList<AeroLite>();

            for (Aeropuerto a : aeros) {
                int capacidadActual = (int) (rnd.nextDouble() * a.getCapacidadMax());
                aeroLites.add(new AeroLite(a.getIdAeropuerto(), a.getNombre(), a.getCapacidadMax(), capacidadActual));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return aeroLites;
    }

    public static ArrayList<VueloLite> calcularVuelos(ArrayList<AeroLite> aeroLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<VueloLite> vueloLites = null;

        try {
            Query q = s.createQuery("select v.origen.idAeropuerto, v.destino.idAeropuerto,  count(v),  avg(v.capacidadMax), avg(v.costoAlquiler), avg(v.capacidadActual/v.capacidadMax) from Vuelo v group by v.origen, v.destino order by 1, 2");
            List<Object[]> objVuelos = q.list();
            vueloLites = new ArrayList<VueloLite>();
            
            q = s.createQuery("select count(v) from Vuelo v");
            int aVuelos = (Integer) q.uniqueResult();
            
            Parametro pNumVuelos = CParametro.buscarXValorUnicoyTipo("SIM_PARAM", "num_vuelos");
            int numVuelos = Integer.parseInt(pNumVuelos.getValor());
            
            double regla =  numVuelos / ((double) aVuelos);

            for (Object[] obj : objVuelos) {
                AeroLite origen = buscarAeroLite((Integer) obj[0], aeroLites);
                AeroLite destino = buscarAeroLite((Integer) obj[1], aeroLites);
                int num = ((Long) obj[2]).intValue();
                //int cap = ((Double) obj[3]).intValue() * regla;
                double alq = (Double) obj[4];
                double plleno = (Double) obj[5];
                //vueloLites.add(new VueloLite(origen, destino, num, cap, alq, plleno));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return vueloLites;
    }

    public static ArrayList<EnvioLite> calcularEnvios(ArrayList<AeroLite> aeroLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<EnvioLite> envioLites = null;

        try {
            Query q = s.createQuery("select e.origen.idAeropuerto, e.destino.idAeropuerto,  count(e) from Envio e group by e.origen, e.destino order by 1, 2");
            List<Object[]> objVuelos = q.list();
            envioLites = new ArrayList<EnvioLite>();

            for (Object[] obj : objVuelos) {
                AeroLite origen = buscarAeroLite((Integer) obj[0], aeroLites);
                AeroLite destino = buscarAeroLite((Integer) obj[1], aeroLites);
                int num = ((Long) obj[2]).intValue();
                envioLites.add(new EnvioLite(origen, destino, num));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return envioLites;
    }

    public static void simular(ArrayList<EnvioLite> envioLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Query q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            Parametro p;

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "alfa_grasp");
            p = (Parametro) q.uniqueResult();
            double alfaGrasp = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "porcentaje_parada");
            p = (Parametro) q.uniqueResult();
            double pParada = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SIM_PARAM");
            q.setParameter("valorUnico", "k_grasp");
            p = (Parametro) q.uniqueResult();
            int intentos = Integer.parseInt(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "costo_almacen_usd_hora");
            p = (Parametro) q.uniqueResult();
            double costoAlmacen = Double.parseDouble(p.getValor());


            RecocidoLite rl = new RecocidoLite(alfaGrasp, intentos, costoAlmacen, envioLites);


            for (EnvioLite e : envioLites) {
                rl.grasp(e);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            return;

        }

    }
}
