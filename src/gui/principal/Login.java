/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import beans.Sesion;
import beans.seguridad.Usuario;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import controllers.CSeguridad;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import org.hibernate.Session;

/**
 *
 * @author msolorzano
 */
public class Login extends javax.swing.JFrame {

    public static String userAnteriorIntentoLogin = "";
    public static int numIntentosFallidos = 0;
    public static int numMaxIntentosFallidos = CSeguridad.getMaxIntentosFallidos();

    /**
     * Creates new form Login
     */
    public Login() {
        try {
            UIManager.setLookAndFeel(new GTKLookAndFeel());
        } catch (Exception e) {
        }
        initComponents();
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("imagenes/logo.png")).getImage());
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
        txtUser = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblOlvidoPass = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flytrack: Log in");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User");
        jLabel1.setMaximumSize(new java.awt.Dimension(120, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(120, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Password");
        jLabel2.setMaximumSize(new java.awt.Dimension(120, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(120, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 30));

        txtUser.setMaximumSize(new java.awt.Dimension(120, 30));
        txtUser.setMinimumSize(new java.awt.Dimension(120, 30));
        txtUser.setPreferredSize(new java.awt.Dimension(120, 30));

        btnLogin.setText("Login");
        btnLogin.setMaximumSize(new java.awt.Dimension(120, 30));
        btnLogin.setMinimumSize(new java.awt.Dimension(120, 30));
        btnLogin.setPreferredSize(new java.awt.Dimension(120, 30));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblOlvidoPass.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        lblOlvidoPass.setText("Olvido su contraseña");
        lblOlvidoPass.setMaximumSize(new java.awt.Dimension(150, 20));
        lblOlvidoPass.setMinimumSize(new java.awt.Dimension(150, 20));
        lblOlvidoPass.setName("2"); // NOI18N
        lblOlvidoPass.setPreferredSize(new java.awt.Dimension(150, 20));

        lblError.setForeground(new java.awt.Color(255, 0, 0));
        lblError.setText("Usuario y/o Contraseña Inválidos");

        txtPass.setMaximumSize(new java.awt.Dimension(120, 30));
        txtPass.setMinimumSize(new java.awt.Dimension(120, 30));
        txtPass.setPreferredSize(new java.awt.Dimension(120, 30));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingrese su usuario y contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOlvidoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblError))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOlvidoPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        lblError.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String usuario = txtUser.getText();
        char[] password = txtPass.getPassword();

        //Verificar si la constrasenia del usuario es la activa o no
        //manejar el numero de intentos fallidos aqui
        Usuario usuarioValidado = null;
        if ((usuarioValidado = CSeguridad.verificarContrasenia(usuario, password)) != null) {
            //VERIFICACION EXITOSA
            lblError.setVisible(Boolean.FALSE);
            usuarioValidado.getContrasenias().size();

            Sesion.setUsuario(usuarioValidado);
            
            PrincipalFrame pf = new PrincipalFrame();
            pf.setVisible(Boolean.TRUE);

            this.setVisible(Boolean.FALSE);
            this.dispose();
        } else {
            //VERIFICACION FALLO
            lblError.setVisible(Boolean.TRUE);
            if (usuario.equals(userAnteriorIntentoLogin)) {
                numIntentosFallidos++;
            } else {
                numIntentosFallidos = 1;
            }
            userAnteriorIntentoLogin = usuario;
            //Solo incremento si el usuario que ha intentado logearse es igual al
            //usuario guardado, si no es asi, intetos fallidos regresa a 1 xD
            // Si llega al limite de intentos fallidos se bloquea la cuenta
            if (numIntentosFallidos >= numMaxIntentosFallidos) {
                //CSeguridad.bloquearCuenta(usuario);
                lblError.setText("Cuenta bloqueada: Supero el numero maximo de intentos fallidos");
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnLogin.doClick();
    }//GEN-LAST:event_txtPassKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblOlvidoPass;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
