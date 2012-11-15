/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.vuelos;

import beans.Aeropuerto;
import beans.Parametro;
import beans.Vuelo;
import controllers.CParametro;
import controllers.CValidator;
import controllers.CVuelo;
import gui.ErrorDialog;
import gui.administracion.aeropuertos.AeropuertoPopup;
import java.awt.Component;
import java.awt.Window;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class VuelosAgregar extends javax.swing.JDialog {
    private final Component padre;
    private VuelosAgregar vVuelo = null;
    private List<Parametro> ListatipoEst;
    private int indicador = 0;
    private Vuelo objVueloo = null; 
    private Aeropuerto aeropuertoOrigen = null;
    private Aeropuerto aeropuertoDestino = null;
    /**
     * Creates new form VuelosModificar
     */
    
    
    public VuelosAgregar(javax.swing.JDialog parent,boolean modal, Vuelo objVuelo,int i) {
            super(parent,true);
            padre=parent;            
            
            padre.setEnabled(false); 
            ((Window)padre).setFocusableWindowState(false);         
            initComponents();
            llenarComboEstado();
            this.setLocationRelativeTo(null); 
            indicador = i;
       
            
            if (i == -1) {
            txt_codigo.setVisible(true);
             cargar_componentes(objVuelo);
             
             lbl_codigo.setVisible(true);
            }
            else{
                cbm_estado.setSelectedIndex(1);
                cbm_estado.setEnabled(false);
            }
            
            objVueloo = objVuelo; 
            
      
    }
    
      private void llenarComboEstado(){
      
        
          ListatipoEst = CVuelo.llenarComboEstado();
            for (int i=0;i<ListatipoEst.size();i++)
                {
                    Parametro TipoDocBE =(Parametro)ListatipoEst.get(i);

                    cbm_estado.addItem(TipoDocBE);
                }
            }
    
    public void cargar_componentes(Vuelo objVuelo){
        txt_codigo.setText(String.valueOf(objVuelo.getIdVuelo()));
        txt_origen.setText(objVuelo.getOrigen().getNombre());
        txt_destino.setText(objVuelo.getOrigen().getNombre());
        txt_capacidad.setText(String.valueOf(objVuelo.getCapacidadActual()));
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        if (objVuelo.getFechaLlegada() != null ) {
           cal1.setTime(objVuelo.getFechaLlegada()); 
           dt_fechLlega.setSelectedDate(cal1);
        }
       
       // cal1.setTime();
        
        if (objVuelo.getFechaLlegada() != null ) {
            cal2.setTime(objVuelo.getFechaLlegada());
            dt_fechLlega.setSelectedDate(cal2);
        }
        
       
        
       for (int k=0 ; k<ListatipoEst.size(); k ++){
        if (objVuelo.getEstado().getValor().compareTo(ListatipoEst.get(k).getValor())==0)  {
            cbm_estado.setSelectedIndex(k);
            break;
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

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_codigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        txt_codigo = new javax.swing.JTextField();
        txt_origen = new javax.swing.JTextField();
        txt_destino = new javax.swing.JTextField();
        txt_capacidad = new javax.swing.JTextField();
        cbm_estado = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dt_fechLlega = new datechooser.beans.DateChooserCombo();
        dt_fechSali = new datechooser.beans.DateChooserCombo();
        btn_origenAero = new javax.swing.JButton();
        btn_origenDest = new javax.swing.JButton();
        txt_monto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Administración - Vuelo - Agregar");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vuelo48x48.png"))); // NOI18N
        jLabel1.setText("Vuelos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_codigo.setText("Codigo Vuelo");

        jLabel3.setText("Origen");

        jLabel4.setText("Estado");

        jLabel6.setText("Destino");

        jLabel7.setText("Capacidad");

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_cancelar.setText("Regresar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        txt_codigo.setEditable(false);
        txt_codigo.setEnabled(false);
        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });

        txt_origen.setEnabled(false);

        txt_destino.setEnabled(false);

        txt_capacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_capacidadKeyReleased(evt);
            }
        });

        cbm_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jLabel8.setText("Fecha Llegada");

        jLabel9.setText("Fecha Salida");

        dt_fechLlega.setNothingAllowed(false);
        try {
            dt_fechLlega.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechLlega.setLocale(new java.util.Locale("es", "PE", ""));

        dt_fechSali.setNothingAllowed(false);
        try {
            dt_fechSali.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechSali.setLocale(new java.util.Locale("es", "PE", ""));

        btn_origenAero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_origenAero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_origenAeroActionPerformed(evt);
            }
        });

        btn_origenDest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_origenDest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_origenDestActionPerformed(evt);
            }
        });

        txt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_montoKeyReleased(evt);
            }
        });

        jLabel10.setText("Monto Alquiler");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_codigo)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_origen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_origenAero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dt_fechSali, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_destino, javax.swing.GroupLayout.DEFAULT_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_origenDest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt_fechLlega, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_codigo)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_origenAero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_monto, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addComponent(txt_codigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_origenDest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_destino, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dt_fechSali, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(dt_fechLlega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_capacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here: 
         Parametro TipoDoc = null;
         if (cbm_estado.getSelectedIndex()>0) {
            TipoDoc = (Parametro)cbm_estado.getSelectedItem();
         }
         
         String error_message = CVuelo.validar( 
                                aeropuertoOrigen,
                                aeropuertoDestino,
                                dt_fechLlega.getSelectedDate(),
                                dt_fechSali.getSelectedDate(),
                                TipoDoc,
                                txt_capacidad.getText(),
                                txt_monto.getText()
                                );
           
        if (error_message == null || error_message.isEmpty()) {
           if (indicador != -1){

                    cbm_estado.setEnabled(false);
                    cbm_estado.setSelectedIndex(1);
                    CVuelo.agregarVuelo(
                                        aeropuertoOrigen,
                                        aeropuertoDestino,
                                        dt_fechLlega.getSelectedDate(),
                                        dt_fechSali.getSelectedDate(),
                                        TipoDoc,
                                        txt_capacidad.getText(),
                                        txt_monto.getText());
                    this.setVisible(false);
                    this.dispose();
            }
          else {
          // modificar
               cbm_estado.setEnabled(true);
            CVuelo.modificarVuelo( 
                                    txt_codigo.getText(),
                                    aeropuertoOrigen,
                                    aeropuertoDestino,
                                    dt_fechLlega.getSelectedDate(),
                                    dt_fechSali.getSelectedDate(),
                                    TipoDoc,
                                    txt_capacidad.getText(),
                                    txt_monto.getText()
                                    );
              this.setVisible(false);
              this.dispose();
        }          
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }       
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
   
            this.dispose();
            this.setVisible(false);
                         
        // TODO add your handling code here:
     
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_origenAeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_origenAeroActionPerformed
        // TODO add your handling code here:
        AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
        aeropuertoOrigen = aeropuertoPU.showDialog();
        if (aeropuertoOrigen != null) {
            txt_origen.setText(aeropuertoOrigen.getNombre());

        }
    }//GEN-LAST:event_btn_origenAeroActionPerformed

    private void btn_origenDestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_origenDestActionPerformed
        // TODO add your handling code here:
        AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
        aeropuertoDestino = aeropuertoPU.showDialog();
        if (aeropuertoDestino != null) {
            txt_destino.setText(aeropuertoDestino.getNombre());

        }
    }//GEN-LAST:event_btn_origenDestActionPerformed

    private void txt_montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyReleased
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if (!CValidator.validarSoloNumeros(letra, txt_monto)) {
            getToolkit().beep();
        }
    }//GEN-LAST:event_txt_montoKeyReleased

    private void txt_capacidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_capacidadKeyReleased
        // TODO add your handling code here:
        char letra = evt.getKeyChar();
        if (!CValidator.validarSoloNumeros(letra, txt_capacidad)) {
            getToolkit().beep();
        }
    }//GEN-LAST:event_txt_capacidadKeyReleased

    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_origenAero;
    private javax.swing.JButton btn_origenDest;
    private javax.swing.JComboBox cbm_estado;
    private datechooser.beans.DateChooserCombo dt_fechLlega;
    private datechooser.beans.DateChooserCombo dt_fechSali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_codigo;
    private javax.swing.JTextField txt_capacidad;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_origen;
    // End of variables declaration//GEN-END:variables
}
