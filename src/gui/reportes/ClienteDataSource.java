        /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import beans.Cliente;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author joao
 */
public class ClienteDataSource implements JRDataSource{

    
    private List<Cliente> listaClientes = new ArrayList<Cliente>();
    private int indiceClienteActual = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceClienteActual < getListaClientes().size();
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  

    if("nombres".equals(jrField.getName())) 
    { 
        valor = getListaClientes().get(indiceClienteActual).getNombres(); 
    } 
    else if("apellidos".equals(jrField.getName())) 
    { 
        valor = getListaClientes().get(indiceClienteActual).getApellidos(); 
    } 
    else if("numdoc".equals(jrField.getName())) 
    { 
        valor = getListaClientes().get(indiceClienteActual).getNumDoc(); 
    } 
     
    return valor; 
}

    /**
     * @return the listaClientes
     */
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
