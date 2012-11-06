/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.envios;

import beans.*;
import controllers.*;
import gui.ErrorDialog;
import gui.administracion.aeropuertos.AeropuertoPopup;
import gui.clientes.ClientesPopUp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguelavg
 */
public class EnvioAgregar extends javax.swing.JDialog {

    /**
     * Creates new form Envio
     */
    private Cliente remitente;
    private Cliente destinatario;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Aeropuerto actual;
    private Tarifa tarifa;
    private TipoCambio tipoCambio;
    private Parametro moneda;
    private Parametro doc;
    private Parametro estado;
    private Envio envio;
    private boolean isNuevo;

    public EnvioAgregar(Envio envio, javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.isNuevo = true;
        this.envio = null;

        if (envio != null) {
            //  si no es nuevo...   
            //  set objetos de combos y popups
            this.remitente = envio.getRemitente();
            this.destinatario = envio.getDestinatario();
            this.actual = envio.getActual();
            this.destino = envio.getDestino();
            this.origen = envio.getOrigen();
            this.moneda = envio.getMoneda();
            this.doc = envio.getTipoDocVenta();
            this.estado = envio.getEstado();
            this.envio = envio;
            this.isNuevo = false;
            //  llenar text boxes y combos       
            this.txt_remitente.setText(this.remitente.getNombres() + " " + this.remitente.getApellidos());
            this.txt_destinatario.setText(this.destinatario.getNombres() + " " + this.destinatario.getApellidos());
            this.txt_destino.setText(this.destino.getNombre() + ", " + this.destino.getCiudad() + ", " + this.destino.getPais());
            this.txt_origen.setText(this.origen.getNombre() + ", " + this.origen.getCiudad() + ", " + this.origen.getPais());
            this.txt_actual.setText(this.actual.getNombre() + ", " + this.actual.getCiudad() + ", " + this.actual.getPais());
            llenarCombos(this.moneda, this.doc, this.estado);
            llenarEscalas(this.envio);

            this.txt_numPaquetes.setEnabled(false);
            this.txt_destino.setEnabled(false);
            this.txt_destinatario.setEnabled(false);
            this.txt_remitente.setEnabled(false);
            this.cmb_moneda.setEnabled(false);
            this.cmb_doc.setEnabled(false);
            this.btn_destino.setEnabled(false);
            this.btn_origen.setEnabled(false);
            this.btn_destinatario.setEnabled(false);
            this.btn_remitente.setEnabled(false);
            this.btn_guardar.setEnabled(false);
            if (envio.getActual().getIdAeropuerto() == envio.getDestino().getIdAeropuerto()) {
                btn_out.setEnabled(true);
            } else {
                btn_out.setEnabled(false);
            }
        } else {
            //  si es nuevo...
            btn_factura.setEnabled(false);
            btn_in.setEnabled(false);
            btn_out.setEnabled(false);
            btn_ruta.setEnabled(false);
            llenarCombos(null, null, null);
            this.isNuevo = true;
        }

    }

    private void llenarCombos(Parametro moneda, Parametro doc, Parametro estado) {
        CEnvio cenvio = new CEnvio();
        ArrayList<Parametro> monedas = cenvio.llenarCombo("TIPO_MONEDA");
        ArrayList<Parametro> docs = cenvio.llenarCombo("TIPO_DOC_PAGO_ENVIO");
        ArrayList<Parametro> estados = cenvio.llenarCombo("ESTADO_ENVIO");

        for (Parametro p : monedas) {
            this.cmb_moneda.addItem(p);

            if (moneda != null && moneda.getIdParametro() == p.getIdParametro()) {
                cmb_moneda.setSelectedItem(p);
            }
        }

        for (Parametro p : docs) {
            this.cmb_doc.addItem(p);

            if (doc != null && doc.getIdParametro() == p.getIdParametro()) {
                cmb_moneda.setSelectedItem(p);
            }
        }

        for (Parametro p : estados) {
            this.cmb_estado.addItem(p);

            if (estado != null && estado.getIdParametro() == p.getIdParametro()) {
                cmb_moneda.setSelectedItem(p);

            }
        }
    }

    private void llenarEscalas(Envio envio) {

        if (envio.getEscalas() == null) {
            return;
        }

        Object[] datos = new Object[6];
        DefaultTableModel dtm = (DefaultTableModel) tbl_escalas.getModel();

        for (int i = dtm.getRowCount(); i > 0; i--) {
            dtm.removeRow(0);
        }

        for (Escala e : envio.getEscalas()) {

            datos[0] = e.getNumEscala();
            datos[1] = e.getVuelo().getOrigen().getNombre() + ", " + e.getVuelo().getOrigen().getCiudad() + ", " + e.getVuelo().getOrigen().getPais();
            datos[2] = e.getVuelo().getDestino().getNombre() + ", " + e.getVuelo().getDestino().getCiudad() + ", " + e.getVuelo().getDestino().getPais();
            datos[3] = e.getVuelo().getFechaSalida();
            datos[4] = e.getVuelo().getFechaLlegada();
            datos[5] = e.getEstado();

            dtm.addRow(datos);
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

        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_origen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_remitente = new javax.swing.JButton();
        txt_remitente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_actual = new javax.swing.JTextField();
        btn_factura = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_destinatario = new javax.swing.JButton();
        txt_destinatario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        montoEnvioText = new javax.swing.JTextField();
        btn_in = new javax.swing.JButton();
        btn_out = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmb_doc = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        numDocPagoEnvioText = new javax.swing.JTextField();
        btn_regresar = new javax.swing.JButton();
        numeroEnvioText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_numPaquetes = new javax.swing.JTextField();
        cmb_moneda = new javax.swing.JComboBox();
        btn_guardar = new javax.swing.JButton();
        btn_ruta = new javax.swing.JButton();
        impuestoFactEnvio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fechaRegistroEnvioText = new javax.swing.JTextField();
        fechaRecojoEnvioText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_destino = new javax.swing.JButton();
        txt_destino = new javax.swing.JTextField();
        btn_origen = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        totalEnvioText = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_escalas = new javax.swing.JTable();

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa24x24.png"))); // NOI18N
        jButton10.setText("Factura");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Envíos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/envio4848.png"))); // NOI18N
        jLabel1.setText("Envíos");
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

        txt_origen.setEditable(false);
        txt_origen.setEnabled(false);
        txt_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_origenActionPerformed(evt);
            }
        });

        jLabel2.setText("Aero. Origen:");

        jLabel3.setText("Estado:");

        btn_remitente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_remitente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remitenteActionPerformed(evt);
            }
        });

        txt_remitente.setEditable(false);
        txt_remitente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_remitenteActionPerformed(evt);
            }
        });

        jLabel5.setText("Remitente:");

        jLabel6.setText("Aero. Actual:");

        txt_actual.setEditable(false);
        txt_actual.setEnabled(false);
        txt_actual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_actualActionPerformed(evt);
            }
        });

        btn_factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tarifa24x24.png"))); // NOI18N
        btn_factura.setText("Factura");
        btn_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturaActionPerformed(evt);
            }
        });

        jLabel7.setText("Destinatario:");

        btn_destinatario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_destinatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_destinatarioActionPerformed(evt);
            }
        });

        txt_destinatario.setEditable(false);
        txt_destinatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_destinatarioActionPerformed(evt);
            }
        });

        jLabel8.setText("Monto Unitario:");

        montoEnvioText.setEditable(false);
        montoEnvioText.setEnabled(false);
        montoEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montoEnvioTextActionPerformed(evt);
            }
        });

        btn_in.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/in.png"))); // NOI18N
        btn_in.setText("Nota de entrada");
        btn_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inActionPerformed(evt);
            }
        });

        btn_out.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/out.png"))); // NOI18N
        btn_out.setText("Nota de salida");
        btn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_outActionPerformed(evt);
            }
        });

        jLabel9.setText("Moneda:");

        jLabel10.setText("Doc. pago:");

        cmb_doc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cmb_doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_docActionPerformed(evt);
            }
        });

        jLabel11.setText("Num. doc. pago");

        numDocPagoEnvioText.setEditable(false);
        numDocPagoEnvioText.setEnabled(false);
        numDocPagoEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocPagoEnvioTextActionPerformed(evt);
            }
        });

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        numeroEnvioText.setEditable(false);
        numeroEnvioText.setEnabled(false);
        numeroEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroEnvioTextActionPerformed(evt);
            }
        });

        jLabel12.setText("Impuestos (IVA)");

        jLabel13.setText("Num. paquetes:");

        txt_numPaquetes.setText("1");
        txt_numPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numPaquetesActionPerformed(evt);
            }
        });
        txt_numPaquetes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numPaquetesKeyTyped(evt);
            }
        });

        cmb_moneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cmb_moneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_monedaActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_ruta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/world.png"))); // NOI18N
        btn_ruta.setText("Ver ruta");
        btn_ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rutaActionPerformed(evt);
            }
        });

        impuestoFactEnvio.setEditable(false);
        impuestoFactEnvio.setText("0.19");
        impuestoFactEnvio.setEnabled(false);
        impuestoFactEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impuestoFactEnvioActionPerformed(evt);
            }
        });

        jLabel14.setText("Num. envío:");

        jLabel15.setText("Fecha Registro");

        fechaRegistroEnvioText.setEditable(false);
        fechaRegistroEnvioText.setEnabled(false);
        fechaRegistroEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaRegistroEnvioTextActionPerformed(evt);
            }
        });

        fechaRecojoEnvioText.setEditable(false);
        fechaRecojoEnvioText.setEnabled(false);
        fechaRecojoEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaRecojoEnvioTextActionPerformed(evt);
            }
        });

        jLabel16.setText("Fecha Recojo");

        btn_destino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_destinoActionPerformed(evt);
            }
        });

        txt_destino.setEditable(false);
        txt_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_destinoActionPerformed(evt);
            }
        });

        btn_origen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btn_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_origenActionPerformed(evt);
            }
        });

        jLabel17.setText("Total");

        totalEnvioText.setEditable(false);
        totalEnvioText.setEnabled(false);
        totalEnvioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalEnvioTextActionPerformed(evt);
            }
        });

        jLabel18.setText("Aero. Destino:");

        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No programado" }));
        cmb_estado.setEnabled(false);
        cmb_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_estadoActionPerformed(evt);
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
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(montoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(impuestoFactEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fechaRecojoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(totalEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fechaRegistroEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numDocPagoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_in, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeroEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_numPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(97, 97, 97)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_remitente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_numPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_origen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_destino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(montoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(impuestoFactEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDocPagoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaRegistroEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaRecojoEnvioText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_escalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Escala", "Origen", "Destino", "Fecha salida", "Fecha llegada", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tbl_escalas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_numPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numPaquetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numPaquetesActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void numDocPagoEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocPagoEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocPagoEnvioTextActionPerformed

    private void cmb_docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_docActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_docActionPerformed

    private void btn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_outActionPerformed

    private void btn_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_inActionPerformed

    private void montoEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoEnvioTextActionPerformed

    private void txt_destinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destinatarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destinatarioActionPerformed

    private void btn_destinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_destinatarioActionPerformed
        ClientesPopUp ClientePU = new ClientesPopUp(this, true);
        destinatario = ClientePU.showDialog();
        if (destinatario != null) {
            txt_destinatario.setText(destinatario.getNombres() + " " + destinatario.getApellidos());
        }
    }//GEN-LAST:event_btn_destinatarioActionPerformed

    private void btn_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturaActionPerformed

    private void txt_actualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_actualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_actualActionPerformed

    private void txt_remitenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_remitenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_remitenteActionPerformed

    private void btn_remitenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remitenteActionPerformed
        // TODO add your handling code here:
        ClientesPopUp ClientePU = new ClientesPopUp(this, true);
        remitente = ClientePU.showDialog();
        if (remitente != null) {
            txt_remitente.setText(remitente.getNombres() + " " + remitente.getApellidos());
        }
    }//GEN-LAST:event_btn_remitenteActionPerformed

    private void txt_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenActionPerformed

    private void cmb_monedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_monedaActionPerformed
        // TODO add your handling code here:
        CTipoCambio ctipocambio = new CTipoCambio();
        this.tipoCambio = null;

        if (cmb_moneda.getSelectedIndex() > 0) {
            this.moneda = (Parametro) cmb_moneda.getSelectedItem();
            String error_message = ctipocambio.verificarTipoCambioDolar(this.moneda.getValorUnico());

            if (error_message == null || error_message.isEmpty()) {
                this.tipoCambio = ctipocambio.buscarDolar(this.moneda.getValorUnico());
            } else {
                ErrorDialog.mostrarError(error_message, this);
            }
        }

    }//GEN-LAST:event_cmb_monedaActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        this.moneda = null;
        this.doc = null;
        this.estado = null;

        if (cmb_moneda.getSelectedIndex() > 0) {
            this.moneda = (Parametro) cmb_moneda.getSelectedItem();
        }
        if (cmb_doc.getSelectedIndex() > 0) {
            this.doc = (Parametro) cmb_doc.getSelectedItem();
        }
        if (cmb_estado.getSelectedIndex() > 0) {
            this.estado = (Parametro) cmb_estado.getSelectedItem();
        }

        CEnvio cenvio = new CEnvio();
        String error_message = "";

        error_message = cenvio.validar(this.moneda, this.doc, this.estado, this.origen, this.actual, this.destino, this.remitente, this.destinatario, this.tarifa, this.tipoCambio, txt_numPaquetes.getText());

        beans.Envio env = null;

        if (error_message == null || error_message.isEmpty()) {
            env = cenvio.agregarEnvio(
                    origen,
                    destino,
                    actual,
                    "PROG",
                    remitente,
                    destinatario,
                    Float.parseFloat(montoEnvioText.getText()),
                    (Parametro) cmb_moneda.getSelectedItem(),
                    -1,
                    Integer.parseInt(txt_numPaquetes.getText()),
                    (Parametro) cmb_doc.getSelectedItem(),
                    -1,
                    Float.parseFloat(impuestoFactEnvio.getText()),
                    "",
                    "");
            this.numeroEnvioText.setText(String.valueOf(env.getIdEnvio()));
            this.numDocPagoEnvioText.setText(String.valueOf(env.getNumDocVenta()));

            llenarEscalas(env);
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rutaActionPerformed
        MonitoreoFrame monitoreoDialog = new MonitoreoFrame(envio);
        monitoreoDialog.setVisible(true);
    }//GEN-LAST:event_btn_rutaActionPerformed

    private void impuestoFactEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impuestoFactEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_impuestoFactEnvioActionPerformed

    private void fechaRegistroEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaRegistroEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaRegistroEnvioTextActionPerformed

    private void fechaRecojoEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaRecojoEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaRecojoEnvioTextActionPerformed

    private void numeroEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroEnvioTextActionPerformed

    private void btn_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_destinoActionPerformed
        AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
        destino = aeropuertoPU.showDialog();
        if (destino != null) {
            txt_destino.setText(destino.getNombre() + ", " + destino.getCiudad() + ", " + destino.getPais());
        }

        CEnvio cenvio = new CEnvio();
        String error_message = cenvio.verificarTarifa(origen, destino);

        if (error_message == null || error_message.isEmpty()) {
            tarifa = cenvio.calcularTarifa(origen, destino);
            montoEnvioText.setText(String.valueOf(tarifa.getMonto()));
            totalEnvioText.setText(String.valueOf(Integer.parseInt(txt_numPaquetes.getText()) * Float.parseFloat(montoEnvioText.getText()) * (1 + Float.parseFloat(impuestoFactEnvio.getText()))));
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
    }//GEN-LAST:event_btn_destinoActionPerformed

    private void txt_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destinoActionPerformed

    private void btn_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_origenActionPerformed
        AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
        origen = aeropuertoPU.showDialog();
        actual = origen;
        if (origen != null) {
            txt_origen.setText(origen.getNombre() + ", " + origen.getCiudad() + ", " + origen.getPais());
            txt_actual.setText(actual.getNombre() + ", " + actual.getCiudad() + ", " + actual.getPais());
        }
        /* CEnvio cenvio = new CEnvio();
         tarifa =  cenvio.calcularTarifa(origen,destino);
         montoEnvioText.setText(String.valueOf(tarifa.getMonto())); */
    }//GEN-LAST:event_btn_origenActionPerformed

    private void totalEnvioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalEnvioTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalEnvioTextActionPerformed

    private void txt_numPaquetesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numPaquetesKeyTyped
        // TODO add your handling code here:
        try {
            totalEnvioText.setText(String.valueOf(Integer.parseInt(txt_numPaquetes.getText()) * Float.parseFloat(montoEnvioText.getText()) * (1 + Float.parseFloat(impuestoFactEnvio.getText()))));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_numPaquetesKeyTyped

    private void cmb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estadoActionPerformed

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
            java.util.logging.Logger.getLogger(EnvioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnvioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnvioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnvioAgregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnvioAgregar(null, new javax.swing.JDialog(), true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_destinatario;
    private javax.swing.JButton btn_destino;
    private javax.swing.JButton btn_factura;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_in;
    private javax.swing.JButton btn_origen;
    private javax.swing.JButton btn_out;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton btn_remitente;
    private javax.swing.JButton btn_ruta;
    private javax.swing.JComboBox cmb_doc;
    private javax.swing.JComboBox cmb_estado;
    private javax.swing.JComboBox cmb_moneda;
    private javax.swing.JTextField fechaRecojoEnvioText;
    private javax.swing.JTextField fechaRegistroEnvioText;
    private javax.swing.JTextField impuestoFactEnvio;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField montoEnvioText;
    private javax.swing.JTextField numDocPagoEnvioText;
    private javax.swing.JTextField numeroEnvioText;
    private javax.swing.JTable tbl_escalas;
    private javax.swing.JTextField totalEnvioText;
    private javax.swing.JTextField txt_actual;
    private javax.swing.JTextField txt_destinatario;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_numPaquetes;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_remitente;
    // End of variables declaration//GEN-END:variables

    public Envio showDialog() {
        setVisible(true);
        if (this.isNuevo) {
            return this.envio;
        } else {
            return null;
        }
    }
}
