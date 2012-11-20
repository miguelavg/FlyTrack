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
public class EnvioDataSource  implements JRDataSource {
    
    //private List<Cliente> listaClientes = new ArrayList<Cliente>();
    private Envio envio= new Envio();
    private int indiceClienteActual = -1;
    
    @Override
    public boolean next() throws JRException {
        return ++indiceClienteActual <1;
    }

    @Override
    
    public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  

    if("numdocpago".equals(jrField.getName())) 
    { 
        valor = getEnvio().getNumDocVenta(); 
    } 
    else if("moneda".equals(jrField.getName())) 
    { 
        valor = getEnvio().getMoneda().getValor(); 
    } 
    else if("nombre".equals(jrField.getName())) 
    { 
        valor = getEnvio().getRemitente().getNombres(); 
    } 
        else if("apellido".equals(jrField.getName())) 
    { 
        valor = getEnvio().getRemitente().getApellidos(); 
    } 
        else if("numdoc".equals(jrField.getName())) 
    { 
        valor = getEnvio().getRemitente().getNumDoc(); 
    } 
        else if("narticulos".equals(jrField.getName())) 
    { 
        valor = getEnvio().getNumPaquetes(); 
    } 
        else if("aorigen".equals(jrField.getName())) 
    { 
        valor = getEnvio().getOrigen().getNombre(); 
    } 
            else if("adestino".equals(jrField.getName())) 
    { 
        valor = getEnvio().getDestino().getNombre(); 
    } 
            else if("vuni".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber(getEnvio().getUnitario()); 
    } 
            else if("vtotal".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber(getEnvio().getMonto()); 
    } 
            else if("iva".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber(getEnvio().getImpuesto()*getEnvio().getMonto()/100 );
    } 
            else if("total".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber((getEnvio().getMonto())+(getEnvio().getImpuesto()/100*getEnvio().getMonto())); 
    } 
    
     
    return valor; 
}

    /**
     * @return the listaClientes
     */
    public Envio getEnvio() {
        return envio;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    
    
}
