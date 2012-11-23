/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Vuelo;
import beans.Envio;
import beans.Escala;
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
public class EscalaDataSource  implements JRDataSource {
    
    
    private List<Escala> listaEscalas = new ArrayList<Escala>();
    private int indiceEscala = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceEscala < getListaEscalas().size();
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  


    
    if("escala".equals(jrField.getName())) 
    { 
     valor = getListaEscalas().get(indiceEscala).getNumEscala();    
    } 
 

        else if("codvuelo".equals(jrField.getName())) 
    { 
        valor = getListaEscalas().get(indiceEscala).getVuelo().getIdVuelo();
    } 
    
        
                else if("origen".equals(jrField.getName())) 
    { 
        valor = getListaEscalas().get(indiceEscala).getVuelo().getOrigen().getNombre() + ", " + getListaEscalas().get(indiceEscala).getVuelo().getOrigen().getCiudad() + ", " + getListaEscalas().get(indiceEscala).getVuelo().getOrigen().getPais();   
    } 
                
                else if("destino".equals(jrField.getName())) 
    { 
        valor = getListaEscalas().get(indiceEscala).getVuelo().getDestino().getNombre() + ", " + getListaEscalas().get(indiceEscala).getVuelo().getDestino().getCiudad() + ", " + getListaEscalas().get(indiceEscala).getVuelo().getDestino().getPais(); 
    } 
                else if("fechasalida".equals(jrField.getName())) 
    { 
        valor = CValidator.formatDate(CEnvio.getFechaSalidaReal(getListaEscalas().get(indiceEscala)));
    } 
                else if("fechallegada".equals(jrField.getName())) 
    { 
        valor = CValidator.formatDate(getListaEscalas().get(indiceEscala).getVuelo().getFechaLlegada());
    } 
                else if("estado".equals(jrField.getName())) 
    { 
        valor = getListaEscalas().get(indiceEscala).getEstado();
    } 
    
    
    
    return valor; 
}

    /**
     * @return the listaVuelos
     */
    public List<Escala> getListaEscalas(){
        return listaEscalas;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaEscalas(List<Escala> listaEscalas) {
        this.listaEscalas = listaEscalas;
    }

    
}
