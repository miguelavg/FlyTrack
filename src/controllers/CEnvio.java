/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logic.Recocido;
import logic.VueloLite;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Filter;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author ronald
 */
public class CEnvio {

    public ArrayList<Parametro> llenarCombo(String tipo) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<Parametro> p = null;
        try {


            Query q;
            q = s.getNamedQuery("ParametrosXTipo");

            q.setParameter("tipo", tipo);
            p = (ArrayList<Parametro>) q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return p;
    }

    public Envio agregarEnvio(
            Aeropuerto aOrigen,
            Aeropuerto aDestino,
            Aeropuerto aActual,
            String estado,
            Cliente remitente,
            Cliente destinatario,
            float monto,
            Parametro moneda,
            int numEnvio,
            int numPaq,
            Parametro docPago,
            int numDocPago,
            float impuesto,
            String fechaRegistro,
            String fechaRecojo) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        Envio e = new Envio();
        try {

            Transaction tx = s.beginTransaction();
            Query q;
            Parametro p;


            e.setOrigen(aOrigen);
            e.setDestino(aDestino);
            e.setActual(aActual);

            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", "PROG");
            q.setParameter("tipo", "ESTADO_ENVIO");
            p = (Parametro) q.uniqueResult();
            e.setEstado(p);


            e.setRemitente(remitente);
            e.setDestinatario(destinatario);
            e.setMonto(numPaq * monto * (1 + impuesto));

            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", moneda.getValorUnico());
            q.setParameter("tipo", moneda.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setMoneda(p);

            e.setNumPaquetes(numPaq);

            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", docPago.getValorUnico());
            q.setParameter("tipo", docPago.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setTipoDocVenta(p);

            Date fechaRegistroDate = new Date();
            Date fechaRecojoDate = null;
            e.setFechaRegistro(fechaRegistroDate);
            e.setFechaRecojo(fechaRecojoDate);

            calcularRuta(e);

            numEnvio = (Integer) s.save(e);
            e.setIdEnvio(numEnvio);
            numDocPago = numEnvio;
            e.setNumDocVenta(numDocPago);
            s.saveOrUpdate(e);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            s.close();
        }
        return e;
    }

    public boolean calcularRuta(Envio envio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();

        try {
            Query q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            Parametro p;

       //   Coger parámetros...
            
            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "temperatura_inicial");
            p = (Parametro) q.uniqueResult();
            double temperaturaInicial = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "temperatura_final");
            p = (Parametro) q.uniqueResult();
            double temperaturaFinal = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "k_sa");
            p = (Parametro) q.uniqueResult();
            int k = Integer.parseInt(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "alfa_sa");
            p = (Parametro) q.uniqueResult();
            double alfaSA = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "alfa_grasp");
            p = (Parametro) q.uniqueResult();
            double alfaGrasp = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "porcentaje_parada");
            p = (Parametro) q.uniqueResult();
            double pParada = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "num_intentos");
            p = (Parametro) q.uniqueResult();
            int intentos = Integer.parseInt(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "costo_almacen_usd_hora");
            p = (Parametro) q.uniqueResult();
            double costoAlmacen = Double.parseDouble(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "limite_llegada_dias");
            p = (Parametro) q.uniqueResult();
            int limite_forward = Integer.parseInt(p.getValor());

            q.setParameter("tipo", "SA_PARAM");
            q.setParameter("valorUnico", "limite_historico_dias");
            p = (Parametro) q.uniqueResult();
            int limite_backward = Integer.parseInt(p.getValor());

            q = s.getNamedQuery("Aeropuertos");
            List<Aeropuerto> aeros = q.list();

            long iFuturo = envio.getFechaRegistro().getTime() + limite_forward * 24 * 60 * 60 * 1000;
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(iFuturo);            
            Date futuro = cal.getTime();

            long iPasado = envio.getFechaRegistro().getTime() - limite_backward * 24 * 60 * 60 * 1000;
            cal.setTimeInMillis(iPasado);            
            Date pasado = cal.getTime();
            
            Filter f_vuelos_s = s.enableFilter("VuelosXAeropuertoSalida");
            f_vuelos_s.setParameter("lower", envio.getFechaRegistro());
            f_vuelos_s.setParameter("upper", futuro);
            
            Filter f_vuelos_l = s.enableFilter("VuelosXAeropuertoLlegada");
            f_vuelos_l.setParameter("lower", envio.getFechaRegistro());
            f_vuelos_l.setParameter("upper", futuro);

       //   Consultar los aeropuertos con sus vuelos de salida
            
            for (Aeropuerto a : aeros) {
                
                a.getVuelosSalida().size();
                a.getVuelosLlegada().size();
                
                if (a.getIdAeropuerto() == envio.getOrigen().getIdAeropuerto()) {
                    envio.setOrigen(a);
                }
                if (a.getIdAeropuerto() == envio.getDestino().getIdAeropuerto()) {
                    envio.setDestino(a);
                }
            }
            
            s.disableFilter("VuelosXAeropuertoSalida");
            s.disableFilter("VuelosXAeropuertoLlegada");
            
       //   Recuperar los promedios de los vuelos históricos
            
            q = s.createQuery("select  v.origen.idAeropuerto, v.destino.idAeropuerto, avg(v.capacidadActual/v.capacidadMax) from Vuelo v where :lower < fechaSalida AND fechaSalida < :upper group by v.origen, v.destino order by 1 ,2");
            q.setParameter("upper", envio.getFechaRegistro());
            q.setParameter("lower", pasado);
            
            List<Object[]> lista = q.list();
            ArrayList<VueloLite> vuelosL = new ArrayList <VueloLite>();
            for(Object[] o : lista){
                vuelosL.add(new VueloLite((Integer)o[0], (Integer)o[1], (Double)o[2]));
            }
            
       //   Comenzamos la simulación...    

            Recocido recocido = new Recocido(k, temperaturaInicial, temperaturaFinal, alfaSA, alfaGrasp, pParada, intentos, envio, costoAlmacen, vuelosL);
            ArrayList<Vuelo> solucion = recocido.simular();
            ArrayList<Escala> escalas = new ArrayList<Escala>();
            Escala e;
            int i = 1;
            Date fecha = envio.getFechaRegistro();

            q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            q.setParameter("tipo", "ESTADO_ESCALA");
            q.setParameter("valorUnico", "ACTV");
            p = (Parametro) q.uniqueResult();

            envio.setEscalas(escalas);

            for (Vuelo v : solucion) {
                e = new Escala();
                e.setEnvio(envio);
                e.setVuelo(v);
                e.setNumEscala(i);
                e.setFechaInicio(fecha);
                e.setEstado(p);
                fecha = v.getFechaLlegada();
                i++;

                envio.getEscalas().add(e);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return false;
    }

    public List<Envio> buscar(Aeropuerto actual, Aeropuerto origen, Aeropuerto destino, Parametro estado, Cliente cliente, String numEnvio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Envio> envios = null;

        try {
            Query q = s.getNamedQuery("Envios");

            if (isInteger(numEnvio)) {
                Filter f_numEnvio = s.enableFilter("EnviosXNumEnvio");
                f_numEnvio.setParameter("idEnvio", Integer.parseInt(numEnvio));
            }

            if (actual != null) {
                Filter f_actual = s.enableFilter("EnviosXActual");
                f_actual.setParameter("idAeropuerto", actual.getIdAeropuerto());
            }

            if (origen != null) {
                Filter f_origen = s.enableFilter("EnviosXOrigen");
                f_origen.setParameter("idAeropuerto", origen.getIdAeropuerto());
            }

            if (destino != null) {
                Filter f_destino = s.enableFilter("EnviosXDestino");
                f_destino.setParameter("idAeropuerto", origen.getIdAeropuerto());
            }

            if (estado != null) {
                Filter f_estado = s.enableFilter("EnviosXEstado");
                f_estado.setParameter("idEstado", estado.getIdParametro());
            }

            if (cliente != null) {
                Filter f_destino = s.enableFilter("EnviosXCliente");
                f_destino.setParameter("idCliente", cliente.getIdCliente());
            }

            envios = q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return envios;

    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Tarifa calcularTarifa(Aeropuerto origen, Aeropuerto destino) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Tarifa tarifa = null;
        try {
            Query q = s.getNamedQuery("Tarifa").setMaxResults(1);
            Parametro p;
            q.setParameter("idorigen", origen.getIdAeropuerto());
            q.setParameter("iddestino", destino.getIdAeropuerto());
            tarifa = (Tarifa) q.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return tarifa;
    }
}
