/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.aeropuertos;

import beans.Parametro;
import controllers.CAeropuerto;
import controllers.CValidator;
import gui.ErrorDialog;
import java.util.List;

/**
 *
 * @author joao
 */
public class AeropuertoAgregar extends javax.swing.JDialog {

    /**
     * Creates new form AeropuertoAgregar
     */
     public beans.Aeropuerto objAero = new beans.Aeropuerto();
     private List<Parametro> ListatipoPar; 
     private List<Parametro> ListatipoEst; 
     private List<Parametro> ListatipoHijo;
    public AeropuertoAgregar(javax.swing.JDialog parent, boolean modal) {
        
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(null);
        llenarComboPais();
        llenarComboEstado();
        limpiarComponentes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_X = new javax.swing.JTextField();
        txt_Y = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_capacidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbm_pais = new javax.swing.JComboBox();
        cbm_ciudad = new javax.swing.JComboBox();
        cbm_estado = new javax.swing.JComboBox();
        btn_ubicar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Agregar");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aeropuerto48x48.png"))); // NOI18N
        jLabel11.setText("Aeropuertos");
        jLabel11.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nombre:");

        txt_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NombreActionPerformed(evt);
            }
        });
        txt_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_NombreKeyReleased(evt);
            }
        });

        jLabel5.setText("Estado:");
        jLabel5.setEnabled(false);

        txt_X.setEnabled(false);

        txt_Y.setEnabled(false);

        jLabel7.setText("X:");

        jLabel6.setText("Y:");

        jLabel8.setText("Capacidad max de Almacenamiento:");

        txt_capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_capacidadKeyReleased(evt);
            }
        });

        jLabel3.setText("Ciudad:");

        jLabel4.setText("País:");

        cbm_pais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbm_pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_paisActionPerformed(evt);
            }
        });

        cbm_ciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        cbm_estado.setEnabled(false);
        cbm_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_estadoActionPerformed(evt);
            }
        });

        btn_ubicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vuelo48x48.png"))); // NOI18N
        btn_ubicar.setText("Ubicar");
        btn_ubicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubicarActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btn_ubicar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbm_pais, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGap(1, 15, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_X, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Y, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btn_ubicar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbm_pais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_X, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_Y, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NombreActionPerformed

   private String validarcampos(){
        String error_message = "";
        if (txt_Nombre.getText().isEmpty()
                ||txt_capacidad.getText().isEmpty()
                ||cbm_pais.getSelectedIndex()==0 
                ||cbm_ciudad.getSelectedIndex()==0 
                 ){
            
            error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
                                    
        }
        else{
            
            if (CValidator.esAlfanumerico(txt_Nombre.getText())){
                
                error_message = "El nombre no puede ser alfanumérico";
            }
            
//            if (idCliente==-1){
//
//                error_message = error_message+ ClienteBL.ValidarDocumento((Parametro)cboTipoDoc.getSelectedItem(),txtNumeroDoc.getText());
//            }
            
            if (!CValidator.isInteger(txt_capacidad.getText())){
                
                error_message = "El capacidad maxima es inválida";
                
            }
           
            
        }
                      
        return error_message;
    }
    
    
    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        beans.Aeropuerto objAeropuerto = new beans.Aeropuerto();


        String error_message = validarcampos();
        if (error_message.isEmpty()) {


            CAeropuerto.agregarAeropuerto(
                    Integer.parseInt(txt_capacidad.getText()),
                    0,
                    (Parametro)cbm_ciudad.getSelectedItem(),
                    Integer.parseInt(txt_X.getText()),
                    Integer.parseInt(txt_Y.getText()),
                    (Parametro)cbm_estado.getSelectedItem(),
                    txt_Nombre.getText(),
                    (Parametro)cbm_pais.getSelectedItem());

            setVisible(false);
            dispose();
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
        
       
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_ubicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubicarActionPerformed
        // TODO add your handling code here:
        MonitoreoFrame Mapa = new MonitoreoFrame(objAero);
        objAero = Mapa.ShowDialog();
        
       
        txt_X.setText(String.valueOf(objAero.getCoordX()));
        txt_Y.setText(String.valueOf(objAero.getCoordY()));
    }//GEN-LAST:event_btn_ubicarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void cbm_paisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_paisActionPerformed
        // TODO add your handling code here:
//        if (cbm_pais.getSelectedIndex() != 0) {
//            cbm_ciudad.removeAllItems();
//
//            ListatipoHijo = ListatipoPar.get(cbm_pais.getSelectedIndex()).getHijos();
//            for (int i = 0; i < ListatipoHijo.size(); i++) {
//                Parametro TipoDocBE = (Parametro) ListatipoHijo.get(i);
//
//                cbm_ciudad.addItem(TipoDocBE);
//            }
//
//
//        }
        
        cbm_ciudad.removeAllItems();
        cbm_ciudad.addItem("Seleccionar");


        if (cbm_pais.getSelectedIndex() > 0) {
            ListatipoHijo = ListatipoPar.get(cbm_pais.getSelectedIndex() - 1).getHijos();
            for (int i = 0; i < ListatipoHijo.size(); i++) {
                Parametro TipoDocBE = (Parametro) ListatipoHijo.get(i);
                cbm_ciudad.addItem(TipoDocBE);
            }
        }        
        
        
    }//GEN-LAST:event_cbm_paisActionPerformed

    private void cbm_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbm_estadoActionPerformed

    private void txt_NombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyReleased
        // TODO add your handling code here:
        
        char letra = evt.getKeyChar();
        if (!CValidator.validarSoloLetrasYEspacio(letra, txt_Nombre)) {
            getToolkit().beep();
        }
        
    }//GEN-LAST:event_txt_NombreKeyReleased

    private void txt_capacidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_capacidadKeyReleased
                char letra = evt.getKeyChar();
        if (!CValidator.validarSoloNumeros(letra, txt_capacidad)) {
            getToolkit().beep();
        }// TODO add your handling code here:
        
    }//GEN-LAST:event_txt_capacidadKeyReleased

    
    private void limpiarComponentes(){
      
    
    }
    
    private void llenarComboPais(){
      
        
      ListatipoPar = CAeropuerto.llenarComboPais();
        
      for (int i=0;i<ListatipoPar.size();i++)
          
        {
            Parametro TipoDocBE =(Parametro)ListatipoPar.get(i);
            
            cbm_pais.addItem(TipoDocBE);
        }
     }
     private void llenarComboEstado(){
      
        
        ListatipoEst = CAeropuerto.llenarComboEstado();
      for (int i=0;i<ListatipoEst.size();i++)
        {
            Parametro TipoDocBE =(Parametro)ListatipoEst.get(i);
            
            cbm_estado.addItem(TipoDocBE);
        }
     }
    
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
            java.util.logging.Logger.getLogger(AeropuertoAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AeropuertoAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AeropuertoAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AeropuertoAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AeropuertoAgregar(new javax.swing.JDialog(), true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_ubicar;
    private javax.swing.JComboBox cbm_ciudad;
    private javax.swing.JComboBox cbm_estado;
    private javax.swing.JComboBox cbm_pais;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_X;
    private javax.swing.JTextField txt_Y;
    private javax.swing.JTextField txt_capacidad;
    // End of variables declaration//GEN-END:variables
}
