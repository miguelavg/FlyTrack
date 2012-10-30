/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Cliente;
import beans.Parametro;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Filter;
/**
 *
 * @author jugox
 */
public class CCliente {
    public void agregarCliente(String Nombre,String Apellidos, String correo, 
            String telefono,String NumeroDoc, 
            Parametro TipoDoc, Parametro Ciudad, Parametro Pais ){
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
//            Parametro pTipoDoc;            
//            Parametro pCiudad;            
//            Parametro pPais;
            
            Cliente ClienteBE = new Cliente(); 
//            
            
//            
//            q = s.getNamedQuery("ParametrosXTipoValorUnico").setFirstResult(0);
//            q.setParameter("tipo", "CIUDAD");
//            q.setParameter("valorUnico", ""+Ciudad.toString()+"");
//            pCiudad = (Parametro) q.uniqueResult();
//            
//            q = s.getNamedQuery("ParametrosXTipoValorUnico").setFirstResult(0);
//            q.setParameter("tipo", "PAIS");
//            q.setParameter("valorUnico", ""+Pais.toString()+"");
//            pPais = (Parametro) q.uniqueResult();
            
            
            ClienteBE.setTipoDoc(TipoDoc);
            ClienteBE.setApellidos(Apellidos);
            ClienteBE.setNombres(Nombre);
            ClienteBE.setNumDoc(NumeroDoc);
            ClienteBE.setTelefono(telefono);
            ClienteBE.seteMail( correo);
            ClienteBE.setCiudad(Ciudad);
            ClienteBE.setPais(Pais);
            
            int i = (Integer)s.save(ClienteBE);
            
            tx.commit();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
                }
        finally {
            s.close();
        }
    }
    public List<Parametro> BuscarTipoDoc(){
        
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Parametro> Lista;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;
            q = s.getNamedQuery("ParametrosXTipo");
            q.setParameter("tipo", "TIPO_DOC");
            Lista = q.list();
            if (Lista.size()>0){
               return Lista; 
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            s.close();
        }
        
        return null;
    }
    public List<Cliente> Buscar(String Nombre, String Apellido, Parametro tipodoc, String numdoc)
    {
        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Cliente> ListaClientes;
        
        try {
            Transaction tx = s.beginTransaction();
            Query q;

            
           q = s.getNamedQuery("Clientes");
           if (!Nombre.equals(""))
           {
             Filter f = s.enableFilter("ClientesXNombre");
             f.setParameter("nombres","%"+Nombre+"%");
           }
           if (!Apellido.equals("")){
                Filter f2 = s.enableFilter("ClientesXApellido");
                f2.setParameter("apellidos",Apellido);

           }
           if (tipodoc.getIdParametro()!=0){
                Filter f3 = s.enableFilter("ClientesXTipoDoc");
                f3.setParameter("tipodoc",tipodoc.getIdParametro());
           }
           if (!numdoc.equals("")){
                Filter f4 = s.enableFilter("ClientesXNumDoc");
                f4.setParameter("numdoc",numdoc);

           }
           
           
           ListaClientes= q.list();
           
           
           return ListaClientes;
                      
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
