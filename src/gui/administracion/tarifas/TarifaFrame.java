/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.tarifas;

import beans.Aeropuerto;
import beans.Parametro;
import beans.Sesion;
import beans.Tarifa;
import beans.TipoCambio;
import beans.seguridad.Permiso;
import controllers.CAeropuerto;
import controllers.CParametro;
import controllers.CSeguridad;
import controllers.CTarifa;
import controllers.CTipoCambio;
import controllers.CValidator;
import gui.administracion.aeropuertos.AeropuertoPopup;
import gui.clientes.ClientesEdit;
import gui.clientes.ClientesPopUp;
import gui.envios.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguelavg
 */
public class TarifaFrame extends javax.swing.JDialog {

    /**
     * Creates new form Envio
     */
    
    Aeropuerto AeroOri;
    Aeropuerto AeroDes;
    CAeropuerto AeropuertoBL = new CAeropuerto();
    List<Tarifa> ListaTarifa;
    CTarifa TarifaBL = new CTarifa();
    CTipoCambio TipoCambioBL= new CTipoCambio();
    CParametro ParametroBL = new CParametro();
    
    public TarifaFrame() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        CTipoCambio ctipocambio = new CTipoCambio();
        ListaTarifa = TarifaBL.Buscar(null, null,"", "");
        llenartabla();
//        tablaTarifa.set(0, 0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAeroOri = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtAeroDes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btn_regresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarifa = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tarifas");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa48x48.png"))); // NOI18N
        jLabel1.setText("Tarifas");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAeroOri.setEnabled(false);
        txtAeroOri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAeroOriActionPerformed(evt);
            }
        });

        jLabel2.setText("Origen:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtAeroDes.setEnabled(false);
        txtAeroDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAeroDesActionPerformed(evt);
            }
        });

        jLabel3.setText("Destino:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAeroOri, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAeroDes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAeroOri, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAeroDes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        tablaTarifa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Aeropuerto Origen", "Aeropuerto Destino", "Monto ($)"
            }
        ));
        tablaTarifa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaTarifa);
        tablaTarifa.getColumnModel().getColumn(0).setMinWidth(0);
        tablaTarifa.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablaTarifa.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaTarifa.getColumnModel().getColumn(1).setPreferredWidth(309);
        tablaTarifa.getColumnModel().getColumn(2).setPreferredWidth(309);
        tablaTarifa.getColumnModel().getColumn(3).setPreferredWidth(100);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAeroOriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAeroOriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAeroOriActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AeropuertoPopup AeropuertoPU = new AeropuertoPopup(this, true);
        AeroOri = AeropuertoPU.showDialog();

        if (AeroOri != null) {
            txtAeroOri.setText(AeroOri.getNombre()+ ", "+AeroOri.getCiudad().getValor());
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        AeropuertoPopup AeropuertoPU = new AeropuertoPopup(this, true);
        AeroDes = AeropuertoPU.showDialog();

        if (AeroDes != null) {
            txtAeroDes.setText(AeroDes.getNombre()+ ", "+AeroDes.getCiudad().getValor());
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtAeroDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAeroDesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAeroDesActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        ListaTarifa=TarifaBL.Buscar(AeroOri,AeroDes,"","");
        llenartabla();
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void llenartabla(){
        DefaultTableModel dtm = (DefaultTableModel) tablaTarifa.getModel();

        for (int i = dtm.getRowCount(); i > 0; i--) {
            dtm.removeRow(0);
        }

        if (ListaTarifa == null) {
            return;
        }

        for (Tarifa t : ListaTarifa) {
            llenarLineaTabla(t, dtm);
        }
    }
    private void llenarLineaTabla  (Tarifa t,  DefaultTableModel dtm){
        
        Aeropuerto origen;
        Aeropuerto destino;
        Double monto=0.00;
        origen=TarifaBL.BuscarAeropuertoXId(t.getOrigen());
        destino=TarifaBL.BuscarAeropuertoXId(t.getDestino());
        
        Parametro moneda;
        moneda = ParametroBL.buscarId(t.getMoneda().getIdParametro());
        if (t.getMoneda().getValor().equals("Soles")){
            
            //TipoCambio tipocambio;
            //tipocambio = TarifaBL.BuscarCambio(t.getMoneda().getIdParametro(),0);
            monto=t.getMonto();//*tipocambio.getTipoCambio();
        }
        else {
            if (t.getMoneda().getValor().equals("Euros")){
            
                //TipoCambio tipocambio;
                //tipocambio = TarifaBL.BuscarCambio(t.getMoneda().getIdParametro(),0);
                monto=t.getMonto();///tipocambio.getTipoCambio();
            }
            else{
                monto= t.getMonto();
            }
        }
        
        Object[] datos = new Object[4];
        datos[0] = t.getIdTarifa();
        datos[1] = origen.getNombre();
        datos[2] = destino.getNombre();
        datos[3] = monto;
       
        dtm.addRow(datos);
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        // TODO add your handling code here:
        CTipoCambio ctipocambio = new CTipoCambio();
        ListaTarifa = TarifaBL.Buscar(null, null,"", "");
        llenartabla();
    }
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

        TarifaEdit tarifaEditGUI = new TarifaEdit(this,true,-1); //llamamos a la clase y creamos un objeto llamado MiVentana
        int result = tarifaEditGUI.showDialog();
        //cargartabla();//le decimos al compilador que queremos que se vea la ventana
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) this.tablaTarifa.getModel();
              
        if (tablaTarifa.getSelectedRow()>-1){
            Integer id=(Integer)tablaTarifa.getValueAt(tablaTarifa.getSelectedRow(), 0);
            TarifaEdit MiVentana = new TarifaEdit(this,true,id);        
            //MiVentana.idCliente=(Integer)ClienteTabla.getValueAt(ClienteTabla.getSelectedRow(), 0);
            MiVentana.showDialog();
            ListaTarifa=TarifaBL.Buscar(null, null,"", "");
            llenartabla();
        }
        
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_regresarActionPerformed

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
            java.util.logging.Logger.getLogger(EnvioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TarifaFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTarifa;
    private javax.swing.JTextField txtAeroDes;
    private javax.swing.JTextField txtAeroOri;
    // End of variables declaration//GEN-END:variables

    private void definirPermisos(){
        List<Permiso> permisos = Sesion.getUsuario().getPerfil().getPermisos();
        boolean crear = CSeguridad.validarPermiso(3, "Tarifas", "Crear", permisos);
        this.btnAgregar.setEnabled(crear);
        boolean modificar = CSeguridad.validarPermiso(3, "Tarifas", "Modificar", permisos);
        this.btnModificar.setEnabled(modificar);
        boolean buscar = CSeguridad.validarPermiso(3, "Tarifas", "Buscar/Listar", permisos);
        this.btnBuscar.setEnabled(buscar);
//        boolean cargaMasiva = CSeguridad.validarPermiso(3, "Tarifas", "Carga Masiva", permisos);
//        this.btnCargaMasiva.setEnabled(cargaMasiva);
        
        this.setLocationRelativeTo(null);
        pack();
    }
}
