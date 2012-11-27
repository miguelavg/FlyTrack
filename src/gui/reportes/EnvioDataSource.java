/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;

import java.util.HashMap;
import java.util.Map;
import beans.Envio;
import controllers.CValidator;
import java.text.SimpleDateFormat;
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
    private Map parametro= new HashMap();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
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
        valor = String.valueOf(getEnvio().getNumDocVenta()); 
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
        valor = CValidator.formatNumber(getEnvio().getUnitario()*getEnvio().getNumPaquetes()); 
    } 
            else if("iva".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber(getEnvio().getImpuesto());
    } 
            else if("total".equals(jrField.getName())) 
    { 
        valor = CValidator.formatNumber(getEnvio().getMonto()); 
    } 
                else if("idenvio".equals(jrField.getName())) 
    { 
        valor = getEnvio().getIdEnvio(); 
    } 
                    else if("destnomb".equals(jrField.getName())) 
    { 
        valor = getEnvio().getDestinatario().getNombres(); 
    } 
                    else if("destapellido".equals(jrField.getName())) 
    { 
        valor = getEnvio().getDestinatario().getApellidos(); 
    } 
                        else if("remnomb".equals(jrField.getName())) 
    { 
        valor = getEnvio().getRemitente().getNombres(); 
    } 
                        else if("remape".equals(jrField.getName())) 
    { 
        valor = getEnvio().getRemitente().getApellidos(); 
    }                         
                        else if("fechaactualrecojo".equals(jrField.getName())) 
    { 
        valor = dateFormat.format(getEnvio().getFechaRecojo()).substring(0, 10); 
    } 
                        else if("horaactualrecojo".equals(jrField.getName())) 
    { 
        valor = dateFormat.format(getEnvio().getFechaRecojo()).substring(11, 16); 
    } 
                        else if("fechaactualregistro".equals(jrField.getName())) 
    { 
        valor = dateFormat.format(getEnvio().getFechaRegistro()).substring(0, 10); 
    } 
                        else if("horaactualregistro".equals(jrField.getName())) 
    { 
        valor = dateFormat.format(getEnvio().getFechaRegistro()).substring(11, 16); 
    }    
                        else if("ciudad".equals(jrField.getName())) 
    { 
        valor = getEnvio().getOrigen().getCiudad().getValor(); 
    }    
     
                      else if("paisciudad".equals(jrField.getName())) 
    { 
        valor = getEnvio().getOrigen().getPais().getValor() + " - " + getEnvio().getOrigen().getCiudad().getValor() + " - " + getEnvio().getOrigen().getNombre(); 
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
     *///Map parametro
    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Map getParametro() {
        return parametro;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setParametro(Map parametro) {
        this.parametro = parametro;
    }
    
    
    
}
