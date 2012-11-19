/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.aeropuertos;
 
import beans.Parametro;
import beans.Sesion;
import beans.seguridad.Permiso;
import controllers.CAeropuerto;
import controllers.CSeguridad;
import java.awt.Cursor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joao
 */
public class Aeropuerto extends javax.swing.JDialog {

    private List<Parametro> ListatipoPar;
    private List<Parametro> ListatipoEst;
    private List<Parametro> ListatipoHijo;
    private beans.Aeropuerto objAero = new beans.Aeropuerto();
    List<beans.Aeropuerto> listaAeropuertos;

    JTextField campoTexto = new JTextField();
    Integer aeropuertolleno=0;

    /**
     * Creates new form Aeropuerto
     */
    public Aeropuerto() {
        initComponents();
        llenarComboPais();
        llenarComboEstado();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        cbm_Pais = new javax.swing.JComboBox();
        cbm_ciudad = new javax.swing.JComboBox();
        cbm_estado = new javax.swing.JComboBox();
        btn_regresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_aeropuerto = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        btn_modificar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Administración - Aeropuertos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aeropuerto48x48.png"))); // NOI18N
        jLabel1.setText("Aeropuertos");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

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
        cbm_Pais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_PaisActionPerformed(evt);
            }
        });

        cbm_ciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbm_ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_ciudadActionPerformed(evt);
            }
        });

        cbm_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbm_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbm_estadoActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 513, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbm_Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbm_Pais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbm_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbm_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_agregarMouseClicked(evt);
            }
        });
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        tbl_aeropuerto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ciudad", "País", "Estado", "Cap. Maxima", "Cap. Actual"
            }
        ));
        jScrollPane1.setViewportView(tbl_aeropuerto);

        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/xml.png"))); // NOI18N
        jButton1.setText("Carga masiva");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
      setCursor(new Cursor(Cursor.WAIT_CURSOR));
        llenarGrillaAero();
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }//GEN-LAST:event_btn_buscarActionPerformed

    private void llenarGrillaAero() {
        
       
        
        Parametro pais = null ;
        Parametro ciudad = null;
        Parametro estado = null;

        if (cbm_Pais.getSelectedIndex() > 0) {
            pais = ((Parametro) cbm_Pais.getSelectedItem());
        }
        if (cbm_ciudad.getSelectedIndex() > 0) {
            ciudad = ((Parametro) cbm_ciudad.getSelectedItem());
        }
        if (cbm_estado.getSelectedIndex() > 0) {
            estado = ((Parametro) cbm_estado.getSelectedItem());
        }


        listaAeropuertos = CAeropuerto.BuscarAeropuerto(pais, ciudad, estado, -1);


        DefaultTableModel dtm = (DefaultTableModel) this.tbl_aeropuerto.getModel();
        Object[] datos = new Object[9];

        int rows = dtm.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            dtm.removeRow(0);
        }

        for (int i = 0; i < listaAeropuertos.size(); i++) {

            datos[0] = listaAeropuertos.get(i).getNombre();
            datos[1] = listaAeropuertos.get(i).getCiudad();
            datos[2] = listaAeropuertos.get(i).getPais();
//           datos[3] = listaAeropuertos.get(i).getCoordX();
//           datos[4] = listaAeropuertos.get(i).getCoordY();
            datos[3] = listaAeropuertos.get(i).getEstado().getValor().toString();
            datos[4] = listaAeropuertos.get(i).getCapacidadMax();
            datos[5] = listaAeropuertos.get(i).getCapacidadActual();

            dtm.addRow(datos);

        }

    }
    

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        AeropuertoAgregar MiVentana = new AeropuertoAgregar(this, true); //llamamos a la clase y creamos un objeto llamado MiVentana
        MiVentana.setVisible(true);//le decimos al compilador que queremos que se vea la ventana
        llenarGrillaAero();

        // MiVentana.setSize(660,415);//le damos el tamaño deseado a nuestra ventana
        // MiVentana.setDefaultCloseOperation(EXIT_ON_CLOSE);//le decimos que al dar clic en la X se cierre nuestra ventana 

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void cbm_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbm_estadoActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
        if (this.tbl_aeropuerto.getSelectedRow() != -1) {
            listaAeropuertos.get(tbl_aeropuerto.getSelectedRow());

            AeropuertoModificar MiVentana = new AeropuertoModificar(this, true, listaAeropuertos.get(tbl_aeropuerto.getSelectedRow())); //llamamos a la clase y creamos un objeto llamado MiVentana
            MiVentana.setVisible(true);//le decimos al compilador que queremos que se vea la ventana
            llenarGrillaAero();
        }else
            JOptionPane.showMessageDialog(this,"Debe seleccionar un Aeropuerto","Advertencia",1);
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void cbm_PaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_PaisActionPerformed
        // TODO add your handling code here:
        cbm_ciudad.removeAllItems();
        cbm_ciudad.addItem("Seleccionar");


        if (cbm_Pais.getSelectedIndex() > 0) {
            ListatipoHijo = ListatipoPar.get(cbm_Pais.getSelectedIndex() - 1).getHijos();
            for (int i = 0; i < ListatipoHijo.size(); i++) {
                Parametro TipoDocBE = (Parametro) ListatipoHijo.get(i);
                cbm_ciudad.addItem(TipoDocBE);
            }
        }
    }//GEN-LAST:event_cbm_PaisActionPerformed

    private void btn_agregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AeropuertoCarga DialogoCarga = new AeropuertoCarga(this, true);
        DialogoCarga.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void cbm_ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbm_ciudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbm_ciudadActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (aeropuertolleno!=1) {
           aeropuertolleno =1;
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
           
           llenarGrillaAero();
           setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } 
        
    }//GEN-LAST:event_formWindowActivated

    private void llenarComboPais() {


        ListatipoPar = CAeropuerto.llenarComboPais();

        for (int i = 0; i < ListatipoPar.size(); i++) {
            Parametro TipoDocBE = (Parametro) ListatipoPar.get(i);

            cbm_Pais.addItem(TipoDocBE);
        }
    }

    private void llenarComboEstado() {

        ListatipoEst = CAeropuerto.llenarComboEstado();
        for (int i = 0; i < ListatipoEst.size(); i++) {
            Parametro TipoDocBE = (Parametro) ListatipoEst.get(i);
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
            java.util.logging.Logger.getLogger(Aeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aeropuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aeropuerto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JComboBox cbm_Pais;
    private javax.swing.JComboBox cbm_ciudad;
    private javax.swing.JComboBox cbm_estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_aeropuerto;
    // End of variables declaration//GEN-END:variables

    private void definirPermisos() {
        List<Permiso> permisos = Sesion.getUsuario().getPerfil().getPermisos();
        
        boolean crear = CSeguridad.validarPermiso(3, "Aeropuertos", "Crear", permisos);
        this.btn_agregar.setEnabled(crear);
        
        boolean modificar = CSeguridad.validarPermiso(3, "Aeropuertos", "Modificar", permisos);
        this.btn_modificar.setEnabled(modificar);
        
        boolean buscar = CSeguridad.validarPermiso(3, "Aeropuertos", "Buscar/Listar", permisos);
        this.btn_buscar.setEnabled(buscar);

//        boolean cargaMasiva = CSeguridad.validarPermiso(3, "Aeropuertos", "Carga Masiva", permisos);
//        this.btnCargaMasiva.setEnabled(cargaMasiva);

        this.setLocationRelativeTo(null);
        pack();
    }
}
