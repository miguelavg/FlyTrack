/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.tipocambio;

import beans.Parametro;
import beans.TipoCambio;
import controllers.CParametro;
import controllers.CPista;
import controllers.CTipoCambio;
import controllers.CValidator;
import gui.ErrorDialog;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
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
public class TipoCambioEdit extends javax.swing.JDialog {

    /**
     * Creates new form TipoCambioEdit
     */
    public TipoCambioEdit(TipoCambio tipoCambio, javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.isNuevo = true;
        this.tipoCambio = null;

        if (tipoCambio != null) {
            this.txt_tipocambio.setText(CValidator.formatNumber(tipoCambio.getTipoCambio()));
            llenarCombos(tipoCambio.getMonedaOrigen(), tipoCambio.getMonedaDestino());
            this.txt_fecha.setText(CValidator.formatDate(tipoCambio.getFechaActualizacion()));

            this.tipoCambio = tipoCambio;
            this.isNuevo = false;
        } else {
            llenarCombos(null, null);
        }

        if (this.isNuevo) {
            this.setTitle("FlyTrack - Administración - Tipos de cambio - Agregar");
        } else {
            this.setTitle("FlyTrack - Administración - Tipos de cambio - Modificar");
        }
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
    private TipoCambio tipoCambio;
    private Parametro monedaOrigen;
    private Parametro monedaDestino;
    private boolean isNuevo;

    public TipoCambio showDialog() {
        setVisible(true);
        if (this.isNuevo) {
            return this.tipoCambio;
        } else {
            return null;
        }
    }

    private void llenarCombos(Parametro origen, Parametro destino) {
        CParametro cparametro = new CParametro();
        List<Parametro> monedas = cparametro.buscar(null, null, "TIPO_MONEDA", null);

        if (monedas == null) {
            return;
        }

        for (Parametro p : monedas) {
            this.cmb_origen.addItem(p);
            this.cmb_destino.addItem(p);

            if (origen != null && p.getIdParametro() == origen.getIdParametro()) {
                this.cmb_origen.setSelectedItem(p);
            }

            if (destino != null && p.getIdParametro() == destino.getIdParametro()) {
                this.cmb_destino.setSelectedItem(p);
            }
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

        btn_padre = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        pnl_titulo = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_fecha = new javax.swing.JLabel();
        lbl_tipocambio = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        lbl_monedaDestino = new javax.swing.JLabel();
        lbl_monedaOrigen = new javax.swing.JLabel();
        txt_tipocambio = new javax.swing.JTextField();
        btn_guardar = new javax.swing.JButton();
        btn_regresar = new javax.swing.JButton();
        cmb_origen = new javax.swing.JComboBox();
        cmb_destino = new javax.swing.JComboBox();

        btn_padre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_padre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_padreActionPerformed(evt);
            }
        });

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tipos de cambio");
        setResizable(false);

        pnl_titulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_titulo.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipocambio48x48.png"))); // NOI18N
        lbl_titulo.setText("Tipos de cambio");

        javax.swing.GroupLayout pnl_tituloLayout = new javax.swing.GroupLayout(pnl_titulo);
        pnl_titulo.setLayout(pnl_tituloLayout);
        pnl_tituloLayout.setHorizontalGroup(
            pnl_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_titulo)
                .addGap(205, 205, 205))
        );
        pnl_tituloLayout.setVerticalGroup(
            pnl_tituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_fecha.setText("Fecha actualiz.:");

        lbl_tipocambio.setText("Tipo de cambio:");

        txt_fecha.setEditable(false);
        txt_fecha.setEnabled(false);

        lbl_monedaDestino.setText("Moneda destino:");

        lbl_monedaOrigen.setText("Moneda origen:");

        txt_tipocambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tipocambioActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        cmb_origen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cmb_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_origenActionPerformed(evt);
            }
        });

        cmb_destino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_tipocambio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txt_tipocambio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_monedaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_monedaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipocambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tipocambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_monedaOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_monedaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_padreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_padreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_padreActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        this.isNuevo = false;
        this.dispose();
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        monedaOrigen = null;
        monedaDestino = null;
        if (cmb_origen.getSelectedIndex() > 0) {
            monedaOrigen = (Parametro) cmb_origen.getSelectedItem();
        }
        if (cmb_destino.getSelectedIndex() > 0) {
            monedaDestino = (Parametro) cmb_destino.getSelectedItem();
        }
        
        TipoCambio objAux = null;
        
        if(this.isNuevo){
            this.tipoCambio = new TipoCambio();
        }
        else{
            objAux = new TipoCambio(tipoCambio);
//            CPista.guardarPista("Administración", "Tipo de Cambio", "Modificar", 
//                                "ANTES: " + tipoCambio.aString() + 
//                                "DESPUES: " + tipoCambio.aString());
        }
        
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        CTipoCambio ctipocambio = new CTipoCambio();
        String error_message = ctipocambio.validar(this.tipoCambio, isNuevo, this.txt_tipocambio.getText(), this.monedaOrigen, this.monedaDestino);
        if (error_message == null || error_message.isEmpty()) {
            this.tipoCambio.setTipoCambio(Double.parseDouble(this.txt_tipocambio.getText()));
            this.tipoCambio.setMonedaOrigen(this.monedaOrigen);
            this.tipoCambio.setMonedaDestino(this.monedaDestino);
            this.tipoCambio.setFechaActualizacion(new Date());
            ctipocambio.guardar(this.tipoCambio);
            
            if(objAux != null){ //modificar
                CPista.guardarPista("Administración", "Tipo de Cambio", "Modificar", 
                                "ANTES: " + objAux.aString() + 
                                "DESPUES: " + tipoCambio.aString());
            }
            else{ //crear
                CPista.guardarPista("Administración", "Tipo de Cambio", "Crear", tipoCambio.aString());
            }
            
            this.setVisible(false);
            this.dispose();
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmb_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_origenActionPerformed

    private void txt_tipocambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tipocambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipocambioActionPerformed

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
            java.util.logging.Logger.getLogger(TipoCambioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TipoCambioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TipoCambioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TipoCambioEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TipoCambioEdit(null, new javax.swing.JDialog(), true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_padre;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JComboBox cmb_destino;
    private javax.swing.JComboBox cmb_origen;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JLabel lbl_fecha;
    private javax.swing.JLabel lbl_monedaDestino;
    private javax.swing.JLabel lbl_monedaOrigen;
    private javax.swing.JLabel lbl_tipocambio;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JPanel pnl_titulo;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_tipocambio;
    // End of variables declaration//GEN-END:variables
}
