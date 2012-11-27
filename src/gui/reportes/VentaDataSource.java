/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

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
public class VentaDataSource implements JRDataSource {
    
    private List<Venta> listaVentas = new ArrayList<Venta>();
    private int indiceVentas = -1;
    
        @Override
    public boolean next() throws JRException {
        return ++indiceVentas < getListaVentas().size();
    }
        
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  


    
    if("aeropuerto".equals(jrField.getName())) 
    { 
     //valor = |;    
    } 

        else if("mes".equals(jrField.getName())) 
    { 
        valor = getListaVentas().get(indiceVentas);
    } 
    
                else if("monto".equals(jrField.getName())) 
    { 
        valor = getListaVentas().get(indiceVentas);
    } 
        
     
    return valor; 
}
    
        public List<Venta> getListaVentas(){
        return listaVentas;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
        
    
}
