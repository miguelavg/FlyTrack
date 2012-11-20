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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
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
    public static boolean esNumero( char input) {
        
            if (!Character.isDigit(input) || input==' ' ){
                return false;
            }
                  
            return true;
        
    }
    
    public static boolean esLetraOEspacio( char input) {
        
            if (Character.isLetter(input) || input==' ' ){
                return true;
            }
                  
            return false;
        
    }
    
    public static boolean validarSoloLetras( char input , JTextField caja){
        if (input!=8){
            if (!Character.isLetter(input)){
//                
                String texto="";
                for (int i=0; i< caja.getText().length();i++){

                    if (Character.isLetter(caja.getText().charAt(i)))
                    {
                        texto +=caja.getText().charAt(i);
                    }

                }
                caja.setText(texto);
                return false;
            }
            
        }
        return true;
    }
    
    public static boolean validarSoloLetrasYEspacio( char input , JTextField caja){
        if (input!=8){
            if (!Character.isLetter(input) && input!=' '){
//                
                String texto="";
                for (int i=0; i< caja.getText().length();i++){

                    if (Character.isLetter(caja.getText().charAt(i))|| caja.getText().charAt(i)==' ' )
                    {
                        texto +=caja.getText().charAt(i);
                    }

                }
                caja.setText(texto);
                return false;
            }
            
        }
        return true;
    }
    public static boolean validarEmail(String email) {
        String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean validarSoloNumeros( char input , JTextField caja){
        
        if (input!=8){
            if (!CValidator.esNumero((input))){
//                
                String texto="";
                for (int i=0; i< caja.getText().length();i++){

                    if (CValidator.esNumero(caja.getText().charAt(i)))
                    {
                        texto +=caja.getText().charAt(i);
                    }

                }
                caja.setText(texto);
                return false;
            }
            
        }
        return true;
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
        
            for (int s=0; s<input.length();s++){
               Character c=input.charAt(s);
               if (!Character.isLetter(c)||!Character.isDigit(c)|| c!=' ') {
                   return false;
               }
            }
                                            
        
        return true;
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
            DecimalFormat format = new  DecimalFormat("0.00");
            formattedNumber = format.format(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return formattedNumber;
        }
    }
}
