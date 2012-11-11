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
import gui.envios.VistaPrevia_Factura;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import controllers.CReportes;
import javax.swing.JFileChooser;

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
            this.txt_unitario.setText(CValidator.formatNumber(this.envio.getUnitario()));
            this.txt_iva.setText(CValidator.formatNumber(this.envio.getImpuesto()));
            this.txt_total.setText(CValidator.formatNumber(this.envio.getMonto()));
            this.txt_numEnvio.setText(String.valueOf(this.envio.getIdEnvio()));
            this.txt_numPaquetes.setText(String.valueOf(this.envio.getNumPaquetes()));
            this.txt_numDoc.setText(String.valueOf(this.envio.getIdEnvio()));
            this.txt_fechaReg.setText(CValidator.formatDate(this.envio.getFechaRegistro()));
            this.txt_fechaRec.setText(CValidator.formatDate(this.envio.getFechaRecojo()));


            // falta llenar campos
            llenarCombos(this.isNuevo, this.moneda, this.doc, this.estado);
            llenarEscalas(this.envio);
            deshabilitarCampos();

        } else {
            //  si es nuevo...
            this.isNuevo = true;
            llenarCombos(this.isNuevo, null, null, null);
            this.origen = Sesion.getUsuario().getIdAeropuerto();
            this.actual = this.origen;
            this.txt_origen.setText(this.origen.getNombre() + ", " + this.origen.getCiudad() + ", " + this.origen.getPais());
            this.txt_actual.setText(this.actual.getNombre() + ", " + this.actual.getCiudad() + ", " + this.actual.getPais());
            CParametro cparametro = new CParametro();
            List<Parametro> params = cparametro.buscar(null, "IVA", "IVA", null);
            if (params != null) {
                Parametro iva = params.get(0);
                txt_iva.setText(iva.getValor());
            }
        }

        habilitarBotones(this.isNuevo);

    }

    private void llenarCombos(boolean isNuevo, Parametro moneda, Parametro doc, Parametro estado) {
        CEnvio cenvio = new CEnvio();
        ArrayList<Parametro> monedas = cenvio.llenarCombo("TIPO_MONEDA");
        ArrayList<Parametro> docs = cenvio.llenarCombo("TIPO_DOC_PAGO_ENVIO");
        ArrayList<Parametro> estados = cenvio.llenarCombo("ESTADO_ENVIO");

        for (Parametro p : monedas) {
            this.cmb_moneda.addItem(p);

            if (moneda != null && moneda.getIdParametro() == p.getIdParametro()) {
                cmb_moneda.setSelectedItem(p);
            }

            if (isNuevo && p.getValorUnico().equals("DOL")) {
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

    private void deshabilitarCampos() {
        this.txt_numPaquetes.setEnabled(false);
        this.txt_destino.setEnabled(false);
        this.txt_destinatario.setEnabled(false);
        this.txt_remitente.setEnabled(false);
        this.cmb_moneda.setEnabled(false);
        this.cmb_doc.setEnabled(false);
        this.btn_destino.setEnabled(false);
        this.btn_destinatario.setEnabled(false);
        this.btn_remitente.setEnabled(false);
        this.btn_guardar.setEnabled(false);
    }

    private void habilitarBotones(boolean siNuevo) {
        btn_factura.setEnabled(!siNuevo);
        btn_in.setEnabled(!siNuevo);
        btn_ruta.setEnabled(!siNuevo);

        if (this.destino != null && this.actual.getIdAeropuerto() == this.destino.getIdAeropuerto()) {
            btn_out.setEnabled(true);
        } else {
            btn_out.setEnabled(false);
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

    private void recalcular() {
        double monto = -1;
        double unitario;
        double impuesto = 0;
        int numPaquetes;

        if (CValidator.isDouble(txt_iva.getText())) {
            impuesto = Double.parseDouble(txt_iva.getText());
        }

        if (tarifa != null && CValidator.isInteger(txt_numPaquetes.getText()) && CValidator.isDouble(txt_unitario.getText())) {
            unitario = Double.parseDouble(txt_unitario.getText());
            numPaquetes = Integer.parseInt(txt_numPaquetes.getText());
            monto = numPaquetes * unitario * (1 + impuesto);
        }

        if (monto > 0) {
            txt_total.setText(CValidator.formatNumber(monto));
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
        txt_unitario = new javax.swing.JTextField();
        btn_in = new javax.swing.JButton();
        btn_out = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmb_doc = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txt_numDoc = new javax.swing.JTextField();
        btn_regresar = new javax.swing.JButton();
        txt_numEnvio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_numPaquetes = new javax.swing.JTextField();
        cmb_moneda = new javax.swing.JComboBox();
        btn_guardar = new javax.swing.JButton();
        btn_ruta = new javax.swing.JButton();
        txt_iva = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_fechaReg = new javax.swing.JTextField();
        txt_fechaRec = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_destino = new javax.swing.JButton();
        txt_destino = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_escalas = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

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

        txt_unitario.setEditable(false);
        txt_unitario.setEnabled(false);
        txt_unitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_unitarioActionPerformed(evt);
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

        cmb_doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_docActionPerformed(evt);
            }
        });

        jLabel11.setText("Num. doc. pago");

        txt_numDoc.setEditable(false);
        txt_numDoc.setEnabled(false);
        txt_numDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numDocActionPerformed(evt);
            }
        });

        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        txt_numEnvio.setEditable(false);
        txt_numEnvio.setEnabled(false);
        txt_numEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numEnvioActionPerformed(evt);
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
        txt_numPaquetes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_numPaquetesFocusLost(evt);
            }
        });
        txt_numPaquetes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numPaquetesKeyTyped(evt);
            }
        });

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

        txt_iva.setEditable(false);
        txt_iva.setText("0.19");
        txt_iva.setEnabled(false);
        txt_iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ivaActionPerformed(evt);
            }
        });

        jLabel14.setText("Num. envío:");

        jLabel15.setText("Fecha Registro");

        txt_fechaReg.setEditable(false);
        txt_fechaReg.setEnabled(false);
        txt_fechaReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechaRegActionPerformed(evt);
            }
        });

        txt_fechaRec.setEditable(false);
        txt_fechaRec.setEnabled(false);
        txt_fechaRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechaRecActionPerformed(evt);
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

        jLabel17.setText("Total");

        txt_total.setEditable(false);
        txt_total.setEnabled(false);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cmb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_fechaRec, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_fechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(txt_numEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btn_destino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_numEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fechaRec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
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

    private void txt_numDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numDocActionPerformed

    private void cmb_docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_docActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_docActionPerformed

    private void btn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outActionPerformed
        // TODO add your handling code here:
        int estados = cmb_estado.getItemCount();
        CEnvio cenvio = new CEnvio();

        for (int i = 1; i < estados; i++) {
            Parametro p = (Parametro) cmb_estado.getItemAt(i);
            if (p.getValorUnico().equals("REC")) {
                cmb_estado.setSelectedItem(p);
                this.envio.setEstado(p);
                break;
            }
        }

        cenvio.guardarEnvio(this.envio);
    }//GEN-LAST:event_btn_outActionPerformed

    private void btn_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_inActionPerformed

    private void txt_unitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_unitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_unitarioActionPerformed

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
        VistaPrevia_Factura VistaPrevia_FacturaDialog = new VistaPrevia_Factura(this.envio);
        VistaPrevia_FacturaDialog.setVisible(true);
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
        double vTipoCambio = 1;

        if (this.isNuevo) {
            this.moneda = (Parametro) cmb_moneda.getSelectedItem();
            String error_message = ctipocambio.verificarTipoCambioDolar(this.moneda.getValorUnico());

            if (error_message == null || error_message.isEmpty()) {
                this.tipoCambio = ctipocambio.buscarDolar(this.moneda.getValorUnico());
                if (this.tipoCambio != null) {
                    vTipoCambio = this.tipoCambio.getTipoCambio();
                }

                if (this.tarifa != null) {
                    txt_unitario.setText(CValidator.formatNumber(tarifa.getMonto() * vTipoCambio));
                    recalcular();
                }
            } else {
                ErrorDialog.mostrarError(error_message, this);
                this.tipoCambio = null;
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

        this.moneda = (Parametro) cmb_moneda.getSelectedItem();
        this.doc = (Parametro) cmb_doc.getSelectedItem();


        CEnvio cenvio = new CEnvio();
        CParametro cparametro = new CParametro();
        String error_message;

        error_message = cenvio.validar(this.moneda, this.doc, this.origen, this.actual, this.destino, this.remitente, this.destinatario, this.tarifa, this.tipoCambio, txt_numPaquetes.getText());
        if (error_message == null || error_message.isEmpty()) {
            this.envio = new Envio();
            this.envio.setActual(actual);
            this.envio.setOrigen(origen);
            this.envio.setDestino(destino);
            List<Parametro> params = cparametro.buscar(null, "PROG", "ESTADO_ENVIO", null);
            this.envio.setEstado(params.get(0));
            this.envio.setRemitente(remitente);
            this.envio.setDestinatario(destinatario);
            this.envio.setMoneda(moneda);
            this.envio.setNumPaquetes(Integer.parseInt(txt_numPaquetes.getText()));
            this.envio.setTipoDocVenta(doc);
            this.envio.setFechaRegistro(new Date());
            this.envio.setFechaRecojo(null);
            this.envio.setImpuesto(Double.parseDouble(txt_iva.getText()));

            int numPaquetes = this.envio.getNumPaquetes();
            double unitario = tarifa.getMonto();
            double impuesto = Double.parseDouble(txt_iva.getText());
            double vTipoCambio = 1;
            if (this.tipoCambio != null) {
                vTipoCambio = this.tipoCambio.getTipoCambio();
            }
            double total = vTipoCambio * numPaquetes * unitario * (1 + impuesto);

            this.envio.setUnitario(unitario * vTipoCambio);
            this.envio.setMonto(total);
            error_message = cenvio.calcularRuta(this.envio);

            if (error_message == null || error_message.isEmpty()) {

                this.txt_fechaReg.setText(CValidator.formatDate(this.envio.getFechaRegistro()));
                llenarEscalas(this.envio);
                this.isNuevo = false;

                cenvio.guardarEnvio(this.envio);
                this.txt_numEnvio.setText(String.valueOf(this.envio.getIdEnvio()));
                this.txt_numDoc.setText(String.valueOf(this.envio.getIdEnvio()));
                cenvio.guardarEnvio(this.envio);
                
                deshabilitarCampos();
                habilitarBotones(this.isNuevo);
            } else {
                ErrorDialog.mostrarError(error_message, this);
            }
        } else {
            ErrorDialog.mostrarError(error_message, this);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rutaActionPerformed
        MonitoreoFrame monitoreoDialog = new MonitoreoFrame(this.envio);
        monitoreoDialog.setVisible(true);
    }//GEN-LAST:event_btn_rutaActionPerformed

    private void txt_ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ivaActionPerformed

    private void txt_fechaRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaRegActionPerformed

    private void txt_fechaRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaRecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaRecActionPerformed

    private void txt_numEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numEnvioActionPerformed

    private void btn_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_destinoActionPerformed
        AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true);
        destino = aeropuertoPU.showDialog();
        if (destino != null && destino.getNombre() != null) {
            txt_destino.setText(destino.getNombre() + ", " + destino.getCiudad() + ", " + destino.getPais());
        }

        CEnvio cenvio = new CEnvio();
        String error_message = cenvio.verificarTarifa(origen, destino);

        if (error_message == null || error_message.isEmpty()) {
            this.tarifa = cenvio.calcularTarifa(origen, destino);
            double vTipoCambio = 1;
            if (this.tipoCambio != null) {
                vTipoCambio = this.tipoCambio.getTipoCambio();
            }

            txt_unitario.setText(CValidator.formatNumber(tarifa.getMonto() * vTipoCambio));
            recalcular();
        } else {
            ErrorDialog.mostrarError(error_message, this);
            this.tarifa = null;
        }
    }//GEN-LAST:event_btn_destinoActionPerformed

    private void txt_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destinoActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_numPaquetesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numPaquetesKeyTyped
        // TODO add your handling code here:
        //recalcular();
    }//GEN-LAST:event_txt_numPaquetesKeyTyped

    private void cmb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estadoActionPerformed

    private void txt_numPaquetesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_numPaquetesFocusLost
        // TODO add your handling code here:
        recalcular();
    }//GEN-LAST:event_txt_numPaquetesFocusLost

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
    private javax.swing.JButton btn_out;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton btn_remitente;
    private javax.swing.JButton btn_ruta;
    private javax.swing.JComboBox cmb_doc;
    private javax.swing.JComboBox cmb_estado;
    private javax.swing.JComboBox cmb_moneda;
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
    private javax.swing.JTable tbl_escalas;
    private javax.swing.JTextField txt_actual;
    private javax.swing.JTextField txt_destinatario;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_fechaRec;
    private javax.swing.JTextField txt_fechaReg;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_numDoc;
    private javax.swing.JTextField txt_numEnvio;
    private javax.swing.JTextField txt_numPaquetes;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_remitente;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_unitario;
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
