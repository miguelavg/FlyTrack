/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Envio;
import beans.Escala;
import beans.Parametro;
import beans.Vuelo;
import java.util.ArrayList;
import java.util.List;
import logic.Recocido;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author miguelavg
 */
public class CEnvio {

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
            
            q = s.getNamedQuery("VuelosXFecha");
            q.setParameter("fechaRegistro", envio.getFechaRegistro());
            List<Vuelo> vuelos = q.list();
            
            
            //Recocido recocido = new Recocido(envio, vuelos, temperaturaInicial, temperaturaFinal, k, alfaSA, alfaGrasp, pParada, intentos);
            //recocido.simular();


        } catch (Exception e) {
        } finally {
            s.close();
        }
        return false;
    }
}
