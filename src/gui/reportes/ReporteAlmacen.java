/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.reportes;


import gui.reportes.*;
import beans.Aeropuerto;
import beans.Cliente;
import beans.Parametro;
import controllers.CAeropuerto;
import controllers.CCliente;
import controllers.CParametro;
import controllers.CReportes;
import controllers.CValidator;
import gui.ErrorDialog;
import gui.administracion.aeropuertos.*;
import java.awt.Cursor;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author jugox
 */
public class ReporteAlmacen extends javax.swing.JFrame {

    /**
     * Creates new form ReporteAlmacen
     */
    beans.Aeropuerto aeroori;
    CCliente cliente= new CCliente(); 
    //ClienteDataSource clienteds= new ClienteDataSource();
    
    AlmacenDataSource vuelods= new AlmacenDataSource();
    
    List<Parametro> ListaEstado;
    CParametro ParametroBL = new CParametro();
    
    List <Cliente> ListaCliente;
    List<beans.Aeropuerto> listaAeropuertos;
    ArrayList<beans.Vuelo> listaVuelos= new  ArrayList<beans.Vuelo>();
    Calendar fechini, fechfin;
    
    //boolean exportar=false;
    
    public ReporteAlmacen() {
        initComponents();
        this.setLocationRelativeTo(null);
        //ListaCliente=cliente.Buscar("", "", null, "");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dt_fechfin = new datechooser.beans.DateChooserCombo();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dt_fechini = new datechooser.beans.DateChooserCombo();
        btnBuscar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOrigen = new javax.swing.JTextPane();
        btn_origen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnExportar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlmacen = new javax.swing.JTable();

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte.png"))); // NOI18N
        jButton2.setText("Exportar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWIndowActivated(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gestion_almacen.png"))); // NOI18N
        jLabel3.setText("Almacén");
        jLabel3.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(353, 353, 353))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dt_fechfin.setNothingAllowed(false);
        try {
            dt_fechfin.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechfin.setLocale(new java.util.Locale("es", "PE", ""));

        jLabel5.setText("Fecha Final:");

        jLabel6.setText("Fecha Inicial:");

        dt_fechini.setNothingAllowed(false);
        try {
            dt_fechini.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dt_fechini.setLocale(new java.util.Locale("es", "PE", ""));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel1.setText("Aeropuerto");

        jScrollPane1.setViewportView(txtOrigen);

        btn_origen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_origenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnRegresar)
                .addGap(311, 311, 311))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dt_fechini, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dt_fechfin, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dt_fechini, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dt_fechfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnBuscar))
                .addGap(29, 29, 29))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporte.png"))); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        tblAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Vuelo", "Aeropuerto", "Movimiento", "N°Paquetes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAlmacen);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 691, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_origenActionPerformed
         //TODO add your handling code here:
                AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
                aeroori = aeropuertoPU.showDialog();
        
                if (aeroori != null) {
            
                        txtOrigen.setText(aeroori.getNombre());
                    }
    }//GEN-LAST:event_btn_origenActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        //File jasper=new File();
    vuelods.setListaVuelos(listaVuelos);
    if (aeroori!=null){
        try {
            //JasperReport reporte = JasperCompileManager.compileReport("NetBeansProjects/FlyTrack/src/gui/reportes/ReporteAlmacen.jrxml");
            String master = System.getProperty("user.dir") +
                                "/src/gui/reportes/ReporteAlmacen.jasper";
            
            JasperReport masterReport = null;
            try
            {
                masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
            }
            catch (JRException e)
            {
                //JOptionPane.showMessageDialog(null, "Error cargando la Guía de Remisión: " + e.getMessage(), "Mensaje",0);
                return;
            }
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, vuelods);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            DateFormat df = new SimpleDateFormat("MM_dd_yyyy HH_mm");
            Date fechaactual=new Date(); 
            fechaactual = Calendar.getInstance().getTime(); 
            String reportDate = df.format(fechaactual);
            
            String nombreReporteAlmacen = "Reporte" + this.aeroori.getNombre() +reportDate+ ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombreReporteAlmacen));
            exporter.exportReport();
            
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle(nombreReporteAlmacen);
            jviewer.setVisible(true);
            //exportar=true;
            
            //CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente el reporte Nro " + nombreReporteAlmacen + "\n");
        } catch (JRException e) {
            e.printStackTrace();
            ErrorDialog.mostrarError("Ocurrió un error ", this);
            
        }
    }
    else {
    ErrorDialog.mostrarError("Debe ingresar un aeropuerto.", this);
    }


        
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        llenarGrillaAero();
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void formWIndowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWIndowActivated
        // TODO add your handling code here:
//                // TODO add your handling code here:
//        setCursor(new Cursor(Cursor.WAIT_CURSOR));
//                  llenarGrillaAero();
//        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formWIndowActivated

    
 private void llenarGrillaAero() {
        
       
        
        //Aeropuerto aeropuerto = null ;
        //Parametro ciudad = null;
        //Parametro estado = null;

//        if (cbm_Pais.getSelectedIndex() > 0) {
//            pais = ((Parametro) cbm_Pais.getSelectedItem());
//        }
//        if (cbm_ciudad.getSelectedIndex() > 0) {
//            ciudad = ((Parametro) cbm_ciudad.getSelectedItem());
//        }
//        if (cbm_estado.getSelectedIndex() > 0) {
//            estado = ((Parametro) cbm_estado.getSelectedItem());
//        }
     if (aeroori!=null){
     fechini = dt_fechini.getSelectedDate();
     fechfin = dt_fechfin.getSelectedDate();
     
     Parametro parentrada;
     Parametro parsalida1;
     Parametro parsalida2;
     
     
     ListaEstado = ParametroBL.buscar("", "FIN", "ESTADO_VUELO", null);
     parentrada=ListaEstado.get(0);
     ListaEstado = ParametroBL.buscar("", "PROG", "ESTADO_VUELO", null);
     parsalida1=ListaEstado.get(0);
     ListaEstado = ParametroBL.buscar("", "CAN", "ESTADO_VUELO", null);
     parsalida2=ListaEstado.get(0);
     
     listaAeropuertos = CAeropuerto.BuscarAeropuertoXEnvioXFechas(aeroori, fechini, fechfin,parentrada.getIdParametro(),parsalida1.getIdParametro(),parsalida2.getIdParametro());

     DefaultTableModel dtm = (DefaultTableModel) this.tblAlmacen.getModel();
     Object[] datos = new Object[5];

     int rows = dtm.getRowCount();
     for (int i = rows - 1; i >= 0; i--) {
         dtm.removeRow(0);
     }
     
     listaAeropuertos.get(0).getVuelosLlegada().size();
//     for (int i=0; i < listaAeropuertos.get(0).getVuelosLlegada().size();i++){
//         listaAeropuertos.get(0).getVuelosLlegada().get(i).getIncidencias().size();
//
//         listaAeropuertos.get(0).getVuelosLlegada().get(i).getEscalas().size();
//     }
    
    //listaAeropuertos.get(0).getVuelosLlegada().get(i).get

     for (int i = 0; i < listaAeropuertos.get(0).getVuelosLlegada().size(); i++) {
         if (listaAeropuertos.get(0).getVuelosLlegada().get(i).getCapacidadActual()>0)
         {
            datos[0] = CValidator.formatDate(listaAeropuertos.get(0).getVuelosLlegada().get(i).getFechaLlegada());

   //         if (listaAeropuertos.get(0).getVuelosLlegada().get(i).getEstado().getValor().equals(parentrada.getValor())){         
   //         datos[1] = "Llegada";
   //         }
   //         else {datos[1] = "";}

            datos[1] = listaAeropuertos.get(0).getVuelosLlegada().get(i).getIdVuelo();
            datos[2] = listaAeropuertos.get(0).getVuelosLlegada().get(i).getOrigen().getNombre();
            //datos[3] = listaAeropuertos.get(0).getVuelosLlegada().get(i).getEstado().getValor();
            datos[3] = "Llegada";
            datos[4] = listaAeropuertos.get(0).getVuelosLlegada().get(i).getCapacidadActual();
            dtm.addRow(datos);

   //         for (int j=0;listaAeropuertos.size()>j;j++)
   //            {listaAeropuertos.get(j).getVuelosLlegada().size();
   //            listaAeropuertos.get(j).getVuelosSalida().size();
   //            };
            //incidencias, escala, envio por almancen

            listaVuelos.add(listaAeropuertos.get(0).getVuelosLlegada().get(i));
         }
     }
     
     
     for (int i = 0; i < listaAeropuertos.get(0).getVuelosSalida().size(); i++) {

         if (listaAeropuertos.get(0).getVuelosSalida().get(i).getCapacidadActual()>0){
         datos[0] = CValidator.formatDate(listaAeropuertos.get(0).getVuelosSalida().get(i).getFechaSalida());
         
//         if (!listaAeropuertos.get(0).getVuelosSalida().get(i).getEstado().getValor().equals(parsalida1.getValor())&&!listaAeropuertos.get(0).getVuelosSalida().get(i).getEstado().getValor().equals(parsalida2.getValor())){         
//         datos[1] = "Salida";
//         }
//         else {datos[1] = "";}
         datos[1] = listaAeropuertos.get(0).getVuelosSalida().get(i).getIdVuelo();
         datos[2] = listaAeropuertos.get(0).getVuelosSalida().get(i).getDestino().getNombre();
         //datos[3] = listaAeropuertos.get(0).getVuelosSalida().get(i).getEstado().getValor();
         datos[3]= "Salida";
         datos[4] = listaAeropuertos.get(0).getVuelosSalida().get(i).getCapacidadActual();
         dtm.addRow(datos);
         listaVuelos.add(listaAeropuertos.get(0).getVuelosSalida().get(i));
         }
     }
     }
     else {
     ErrorDialog.mostrarError("Debe ingresar un aeropuerto.", this);
     }
    }
 
 
// 
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
            java.util.logging.Logger.getLogger(ReporteAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteAlmacen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteAlmacen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btn_origen;
    private datechooser.beans.DateChooserCombo dt_fechfin;
    private datechooser.beans.DateChooserCombo dt_fechini;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAlmacen;
    private javax.swing.JTextPane txtOrigen;
    // End of variables declaration//GEN-END:variables
}
