/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.Recocido;
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

        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
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
            String aOrigen,
            String aDestino,
            String aActual,
            Parametro registrado,
            String remitente,
            String destinatario,
            float monto,
            Parametro moneda,
            int numEnvio,
            int numPaq,
            Parametro docPago,
            int numDocPago,
            float impuesto,
            String fechaRegistro,
            String fechaRecojo) {

        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();

        Envio e = new Envio();
        try {

            Transaction tx = s.beginTransaction();
            Query q;
            Parametro p;

            Aeropuerto a = null;


            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aOrigen));
            a = (Aeropuerto) q.uniqueResult();
            e.setOrigen(a);

            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aDestino));
            a = (Aeropuerto) q.uniqueResult();
            e.setDestino(a);

            q = s.getNamedQuery("AeropuertosxID").setFirstResult(0);
            q.setParameter("idaero", Integer.parseInt(aActual));
            a = (Aeropuerto) q.uniqueResult();
            e.setActual(a);
            q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", registrado.getValorUnico());
            q.setParameter("tipo", registrado.getTipo());
            p = (Parametro) q.uniqueResult();
            e.setEstado(p);

            q = s.getNamedQuery("ClienteXID").setFirstResult(0);
            q.setParameter("idcliente", Integer.parseInt(remitente));
            e.setRemitente((Cliente) q.uniqueResult());

            q = s.getNamedQuery("ClienteXID").setFirstResult(0);
            q.setParameter("idcliente", Integer.parseInt(destinatario));
            e.setDestinatario((Cliente) q.uniqueResult());

            e.setMonto(monto * (1 + impuesto));

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

            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaRegistroDate = null;
            Date fechaRecojoDate = null;
            try {

                fechaRegistroDate = formatoDelTexto.parse(fechaRegistro);
                fechaRecojoDate = formatoDelTexto.parse(fechaRecojo);

            } catch (ParseException ex) {

                ex.printStackTrace();

            }
            e.setFechaRegistro(fechaRegistroDate);
            e.setFechaRecojo(fechaRecojoDate);

            calcularRuta(e);

            numEnvio = (Integer) s.save(e);
            e.setIdEnvio(numEnvio);
            numDocPago = numEnvio;
            e.setNumDocVenta(numDocPago);
            //s.saveOrUpdate(e);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            s.close();
        }
        return e;
    }

    public boolean calcularRuta(Envio envio) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();

        try {
            Query q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            Parametro p;

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
            double alfaGrasp = Integer.parseInt(p.getValor());

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

            q = s.getNamedQuery("VuelosXFecha");
            q.setParameter("fechaRegistro", envio.getFechaRegistro());
            List<Vuelo> vuelos = q.list();

            Recocido recocido = new Recocido(k, temperaturaInicial, temperaturaFinal, alfaSA, alfaGrasp, pParada, intentos, envio, vuelos, costoAlmacen);
            ArrayList<Vuelo> solucion = recocido.simular();
            ArrayList<Escala> escalas = new ArrayList<Escala>();
            Escala e;
            int i = 1;
            Date fecha = envio.getFechaRegistro();

            q.setParameter("tipo", "ESTADO_ESCALA");
            q.setParameter("valorUnico", "ACTV");
            p = (Parametro) q.uniqueResult();

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
        } finally {
            s.close();
        }
        return false;
    }

    public List<Envio> buscar(Aeropuerto actual, Aeropuerto origen, Aeropuerto destino, Parametro estado, Cliente cliente, String numEnvio) {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Envio> envios = null;
        
        try {
            Query q = s.getNamedQuery("Envios");
            
            if (isInteger(numEnvio)) {
                Filter f_numEnvio = s.enableFilter("EnviosXNumEnvio");
                f_numEnvio.setParameter("idEnvio", Integer.parseInt(numEnvio));
            }
            
            if (actual != null){
                Filter f_actual = s.enableFilter("EnviosXActual");
                f_actual.setParameter("idAeropuerto", actual.getIdAeropuerto());
            }
            
            if (origen != null){
                Filter f_origen = s.enableFilter("EnviosXOrigen");
                f_origen.setParameter("idAeropuerto", origen.getIdAeropuerto());
            }
            
            if (destino != null){
                Filter f_destino = s.enableFilter("EnviosXDestino");
                f_destino.setParameter("idAeropuerto", origen.getIdAeropuerto());
            }
            
            if (estado != null){
                Filter f_estado = s.enableFilter("EnviosXEstado");
                f_estado.setParameter("idEstado", estado.getIdParametro());
            }
            
            if (cliente != null){
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
}
