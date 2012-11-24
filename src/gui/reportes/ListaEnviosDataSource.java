/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Incidencia;
import beans.Envio;
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
public class ListaEnviosDataSource implements JRDataSource {
    
    private List<Envio> listaEnvios = new ArrayList<Envio>();
    private int indiceEnvios = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceEnvios < getListaEnvios().size();
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  


    
    if("fecha".equals(jrField.getName())) 
    { 
     valor = CValidator.formatDate(getListaEnvios().get(indiceEnvios).getFechaRecojo());    
    } 

        else if("cliente".equals(jrField.getName())) 
    { 
        valor = getListaEnvios().get(indiceEnvios).getRemitente().getApellidos()+getListaEnvios().get(indiceEnvios).getRemitente().getNombres();
    } 
    
                else if("origenDestino".equals(jrField.getName())) 
    { 
        valor = getListaEnvios().get(indiceEnvios).getOrigen().getNombre();
    } 
        
                else if("importe".equals(jrField.getName())) 
    { 
        valor = getListaEnvios().get(indiceEnvios).getMonto()/ (1 + getListaEnvios().get(indiceEnvios).getIva());   
    } 
                
                else if("iva".equals(jrField.getName())) 
    { 
        valor = getListaEnvios().get(indiceEnvios).getImpuesto(); 
    } 
                else if("total".equals(jrField.getName())) 
    { 
        valor = getListaEnvios().get(indiceEnvios).getMonto();
    } 
    
    
    
    return valor; 
}
    public List<Envio> getListaEnvios(){
        return listaEnvios;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaEnvios(List<Envio> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }

}
