/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.envios;
import javax.swing.JFileChooser;
import controllers.CReportes;
import gui.ErrorDialog;


/**
 *
 * @author joao
 */
import beans.Envio;
import beans.Sesion;
import beans.seguridad.Usuario;
import controllers.CValidator;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class VistaPrevia_Factura extends javax.swing.JDialog {

    /**
     * Creates new form VistaPrevia_Factura
     */
    public VistaPrevia_Factura(Envio envio) {
        initComponents();
        this.envio=envio;
        cargarcampos();
        cargartabla();
    }
    private Envio envio;
    
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
        btn_docPago = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFactura = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtMoneda = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTipoDoc = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VistaPrevia_Factura");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        jLabel1.setText("Documento de Pago");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_docPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa24x24.png"))); // NOI18N
        btn_docPago.setText("Guardar Documento de pago en pdf");
        btn_docPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_docPagoActionPerformed(evt);
            }
        });

        TablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro de Articulos", "Descripcion de la mercancia", "Valor Unitario", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaFactura);
        TablaFactura.getColumnModel().getColumn(0).setPreferredWidth(30);
        TablaFactura.getColumnModel().getColumn(1).setPreferredWidth(300);
        TablaFactura.getColumnModel().getColumn(2).setPreferredWidth(50);
        TablaFactura.getColumnModel().getColumn(3).setPreferredWidth(50);

        jLabel2.setText("Elaborada por : ");

        txtEmpleado.setEnabled(false);

        jLabel3.setText("Empresa             :  Flytrack ");

        jLabel4.setText("Dirección            :  Av. Universitaria San Miguel ");

        jLabel5.setText("País                         : Peru");

        jLabel6.setText("Telefono             : 4546354");

        jLabel7.setText("Nro de Documento de pago:");

        txtNumero.setEnabled(false);

        txtMoneda.setEnabled(false);

        jLabel10.setText("Moneda                :");

        jLabel11.setText("Tipo de Documento:");

        txtTipoDoc.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(btn_docPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10))
                                    .addGap(73, 73, 73)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(107, 107, 107)
                                .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 253, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btn_docPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//  public class TextRenderer extends JTextArea implements TableCellRenderer{
//    public TextRenderer(){
//      setLineWrap(true);
//      setWrapStyleWord(true);
//      setOpaque(true);
//    }
//    public Component getTableCellRendererComponent(JTable table, Object value,
//    boolean isSelected, boolean hasFocus, int row, int column){
//      setText((value == null) ? &#34;&#34;: value.toString());
//      return this;
//    }
//  }
    
    
    
//    private void updateRowHeights()
//{
//    try
//    {
//        for (int row = 0; row < this.TablaFactura.getRowCount(); row++)
//        {
//            int rowHeight = this.TablaFactura.getRowHeight();
//
//            for (int column = 0; column < this.TablaFactura.getColumnCount(); column++)
//            {
//                Component comp = this.TablaFactura.prepareRenderer(this.TablaFactura.getCellRenderer(row, column), row, column);
//                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
//            }
//
//            this.TablaFactura.setRowHeight(row, rowHeight);
//        }
//    }
//    catch(ClassCastException e) {}
//}
    
public void cargarcampos() {   
        txtNumero.setText(String.valueOf(envio.getIdEnvio()));
        //LabelAero.setText(this.envio.getDestino().getNombre()+ " - "+this.envio.getDestino().getPais().getValor() );
        txtEmpleado.setText(Sesion.getUsuario().getNombres()+" "+Sesion.getUsuario().getApellidos());
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        txtMoneda.setText(this.envio.getMoneda().getValor());
        txtTipoDoc.setText(this.envio.getTipoDocVenta().getValor());
        
  
}
        
public void cargartabla(){
    
    //    List<Usuario> listaUsuarios = CUsuario.Buscar( null, null,null,null);    
        //List<Cliente> ListaClientes=ClienteBL.Buscar("","",null,"");
        TablaFactura.setRowHeight(100); 
        DefaultTableModel dtm = (DefaultTableModel) this.TablaFactura.getModel();
        
        
        int rows=dtm.getRowCount();
        for (int i=rows-1; i>=0; i--){
            dtm.removeRow(0);
        }
        
        Object[] datos = new Object[4];
        Integer numPaquetes=envio.getNumPaquetes();
        Double valorUnitario=envio.getUnitario();
        //CValidator validator;
       
       //for (int i = 0; i < listaUsuarios.size(); i++) {
           datos[0] = numPaquetes.toString();
           datos[1] = "<html> Envio del Aeropuerto de Origen: <br> "+""+envio.getOrigen().getNombre()+" <br> "+" Al Aeropuerto de Destino: <br> "+ envio.getDestino().getNombre()+" <br> "+"<html> ";
           datos[2] =CValidator.formatNumber(envio.getUnitario());
           datos[3]= CValidator.formatNumber(envio.getMonto()); 
           
           
           dtm.addRow(datos);
           
           //dtm.
           //   DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)myTable.getCellRenderer(i, j);
                 
          //this.TablaFactura.setRowHeight(0, 50);       
           
           datos[0] = " ";
           datos[1] = " ";
           datos[2] ="IGV:";
           datos[3]= CValidator.formatNumber(envio.getImpuesto()/100*envio.getMonto()); 
                
           dtm.addRow(datos);

           datos[0] = " ";
           datos[1] = " ";
           datos[2] ="Monto Total:";
           datos[3]= CValidator.formatNumber((envio.getMonto())+(envio.getImpuesto()/100*envio.getMonto())); 
           
           
                
           dtm.addRow(datos);
//           updateRowHeights();
       //}
    }    
    
    
    private void btn_docPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_docPagoActionPerformed
//        JFileChooser jfc = new JFileChooser();
//                String error_message = "";
//                
//        int rslt = jfc.showSaveDialog(this);
//        if (rslt == JFileChooser.APPROVE_OPTION) {
//            try{
//            String strArch = jfc.getSelectedFile().getName();
//            if (!strArch.trim().isEmpty()) {
//                try{
//                
//                String ruta = jfc.getSelectedFile().getPath().trim();
//                if (!ruta.isEmpty()) {
//                    try {
//                        //beAlmacen alma = (new blHelper()).obtenerDatosAlmacen();
//                        //if (alma.getNombre() != null){
//                        //if (this.tblReporte.getRowCount()>=1){
//                        if (!ruta.endsWith(".pdf")) {
//                            ruta += ".pdf";
//                        }
//                        //System.out.println(this.usuario.getNombre());
//                        float[] anchos = {5f, 11f, 5f,5f};
//                        //this.usuario.getNombre()
//                        //alma.getNombre()
//                        //this.palletElegido
//                        String usuariologeado=Sesion.getUsuario().getNombres()+" "+Sesion.getUsuario().getApellidos();
//                        if (this.envio.getTipoDocVenta().getValor().equals("Factura")) {
//                            CReportes.crearPDF_Trazabilidad_Factura(ruta, "Factura", usuariologeado, "FlyTrack", "Factura", anchos, this.envio);
//                            CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente la factura en la ruta\n" + ruta);                        
//                        }
//                        if (this.envio.getTipoDocVenta().getValor().equals("Boleta")) {
//                            CReportes.crearPDF_Trazabilidad_Factura(ruta, "Boleta", usuariologeado, "FlyTrack", "Boleta", anchos, this.envio);
//                            CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente la boleta en la ruta\n" + ruta);
//                            
//                        }
//                        //}
//                        //else
//                        //CReportes.mostrarMensajeAdvertencia("No existen registros en el historial.");
//                        //}
//                        //else
//                        //  visualHelper.mostrarMensajeAdvertencia("No se ha ingresado información sobre el almacén.");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        //CReportes.mostrarMensajeError("Ocurrió un error al generar el documento de pago.");
//                        error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
//                        ErrorDialog.mostrarError(error_message, this);
//                    }
//                } else {
//                    //CReportes.mostrarMensajeError("Especifique una ruta válida para guardar el archivo del documento de pago.");
//                    error_message = error_message + CValidator.buscarError("ERROR_FT015") + "\n";
//                    ErrorDialog.mostrarError(error_message, this);
//                }
//            }catch (Exception e) {
//                        e.printStackTrace();
//                        //CReportes.mostrarMensajeError("Ocurrió un error al generar el documento de pago.");
//                        error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
//                        ErrorDialog.mostrarError(error_message, this);
//                    }       
//            } else {
//                //CReportes.mostrarMensajeError("Especifique un nombre al archivo que va a imprimir.");
//                error_message = error_message + CValidator.buscarError("ERROR_FT016") + "\n";
//                ErrorDialog.mostrarError(error_message, this);
//            }
//        }
//            catch (Exception e) {
//                        e.printStackTrace();
//                        //CReportes.mostrarMensajeError("Ocurrió un error al generar el documento de pago.");
//                        error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
//                        ErrorDialog.mostrarError(error_message, this);
//                    }  
//        }
        
        
        
        
//        try {
//            JasperReport reporte = JasperCompileManager.compileReport("/home/joao/NetBeansProjects/FlyTrack/src/gui/reportes/ReportePrueba.jrxml");
////    JasperReport reporte= (JasperReport) JRLoader.loadObjectFromFile("/home/joao/NetBeansProjects/FlyTrack/src/gui/reportes/ReportePrueba.jrxml"); 
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, enviods);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporteEnPdf.pdf"));
//            exporter.exportReport();
//
//
//        } catch (JRException e) {
//            e.printStackTrace();
//        }


    }//GEN-LAST:event_btn_docPagoActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrevia_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrevia_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrevia_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrevia_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrevia_Factura(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaFactura;
    private javax.swing.JButton btn_docPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtMoneda;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTipoDoc;
    // End of variables declaration//GEN-END:variables
}
