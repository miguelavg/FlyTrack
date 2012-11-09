/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import beans.Sesion;
import beans.Vuelo;
import beans.seguridad.*;
import controllers.*;
import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import gui.clientes.Clientes;
import gui.administracion.aeropuertos.Aeropuerto;
import gui.envios.EnvioDialog;
import gui.reportes.ReporteFrame;
import gui.administracion.vuelos.Vuelos;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author ronald
 */
public class PrincipalFrame extends javax.swing.JFrame {
       
    /**
     * Creates new form PrincipalFrame
     */
    public PrincipalFrame() {
        try {
        UIManager.setLookAndFeel(new GTKLookAndFeel());
    } catch (Exception e) { }
        
        initComponents();
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("imagenes/logo.png")).getImage());
        definirPermisos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        btnEnvios = new javax.swing.JButton();
        lblEnvios = new javax.swing.JLabel();
        btnSimulacion = new javax.swing.JButton();
        lblSimulacion = new javax.swing.JLabel();
        lblAdministracion = new javax.swing.JLabel();
        btnAdministracion = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        lblCerrarSesion = new javax.swing.JLabel();
        btnReportes = new javax.swing.JButton();
        lblReportes = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        btnSeguridad = new javax.swing.JButton();
        lblSeguridad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        btnEnvios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envio4848.png"))); // NOI18N
        btnEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviosActionPerformed(evt);
            }
        });

        lblEnvios.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblEnvios.setText("Envíos");

        btnSimulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/simulacion48x48.png"))); // NOI18N
        btnSimulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimulacionActionPerformed(evt);
            }
        });

        lblSimulacion.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblSimulacion.setText("Simulación");

        lblAdministracion.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblAdministracion.setText("Administración");

        btnAdministracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Configuration.png"))); // NOI18N
        btnAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracionActionPerformed(evt);
            }
        });

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exit48x48.png"))); // NOI18N
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        lblCerrarSesion.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblCerrarSesion.setText("Cerrar Sesión");

        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte48x48.png"))); // NOI18N
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        lblReportes.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblReportes.setText("Reportes");

        lblClientes.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblClientes.setText("Clientes");

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario48x48.png"))); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnSeguridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/seguridad48x48.png"))); // NOI18N
        btnSeguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeguridadActionPerformed(evt);
            }
        });

        lblSeguridad.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblSeguridad.setText("Seguridad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnClientes)
                        .addGap(36, 36, 36)
                        .addComponent(lblClientes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEnvios)
                        .addGap(36, 36, 36)
                        .addComponent(lblEnvios))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSeguridad)
                        .addGap(36, 36, 36)
                        .addComponent(lblSeguridad))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdministracion)
                        .addGap(36, 36, 36)
                        .addComponent(lblAdministracion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSimulacion)
                        .addGap(36, 36, 36)
                        .addComponent(lblSimulacion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReportes)
                            .addComponent(btnCerrarSesion))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCerrarSesion)
                            .addComponent(lblReportes))))
                .addGap(244, 244, 244))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdministracion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblAdministracion)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSeguridad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnvios))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblSeguridad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEnvios)
                        .addGap(21, 21, 21)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblSimulacion)
                        .addGap(34, 34, 34)
                        .addComponent(lblClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnSimulacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClientes)
                        .addGap(9, 9, 9)
                        .addComponent(btnReportes)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrarSesion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(lblCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(18, 18, 18))
        );

        jLabel3.setText("Copyleft (l). Todos los derechos compartidos.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionActionPerformed
        // TODO add your handling code here:
        MenuAdministracionFrame menuAdmin = new MenuAdministracionFrame();
        menuAdmin.setVisible(true);
    }//GEN-LAST:event_btnAdministracionActionPerformed

    private void btnEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviosActionPerformed
        // TODO add your handling code here:
        EnvioDialog envio = new EnvioDialog();
        envio.setVisible(true);
    }//GEN-LAST:event_btnEnviosActionPerformed

    private void btnSimulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimulacionActionPerformed
        // TODO add your handling code here:
        //Simulacion simulacion = new Simulacion();
        //simulacion.setVisible(true);
    }//GEN-LAST:event_btnSimulacionActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        Clientes clientes = new Clientes();
        clientes.setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
        ReporteFrame reporteFrame = new ReporteFrame();
        reporteFrame.setVisible(true);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        Sesion.setUsuario(null);
        this.dispose();
        Login login = new Login();
        login.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnSeguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeguridadActionPerformed
        // TODO add your handling code here:
        MenuSeguridadFrame menuSeguridad = new MenuSeguridadFrame();
        menuSeguridad.setVisible(true);
    }//GEN-LAST:event_btnSeguridadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PrincipalFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministracion;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEnvios;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSeguridad;
    private javax.swing.JButton btnSimulacion;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAdministracion;
    private javax.swing.JLabel lblCerrarSesion;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblEnvios;
    private javax.swing.JLabel lblReportes;
    private javax.swing.JLabel lblSeguridad;
    private javax.swing.JLabel lblSimulacion;
    // End of variables declaration//GEN-END:variables

    private void definirPermisos(){
        
        List<Permiso> permisos = Sesion.getUsuario().getPerfil().getPermisos();
        boolean administracion = CSeguridad.validarPermiso(1, null,"Administracion",permisos);
        this.btnAdministracion.setEnabled(administracion);
        this.lblAdministracion.setEnabled(administracion);
        boolean seguridad = CSeguridad.validarPermiso(1, null,"Seguridad",permisos);
        this.btnSeguridad.setEnabled(seguridad);
        this.lblSeguridad.setEnabled(seguridad);
        boolean envios = CSeguridad.validarPermiso(1, null,"Envios",permisos);
        this.btnEnvios.setEnabled(envios);
        this.lblEnvios.setEnabled(envios);
        boolean clientes = CSeguridad.validarPermiso(1, null,"Clientes",permisos);
        this.btnClientes.setEnabled(clientes);
        this.lblClientes.setEnabled(clientes);
        boolean reportes = CSeguridad.validarPermiso(1, null,"Reportes",permisos);
        this.btnReportes.setEnabled(reportes);
        this.lblReportes.setEnabled(reportes);

        this.setLocationRelativeTo(null);
        pack();
    }
}
