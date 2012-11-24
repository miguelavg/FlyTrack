/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jugox
 */
public class XmlEnvio implements Serializable {
    private int idEnvio;
    
    private Date fechaRegistro;
    
    private Date fechaRecojo;
     
    private int numPaquetes;
    private double monto;
    private double iva;
    private double impuesto;
    private double unitario;
    private int numDocVenta;
    
}
