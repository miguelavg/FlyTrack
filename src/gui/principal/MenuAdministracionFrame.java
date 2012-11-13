/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import beans.Sesion;
import beans.seguridad.Permiso;
import controllers.CSeguridad;
import gui.administracion.tarifas.TarifaFrame;
import gui.administracion.vuelos.Vuelos;
import gui.administracion.aeropuertos.Aeropuerto;
import gui.administracion.tipocambio.TipoCambioDialog;
import gui.clientes.ClientesPopUp;
import gui.seguridad.parametros.ParametroDialog;
import java.util.List;

/**
 *
 * @author ronald
 */
public class MenuAdministracionFrame extends javax.swing.JDialog {

    /**
     * Creates new form MenuAdministracionFrame
     */
    public MenuAdministracionFrame() {
        initComponents();
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

        btnTarifas = new javax.swing.JButton();
        lblTarifas = new javax.swing.JLabel();
        btnVuelos = new javax.swing.JButton();
        btnAeropuertos = new javax.swing.JButton();
        lblVuelos = new javax.swing.JLabel();
        lblAeropuertos = new javax.swing.JLabel();
        lblTipoCambio = new javax.swing.JLabel();
        btnTipoCambio = new javax.swing.JButton();
        btnParametros = new javax.swing.JButton();
        lblParametros = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Administración");
        setMinimumSize(new java.awt.Dimension(550, 115));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(550, 115));
        setResizable(false);

        btnTarifas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa48x48.png"))); // NOI18N
        btnTarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifasActionPerformed(evt);
            }
        });

        lblTarifas.setText("Tarifas");

        btnVuelos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vuelo48x48.png"))); // NOI18N
        btnVuelos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVuelosActionPerformed(evt);
            }
        });

        btnAeropuertos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aeropuerto48x48.png"))); // NOI18N
        btnAeropuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAeropuertosActionPerformed(evt);
            }
        });

        lblVuelos.setText("  Vuelos ");

        lblAeropuertos.setText("Aeropuertos");

        lblTipoCambio.setText("Tipo de cambio");

        btnTipoCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipocambio48x48.png"))); // NOI18N
        btnTipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoCambioActionPerformed(evt);
            }
        });

        btnParametros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/parametro48x48.png"))); // NOI18N
        btnParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametrosActionPerformed(evt);
            }
        });

        lblParametros.setText("Parametros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTarifas)
                    .addComponent(lblTarifas))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnTipoCambio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTipoCambio)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblParametros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnParametros)
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVuelos)
                        .addGap(24, 24, 24)
                        .addComponent(lblAeropuertos)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAeropuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTipoCambio)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTarifas)
                            .addComponent(btnParametros)
                            .addComponent(btnVuelos)
                            .addComponent(btnAeropuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAeropuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifasActionPerformed
        // TODO add your handling code here:
        TarifaFrame tarifa = new TarifaFrame();
        tarifa.setVisible(true);
    }//GEN-LAST:event_btnTarifasActionPerformed

    private void btnVuelosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVuelosActionPerformed
        // TODO add your handling code here:
        Vuelos vuelo = new Vuelos();
        vuelo.setModal(true);
        vuelo.setVisible(true);
    }//GEN-LAST:event_btnVuelosActionPerformed

    private void btnAeropuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAeropuertosActionPerformed
        // TODO add your handling code here:
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setModal(true);
        aeropuerto.setVisible(true);
       
    }//GEN-LAST:event_btnAeropuertosActionPerformed

    private void btnTipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoCambioActionPerformed
        // TODO add your handling code here:
        TipoCambioDialog tipoCambioDialog = new TipoCambioDialog();
        tipoCambioDialog.setVisible(true);
    }//GEN-LAST:event_btnTipoCambioActionPerformed

    private void btnParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametrosActionPerformed
        // TODO add your handling code here:
        ParametroDialog parametro = new gui.seguridad.parametros.ParametroDialog();
        parametro.setVisible(true);
    }//GEN-LAST:event_btnParametrosActionPerformed

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
            java.util.logging.Logger.getLogger(MenuAdministracionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdministracionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdministracionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdministracionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuAdministracionFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAeropuertos;
    private javax.swing.JButton btnParametros;
    private javax.swing.JButton btnTarifas;
    private javax.swing.JButton btnTipoCambio;
    private javax.swing.JButton btnVuelos;
    private javax.swing.JLabel lblAeropuertos;
    private javax.swing.JLabel lblParametros;
    private javax.swing.JLabel lblTarifas;
    private javax.swing.JLabel lblTipoCambio;
    private javax.swing.JLabel lblVuelos;
    // End of variables declaration//GEN-END:variables

    private void definirPermisos(){
        
        List<Permiso> permisos = Sesion.getUsuario().getPerfil().getPermisos();
        
        boolean tarifas = CSeguridad.validarPermiso(2, "Administracion", "Tarifas", permisos);
        this.btnTarifas.setEnabled(tarifas);
        this.lblTarifas.setEnabled(tarifas);
        
        boolean tipoCambio = CSeguridad.validarPermiso(2, "Administracion", "TipoCambio", permisos);
        this.btnTipoCambio.setEnabled(tipoCambio);
        this.lblTipoCambio.setEnabled(tipoCambio);
        
        boolean vuelos = CSeguridad.validarPermiso(2, "Administracion", "Vuelos", permisos);
        this.btnVuelos.setEnabled(vuelos);
        this.lblVuelos.setEnabled(vuelos);
        
        boolean aeropuertos = CSeguridad.validarPermiso(2, "Administracion", "Aeropuertos", permisos);
        this.btnAeropuertos.setEnabled(aeropuertos);
        this.lblAeropuertos.setEnabled(aeropuertos);
        
        boolean parametros = CSeguridad.validarPermiso(2, "Administracion", "Parametros", permisos);
        this.btnParametros.setEnabled(parametros);
        this.lblParametros.setEnabled(parametros);
        
        this.setLocationRelativeTo(null);
        pack();
    }
}
