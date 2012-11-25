/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import gui.ErrorDialog;
import java.awt.Component;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author ronald
 */
public class CMail {
    private String mailSMTPServers;
    private String mailSMTPServerPort;
    private String from;
    private String password;
    
    public void sendMail(String to, String subject, String message){
        Properties prop = new Properties();
        
            from = CParametro.buscarXValorUnicoyTipo("MAIL", "FROM").getValor();
        password = CParametro.buscarXValorUnicoyTipo("MAIL", "PWD").getValor();
        mailSMTPServers = CParametro.buscarXValorUnicoyTipo("MAIL", "SMTP_SERVER").getValor();
        mailSMTPServerPort = CParametro.buscarXValorUnicoyTipo("MAIL", "SMTP_PORT").getValor();
        
        prop.put("mail.transport.protocol","smtp");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host",mailSMTPServers);
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.user",from);
        prop.put("mail.debug","true");
        prop.put("mail.smtp.port",mailSMTPServerPort);
        prop.put("mail.smtp.socketFactory.port",mailSMTPServerPort);
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.user", from);
        prop.setProperty("mail.password",password);
        prop.put("mail.smtp.timeout", "15000");
        prop.put("mail.smtp.connectiontimeout", "15000");
        
        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);
        
        Message msg = new MimeMessage(session);
        
        try{
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(subject);
            msg.setText(message);
        }
        catch(Exception e){
            //System.out.println(">> Erro: Completar Message" + e);
            //ErrorDialog.mostrarError("Error al enviar mensaje", this);
        }
        Transport tr;
        
        try{
            tr = session.getTransport("smtp");
            tr.connect(mailSMTPServers,from,password);
            
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        }
        catch(Exception e){
            //System.out.println(">> Erro: Envio Message" + e);
            //ErrorDialog.mostrarError("Error al enviar mensaje", this);
        }
    }
}
