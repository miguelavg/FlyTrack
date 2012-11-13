/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.aeropuertos;

import beans.Cliente;
import beans.Parametro;
import controllers.CAeropuerto;
import gui.clientes.ClientesPopUp;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class AeropuertoPopup extends javax.swing.JDialog {
 
    private CAeropuerto Caeropuerto = new CAeropuerto();
    
    private List<Parametro> ListatipoPar; 
    private List<Parametro> ListatipoEst; 
    private List<Parametro> ListatipoHijo;
    private beans.Aeropuerto objAero = new beans.Aeropuerto();
    
     List<beans.Aeropuerto> listaAeropuertos;
    /**
     * Creates new form AeropuertoPopup
     */
    public AeropuertoPopup(javax.swing.JDialog parent, boolean modal) {
       super(parent, modal);
        initComponents();
        llenarComboPais();
        llenarComboEstado();
        
    }
    
    public AeropuertoPopup(javax.swing.JFrame parent, boolean modal) {
       super(parent, modal);
        initComponents();
        llenarComboPais();
        llenarComboEstado();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        cbm_Pais = new javax.swing.JComboBox();
        cbm_ciudad = new javax.swing.JComboBox();
        cbm_estado = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_aeropuerto = new javax.swing.JTable();
        btn_buscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Aeropuerto - Buscar");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("País:");

        jLabel3.setText("Ciudad:");

        jLabel4.setText("Estado:");

        btn_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        cbm_Pais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbm_Pais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbm_PaisMouseReleased(evt);
            }
        });
        cbm_Pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_PaisActionPerformed(evt);
            }
        });

        cbm_ciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        cbm_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbm_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_estadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(cbm_Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbm_Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 209));

        tbl_aeropuerto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ciudad", "País", "Estado", "X", "Y", "Capacidad max de almacenamiento", "Capacidad actual de almacenamiento"
            }
        ));
        tbl_aeropuerto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_aeropuertoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_aeropuerto);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btn_buscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btn_buscar1.setText("Buscar");
        btn_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(269, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
          Parametro TipoPais = null;
          Parametro TipoCiudad = null;
          Parametro TIpoEstado = null;
          
          if (cbm_Pais.getSelectedIndex() > 0){
            TipoPais = (Parametro)cbm_Pais.getSelectedItem();
          }
          if (cbm_ciudad.getSelectedIndex()>0) {
            TipoCiudad = (Parametro)cbm_ciudad.getSelectedItem();
          }
          if (cbm_estado.getSelectedIndex()>0){
            TIpoEstado = (Parametro)cbm_estado.getSelectedItem();
          }
          
          listaAeropuertos = CAeropuerto.BuscarAeropuerto(
                  TipoPais,TipoCiudad, TIpoEstado);
        
        DefaultTableModel dtm = (DefaultTableModel) this.tbl_aeropuerto.getModel();
        Object[] datos = new Object[9];
         int rows=dtm.getRowCount();
        for (int i=rows-1; i>=0; i--){
            dtm.removeRow(0);
        }
        
        for (int i = 0; i < listaAeropuertos.size(); i++) {
            
           datos[0] = listaAeropuertos.get(i).getNombre();
           datos[1] = listaAeropuertos.get(i).getCiudad();
           datos[2] = listaAeropuertos.get(i).getPais();
           datos[3] = listaAeropuertos.get(i).getCoordX();
           datos[4] = listaAeropuertos.get(i).getCoordY();
           
           datos[5] = listaAeropuertos.get(i).getCapacidadMax();
           datos[6] = listaAeropuertos.get(i).getCapacidadActual();
           
           dtm.addRow(datos);
       }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void cbm_PaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_PaisActionPerformed
        // TODO add your handling code here:
          cbm_ciudad.removeAllItems();
        
       
        
         ListatipoHijo = ListatipoPar.get(cbm_Pais.getSelectedIndex()).getHijos();
            for (int i=0;i<ListatipoHijo.size();i++)
        {
            Parametro TipoDocBE =(Parametro)ListatipoHijo.get(i);
            
            cbm_ciudad.addItem(TipoDocBE);
        }
    }//GEN-LAST:event_cbm_PaisActionPerformed

    private void cbm_PaisMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbm_PaisMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbm_PaisMouseReleased

    private void tbl_aeropuertoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_aeropuertoMouseClicked
        // TODO add your handling code here:
        
      //  beans.Aeropuerto ObjAero = new beans.Aeropuerto();
        objAero = listaAeropuertos.get(tbl_aeropuerto.getSelectedRow());
        
        this.dispose();
         
        
    }//GEN-LAST:event_tbl_aeropuertoMouseClicked

    private void cbm_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbm_estadoActionPerformed

    private void btn_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar1ActionPerformed

   
   
            
            
    private void llenarComboPais(){
    
        
        ListatipoPar = CAeropuerto.llenarComboPais();
      for (int i=0;i<ListatipoPar.size();i++)
        {
            Parametro TipoDocBE =(Parametro)ListatipoPar.get(i);
            
            cbm_Pais.addItem(TipoDocBE);
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
     
     public beans.Aeropuerto showDialog(){
        setVisible(true);        
        return objAero;
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
            java.util.logging.Logger.getLogger(AeropuertoPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AeropuertoPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AeropuertoPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AeropuertoPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AeropuertoPopup dialog = new AeropuertoPopup(new javax.swing.JDialog(), true);
                   dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_buscar1;
    private javax.swing.JComboBox cbm_Pais;
    private javax.swing.JComboBox cbm_ciudad;
    private javax.swing.JComboBox cbm_estado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_aeropuerto;
    // End of variables declaration//GEN-END:variables
}
