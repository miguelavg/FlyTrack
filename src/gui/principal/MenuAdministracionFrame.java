/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal;

import gui.administracion.tarifas.TarifaFrame;
import gui.administracion.vuelos.Vuelos;
import gui.administracion.aeropuertos.Aeropuerto;
import gui.clientes.ClientesPopUp;
import gui.seguridad.parametros.ParametroDialog;

/**
 *
 * @author ronald
 */
public class MenuAdministracionFrame extends javax.swing.JFrame {

    /**
     * Creates new form MenuAdministracionFrame
     */
    public MenuAdministracionFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tarifaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        vueloButton = new javax.swing.JButton();
        aeropuertoButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tarifaButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tarifaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa48x48.png"))); // NOI18N
        tarifaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifaButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Tarifas");

        vueloButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vuelo48x48.png"))); // NOI18N
        vueloButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vueloButtonActionPerformed(evt);
            }
        });

        aeropuertoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aeropuerto48x48.png"))); // NOI18N
        aeropuertoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aeropuertoButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("  Vuelos ");

        jLabel3.setText("Aeropuerto");

        jLabel4.setText("Tipo de cambio");

        tarifaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipocambio48x48.png"))); // NOI18N
        tarifaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifaButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tarifaButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(tarifaButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vueloButton)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(aeropuertoButton)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vueloButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aeropuertoButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tarifaButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tarifaButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tarifaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifaButtonActionPerformed
        // TODO add your handling code here:
        TarifaFrame tarifa = new TarifaFrame();
        tarifa.setVisible(true);
    }//GEN-LAST:event_tarifaButtonActionPerformed

    private void vueloButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vueloButtonActionPerformed
        // TODO add your handling code here:
        Vuelos vuelo = new Vuelos();
        vuelo.setVisible(true);
    }//GEN-LAST:event_vueloButtonActionPerformed

    private void aeropuertoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aeropuertoButtonActionPerformed
        // TODO add your handling code here:
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setVisible(true);
       
    }//GEN-LAST:event_aeropuertoButtonActionPerformed

    private void tarifaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifaButton1ActionPerformed
        // TODO add your handling code here:
        ParametroDialog parametroDialog = new ParametroDialog();
        parametroDialog.setVisible(true);
    }//GEN-LAST:event_tarifaButton1ActionPerformed

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
    private javax.swing.JButton aeropuertoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton tarifaButton;
    private javax.swing.JButton tarifaButton1;
    private javax.swing.JButton vueloButton;
    // End of variables declaration//GEN-END:variables
}
