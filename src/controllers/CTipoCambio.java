/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Aeropuerto;
import beans.Parametro;
import beans.TipoCambio;
import beans.Sesion;
import beans.Tarifa;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author miguelavg
 */
public class CTipoCambio {

    public List<TipoCambio> buscar(Parametro origen, Parametro destino) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<TipoCambio> tipos = null;

        try {
            Query q = s.getNamedQuery("TiposCambio");

            if (origen != null) {
                Filter f_origen = s.enableFilter("TiposCambioXOrigen");
                f_origen.setParameter("idMoneda", origen.getIdParametro());
            }

            if (destino != null) {
                Filter f_destino = s.enableFilter("TiposCambioXDestino");
                f_destino.setParameter("idMoneda", destino.getIdParametro());
            }

            tipos = q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return tipos;
    }

    public TipoCambio buscarDolar(String valorUnico) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        TipoCambio tipo = null;

        try {
            Query q = s.getNamedQuery("TiposCambioXValosresUnicos");
            q.setParameter("monedaOrigen", valorUnico);
            q.setParameter("monedaDestino", "DOL");
            tipo = (TipoCambio) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return tipo;
    }

    public TipoCambio buscarId(int id) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        TipoCambio tipo = null;

        try {
            Query q = s.getNamedQuery("TiposCambioXId");
            q.setParameter("id", id);
            tipo = (TipoCambio) q.uniqueResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return tipo;
    }

    public String validar(TipoCambio tipoCambio, boolean isNuevo, String vTipoCambio, Parametro monedaOrigen, Parametro monedaDestino) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        TipoCambio t;
        try {

            if (monedaOrigen == null || monedaDestino == null || vTipoCambio == null || vTipoCambio.isEmpty()) {
                error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
            }

            if (monedaOrigen != null && monedaDestino != null) {
                Query q = s.getNamedQuery("TiposCambioXMonedas");
                q.setParameter("idMonedaOrigen", monedaOrigen.getIdParametro());
                q.setParameter("idMonedaDestino", monedaDestino.getIdParametro());
                List<TipoCambio> tipos = q.list();

                if (tipos.size() > 0) {
                    t = tipos.get(0);;
                    if (isNuevo) {
                        error_message = error_message + CValidator.buscarError("ERROR_FT017") + "\n";
                    } else {
                        if (t.getIdTipoCambio() != tipoCambio.getIdTipoCambio()) {
                            error_message = error_message + CValidator.buscarError("ERROR_FT017") + "\n";
                        }
                    }
                }
                
                if(monedaOrigen.getIdParametro() == monedaDestino.getIdParametro()){
                    error_message = error_message + CValidator.buscarError("ERROR_FT003") + "\n";
                }

            }

            if (!CValidator.isDouble(vTipoCambio) && Double.parseDouble(vTipoCambio) > 0) {
                error_message = error_message + CValidator.buscarError("ERROR_FT004") + "\n";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return error_message;
    }

    public boolean guardar(TipoCambio tipoCambio) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        boolean siGuardo = true;
        try {
            Transaction tx = s.beginTransaction();
            s.saveOrUpdate(tipoCambio);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            siGuardo = false;
        } finally {
            s.close();
        }
        return siGuardo;
    }

    public String verificarTipoCambioDolar(String valorUnico) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        TipoCambio tipo;
        String error_message = "";
        
        if(valorUnico.equals("DOL")){
            return error_message;
        }

        try {
            Query q = s.getNamedQuery("TiposCambioXValosresUnicos");
            q.setParameter("monedaOrigen", valorUnico);
            q.setParameter("monedaDestino", "DOL");
            tipo = (TipoCambio) q.uniqueResult();

            if (tipo == null) {
                error_message = error_message + CValidator.buscarError("ERROR_FT012") + "\n";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            error_message = error_message + CValidator.buscarError("ERROR_FT012") + "\n";
        } finally {
            s.close();
        }
        return error_message;
    }
}
