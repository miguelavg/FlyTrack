/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flytrack;

import controllers.CTest;
import gui.principal.Login;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguelavg
 */
public class FlyTrack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String password = "manuel";
        String passwordMD5 = "";
        try {
            byte[] passwordBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            passwordMD5 = md.digest(passwordBytes).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(passwordMD5);
        System.exit(0);
        
        ArrayList<Date> lista = new ArrayList<Date>();
        for(int i = 0; i < 10; i++){
            System.out.println(new Date().getTime());
        }
    }
}
