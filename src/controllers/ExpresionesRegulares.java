/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author joao
 */
public class ExpresionesRegulares {
    
  public final static String EJEMPLO = "[a-z \\-\\+]+";
  public final static String SOLO_LETRAS = "[a-zA-ZáéíóúÁÉÍÓÚÑñ ]";
  public final static String SOLO_NUMEROS = "[0-9]";
  public final static String FORMATO_CORREO = "[_0-9a-z-\\.@;]";
  public final static String FORMATO_DIRECCION = "[a-zA-ZáéíóúÁÉÍÓÚÑñ 0-9\"\\s.,-_&]";
  public final static String NUMERO_DECIMAL = "^\\d+(\\.\\d{1,2})?$";
  public final static String FORMATO_NOMBRE_PROVEEDOR = "[a-zA-ZáéíóúÁÉÍÓÚÑñ 0-9\"\\s.,-_&]";
  public final static String CARACTER_LETRA_MAS_TILDES = "[a-zñáéíóúü\\'\\s-]";
  public final static String FORMATO_FECHA = "^[0-3]\\d/[01]\\d/\\d{4}$";

    
}
