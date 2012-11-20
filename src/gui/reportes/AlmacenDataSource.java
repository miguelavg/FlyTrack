/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Vuelo;
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
public class AlmacenDataSource implements JRDataSource {
    
    
    private List<Vuelo> listaVuelos = new ArrayList<Vuelo>();
    private int indiceVuelos = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceVuelos < getListaVuelos().size();
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  

    if("fecha".equals(jrField.getName())) 
    { 
     if ("Finalizado".equals(getListaVuelos().get(indiceVuelos).getEstado().getValor())){
     valor = CValidator.formatDate(getListaVuelos().get(indiceVuelos).getFechaLlegada()); 
     }
     else {
     valor = CValidator.formatDate(getListaVuelos().get(indiceVuelos).getFechaSalida()); 
     } 
    } 

    
    else if("vuelo".equals(jrField.getName())) 
    { 
     valor = getListaVuelos().get(indiceVuelos).getIdVuelo();    
    } 
    else if("aeropuerto".equals(jrField.getName())) 
    {
      if ("Finalizado".equals(getListaVuelos().get(indiceVuelos).getEstado().getValor())){
     valor = getListaVuelos().get(indiceVuelos).getOrigen().getNombre(); 
     }
     else {
     valor = getListaVuelos().get(indiceVuelos).getDestino().getNombre(); 
     }    
    } 
        else if("movimiento".equals(jrField.getName())) 
    { 
      if ("Finalizado".equals(getListaVuelos().get(indiceVuelos).getEstado().getValor())){
     valor = "Llegada"; 
     }
     else {
     valor = "Salida"; 
     } 
    } 
        else if("nropaquetes".equals(jrField.getName())) 
    { 
        valor = getListaVuelos().get(indiceVuelos).getCapacidadActual(); 
    } 
    
    return valor; 
}

    /**
     * @return the listaVuelos
     */
    public List<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaVuelos(List<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }

    
}
