/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.envios;

import beans.Aeropuerto;
import beans.Envio;
import beans.Escala;
import beans.Parametro;
import beans.Vuelo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 * @author ronald
 */
public class MonitoreoFrame extends javax.swing.JDialog {

    /**
     * Creates new form MonitoreoFrame
     */
    public MonitoreoFrame(Envio envio) {
        initComponents();
        this.envio = envio;
        this.setLocationRelativeTo(null);
    }

    public void showFrame() {
        this.paint(this.getGraphics());
        setVisible(true);
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke strokeESC = KeyStroke.getKeyStroke("ESCAPE");
        Action actionListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(Boolean.FALSE);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(strokeESC, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);

        return rootPane;
    }
    private Envio envio;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (envio == null) {
            return;
        }

        if (envio.getEscalas() == null) {
            return;
        }

        for (Escala e : envio.getEscalasOrdenadasDesc()) {
            Vuelo v = e.getVuelo();

            if (v == null) {
                continue;
            }

            Parametro p = v.getEstado();

            if (p == null) {
                continue;
            }

            if (p.getValorUnico().equals("FIN")) {
                g.setColor(Color.BLUE);
            }

            if (p.getValorUnico().equals("PROG") || p.getValorUnico().equals("RET")) {
                g.setColor(Color.YELLOW);
            }

            if (p.getValorUnico().equals("VUE")) {
                g.setColor(Color.GREEN);
            }

            if (p.getValorUnico().equals("CAN") || e.getEstado().getValorUnico().equals("CAN")) {
                g.setColor(Color.RED);
            }

            Aeropuerto o = v.getOrigen();
            Aeropuerto d = v.getDestino();

            if (o != null && d != null) {
                Graphics2D g2 = (Graphics2D) g;
                Stroke stroke = new BasicStroke(2.0f);
                g2.setStroke(stroke);
                g2.drawLine(o.getCoordX(), o.getCoordY(), d.getCoordX(), d.getCoordY());
                g2.drawString(o.getPais().getValor() + '-' + o.getNombre(), o.getCoordX() + 15, o.getCoordY());
                g2.fillOval(o.getCoordX() - 5, o.getCoordY() - 5, 10, 10);
                g2.drawOval(o.getCoordX() - 5, o.getCoordY() - 5, 10, 10);
                g2.drawString(d.getPais().getValor() + '-' + d.getNombre(), d.getCoordX() + 15, d.getCoordY());
                g2.fillOval(d.getCoordX() - 5, d.getCoordY() - 5, 10, 10);
                g2.drawOval(d.getCoordX() - 5, d.getCoordY() - 5, 10, 10);
            }

        }

        g.dispose();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Envíos -  Monitoreo");
        setMaximumSize(new java.awt.Dimension(1024, 582));
        setMinimumSize(new java.awt.Dimension(1024, 582));
        setModal(true);
        setPreferredSize(null);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/world-map1024x582.PNG"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.paint(this.getGraphics());
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(MonitoreoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitoreoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitoreoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitoreoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoreoFrame(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
