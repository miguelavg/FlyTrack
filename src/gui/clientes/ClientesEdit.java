/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.clientes;
import beans.Cliente;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import beans.Parametro;
import controllers.CCliente;
import controllers.CParametro;
import controllers.CValidator;
import gui.ErrorDialog;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author jugox
 */
public class ClientesEdit extends javax.swing.JDialog {
    CCliente ClienteBL = new CCliente();
    CParametro ParametroBL = new CParametro();
    List<Parametro> ListaTipoDoc ;
    List<Parametro> ListaCiudades;
    List<Parametro> ListaPaises;
    Cliente ClienteBE;
    int idCliente=-1;
    boolean carga=false;
    /**
     * Creates new form ClientesModificar
     */
    public ClientesEdit(javax.swing.JDialog parent, boolean modal,int id) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        idCliente=id;
        llenarcombos(); 
        if (idCliente!=-1){
            cargarcampos();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNumeroDoc = new javax.swing.JTextField();
        cboTipoDoc = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboCiudad = new javax.swing.JComboBox();
        cboPais = new javax.swing.JComboBox();
        Ciudad = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Flytrack-Clientes - Editar");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nombres");

        jLabel4.setText("Tipo Doc.");

        jLabel5.setText("Apellidos");

        jLabel6.setText("Teléfono");

        jLabel7.setText("Número Doc.");

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomKeyRel(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidosKeyReleased(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelKeyPress(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelKeyRel(evt);
            }
        });

        txtNumeroDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroDocKeyReleased(evt);
            }
        });

        cboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jLabel8.setText("Correo");

        jLabel11.setText("País");

        cboCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        cboPais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cboPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPaisActionPerformed(evt);
            }
        });

        Ciudad.setText("Ciudad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(37, 37, 37)
                        .addComponent(btnCancelar)
                        .addGap(224, 224, 224))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(Ciudad)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtNumeroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(29, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ciudad)
                    .addComponent(cboCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group-2.png"))); // NOI18N
        jLabel1.setText("Clientes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void cargarcampos(){
    
           ClienteBE=ClienteBL.BuscarXid(idCliente);
           txtNombres.setText(ClienteBE.getNombres());
//           txtNombres.setEditable(false);
           txtApellidos.setText(ClienteBE.getApellidos());
           txtApellidos.setEditable(false);
           txtCorreo.setText(ClienteBE.geteMail());
           txtNumeroDoc.setText(ClienteBE.getNumDoc());
           txtTelefono.setText(ClienteBE.getTelefono());
           
           
           
           cboPais.setSelectedItem(ClienteBE.getPais());
           
           for(int i=1;i<cboPais.getItemCount();i++){
               Parametro pais = (Parametro)cboPais.getItemAt(i);
               if (pais.getIdParametro()==ClienteBE.getPais().getIdParametro())
               {
               cboPais.setSelectedIndex(i);
               break;
               
               }
           }
           
//           for(int i=1;i<cboCiudad.getItemCount();i++){
//               Parametro ciudad = (Parametro)cboCiudad.getItemAt(i);
//               if (ciudad.getIdParametro()==ClienteBE.getCiudad().getIdParametro())
//               {
//               cboCiudad.setSelectedIndex(i);
//               break;
//               
//               }
//           }
           
           for(int i=1;i<cboTipoDoc.getItemCount();i++){
               Parametro tipodoc = (Parametro)cboTipoDoc.getItemAt(i);
               if (tipodoc.getIdParametro()==ClienteBE.getTipoDoc().getIdParametro())
               {
                 cboTipoDoc.setSelectedIndex(i);
                 break;
               
               }
           }
            cboTipoDoc.setEnabled(false);  
            txtNumeroDoc.setEditable(false);
//           cboPais.setSelectedItem(ClienteBE.getPais());
//           cboTipoDoc.setSelectedItem(ClienteBE.getTipoDoc());
            
    
        
    }
    
    public void llenarcombos(){
        
        ListaTipoDoc=ParametroBL.buscar(null, null, "TIPO_DOC", null);
        ListaCiudades=ParametroBL.buscar(null, null, "CIUDAD", null);
        ListaPaises= ParametroBL.buscar(null, null, "PAIS", null);
        
//        SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session s = sf.openSession();
//        try {
//            Transaction tx = s.beginTransaction();
//            Query q;
//            
//            q = s.getNamedQuery("ParametrosXTipo");
//            q.setParameter("tipo", "TIPO_DOC");
//            ListaTipoDoc = q.list();
////      
//            Query q2 = s.getNamedQuery("ParametrosXTipo");
//            q2.setParameter("tipo", "CIUDAD");
//            ListaCiudades = q2.list();
//            
//            Query q3= s.getNamedQuery("ParametrosXTipo");
//            q3.setParameter("tipo", "PAIS");
//            ListaPaises=q3.list();
//                     
//            
//            
//            }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//                }
//        finally {
//            s.close();
//        }
        for (int i=0;i<ListaTipoDoc.size();i++)
        {
            Parametro TipoDocBE =(Parametro)ListaTipoDoc.get(i);
            
            cboTipoDoc.addItem(TipoDocBE);
        }
        
        
        
        for (int i=0;i<ListaPaises.size();i++)
        {
            Parametro Pais =(Parametro)ListaPaises.get(i);
            
            cboPais.addItem(Pais);
        }
        
        cboPais.setSelectedIndex(0);
        carga=true;
        
//        for (int i=0;i<ListaCiudades.size();i++)
//        {
//            Parametro Ciudad =(Parametro)ListaCiudades.get(i);
//            
//            cboCiudad.addItem(Ciudad);
//        }
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        
        String error_message = validarcampos();
        if (error_message.isEmpty()){
            if (idCliente==-1){
            ClienteBL.agregarCliente(txtNombres.getText(),txtApellidos.getText(),txtCorreo.getText(),
                    txtTelefono.getText(),                txtNumeroDoc.getText(),(Parametro)cboTipoDoc.getSelectedItem(),
                    (Parametro)cboCiudad.getSelectedItem(),(Parametro)cboPais.getSelectedItem());
            }
            else{
                ClienteBL.ModificarCliente(idCliente,txtNombres.getText(),txtApellidos.getText(),txtCorreo.getText(),
                    txtTelefono.getText(),                txtNumeroDoc.getText(),(Parametro)cboTipoDoc.getSelectedItem(),
                    (Parametro)cboCiudad.getSelectedItem(),(Parametro)cboPais.getSelectedItem());

            }
            setVisible(false);
            dispose();
        }
        else{
            ErrorDialog.mostrarError(error_message, this);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    private String validarcampos(){
        String error_message = "";
        if (txtNombres.getText().isEmpty()||txtApellidos.getText().isEmpty() || txtCorreo.getText().isEmpty() ||
                    txtTelefono.getText().isEmpty() ||  txtNumeroDoc.getText().isEmpty()
                ||cboCiudad.getSelectedIndex()==0 || cboPais.getSelectedIndex()==0 ||cboTipoDoc.getSelectedIndex()==0 ){
            
            error_message = error_message + CValidator.buscarError("ERROR_FT001") + "\n";
                                    
        }
        else{
            
            if (!CValidator.validarEmail(txtCorreo.getText())){
                
                error_message = "El e-mail es inválido";
                
            }
            
            if (CValidator.esAlfanumerico(txtNombres.getText())){
                
                error_message = "El nombre no puede ser alfanumérico";
                
            }
            
            if (idCliente==-1){

                error_message = error_message+ ClienteBL.ValidarDocumento((Parametro)cboTipoDoc.getSelectedItem(),txtNumeroDoc.getText());
            }
            
            if (CValidator.esAlfanumerico(txtNombres.getText())){
                
                error_message = "El nombre es inválido";
                
            }
            
            if (CValidator.esAlfanumerico(txtApellidos.getText())){
                
                error_message = "El Apellido es inválido";
                
            }
            
            if (!CValidator.isInteger(txtTelefono.getText())){
                
                error_message = "El teléfono es inválido";
                
            }
            
            if (!CValidator.isInteger(txtNumeroDoc.getText())){
                
                error_message = "El teléfono es inválido";
            }
            
            
        }
                      
        return error_message;
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cboPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPaisActionPerformed
        // TODO add your handling code here:
        if (cboPais.getSelectedIndex()!=0){
            cboCiudad.removeAllItems();


            cboCiudad.addItem("Seleccionar");
             ListaCiudades = ListaPaises.get(cboPais.getSelectedIndex()-1).getHijos();
                for (int i=0;i<ListaCiudades.size();i++)
            {
                Parametro TipoDocBE =(Parametro)ListaCiudades.get(i);

                cboCiudad.addItem(TipoDocBE);
            }

            if (carga && idCliente!=-1){
                for(int i=1;i<cboCiudad.getItemCount();i++){
                   Parametro ciudad = (Parametro)cboCiudad.getItemAt(i);
                   if (ciudad.getIdParametro()==ClienteBE.getCiudad().getIdParametro())
                   {
                   cboCiudad.setSelectedIndex(i);
                   break;

                   }
               }
            }
        }
    }//GEN-LAST:event_cboPaisActionPerformed

    private void txtTelKeyPress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyPress
        // TODO add your handling code here:
        
       
        
        
        
    }//GEN-LAST:event_txtTelKeyPress

    private void txtTelKeyRel(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyRel
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!CValidator.validarSoloNumeros(letra, txtTelefono)){
            getToolkit().beep(); 
        }
        
    }//GEN-LAST:event_txtTelKeyRel

    private void txtNomKeyRel(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyRel
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!CValidator.validarSoloLetrasYEspacio(letra, txtNombres)){
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtNomKeyRel

    private void txtNumeroDocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocKeyReleased
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!CValidator.validarSoloNumeros(letra, txtNumeroDoc)){
            getToolkit().beep(); 
        }
    }//GEN-LAST:event_txtNumeroDocKeyReleased

    private void txtApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyReleased
        // TODO add your handling code here:
        char letra=evt.getKeyChar();
        if (!CValidator.validarSoloLetrasYEspacio(letra, txtApellidos)){
            getToolkit().beep(); 
        }
        
    }//GEN-LAST:event_txtApellidosKeyReleased
    public int showDialog(){
        setVisible(true);
              
        return 1;
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
            java.util.logging.Logger.getLogger(ClientesEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientesEdit dialog = new ClientesEdit(new javax.swing.JDialog(), true,-1);
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
    private javax.swing.JLabel Ciudad;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cboCiudad;
    private javax.swing.JComboBox cboPais;
    private javax.swing.JComboBox cboTipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumeroDoc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
