/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import beans.Sesion;
import beans.seguridad.Permiso;
import controllers.CSeguridad;
import gui.seguridad.parametros.ParametroDialog;
import gui.seguridad.perfiles.PerfilFrame;
import gui.seguridad.usuarios.UsuarioFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author ronald
 */
public class MenuSeguridadFrame extends javax.swing.JDialog {

    /**
     * Creates new form MenuSeguridadFrame
     */
    public MenuSeguridadFrame() {
        initComponents();
        definirPermisos();
    }
    
    protected JRootPane createRootPane() { 
        JRootPane rootPane = new JRootPane();
        KeyStroke strokeESC = KeyStroke.getKeyStroke("ESCAPE");
        KeyStroke strokeBACKSPACE = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0);        
        Action actionListener = new AbstractAction() { 
          public void actionPerformed(ActionEvent actionEvent) { 
            setVisible(false);
          } 
        } ;
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(strokeESC, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);
        inputMap.put(strokeBACKSPACE, "BACKSPACE");
        rootPane.getActionMap().put("BACKSPACE", actionListener);

    return rootPane;
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPerfiles = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        lblPerfiles = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seguridad");
        setMaximumSize(new java.awt.Dimension(300, 115));
        setMinimumSize(new java.awt.Dimension(300, 115));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(300, 115));
        setResizable(false);

        btnPerfiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil48x48.png"))); // NOI18N
        btnPerfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilesActionPerformed(evt);
            }
        });

        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario48x48.png"))); // NOI18N
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        lblPerfiles.setText("Perfiles");

        lblUsuarios.setText("Usuarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsuarios)
                        .addGap(79, 79, 79)
                        .addComponent(lblPerfiles))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuarios)
                        .addGap(79, 79, 79)
                        .addComponent(btnPerfiles)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPerfiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuarios)
                    .addComponent(lblPerfiles))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPerfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilesActionPerformed
        // TODO add your handling code here:
        PerfilFrame perfilframe = new PerfilFrame();
        perfilframe.setVisible(true);
    }//GEN-LAST:event_btnPerfilesActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        // TODO add your handling code here:
        UsuarioFrame usuarioframe = new UsuarioFrame();
        usuarioframe.setVisible(true);
    }//GEN-LAST:event_btnUsuariosActionPerformed

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
            java.util.logging.Logger.getLogger(MenuSeguridadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSeguridadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSeguridadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSeguridadFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSeguridadFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPerfiles;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel lblPerfiles;
    private javax.swing.JLabel lblUsuarios;
    // End of variables declaration//GEN-END:variables

    private void definirPermisos(){
        
        List<Permiso> permisos = Sesion.getUsuario().getPerfil().getPermisos();
        boolean usuarios = CSeguridad.validarPermiso(2, "Seguridad", "Usuarios", permisos);
        this.btnUsuarios.setEnabled(usuarios);
        this.lblUsuarios.setEnabled(usuarios);
        boolean perfiles = CSeguridad.validarPermiso(2, "Seguridad", "Perfiles", permisos);
        this.btnPerfiles.setEnabled(perfiles);
        this.lblPerfiles.setEnabled(perfiles);
        
        this.setLocationRelativeTo(null);
        pack();
    }
}
