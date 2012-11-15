/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.vuelos;

import beans.Incidencia;
import beans.Parametro;
import beans.Vuelo;
import controllers.CAeropuerto;
import controllers.CIncidencia;
import controllers.CVuelo;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author jorge
 */
public class Incidencias extends javax.swing.JDialog {

    public List<Parametro> ListatipoInci = null;
    public List<Incidencia> ListaInci = null;
    public Vuelo objV = null;
    public Incidencia objInc = null;

    /**
     * Creates new form Incidencias
     */
    public Incidencias(javax.swing.JDialog parent, boolean modal, Vuelo objVuelo) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        llenarComboIncidencia();
        objV = objVuelo;
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
        jLabel2 = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JButton();
        txt_codigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dt_fechini = new datechooser.beans.DateChooserCombo();
        cbm_incidencia = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        dt_fechfin = new datechooser.beans.DateChooserCombo();
        jPanel2 = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_incidencia = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Administración - Vuelos - Incidencia");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/incidencia48x48.png"))); // NOI18N
        jLabel1.setText("Incidencias");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Codigo Vuelo");

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

        jLabel8.setText("Fecha Inicio");

        dt_fechini.setFormat(0);
        dt_fechini.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
        try {
            dt_fechini.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechini.setLocale(new java.util.Locale("es", "PE", ""));

        cbm_incidencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jLabel3.setText("Tipo Incidencia");

        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        jLabel9.setText("Fecha Fin");

        dt_fechfin.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
        try {
            dt_fechfin.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechfin.setLocale(new java.util.Locale("es", "PE", ""));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbm_incidencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dt_fechfin, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dt_fechini, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dt_fechini, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbm_incidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dt_fechfin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        tbl_incidencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Incidencia", "Cód. vuelo", "Fecha ", "Descripcion", "Tipo"
            }
        ));
        tbl_incidencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_incidenciaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_incidencia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed

//        vVuelo.dispose();
        this.dispose();


        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:

        ListaInci = objV.getIncidencias(); //CIncidencia.BuscarIncidencia(p,dt_fechini.getSelectedDate(),dt_fechfin.getSelectedDate());   
        if (ListaInci != null) {

            llenarGrillaIncidencia();
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void tbl_incidenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_incidenciaMouseClicked
        // TODO add your handling code here:
//        IncidenciaEdit VInci = new IncidenciaEdit(this, true, objV, 1);
//        VInci.setModal(true);
//        VInci.setVisible(true);
//        ListaInci = objV.getIncidencias();
//        llenarGrillaIncidencia();

    }//GEN-LAST:event_tbl_incidenciaMouseClicked

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        IncidenciaEdit VInci = new IncidenciaEdit(this, true, objV, 1);
        VInci.setModal(true);
        objInc = VInci.showDialog();
        if (objInc != null) {

            cambioEstadoVuelo();

        }

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        ListaInci = objV.getIncidencias(); //CIncidencia.BuscarIncidencia(p,dt_fechini.getSelectedDate(),dt_fechfin.getSelectedDate());   
        if (ListaInci != null) {

            llenarGrillaIncidencia();
        }
    }//GEN-LAST:event_formWindowOpened

    public void llenarGrillaIncidencia() {

        Parametro TipoDoc;
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_incidencia.getModel();
        int rows = dtm.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            dtm.removeRow(0);
        }

        TableColumn column = null;
        column = tbl_incidencia.getColumnModel().getColumn(0);
        column.setMaxWidth(0);
        Object[] datos = new Object[5];


        Calendar fec = Calendar.getInstance();
        Parametro p = null;
        if (cbm_incidencia.getSelectedIndex() > 0) {
            p = (Parametro) cbm_incidencia.getSelectedItem();
        }


        for (int i = 0; i < ListaInci.size(); i++) {


            datos[0] = ListaInci.get(i).getIdIncidencia();


            datos[1] = ListaInci.get(i).getVuelo().getIdVuelo();
            datos[2] = ListaInci.get(i).getFecha();
            datos[3] = ListaInci.get(i).getDescripcion();
            datos[4] = ListaInci.get(i).getEstado().getValor();

            fec.setTime(ListaInci.get(i).getFecha());

            if (p != null) {
                if ((int) (ListaInci.get(i).getEstado().getIdParametro())
                        == (p.getIdParametro())) {
                    if ((dt_fechini.getSelectedDate() != null)
                            && (dt_fechfin.getSelectedDate() != null)) {
                        if ((dt_fechini.getSelectedDate().compareTo(fec) == -1)
                                && (dt_fechfin.getSelectedDate().compareTo(fec) == 1)) {
                            dtm.addRow(datos);
                        }

                    }
                    if ((dt_fechini.getSelectedDate() == null)
                            && (dt_fechfin.getSelectedDate() != null)) {
                        if (dt_fechfin.getSelectedDate().compareTo(fec) == 1) {
                            dtm.addRow(datos);
                        }

                    }
                    if ((dt_fechini.getSelectedDate() != null)
                            && (dt_fechfin.getSelectedDate() == null)) {
                        if (dt_fechini.getSelectedDate().compareTo(fec) == -1) {
                            dtm.addRow(datos);
                        }

                    }
                    if ((dt_fechini.getSelectedDate() == null)
                            && (dt_fechfin.getSelectedDate() == null)) {

                        dtm.addRow(datos);
                    }
                }
            } else {
                if ((dt_fechini.getSelectedDate() != null)
                        && (dt_fechfin.getSelectedDate() != null)) {
                    if ((dt_fechini.getSelectedDate().compareTo(fec) == -1)
                            && (dt_fechfin.getSelectedDate().compareTo(fec) == 1)) {
                        dtm.addRow(datos);
                    }

                }
                if ((dt_fechini.getSelectedDate() == null)
                        && (dt_fechfin.getSelectedDate() != null)) {
                    if (dt_fechfin.getSelectedDate().compareTo(fec) == 1) {
                        dtm.addRow(datos);
                    }

                }
                if ((dt_fechini.getSelectedDate() != null)
                        && (dt_fechfin.getSelectedDate() == null)) {
                    if (dt_fechini.getSelectedDate().compareTo(fec) == -1) {
                        dtm.addRow(datos);
                    }
                }
                if ((dt_fechini.getSelectedDate() == null)
                        && (dt_fechfin.getSelectedDate() == null)) {

                    dtm.addRow(datos);
                }
            }

        }

    }

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
            java.util.logging.Logger.getLogger(Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Incidencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Incidencias(new javax.swing.JDialog(), true, new Vuelo()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JComboBox cbm_incidencia;
    private datechooser.beans.DateChooserCombo dt_fechfin;
    private datechooser.beans.DateChooserCombo dt_fechini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_incidencia;
    private javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables

    private void llenarComboIncidencia() {
        ListatipoInci = CIncidencia.llenarComboEstado();;

        for (int i = 0; i < ListatipoInci.size(); i++) {
            Parametro TipoDocBE = (Parametro) ListatipoInci.get(i);

            cbm_incidencia.addItem(TipoDocBE);
        }
    }

    private void cambioEstadoVuelo() {
        String val;
        String valorPara;
        val = objInc.getEstado().getValorUnico();

        if (val.compareTo("DES") == 0) //DESPEGUE -- > EN VUELO 16
        {
            valorPara = "VUE";
            cambiarEstado(objInc.getVuelo(), valorPara);
        }
        if (val.compareTo("ATE") == 0)//ATERRIZAJE --> FINALIZADO 17
        {
            valorPara = "FIN";
            cambiarEstado(objInc.getVuelo(), valorPara);
        }
        if (val.compareTo("RET") == 0) // RETRASO --> retraso 15
        {
            valorPara = "RET";
            cambiarEstado(objInc.getVuelo(), valorPara);
        }
        if (val.compareTo("CAN") == 0) //cancelado --> cancelado 18
        {
            valorPara = "CAN";
            cambiarEstado(objInc.getVuelo(), valorPara);
        }

    }

    private void cambiarEstado(Vuelo objVuelo, String valorPara) {

        CVuelo.modificarVueloEstado(objVuelo, valorPara);
    }
}
