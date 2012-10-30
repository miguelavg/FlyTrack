/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flytrack;

import controllers.CTest;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author miguelavg
 */
public class FlyTrack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Date> lista = new ArrayList<Date>();
        for(int i = 0; i < 10; i++){
            System.out.println(new Date().getTime());
        }
    }
}
