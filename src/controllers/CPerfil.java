/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Sesion;
import beans.seguridad.Perfil;
import beans.seguridad.Usuario;
import java.util.List;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author joao
 */
public class CPerfil {
    
         public List<Perfil> Buscar(Integer idperfil, String nombre,String descripcion,Integer Estado)
    {
        SessionFactory sf = Sesion.getSessionFactory();
        Session s = sf.openSession();
        List<Perfil> ListaPerfiles;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
           q = s.getNamedQuery("Perfil");
           ListaPerfiles= q.list();
           
           
           return ListaPerfiles;
                      
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
        
    }
    
}
