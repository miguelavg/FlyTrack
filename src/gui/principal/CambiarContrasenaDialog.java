/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import beans.Parametro;
import beans.seguridad.Contrasena;
import beans.seguridad.Usuario;
import controllers.CContrasena;
import controllers.CMail;
import controllers.CParametro;
import controllers.CSeguridad;
import controllers.CUsuario;
import gui.InformationDialog;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author msolorzano
 */
public class CambiarContrasenaDialog extends javax.swing.JDialog {

    private Usuario usuario;
    private Contrasena contrasenia;
    private boolean ejecutoCambio = false; 
    //la variable cambio solo se ejecuta cuando se realiza el cambio en si, caso contrario debe seguir como false
    
    /**
     * Creates new form CambiarContrasenaDialog
     */
    public CambiarContrasenaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public CambiarContrasenaDialog(java.awt.Frame parent, boolean modal, 
            Usuario usuarioAModificar, Contrasena contraseniaAModificar){
        this(parent, modal);
        usuario = usuarioAModificar;
        contrasenia = contraseniaAModificar;
        lblValidacionesContrasenia.setText("<html>Las contraseñas deben tener las siguientes indicaciones: <br>" +
                "Longitud Minima de Caraceteres: <b>" + (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_LONG_MINIMA").getValor()) + "</b><br>" +
                "Numero mínimo de caracteres numéricos: <b>" + (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_NUM").getValor()) + "</b><br>" +
                "Numero mínimo de caracteres especiales: <b>" + (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_ESP").getValor()) + "</b><br>" +
                "Numero mínimo de caracteres mayúsculas: <b>" + (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MAYUS").getValor()) + "</b><br>" +
                "Numero mínimo de caracteres minúsculas: <b>" + (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_MINIMO_CAR_MINUS").getValor()) + "</b><br>" +
                "Diferente a las <b>"+ (CParametro.buscarXValorUnicoyTipo("SEGURIDAD", "PASS_NUM_CONT_HIST").getValor()) + "</b> contraseñas anteriores<html>");
    }
    
    protected JRootPane createRootPane() { 
        JRootPane rootPane = new JRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action accion = new AbstractAction() { 
          public void actionPerformed(ActionEvent actionEvent) { 
            if(!ejecutoCambio) usuario = null; 
            setVisible(Boolean.TRUE);
            dispose();
          } 
        } ;
        inputMap.put(stroke, "EXIT");
        rootPane.getActionMap().put("EXIT", accion);
        
        stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        accion = new AbstractAction() { 
          public void actionPerformed(ActionEvent actionEvent) { 
            btnConfirmar.doClick();
          } 
        } ;
        inputMap.put(stroke, "CAMBIARCONTRASENA");
        rootPane.getActionMap().put("CAMBIARCONTRASENA", accion);
        
        return rootPane;
    }
    
    public Usuario showDialog(){
        setVisible(Boolean.TRUE);
        return usuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassActual = new javax.swing.JPasswordField();
        txtPassNueva = new javax.swing.JPasswordField();
        txtPassNueva2 = new javax.swing.JPasswordField();
        lblTitulo = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        lblValidacionesContrasenia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Contraseña Actual");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Contraseña Nueva");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reconfirmar Contraseña");

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cambiar Contraseña");

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setText("Regresar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 0, 12));

        lblValidacionesContrasenia.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lblValidacionesContrasenia.setForeground(new java.awt.Color(255, 0, 0));
        lblValidacionesContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValidacionesContrasenia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassNueva2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPassActual, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblValidacionesContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassActual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassNueva2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblValidacionesContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        usuario = null;
        this.setVisible(Boolean.TRUE);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        //Solo se procede a realizar la accion si en los 3 textbox hay informacion
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if( txtPassActual.getPassword().length > 0 && txtPassNueva.getPassword().length > 0 && 
            txtPassNueva2.getPassword().length > 0){
            boolean actualContraseniasIguales = CSeguridad.passwordCorrecta(contrasenia.getText(), CContrasena.encriptarContrasena(this.txtPassActual.getPassword()));
            boolean nuevasContraseniasIguales = CSeguridad.passwordCorrecta(txtPassNueva.getPassword(), txtPassNueva2.getPassword());
            boolean validacionesContrasenia = CSeguridad.validarContrasena(txtPassNueva.getPassword(), usuario.getIdUsuario());
            /*
             * 1. La contrasena actual coincida con la contrasena ativa obtenida del usuario que se pido cambio de contrasenia
             * 2. La contrasena nueva en ambas cajas de textos se debe comprobar que coincidan
             * 3. La contrasena nueva debe pasar todas las validaciones del caso
             */
            if( actualContraseniasIguales && nuevasContraseniasIguales && validacionesContrasenia){
                CContrasena.desactivarContrasena(contrasenia); //Desactivo su contrasena activa actual
                CContrasena.agregarContrasenaActiva(txtPassNueva.getPassword(), usuario); //agrego la nueva contrasena activa
                ejecutoCambio = true;//ya ejecuto el cambio, el objetivo de esta panatlla
                usuario = new CUsuario().BuscarXid(usuario.getIdUsuario()); //actualizo el usuario, con las contrasenas
                InformationDialog.mostrarInformacion("La contraseña ha sido cambiada satisfactoriamente", this);
                new CMail().sendMail("flytrack.no.reply@gmail.com",
                                        "manuelmanuel", 
                                        usuario.geteMail(),
                                        "[FlyTrack] Modificar contraseña",
                                        "Estimado usuario " + usuario.getLogIn() + " .\n\nSu contraseña ha sido modificada: " + 
                                        new String(txtPassNueva.getPassword()) + ".\n\nSoporte FlyTrack.");
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                this.setVisible(Boolean.TRUE);
                this.dispose();
            }
            else{
                if(!nuevasContraseniasIguales)
                    lblMensaje.setText("Las contraseñas nuevas ingresadas deben COINCIDIR");
                else if(!validacionesContrasenia)
                    lblMensaje.setText("Las contraseñas deben respetar las indicaciones");
                else if(!actualContraseniasIguales)
                    lblMensaje.setText("Ingrese sus datos válidos correctamente");
                else
                    lblMensaje.setText("");
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
        else{
            lblMensaje.setText("Ingrese sus datos válidos correctamente");
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
    }//GEN-LAST:event_btnConfirmarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasenaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasenaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasenaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CambiarContrasenaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CambiarContrasenaDialog dialog = new CambiarContrasenaDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValidacionesContrasenia;
    private javax.swing.JPasswordField txtPassActual;
    private javax.swing.JPasswordField txtPassNueva;
    private javax.swing.JPasswordField txtPassNueva2;
    // End of variables declaration//GEN-END:variables
}
