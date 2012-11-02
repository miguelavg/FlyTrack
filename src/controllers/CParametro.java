/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
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
public class CParametro {

    public List<Parametro> buscar(String valor, String valorUnico, String tipo, Parametro padre) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Parametro> params = null;

        try {
            Query q = s.getNamedQuery("ParametrosAdmin");

            if (valor != null && !valor.isEmpty()) {
                Filter f_actual = s.enableFilter("ParametrosXValor");
                f_actual.setParameter("valor", "%" + valor + "%");
            }

            if (valorUnico != null && !valorUnico.isEmpty()) {
                Filter f_origen = s.enableFilter("ParametrosXValorUnico");
                f_origen.setParameter("valorUnico", "%" + valorUnico + "%");
            }

            if (tipo != null && !tipo.isEmpty()) {
                Filter f_destino = s.enableFilter("ParametrosXTipo");
                f_destino.setParameter("tipo", "%" + tipo + "%");
            }

            if (padre != null) {
                Filter f_estado = s.enableFilter("ParametrosXPadre");
                f_estado.setParameter("idPadre", padre.getIdParametro());
            }

            params = q.list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return params;
    }

    public String validar(Parametro parametro, boolean isNuevo, String valor, String valorUnico, String tipo) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        Parametro p;
        try {
            Query q = s.getNamedQuery("ParametrosXTipoXValorUnico");
            q.setParameter("valorUnico", valorUnico);
            q.setParameter("tipo", tipo);
            List<Parametro> params = q.list();
            
            if(valor == null || valor.isEmpty() || valorUnico == null || valorUnico.isEmpty() || tipo == null || tipo.isEmpty()){
                error_message = error_message + "Los campos Valor, Valor único y Tipo son obligatorios.\n";
            }
                

            if (params.size() > 0) {
                p = params.get(0);
                if (isNuevo) {
                    error_message = error_message + "Ya existe un parámetro con la misma combinación Valor único y Tipo.\n";
                } else {
                    if (parametro.getIdParametro() != p.getIdParametro()) {
                        error_message = error_message + "Ya existe un parámetro con la misma combinación Valor único y Tipo.\n";
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }

        return error_message;
    }
    
    public boolean guardar(Parametro parametro){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        boolean siGuardo = true;
        try {
            Transaction tx = s.beginTransaction();
            s.saveOrUpdate(parametro);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            siGuardo = false;
        } finally {
            s.close();
        }
        return siGuardo;
    }
    
    public Parametro buscarId(int id){
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        Parametro parametro = null;
        try {
            Query q = s.getNamedQuery("ParametroXId");
            q.setParameter("id", id);
            parametro = (Parametro) q.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return parametro;
    }
}
