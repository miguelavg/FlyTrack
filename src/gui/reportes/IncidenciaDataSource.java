/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Vuelo;
import beans.Envio;
import beans.Incidencia;
import beans.Escala;
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
public class IncidenciaDataSource  implements JRDataSource {
    
    
    private List<Incidencia> listaIncidencias = new ArrayList<Incidencia>();
    private int indiceIncidencias = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceIncidencias < getListaIncidencias().size();
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  


    
    if("fecha".equals(jrField.getName())) 
    { 
     valor = CValidator.formatDate(getListaIncidencias().get(indiceIncidencias).getFecha());    
    } 
 

        else if("vuelo".equals(jrField.getName())) 
    { 
        valor = getListaIncidencias().get(indiceIncidencias).getVuelo().getIdVuelo();
    } 
    
        
                else if("origen".equals(jrField.getName())) 
    { 
        valor = getListaIncidencias().get(indiceIncidencias).getVuelo().getOrigen().getNombre() ;   
    } 
                
                else if("destino".equals(jrField.getName())) 
    { 
        valor = getListaIncidencias().get(indiceIncidencias).getVuelo().getDestino().getNombre(); 
    } 
                else if("incidencia".equals(jrField.getName())) 
    { 
        valor = getListaIncidencias().get(indiceIncidencias).getEstado().getValor();
    } 
    
    
    
    return valor; 
}

    /**
     * @return the listaVuelos
     */
    public List<Incidencia> getListaIncidencias(){
        return listaIncidencias;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaIncidencias(List<Incidencia> listaIncidencias) {
        this.listaIncidencias = listaIncidencias;
    }

    
}
