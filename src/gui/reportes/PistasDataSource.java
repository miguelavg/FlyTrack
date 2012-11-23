/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Escala;
import beans.seguridad.Pista;
import controllers.CEnvio;
import controllers.CValidator;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author joao
 */
public class PistasDataSource implements JRDataSource{
    
    private List<Pista> listaPistas = new ArrayList<Pista>();
    private int indicePistas = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indicePistas < getListaPistas().size();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException
    { 
        Object valor = null;  

        if("npista".equals(jrField.getName())) 
        { 
            valor = indicePistas + 1;    
        } 
        else if("usuario".equals(jrField.getName())) 
        { 
            valor = getListaPistas().get(indicePistas).getUsuario();
        } 
        else if("modprincipal".equals(jrField.getName())) 
        { 
            valor = getListaPistas().get(indicePistas).getModuloPrincipal();   
        } 
        else if("modsecundario".equals(jrField.getName())) 
        { 
            valor = getListaPistas().get(indicePistas).getModuloSecundario(); 
        } 
        else if("accion".equals(jrField.getName())) 
        { 
            valor = getListaPistas().get(indicePistas).getAccion();
        } 
        else if("fecha".equals(jrField.getName())) 
        { 
            valor = CValidator.formatDate(getListaPistas().get(indicePistas).getFecha());
        } 
        else if("mensaje".equals(jrField.getName())) 
        { 
            valor = getListaPistas().get(indicePistas).getMensaje();
        }     
    return valor; 
    }
    
    public List<Pista> getListaPistas(){
        return listaPistas;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaPistas(List<Pista> listaPistas) {
        this.listaPistas = listaPistas;
    }
    
    
}
