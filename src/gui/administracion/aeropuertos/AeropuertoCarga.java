/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administracion.aeropuertos;

import beans.Parametro;
import com.thoughtworks.xstream.XStream;
import controllers.CAeropuerto;
import controllers.CParametro;
import controllers.CSerializer;
import controllers.CValidator;
import gui.ErrorDialog;
import gui.InformationDialog;
import java.awt.Cursor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import xml.XmlAeropuertoString;

/**
 *
 * @author jugox
 */
public class AeropuertoCarga extends javax.swing.JDialog {

    /**
     * Creates new form AeropuertoCarga
     */
    boolean archivovalido=false;
    CParametro cParametro = new CParametro();
        
    static ArrayList<beans.Aeropuerto> listaaero = new ArrayList<beans.Aeropuerto>();
    
    public AeropuertoCarga(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
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
        txtRuta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnRuta = new javax.swing.JButton();
        btn_regresar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Cargar aeropuertos");
        setResizable(false);

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
                .addGap(164, 164, 164)
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

        txtRuta.setEnabled(false);

        jLabel2.setText("Seleccionar archivo");

        btnRuta.setText("...");
        btnRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaActionPerformed(evt);
            }
        });

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRuta)
                .addGap(29, 29, 29))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRuta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_regresarActionPerformed
    public  void generaraeropuertos(){
        
        beans.Aeropuerto aero= new beans.Aeropuerto();
        
        aero.setCapacidadActual(0);
        aero.setCapacidadMax(200);
        aero.setCoordX(200);
        aero.setCoordY(400);
        aero.setNombre("Luis Alberto Bonnet");
    
        Parametro pais=cParametro.buscarId(39);
        Parametro ciudad=cParametro.buscarId(41);
        Parametro estado=cParametro.buscarId(27);
    
        aero.setPais(pais);
        aero.setCiudad(ciudad);
        aero.setEstado(estado);

        listaaero.add(aero);
        
        beans.Aeropuerto aero2= new beans.Aeropuerto();
        
        aero2.setCapacidadActual(0);
        aero2.setCapacidadMax(300);
        aero2.setCoordX(200);
        aero2.setCoordY(400);
        aero2.setNombre("Jorge Soto");
        
        Parametro pais2=cParametro.buscarId(39);
        Parametro ciudad2=cParametro.buscarId(42);
        Parametro estado2=cParametro.buscarId(27);
        
        aero2.setPais(pais2);
        aero2.setCiudad(ciudad2);
        aero2.setEstado(estado2);
        
        listaaero.add(aero2);
        
        beans.Aeropuerto aero3= new beans.Aeropuerto();
        
        aero3.setCapacidadActual(0);
        aero3.setCapacidadMax(200);
        aero3.setCoordX(200);
        aero3.setCoordY(600);
        aero3.setNombre("Roberto Palacios");
        
        Parametro pais3=cParametro.buscarId(39);
        Parametro ciudad3=cParametro.buscarId(43);
        Parametro estado3=cParametro.buscarId(27);
        
        aero3.setPais(pais3);
        aero3.setCiudad(ciudad3);
        aero3.setEstado(estado3);
        
        listaaero.add(aero3);
        
        beans.Aeropuerto aero4= new beans.Aeropuerto();
        
        aero4.setCapacidadActual(0);
        aero4.setCapacidadMax(200);
        aero4.setCoordX(200);
        aero4.setCoordY(800);
        aero4.setNombre("Julinho");
        
        Parametro pais4=cParametro.buscarId(29);
        Parametro ciudad4=cParametro.buscarId(45);
        Parametro estado4=cParametro.buscarId(27);        
        
        aero4.setPais(pais4);
        aero4.setCiudad(ciudad4);
        aero4.setEstado(estado4);
            
     
        listaaero.add(aero4);
        
        
        
    }
    
    public  void serializar(ArrayList lista, String arch) {

        XStream xstream = new XStream();
        String xml = xstream.toXML(lista);

        try {
            FileWriter fw = new FileWriter(arch);
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
    
    private ArrayList<beans.Aeropuerto>  PasaValores(ArrayList<XmlAeropuertoString> xmlAeropuertos){
        
        String error_message="";
        ArrayList<beans.Aeropuerto> aeropuertos = new ArrayList<beans.Aeropuerto>();
        for (int i= 0; i<xmlAeropuertos.size();i++){
            beans.Aeropuerto aero = new beans.Aeropuerto();
            XmlAeropuertoString xmlaero= (XmlAeropuertoString)xmlAeropuertos.get(i);
            Integer s=i;
            if (CValidator.isInteger(xmlaero.getCapacidadActual())&&(Integer.parseInt(xmlaero.getCapacidadActual())>=0)){
            aero.setCapacidadActual(Integer.parseInt(xmlaero.getCapacidadActual()));
            }
            else {
            //error="Los valores de la capacidad actual no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                
                        ErrorDialog.mostrarError("Los valores de la capacidad actual no son validos."+"  Error en el objeto  "+s.toString(), this);
                        aeropuertos=null;
            break;        
            }
            
            if (CValidator.isInteger(xmlaero.getCapacidadMax())&&(Integer.parseInt(xmlaero.getCapacidadMax())>=0)){
            aero.setCapacidadMax(Integer.parseInt(xmlaero.getCapacidadMax()));
            }
            else {
            //error="Los valores de la capacidad maxima no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                        ErrorDialog.mostrarError("Los valores de la capacidad maxima no son validos."+"  Error en el objeto   "+s.toString(), this);
                        aeropuertos=null;
            break;        
            }
            
            //aero.setCapacidadMax(xmlaero.getCapacidadMax());
            
            
            if (CValidator.isInteger(xmlaero.getCoordX())&&(Integer.parseInt(xmlaero.getCoordX())>=0)){
            aero.setCoordX(Integer.parseInt(xmlaero.getCoordX()));
            }
            else {
            //error="Los valores de la coordenada X no son validos.";
               // error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                ErrorDialog.mostrarError("Los valores de la coordenada X no son validos."+"  Error en el objeto   "+s.toString(), this);
                aeropuertos=null;
                break;        
            }
            //aero.setCoordX(xmlaero.getCoordX());
            
            if (CValidator.isInteger(xmlaero.getCoordY())&&(Integer.parseInt(xmlaero.getCoordY())>=0)){
            aero.setCoordY(Integer.parseInt(xmlaero.getCoordY()));
            }
            else {
            //error="Los valores de la coordenada Y no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                ErrorDialog.mostrarError("Los valores de la coordenada Y no son validos."+"   Error en el objeto   "+s.toString(), this);
                aeropuertos=null;
                break;        
            }
            
            //aero.setCoordY(xmlaero.getCoordY());
            beans.Aeropuerto aeropuerto=CAeropuerto.BuscarNombre(xmlaero.getNombre());
            
            if (aeropuerto == null) {
                aero.setNombre(xmlaero.getNombre());
            } else {
                ErrorDialog.mostrarError("Los valores del nombre del aeropuerto no son validos." + "  Error en el objeto   " + s.toString(), this);
                aeropuertos = null;
                break;

            }
                
                
                
            
            Parametro pais=CParametro.buscarXValorUnicoyTipo("PAIS",xmlaero.getPais());
            
            if (pais!=null){
                            aero.setPais(pais);
            }
            else {
                //error="Los valores del pais no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                ErrorDialog.mostrarError("Los valores del pais no son validos."+"  Error en el objeto   "+s.toString(), this);
                aeropuertos=null;
                break;        
            }
            
            Parametro ciudad=CParametro.buscarXValorUnicoyTipo("CIUDAD",xmlaero.getCiudad());
            
            if (ciudad!=null){
                aero.setCiudad(ciudad);
            }
            else {
                //error="Los valores de ciudad no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                ErrorDialog.mostrarError("Los valores de ciudad no son validos." +"  Error en el objeto   "+s.toString(), this);
                aeropuertos=null;
                break;        
            }
            
            Parametro estado=CParametro.buscarXValorUnicoyTipo("ESTADO_AEROPUERTO",xmlaero.getEstado());
            
            if (estado!=null){
                aero.setEstado(estado);
            }
            else {
                //error="Los valores del estado no son validos.";
                //error_message = error_message + CValidator.buscarError("ERROR_FT014") + "\n";
                ErrorDialog.mostrarError("Los valores del estado no son validos."+"  Error en el objeto   "+s.toString(), this);
                aeropuertos=null;
                break;        
            }

            aeropuertos.add(aero);
        }
        return aeropuertos;
        
    }
    
    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
//        generaraeropuertos();
//        serializar(listaaero, "PruebaCargaAero.xml");
//                
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (archivovalido) {
            try {
                ArrayList<XmlAeropuertoString> xmlAeropuertos = CSerializer.deserializar(txtRuta.getText());
//          ArrayList aeropuertos=CSerializer.deserializar("PruebaCargaAero.xml");\
                ArrayList<beans.Aeropuerto> aeropuertos = PasaValores(xmlAeropuertos);
                    if (aeropuertos != null ) {
                        try {
                            //setCursor(new Cursor(Cursor.WAIT_CURSOR));
                            //CAeropuerto.ValidarCaga(vuelos);
                            for (int i = 0; i < aeropuertos.size(); i++) {
                                beans.Aeropuerto aero = (beans.Aeropuerto) aeropuertos.get(i);
                                CAeropuerto.cargarAeropuerto(aero);
                            }
                            //setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            InformationDialog.mostrarInformacion("La operación se realizó con éxito ", this);
                            this.dispose();
                        } catch (Exception e) {
                            //e.printStackTrace();
                            ErrorDialog.mostrarError("Ocurrió un error al cargar los archivos xml.", this);
                        }
                    }
            } catch (Exception e) {
                //e.printStackTrace();
                ErrorDialog.mostrarError("Ocurrió un error al cargar los archivos xml.", this);
            }
        }else {
        ErrorDialog.mostrarError("Ocurrió un error al cargar los archivos xml.", this);    
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btnRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        int rslt = jfc.showSaveDialog(this);
        if (rslt == JFileChooser.APPROVE_OPTION){
            String strArch = jfc.getSelectedFile().getName();
            if (!strArch.trim().isEmpty()){
                String ruta = jfc.getSelectedFile().getPath().trim();
                if (!ruta.isEmpty()){
                    try{
                       if (!ruta.endsWith(".xml")){
                            ErrorDialog.mostrarError("Debe especificar un archivo xml", this);
                        }
                       else{
                           
                           archivovalido=true;
                           txtRuta.setText(ruta);
                       }
                               
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        ErrorDialog.mostrarError("El archivo debe ser xml.",this);
                    }
                }
                else
                    ErrorDialog.mostrarError("Especifique una ruta válida para guardar el archivo",this);
            }
            else
                ErrorDialog.mostrarError("Especifique un nombre al archivo",this);
        }
        
    }//GEN-LAST:event_btnRutaActionPerformed

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
            java.util.logging.Logger.getLogger(AeropuertoCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AeropuertoCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AeropuertoCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AeropuertoCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AeropuertoCarga dialog = new AeropuertoCarga(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton btnRuta;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
