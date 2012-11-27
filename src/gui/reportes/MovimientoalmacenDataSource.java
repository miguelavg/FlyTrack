/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Incidencia;
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
public class MovimientoalmacenDataSource implements JRDataSource{
    
    private List<Movimientoalmacen> listamovimientos = new ArrayList<Movimientoalmacen>();
    private int indiceMovimientos = -1;
    
        @Override
    public boolean next() throws JRException {
        return ++indiceMovimientos < getListaMovimientos().size();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  


    
    if("fecha".equals(jrField.getName())) 
    { 
     valor = CValidator.formatDate(getListaMovimientos().get(indiceMovimientos).getFecha());
                 
    } 

        else if("motivo".equals(jrField.getName())) 
    { 
        valor = getListaMovimientos().get(indiceMovimientos).getMotivo();
    } 
    
        
                else if("movimiento".equals(jrField.getName())) 
    { 
        valor = getListaMovimientos().get(indiceMovimientos).getMovimiento();
    } 
                
                else if("nropaquetes".equals(jrField.getName())) 
    { 
        valor = getListaMovimientos().get(indiceMovimientos).getNumpaq();
    } 

    
    
    
    return valor; 
}
    
     /**
     * @return the listaVuelos
     */
    public List<Movimientoalmacen> getListaMovimientos(){
        return listamovimientos;
    }

    /**
     * @param listaEnvio the listaClientes to set
     */
    public void setListaMovimientos(List<Movimientoalmacen> listamovimientos) {
        this.listamovimientos = listamovimientos;
    }
    
    
    
}
