/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Parametro;
import beans.Sesion;
import beans.Vuelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import logic.AeroLite;
import logic.EnvioLite;
import logic.MovimientoAlmacen;
import logic.RecocidoLite;
import logic.VueloLite;
import org.hibernate.Filter;
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

    public ArrayList<AeroLite> calcularAeropuertos(Date futuro, Date pasado) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<AeroLite> aeroLites = null;
        Random rnd = new Random();

        try {

            Query q = s.getNamedQuery("Aero");

            Filter f_vuelos_s = s.enableFilter("VuelosSalidaSim");
            f_vuelos_s.setParameter("lower", pasado);
            f_vuelos_s.setParameter("upper", futuro);

            Filter f_vuelos_l = s.enableFilter("VuelosLlegadaSim");
            f_vuelos_l.setParameter("lower", pasado);
            f_vuelos_l.setParameter("upper", futuro);

            List<Aeropuerto> aeros = q.list();
            aeroLites = new ArrayList<AeroLite>();


            for (Aeropuerto a : aeros) {
                int capacidadActual = a.getCapacidadActual();
                aeroLites.add(new AeroLite(a.getIdAeropuerto(), a.getNombre() + ", " + a.getPais().getValor() + ", " + a.getCiudad().getValor(), a.getCapacidadMax(), a.getCapacidadMax(), capacidadActual, a.getCoordX(), a.getCoordY()));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return aeroLites;
    }

    public class CustomComparator implements Comparator<MovimientoAlmacen> {

        @Override
        public int compare(MovimientoAlmacen m1, MovimientoAlmacen m2) {
            if (m1.getFecha().before(m2.getFecha())) {
                return -1;
            }
            if (m1.getFecha().after(m2.getFecha())) {
                return 1;
            }
            return 0;
        }
    }

    private int calcularActual(Aeropuerto aeropuerto) {
        int actual = aeropuerto.getCapacidadActual();
        int suma = actual;
        int movimientos = 1;

        ArrayList<MovimientoAlmacen> moves = new ArrayList<MovimientoAlmacen>();
        for (Vuelo v : aeropuerto.getVuelosSalida()) {
            moves.add(new MovimientoAlmacen(v.getFechaSalida(), "I", v.getCapacidadActual()));
        }
        for (Vuelo v : aeropuerto.getVuelosLlegada()) {
            moves.add(new MovimientoAlmacen(v.getFechaLlegada(), "O", v.getCapacidadActual()));
        }

        Collections.sort(moves, new CustomComparator());

        for (MovimientoAlmacen m : moves) {
            if (m.getTipo().equals("I")) {
                actual = actual + m.getCantidad();
            }
            if (m.getTipo().equals("O")) {
                actual = actual - m.getCantidad();
            }

            suma = suma + actual;
            movimientos++;
        }

        int prom = suma / movimientos;

        if (prom < 0) {
            return 0;
        }
        return prom;
    }

    public static ArrayList<VueloLite> calcularVuelos(Date ahora, Date pasado, int num_vuelos, ArrayList<AeroLite> aeroLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<VueloLite> vueloLites = null;

        try {
            Query q = s.createQuery("select v.origen.idAeropuerto, v.destino.idAeropuerto,  count(v),  avg(v.capacidadMax), avg(v.costoAlquiler), avg(v.capacidadActual/cast(v.capacidadMax as float)) from Vuelo v where v.fechaLlegada <= :upper AND v.fechaSalida >= :lower group by v.origen, v.destino order by 1, 2");
            q.setParameter("upper", ahora);
            q.setParameter("lower", pasado);
            List<Object[]> objVuelos = q.list();
            vueloLites = new ArrayList<VueloLite>();

            q = s.createQuery("select count(v) from Vuelo v where v.fechaLlegada <= :upper AND v.fechaSalida >= :lower");
            q.setParameter("upper", ahora);
            q.setParameter("lower", pasado);
            int aVuelos = ((Long) q.uniqueResult()).intValue();


            double regla = num_vuelos / ((double) aVuelos);

            for (Object[] obj : objVuelos) {
                AeroLite origen = buscarAeroLite((Integer) obj[0], aeroLites);
                AeroLite destino = buscarAeroLite((Integer) obj[1], aeroLites);
                Long numPre = (Long) obj[2];
                int num = (int) (numPre.doubleValue() * regla);
                int cap = ((Double) obj[3]).intValue();
                double alq = (Double) obj[4];
                double plleno = (Double) obj[5];
                vueloLites.add(new VueloLite(origen, destino, num, num, cap, cap, alq, plleno));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return vueloLites;
    }

    public static ArrayList<EnvioLite> calcularEnvios(Date ahora, Date pasado, int num_envios, ArrayList<AeroLite> aeroLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<EnvioLite> envioLites = null;

        try {
            Query q = s.createQuery("select e.origen.idAeropuerto, e.destino.idAeropuerto,  sum(e.numPaquetes) from Envio e where e.fechaRegistro <= :upper AND e.fechaRegistro >= :lower  group by e.origen, e.destino order by 1, 2");
            q.setParameter("upper", ahora);
            q.setParameter("lower", pasado);
            List<Object[]> objVuelos = q.list();
            envioLites = new ArrayList<EnvioLite>();

            q = s.createQuery("select sum(e.numPaquetes) from Envio e where e.fechaRegistro <= :upper AND e.fechaRegistro >= :lower");
            q.setParameter("upper", ahora);
            q.setParameter("lower", pasado);
            int aEnvios = ((Long) q.uniqueResult()).intValue();

            double regla = num_envios / ((double) aEnvios);

            for (Object[] obj : objVuelos) {
                AeroLite origen = buscarAeroLite((Integer) obj[0], aeroLites);
                AeroLite destino = buscarAeroLite((Integer) obj[1], aeroLites);

                Long numPre = (Long) obj[2];
                double dnum = (numPre.doubleValue() * regla);
                if (dnum > 0.5 && dnum < 1) {
                    dnum = 1;
                }
                int num = (int) dnum;

                envioLites.add(new EnvioLite(origen, destino, num, num));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return envioLites;
    }

    public static int simular(ArrayList<EnvioLite> envioLites, ArrayList<AeroLite> aeroLites, ArrayList<VueloLite> vueloLites) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        int fallas = 0;

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

            q.setParameter("tipo", "SIM_PARAM");
            q.setParameter("valorUnico", "p_umbral");
            p = (Parametro) q.uniqueResult();
            double ulleno = Double.parseDouble(p.getValor());

            double plleno;



            RecocidoLite rl = new RecocidoLite(alfaGrasp, intentos, costoAlmacen, envioLites);


            for (EnvioLite e : envioLites) {
                for (AeroLite a : aeroLites) {
                    a.setCongestiona(false);
                    plleno = a.getCapacidad_actual() / ((double) a.getCapacidad_maxima());

                    if (plleno > ulleno) {
                        int tCongestiona = a.getTiempo_congestiona();
                        a.setTiempo_congestiona(tCongestiona + 1);
                    }
                }

                for (VueloLite v : vueloLites) {
                    v.setCongestiona(false);
                    plleno = v.getCapacidad_actual() / ((double) v.getCapacidad_maxima());

                    if (plleno > ulleno) {
                        int tCongestiona = v.getTiempo_congestiona();
                        v.setTiempo_congestiona(tCongestiona + 1);
                    }
                }

                fallas = fallas + rl.grasp(e);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return fallas;

    }
}
