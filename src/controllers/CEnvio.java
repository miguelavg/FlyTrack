/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logic.Recocido;
import logic.VueloHist;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.ParamDef;

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

    public ArrayList<Parametro> getMonedas() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        ArrayList<Parametro> p = null;
        try {
            Query q;
            q = s.getNamedQuery("MonedasDolarTC");
            p = (ArrayList<Parametro>) q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return p;
    }

    private static String genMail(Envio envio, boolean siRemitente) {
        String ret = "";

        if (envio.getEstado().getValorUnico().equals("PROG")) {
            if (!siRemitente) {
                ret = "Se ha registrado un envío para usted de " + envio.getRemitente().getNombres() + " " + envio.getRemitente().getApellidos() + "\n";
            } else {
                ret = "Usted ha registrado un envío para " + envio.getDestinatario().getNombres() + " " + envio.getDestinatario().getApellidos() + "\n";
            }

        }

        if (envio.getEstado().getValorUnico().equals("XREC")) {
            if (!siRemitente) {
                ret = "El envío ha llegado a su destino y puede pasar a recogerlo al aeropuerto " + envio.getActual() + "\n";
            } else {
                ret = "Su envío ha llegado a su destino y está pendiente de ser recojido por " + envio.getDestinatario().getNombres() + " " + envio.getDestinatario().getApellidos() + "\n";
            }
        }

        if (envio.getEstado().getValorUnico().equals("REC")) {
            if (!siRemitente) {
                ret = "Usted ha recogido su envío. Gracias por utilizar nuestros servicios.\n";
            } else {
                ret = "Su envío ha sido recojido por " + envio.getDestinatario().getNombres() + " " + envio.getDestinatario().getApellidos() + "\n";
            }
        }

        if (envio.getEstado().getValorUnico().equals("IND")) {
            if (!siRemitente) {
                ret = "Se ha cancelado un vuelo en el que su envío iba a viajar y no se ha podido regenerar una ruta válida. Se le contactará en breve.\n";
            } else {
                ret = "Se ha cancelado un vuelo en el que su envío iba a viajar y no se ha podido regenerar una ruta válida. Se le contactará en breve.\n";
            }
        }

        if (envio.getEstado().getValorUnico().equals("CAN")) {
            if (!siRemitente) {
                ret = "Se ha anulado su envío.\n";
            } else {
                ret = "Se ha anulado su envío.\n";
            }
        }

        if (envio.getEstado().getValorUnico().equals("ESC")) {
            if (!siRemitente) {
                ret = "Su envío ha aterrizado en el aeropuerto " + envio.getActual() + "\n";
            } else {
                ret = "Su envío ha aterrizado en el aeropuerto " + envio.getActual() + "\n";
            }
        }

        if (envio.getEstado().getValorUnico().equals("VUE")) {
            Aeropuerto destino = null;
            for (Escala escala : envio.getEscalasOrdenadasAsc()) {
                if (escala.getEstado().getValorUnico().equals("EFE")) {
                    destino = escala.getVuelo().getDestino();
                }
            }
            
            if (destino != null) {
                ret = "Su envío ha despegado con destino al aeropuerto " + destino + ".\n";
            } else {
                ret = "Su envío ha despegado con destino al aeropuerto " + destino + ".\n";
            }
        }


        ret = ret + "\n" + "Su bitácora es la siguiente: \n";

        for (Escala escala : envio.getEscalasOrdenadasAsc()) {
            ret = ret + "+ " + escala.getEstado().getValor() + " >>" + escala.getVuelo().getOrigen().getNombre() + ", " + escala.getVuelo().getOrigen().getCiudad() + ", " + escala.getVuelo().getOrigen().getPais() + " - " + escala.getVuelo().getDestino().getNombre() + ", " + escala.getVuelo().getDestino().getCiudad() + ", " + escala.getVuelo().getDestino().getPais() + " : " + CValidator.formatDate(CEnvio.getFechaSalidaReal(escala)) + " -> " + CValidator.formatDate(CEnvio.getFechaLlegadaReal(escala)) + "\n";
        }

        ret = ret + "\n" + "Soporte FlyTrack";
        return ret;
    }

    public void guardarEnvio(Envio envio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            s.saveOrUpdate(envio.getActual());
            s.saveOrUpdate(envio);
//            ArrayList<Envio> listaenvio= new ArrayList<Envio>();
//            listaenvio.add(envio);   
            //CSerializer.serializar(listaenvio, "PruebaEnvio");
            for (Escala e : envio.getEscalas()) {
                s.saveOrUpdate(e.getVuelo());
            }

            tx.commit();

            enviarCorreos(envio);
        } catch (Exception e) {
            System.out.println("cualquier mierda");
        } finally {
            s.close();
        }
    }
        
    public void guardarEnvio2(Envio envio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        try {
            Transaction tx = s.beginTransaction();
            s.save(envio);
//            System.out.println("Antes del commit");
            tx.commit();
//            System.out.println("Despues del commit");
        } catch (Exception e) {
            System.out.println("La excepcion" + e.getMessage() + e.toString());
        } finally {
            s.close();
        }
    }

    public static void enviarCorreos(Envio envio) {
        try {
            new CMail().sendMail(
                    envio.getRemitente().geteMail(),
                    "[FlyTrack] Envío #" + envio.getIdEnvio(),
                    genMail(envio, true));

            new CMail().sendMail(
                    envio.getDestinatario().geteMail(),
                    "[FlyTrack] Envío #" + envio.getIdEnvio(),
                    genMail(envio, false));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
    }

    public String calcularRuta(Envio envio, Date ahora, int numEscala) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";

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

            q.setParameter("tipo", "ESTADO_VUELO");
            q.setParameter("valorUnico", "PROG");
            p = (Parametro) q.uniqueResult();
            int idProg = p.getIdParametro();


            long iFuturo = ahora.getTime() + limite_forward * 24 * 60 * 60 * 1000;
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(iFuturo);
            Date futuro = cal.getTime();

            long iPasado = ahora.getTime() - limite_backward * 24 * 60 * 60 * 1000;
            cal.setTimeInMillis(iPasado);
            Date pasado = cal.getTime();

            Filter f_vuelos_s = s.enableFilter("VuelosXAeropuertoSalida");
            f_vuelos_s.setParameter("lower", ahora);
            f_vuelos_s.setParameter("upper", futuro);
            f_vuelos_s.setParameter("idEstado", idProg);

            Filter f_vuelos_l = s.enableFilter("VuelosXAeropuertoLlegada");
            f_vuelos_l.setParameter("lower", ahora);
            f_vuelos_l.setParameter("upper", futuro);
            f_vuelos_l.setParameter("idEstado", idProg);

            q = s.getNamedQuery("Aeropuertos");
            List<Aeropuerto> aeros = q.list();

            //   Consultar los aeropuertos con sus vuelos de salida

            for (Aeropuerto a : aeros) {

                for(Vuelo v : a.getVuelosSalida()){
                    v.getIncidencias().size();
                }
                
                for(Vuelo v : a.getVuelosLlegada()){
                    v.getIncidencias().size();
                }

                if (a.getIdAeropuerto() == envio.getOrigen().getIdAeropuerto()) {
                    envio.setOrigen(a);
                }
                if (a.getIdAeropuerto() == envio.getActual().getIdAeropuerto()) {
                    envio.setActual(a);
                }
                if (a.getIdAeropuerto() == envio.getDestino().getIdAeropuerto()) {
                    envio.setDestino(a);
                }
            }

            s.disableFilter("VuelosXAeropuertoSalida");
            s.disableFilter("VuelosXAeropuertoLlegada");

            //   Recuperar los promedios de los vuelos históricos

            q = s.createQuery("select  v.origen.idAeropuerto, v.destino.idAeropuerto, avg(v.capacidadActual/cast(v.capacidadMax as float)) from Vuelo v where :lower < fechaSalida AND fechaSalida < :upper group by v.origen, v.destino order by 1 ,2");
            q.setParameter("upper", ahora);
            q.setParameter("lower", pasado);

            List<Object[]> lista = q.list();
            ArrayList<VueloHist> vuelosL = new ArrayList<VueloHist>();
            for (Object[] o : lista) {
                vuelosL.add(new VueloHist((Integer) o[0], (Integer) o[1], (Double) o[2]));
            }

            //   Comenzamos la simulación...    

            Recocido recocido = new Recocido(k, temperaturaInicial, temperaturaFinal, alfaSA, alfaGrasp, pParada, intentos, envio, costoAlmacen, vuelosL);
            ArrayList<Vuelo> solucion = recocido.simular();

            Escala e;
            Date fecha = ahora;
            q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            q.setParameter("tipo", "ESTADO_ESCALA");
            q.setParameter("valorUnico", "PROG");
            p = (Parametro) q.uniqueResult();

            boolean original = false;
            if (envio.getEscalas() == null || envio.getEscalas().isEmpty()) {
                envio.setEscalas(new ArrayList<Escala>());
                original = true;
            }

            int capacidad;

            if (solucion == null || solucion.size() < 1) {
                error_message = CValidator.buscarError("ERROR_FT013") + "\n";
            } else {
                for (Vuelo v : solucion) {
                    e = new Escala();
                    e.setEnvio(envio);
                    e.setVuelo(v);
                    e.setNumEscala(numEscala++);
                    e.setFechaInicio(fecha);
                    e.setEstado(p);
                    e.setOriginal(original);
                    fecha = v.getFechaLlegada();
                    capacidad = e.getVuelo().getCapacidadActual();
                    e.getVuelo().setCapacidadActual(capacidad + envio.getNumPaquetes());
                    envio.getEscalas().add(e);
                }
            }

            //capacidad = envio.getActual().getCapacidadActual();
            //envio.getActual().setCapacidadActual(capacidad + envio.getNumPaquetes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return error_message;
    }

    public List<Envio> buscar(Aeropuerto actual, Aeropuerto origen, Aeropuerto destino, Parametro estado, Cliente cliente, String numEnvio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Envio> envios = null;

        try {
            Query q = s.getNamedQuery("Envios");

            if (numEnvio != null && !numEnvio.isEmpty()) {
                Filter f_numEnvio = s.enableFilter("EnviosXNumEnvio");
                if (CValidator.isInteger(numEnvio)) {
                    f_numEnvio.setParameter("numEnvio", Integer.parseInt(numEnvio));
                } else {
                    f_numEnvio.setParameter("numEnvio", -1);
                }
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

    public String verificarTarifa(Aeropuerto origen, Aeropuerto destino) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        Tarifa tarifa;
        try {
            Query q = s.getNamedQuery("Tarifa").setMaxResults(1);
            Parametro p;
            q.setParameter("idorigen", origen.getIdAeropuerto());
            q.setParameter("iddestino", destino.getIdAeropuerto());
            tarifa = (Tarifa) q.uniqueResult();

            if (tarifa == null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT011") + "\n";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            error_message = error_message + CValidator.buscarError("ERROR_FT011") + "\n";
        } finally {
            s.close();
        }
        return error_message;
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

    public Envio buscarId(int id) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Envio envio = null;
        try {
            Query q = s.getNamedQuery("EnvioID");
            q.setParameter("idenvio", id);
            envio = (Envio) q.uniqueResult();

            for (Escala escala : envio.getEscalas()) {
                escala.getVuelo().getIncidencias().size();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return envio;
    }

    public String validar(Parametro moneda, Parametro doc, Aeropuerto origen, Aeropuerto actual, Aeropuerto destino, Cliente remitente, Cliente destinatario, Tarifa tarifa, TipoCambio tipoCambio, String numPaquetes) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        try {

            if (moneda == null || doc == null || origen == null || actual == null || destino == null || remitente == null || destinatario == null || numPaquetes == null || numPaquetes.isEmpty()) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }

            if (origen != null && destino != null) {
                Query q = s.getNamedQuery("TiposCambioXMonedas");

                if (origen.getIdAeropuerto() == destino.getIdAeropuerto()) {
                    error_message = error_message + CValidator.buscarError("ERROR_FT008") + "\n";
                }

            }

            if (!CValidator.isInteger(numPaquetes)) {
                error_message = error_message + CValidator.buscarError("ERROR_FT010") + "\n";
            }

            if (tarifa == null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT011") + "\n";
            }

            if (tipoCambio == null && moneda != null && !moneda.getValorUnico().equals("DOL")) {
                error_message = error_message + CValidator.buscarError("ERROR_FT012") + "\n";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return error_message;
    }

    public static int getNextNumDoc(String tipoDoc) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        int numDoc = -1;
        try {
            Query q;
            if (tipoDoc.equals("BOL")) {
                q = s.createSQLQuery("select nextval('envio_numboleta_seq')");
                numDoc = ((BigInteger) q.uniqueResult()).shortValue();
            }
            if (tipoDoc.equals("FAC")) {
                q = s.createSQLQuery("select nextval('envio_numfactura_seq')");
                numDoc = ((BigInteger) q.uniqueResult()).shortValue();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return numDoc;
    }
    
     public static List<Parametro> llenarComboEst() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Parametro> ListaTipoEst = null;
                
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "ESTADO_ENVIO");
            ListaTipoEst = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoEst;
    }
     
      public static List<Parametro> llenarTipoDoc() {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Parametro> ListaTipoEst = null;
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            q = s.getNamedQuery("ParametrosAeropuerto");
            q.setParameter("tipo", "TIPO_DOC");
            ListaTipoEst = q.list();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            s.close();
        }

        return ListaTipoEst;
    }

      public static List<Envio> BuscarRepoEnviar
                (Parametro Estado, 
                 Aeropuerto 
                 PaisOrigen, 
                 Aeropuerto PaisDestino, 
                 Calendar fechini, 
                 Calendar fechfinal, 
                 Parametro TipoDoc) {

        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Envio> ListaEnvio = null;
        Date ini = null;
        Date fin = null;
        String inii = null;

        try {
                Transaction tx = s.beginTransaction();
                Query q;

            
           q = s.getNamedQuery("Envios");

            if (PaisOrigen != null) {
                Filter f_origen = s.enableFilter("EnviosXOrigen");
                f_origen.setParameter("idAeropuerto", PaisOrigen.getIdAeropuerto());
            }

            if (PaisDestino != null) {
                Filter f_destino = s.enableFilter("EnviosXDestino");
                f_destino.setParameter("idAeropuerto", PaisDestino.getIdAeropuerto());
            }
            if (fechini != null) {
                ini = fechini.getTime();
                Filter f_inisalida = s.enableFilter("EnviosXFechaIni");
                f_inisalida.setParameter("fecharegistro", ini);
            }

            if (fechfinal != null) {
                fin = fechfinal.getTime();
                Filter f_finllega = s.enableFilter("EnviosXFechaFin");
                f_finllega.setParameter("fecharegistro", fin);
            }

            if (Estado != null) {
                Filter f_estado = s.enableFilter("EnviosXEstado");
                f_estado.setParameter("Estado", Estado.getIdParametro());
            }

            ListaEnvio = q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return ListaEnvio;

    }

      public static Date getFechaSalidaReal(Escala escala) {
        Vuelo v = escala.getVuelo();
        Date d = null;

        for (Incidencia i : v.getIncidencias()) {
            if (i.getEstado().getValorUnico().equals("DES")) {
                d = i.getFecha();
                break;
            }
        }

        if (d == null) {
            d = v.getFechaSalida();
        }

        return d;
    }

    public static Date getFechaLlegadaReal(Escala escala) {
        Vuelo v = escala.getVuelo();
        Date d = null;

        for (Incidencia i : v.getIncidencias()) {
            if (i.getEstado().getValorUnico().equals("ATE")) {
                d = i.getFecha();
                break;
            }
        }

        if (d == null) {
            d = v.getFechaLlegada();
        }

        return d;
    }
}
