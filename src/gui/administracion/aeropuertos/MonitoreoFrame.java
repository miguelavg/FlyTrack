/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.aeropuertos;

import gui.envios.*;
import beans.Aeropuerto;
import beans.Envio;
import beans.Escala;
import beans.Parametro;
import beans.Vuelo;
import controllers.CAeropuerto;
import controllers.CVuelo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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
    public boolean bandera = true;
    beans.Aeropuerto objAero = null;
    private List<Aeropuerto> ListaAeropuerto = null;

    public MonitoreoFrame(beans.Aeropuerto ObjAero) {
        initComponents();
        this.setLocationRelativeTo(null);
        ListaAeropuerto = CAeropuerto.GenerarListaAeropuerto();
        objAero = ObjAero;
    }

    public Aeropuerto ShowDialog() {

        pintar(this.getGraphics());
        setVisible(true);

        return objAero;
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action accion = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
            }
        };
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", accion);

        return rootPane;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g);
        if (bandera == false) {
            bandera = true;
            this.update(g);
            //this.repaint();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_mapa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Ubicar aeropuerto");
        setMinimumSize(new java.awt.Dimension(1024, 582));
        setModal(true);
        setResizable(false);

        lbl_mapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/world-map1024x582-1.PNG"))); // NOI18N
        lbl_mapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_mapaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_mapa)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_mapa, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_mapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_mapaMouseClicked
        // TODO add your handling code here:
        bandera = true;
        Graphics c = this.getGraphics();
        // pintar();
        c.setColor(Color.ORANGE);
        c.fillOval(evt.getX(), evt.getY(), 10, 10);
        c.drawOval(evt.getX(), evt.getY(), 10, 10);

        int result = JOptionPane.showConfirmDialog(this, "Desea Guardar la nueva posición?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == result) {
            objAero.setCoordX(evt.getX());

            objAero.setCoordY(evt.getY());
            this.dispose();
        } else {
            bandera = false;
            paint(c);
        }
    }//GEN-LAST:event_lbl_mapaMouseClicked

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
                new MonitoreoFrame(new beans.Aeropuerto()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_mapa;
    // End of variables declaration//GEN-END:variables

    private void pintar(Graphics c) {



        for (int j = 0; j < ListaAeropuerto.size(); j++) {

            c.setColor(Color.BLACK);

            c.drawString(ListaAeropuerto.get(j).getPais().getValor() + '-' + ListaAeropuerto.get(j).getNombre(), ListaAeropuerto.get(j).getCoordX() + 15, ListaAeropuerto.get(j).getCoordY());
            c.setColor(Color.red);
            c.fillOval(ListaAeropuerto.get(j).getCoordX(), ListaAeropuerto.get(j).getCoordY(), 10, 10);

            c.drawOval(ListaAeropuerto.get(j).getCoordX(), ListaAeropuerto.get(j).getCoordY(), 10, 10);
            // c.draw3DRect(, rootPaneCheckingEnabled);

            // c.drawRect(ListaAeropuerto.get(j).getCoordX(), ListaAeropuerto.get(j).getCoordY(), 10,10 );       
        }

        c.setColor(Color.GREEN);
        c.fillOval(objAero.getCoordX(), objAero.getCoordY(), 10, 10);

        c.drawOval(objAero.getCoordX(), objAero.getCoordY(), 10, 10);
    }
}
