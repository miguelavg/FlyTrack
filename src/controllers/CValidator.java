/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Parametro;
import beans.Sesion;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author miguelavg
 */
public class CValidator {

    public static String buscarError(String error_code) {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        String error_message = "";
        try {
            Query q = s.getNamedQuery("ParametrosXTipoXValorUnico").setMaxResults(1);
            q.setParameter("tipo", "ERROR_MSG");
            q.setParameter("valorUnico", error_code);
            Parametro error = (Parametro) q.uniqueResult();
            error_message = error.getValor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return error_message;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean esAlfanumerico(String input){
        
     
            
            for (Integer c=0;c<10;c++){
               
                if (input.contains(c.toString())){
                    
                    return true;
                }
            }
            
        
        
        return false;
    }

    public static String formatDate(Date date) {
        String formattedDate = "";

        try {
            String DateFormat = "dd/MM/yyyy HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(DateFormat);
            formattedDate = format.format(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return formattedDate;
        }
    }

    public static String formatNumber(double number) {
        String formattedNumber = "";

        try {
            DecimalFormat format = new DecimalFormat("0.00");
            formattedNumber = format.format(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return formattedNumber;
        }
    }
}
