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
import gui.reportes.EnvioDataSource;
import gui.reportes.EscalaDataSource;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author miguelavg
 */
public class EnvioAgregar extends javax.swing.JFrame {

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
    private Parametro estadoFactura;
    private Envio envio;
    private boolean isNuevo;
    private boolean wasNuevo;
    private double iva;
    EscalaDataSource escalads;
    EnvioDataSource enviods;

    public EnvioAgregar(Envio envio, javax.swing.JFrame parent) {
        //super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.isNuevo = true;
        this.wasNuevo = true;
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
            this.estadoFactura = envio.getEstadoFactura();
            this.doc = envio.getTipoDocVenta();
            this.estado = envio.getEstado();
            this.envio = envio;
            this.isNuevo = false;
            this.wasNuevo = false;

            //  llenar text boxes y combos       
            this.txt_remitente.setText(this.remitente.getNombres() + " " + this.remitente.getApellidos());
            this.txt_destinatario.setText(this.destinatario.getNombres() + " " + this.destinatario.getApellidos());
            this.txt_destino.setText(this.destino.getNombre() + ", " + this.destino.getCiudad() + ", " + this.destino.getPais());
            this.txt_origen.setText(this.origen.getNombre() + ", " + this.origen.getCiudad() + ", " + this.origen.getPais());

            if (this.actual != null) {
                this.txt_actual.setText(this.actual.getNombre() + ", " + this.actual.getCiudad() + ", " + this.actual.getPais());
            }
            this.txt_unitario.setText(CValidator.formatNumber(this.envio.getUnitario()));
            this.txt_iva.setText(CValidator.formatNumber(this.envio.getImpuesto()));
            this.lbl_iva.setText("IVA(" + CValidator.formatNumber(this.envio.getIva() * 100) + "%):");
            this.txt_total.setText(CValidator.formatNumber(this.envio.getMonto()));
            this.txt_numEnvio.setText(String.valueOf(this.envio.getIdEnvio()));
            this.txt_numPaquetes.setText(String.valueOf(this.envio.getNumPaquetes()));
            this.txt_numDoc.setText(String.valueOf(this.envio.getNumDocVenta()));
            this.txt_fechaReg.setText(CValidator.formatDate(this.envio.getFechaRegistro()));
            if (this.envio.getFechaRecojo() != null) {
                this.txt_fechaRec.setText(CValidator.formatDate(this.envio.getFechaRecojo()));
            }


            // falta llenar campos
            llenarCombos(this.isNuevo, this.moneda, this.doc, this.estado, this.estadoFactura);
            llenarEscalas(this.envio);
            deshabilitarCampos();

        } else {
            //  si es nuevo...
            this.isNuevo = true;
            llenarCombos(this.isNuevo, null, null, null, null);

            if (Sesion.getUsuario().getIdAeropuerto() != null) {
                this.origen = Sesion.getUsuario().getIdAeropuerto();
            } else {
                this.origen = null;
            }

            if (this.origen != null) {

                this.actual = this.origen;

                this.txt_origen.setText(this.origen.getNombre() + ", " + this.origen.getCiudad() + ", " + this.origen.getPais());
                this.txt_actual.setText(this.actual.getNombre() + ", " + this.actual.getCiudad() + ", " + this.actual.getPais());

                Parametro pIva;
                List<Parametro> params = CParametro.buscar(null, null, "IVA", this.origen.getCiudad());
                if (params != null && !params.isEmpty()) {
                    pIva = params.get(0);
                } else {
                    pIva = CParametro.buscarXValorUnicoyTipo("IVA", "IVA");
                }

                if (pIva != null) {
                    this.iva = Double.parseDouble(pIva.getValor());
                    this.lbl_iva.setText("IVA(" + CValidator.formatNumber(this.iva * 100) + "%):");
                }
            }

        }

        if (this.isNuevo) {
            this.setTitle("FlyTrack - Envíos - Agregar");
        } else {
            this.setTitle("FlyTrack - Envíos - Modificar");
        }

        habilitarBotones(this.isNuevo);

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

    private void llenarCombos(boolean isNuevo, Parametro moneda, Parametro doc, Parametro estado, Parametro estadoFactura) {
        CEnvio cenvio = new CEnvio();
        ArrayList<Parametro> monedas = cenvio.getMonedas();
        Parametro dol = CParametro.buscarXValorUnicoyTipo("TIPO_MONEDA", "DOL");
        monedas.add(dol);

        ArrayList<Parametro> docs = cenvio.llenarCombo("TIPO_DOC_PAGO_ENVIO");
        ArrayList<Parametro> estados = cenvio.llenarCombo("ESTADO_ENVIO");
        ArrayList<Parametro> estadosFactura = cenvio.llenarCombo("ESTADO_FACTURA");

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
                cmb_doc.setSelectedItem(p);
            }
        }

        for (Parametro p : estados) {
            this.cmb_estado.addItem(p);

            if (estado != null && estado.getIdParametro() == p.getIdParametro()) {
                cmb_estado.setSelectedItem(p);

            }
        }

        for (Parametro p : estadosFactura) {
            this.cmb_estadoFactura.addItem(p);

            if (estadoFactura != null && estadoFactura.getIdParametro() == p.getIdParametro()) {
                cmb_estadoFactura.setSelectedItem(p);
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

        btn_in.setEnabled(!siNuevo);
        btn_ruta.setEnabled(!siNuevo);
        btn_bitacorareal.setEnabled(!siNuevo);
        btn_almacen.setEnabled(!siNuevo);

        if (this.actual != null && this.actual.getIdAeropuerto() == this.origen.getIdAeropuerto() && !this.isNuevo && !this.envio.getEstado().getValorUnico().equals("CAN")) {
            btn_anular.setEnabled(true);
        } else {
            btn_anular.setEnabled(false);
        }

        if (this.destino != null && this.actual != null && this.actual.getIdAeropuerto() == this.destino.getIdAeropuerto()) {
            btn_out.setEnabled(true);
        } else {
            btn_out.setEnabled(false);
        }

        if (!this.isNuevo && this.envio.getEstadoFactura().getValorUnico().equals("EM")) {
            btn_factura.setEnabled(true);
        } else {
            btn_factura.setEnabled(false);
        }

        if (!this.isNuevo && this.envio.getEstado().getValorUnico().equals("IND")) {
            btn_recalcular.setEnabled(true);
        } else {
            btn_recalcular.setEnabled(false);
        }
    }

    private void llenarEscalas(Envio envio) {

        if (envio.getEscalas() == null) {
            return;
        }

        Object[] datos = new Object[7];
        DefaultTableModel dtm = (DefaultTableModel) tbl_escalas.getModel();

        for (int i = dtm.getRowCount(); i > 0; i--) {
            dtm.removeRow(0);
        }

        for (Escala e : envio.getEscalasOrdenadasAsc()) {

            datos[0] = e.getNumEscala();
            datos[1] = e.getVuelo().getIdVuelo();
            datos[2] = e.getVuelo().getOrigen().getNombre() + ", " + e.getVuelo().getOrigen().getCiudad() + ", " + e.getVuelo().getOrigen().getPais();
            datos[3] = e.getVuelo().getDestino().getNombre() + ", " + e.getVuelo().getDestino().getCiudad() + ", " + e.getVuelo().getDestino().getPais();
            datos[4] = CValidator.formatDate(CEnvio.getFechaSalidaReal(e));
            datos[5] = CValidator.formatDate(CEnvio.getFechaLlegadaReal(e));
            datos[6] = e.getEstado();

            dtm.addRow(datos);
        }
    }

    private void recalcular() {
        double monto = -1;
        double unitario;
        double impuesto = -1;
        int numPaquetes;

        if (tarifa != null && CValidator.isInteger(txt_numPaquetes.getText()) && CValidator.isDouble(txt_unitario.getText())) {
            unitario = Double.parseDouble(txt_unitario.getText());
            numPaquetes = Integer.parseInt(txt_numPaquetes.getText());
            monto = numPaquetes * unitario * (1 + this.iva);
            impuesto = numPaquetes * unitario * this.iva;
        }

        if (monto > 0 && impuesto > 0) {
            txt_iva.setText(CValidator.formatNumber(impuesto));
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
        btn_ruta1 = new javax.swing.JButton();
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
        lbl_iva = new javax.swing.JLabel();
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
        cmb_estadoFactura = new javax.swing.JComboBox();
        btn_anular = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_almacen = new javax.swing.JButton();
        btn_bitacorareal = new javax.swing.JButton();
        btn_recalcular = new javax.swing.JButton();
        btn_bitacorateorica = new javax.swing.JButton();
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

        btn_ruta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/world.png"))); // NOI18N
        btn_ruta1.setText("Ver ruta");
        btn_ruta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ruta1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlyTrack - Envíos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

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
                .addGap(575, 575, 575)
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
        btn_factura.setText("Doc. pago");
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

        cmb_doc.setEnabled(false);
        cmb_doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_docActionPerformed(evt);
            }
        });

        jLabel11.setText("Num. doc. pago:");

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

        lbl_iva.setText("IVA:");

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
        txt_iva.setEnabled(false);
        txt_iva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ivaActionPerformed(evt);
            }
        });

        jLabel14.setText("Num. envío:");

        jLabel15.setText("Fecha Registro:");

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

        jLabel16.setText("Fecha Recojo:");

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

        jLabel17.setText("Total:");

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

        cmb_estadoFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Por emitir" }));
        cmb_estadoFactura.setEnabled(false);
        cmb_estadoFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_estadoFacturaActionPerformed(evt);
            }
        });

        btn_anular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove.png"))); // NOI18N
        btn_anular.setText("Anular");
        btn_anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anularActionPerformed(evt);
            }
        });

        jLabel4.setText("Estado doc.:");

        btn_almacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/storage.png"))); // NOI18N
        btn_almacen.setText("Mov. almacén");

        btn_bitacorareal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lists.png"))); // NOI18N
        btn_bitacorareal.setText("Bitácora Real");
        btn_bitacorareal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bitacorarealActionPerformed(evt);
            }
        });

        btn_recalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar24x24.png"))); // NOI18N
        btn_recalcular.setText("Recalcular ruta");
        btn_recalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recalcularActionPerformed(evt);
            }
        });

        btn_bitacorateorica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lists.png"))); // NOI18N
        btn_bitacorateorica.setText("Bitácora Teórica");
        btn_bitacorateorica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bitacorateoricaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_fechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_estadoFactura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fechaRec, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(298, 298, 298)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_iva)
                            .addComponent(txt_total)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(btn_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(320, 320, 320)
                                .addComponent(jLabel18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txt_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txt_numEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_numPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbl_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_in, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_bitacorareal, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_anular, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_recalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_bitacorateorica))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(479, 479, 479))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_in, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bitacorareal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_out, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_recalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_bitacorateorica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_remitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_destinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_numPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_numEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_estadoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fechaRec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_escalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Escala", "Cód. vuelo", "Origen", "Destino", "Fecha salida", "Fecha llegada", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tbl_escalas);
        tbl_escalas.getColumnModel().getColumn(0).setMinWidth(60);
        tbl_escalas.getColumnModel().getColumn(0).setMaxWidth(60);
        tbl_escalas.getColumnModel().getColumn(1).setMinWidth(85);
        tbl_escalas.getColumnModel().getColumn(1).setMaxWidth(85);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btn_ruta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ruta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ruta1ActionPerformed

    private void btn_recalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recalcularActionPerformed
        // TODO add your handling code here:
        CEnvio cenvio = new CEnvio();

        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        String error_message = cenvio.calcularRuta(this.envio, this.envio.getFechaRegistro(), 1);

        if (error_message == null || error_message.isEmpty()) {
            this.estado = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", "PROG");
            this.envio.setEstado(this.estado);

            cenvio.guardarEnvio(this.envio);

            llenarEscalas(this.envio);
            llenarCombos(this.isNuevo, this.moneda, this.doc, this.estado, this.estadoFactura);
            btn_recalcular.setEnabled(false);
        } else {
            
            ErrorDialog.mostrarError(error_message, this);
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_recalcularActionPerformed

    private void btn_anularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anularActionPerformed
        // TODO add your handling code here:
        int estados = cmb_estado.getItemCount();
        int estadosFactura = cmb_estadoFactura.getItemCount();
        CEnvio cenvio = new CEnvio();

        for (int i = 1; i < estados; i++) {
            Parametro p = (Parametro) cmb_estado.getItemAt(i);
            if (p.getValorUnico().equals("CAN")) {
                cmb_estado.setSelectedItem(p);
                this.envio.setEstado(p);
                break;
            }
        }

        for (int i = 1; i < estadosFactura; i++) {
            Parametro p = (Parametro) cmb_estadoFactura.getItemAt(i);
            if (p.getValorUnico().equals("AN")) {
                cmb_estadoFactura.setSelectedItem(p);
                this.envio.setEstadoFactura(p);
                break;
            }
        }

        Parametro estadoEscala = CParametro.buscarXValorUnicoyTipo("ESTADO_ESCALA", "CAN");
        int cAero = this.envio.getActual().getCapacidadActual();
        this.envio.getActual().setCapacidadActual(cAero - this.envio.getNumPaquetes());

        for (Escala e : this.envio.getEscalas()) {
            e.setEstado(estadoEscala);
            int cVuelo = e.getVuelo().getCapacidadActual();
            e.getVuelo().setCapacidadActual(cVuelo - this.envio.getNumPaquetes());
        }

        this.envio.setFechaRecojo(new Date());
        llenarEscalas(this.envio);

        btn_anular.setEnabled(false);
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        cenvio.guardarEnvio(this.envio);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_anularActionPerformed

    private void cmb_estadoFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estadoFacturaActionPerformed

    private void cmb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estadoActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destinoActionPerformed

    private void btn_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_destinoActionPerformed

        if (actual != null) {
            AeropuertoPopup aeropuertoPU = new AeropuertoPopup(this, true, actual.getIdAeropuerto());
            destino = aeropuertoPU.showDialog();
            if (destino != null && destino.getNombre() != null) {
                txt_destino.setText(destino.getNombre() + ", " + destino.getCiudad() + ", " + destino.getPais());

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
            } else {
                txt_destino.setText("");
            }
        }
    }//GEN-LAST:event_btn_destinoActionPerformed

    private void txt_fechaRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaRecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaRecActionPerformed

    private void txt_fechaRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaRegActionPerformed

    private void txt_ivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ivaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ivaActionPerformed

    private void btn_rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rutaActionPerformed
        MonitoreoFrame monitoreoDialog = new MonitoreoFrame(this.envio);
        monitoreoDialog.showFrame();
    }//GEN-LAST:event_btn_rutaActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        this.moneda = null;
        this.doc = null;
        this.estado = null;

        this.moneda = (Parametro) cmb_moneda.getSelectedItem();
        this.doc = (Parametro) cmb_doc.getSelectedItem();

        CEnvio cenvio = new CEnvio();
        String error_message;

        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        error_message = cenvio.validar(this.moneda, this.doc, this.origen, this.actual, this.destino, this.remitente, this.destinatario, this.tarifa, this.tipoCambio, txt_numPaquetes.getText());
        if (error_message == null || error_message.isEmpty()) {
            this.envio = new Envio();
            this.envio.setActual(actual);
            this.envio.setOrigen(origen);
            this.envio.setDestino(destino);
            this.estado = CParametro.buscarXValorUnicoyTipo("ESTADO_ENVIO", "PROG");
            this.envio.setEstado(this.estado);
            this.estadoFactura = CParametro.buscarXValorUnicoyTipo("ESTADO_FACTURA", "EM");
            this.envio.setEstadoFactura(this.estadoFactura);
            this.envio.setRemitente(remitente);
            this.envio.setDestinatario(destinatario);
            this.envio.setMoneda(moneda);
            this.envio.setNumPaquetes(Integer.parseInt(txt_numPaquetes.getText()));
            this.envio.setTipoDocVenta(doc);
            this.envio.setFechaRegistro(new Date());
            this.envio.setFechaRecojo(null);
            this.envio.setImpuesto(Double.parseDouble(txt_iva.getText()));
            this.envio.setIva(this.iva);

            int numPaquetes = this.envio.getNumPaquetes();
            double unitario = tarifa.getMonto();
            double impuesto = this.iva;
            double vTipoCambio = 1;
            if (this.tipoCambio != null) {
                vTipoCambio = this.tipoCambio.getTipoCambio();
            }
            double total = vTipoCambio * numPaquetes * unitario * (1 + impuesto);

            this.envio.setUnitario(unitario * vTipoCambio);
            this.envio.setMonto(total);
            error_message = cenvio.calcularRuta(this.envio, this.envio.getFechaRegistro(), 1);

            if (error_message == null || error_message.isEmpty()) {

                this.txt_fechaReg.setText(CValidator.formatDate(this.envio.getFechaRegistro()));
                llenarEscalas(this.envio);
                this.isNuevo = false;

                int capacidad = envio.getActual().getCapacidadActual();
                envio.getActual().setCapacidadActual(capacidad + envio.getNumPaquetes());

                this.envio.setNumDocVenta(CEnvio.getNextNumDoc(doc.getValorUnico()));
                cenvio.guardarEnvio(this.envio);

                llenarCombos(this.isNuevo, this.moneda, this.doc, this.estado, this.estadoFactura);
                this.txt_numEnvio.setText(String.valueOf(this.envio.getIdEnvio()));
                this.txt_numDoc.setText(String.valueOf(this.envio.getNumDocVenta()));

                deshabilitarCampos();
                habilitarBotones(this.isNuevo);
            } else {
                this.envio = null;
                ErrorDialog.mostrarError(error_message, this);
            }
        } else {
            this.envio = null;
            ErrorDialog.mostrarError(error_message, this);
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_guardarActionPerformed

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

    private void txt_numPaquetesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numPaquetesKeyTyped
        // TODO add your handling code here:
        //recalcular();
    }//GEN-LAST:event_txt_numPaquetesKeyTyped

    private void txt_numPaquetesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_numPaquetesFocusLost
        // TODO add your handling code here:
        recalcular();
    }//GEN-LAST:event_txt_numPaquetesFocusLost

    private void txt_numPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numPaquetesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numPaquetesActionPerformed

    private void txt_numEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numEnvioActionPerformed

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

        if (!this.envio.getEstado().getValorUnico().equals("REC")) {
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
            int cAero = this.envio.getActual().getCapacidadActual();
            this.envio.getActual().setCapacidadActual(cAero - this.envio.getNumPaquetes());
            this.envio.setFechaRecojo(new Date());
            txt_fechaRec.setText(CValidator.formatDate(this.envio.getFechaRecojo()));
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            cenvio.guardarEnvio(this.envio);
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
//        NotaSalida NotaSalida = new NotaSalida(this, true, this.envio);
//        NotaSalida.setVisible(true);
        
            //Calendar calendar = Calendar.getInstance();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        
        enviods = new EnvioDataSource();
        enviods.setEnvio(this.envio);

        try {
            String master = System.getProperty("user.dir")
                    + "/src/gui/reportes/nota_salida.jasper";

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
            } catch (JRException e) {

                return;
            }
            DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy HH_mm");
            Map parametro = new HashMap();
            String nombreempleado = Sesion.getUsuario().getNombres() + " " + Sesion.getUsuario().getApellidos();
            String horaactual = dateFormat.format(this.envio.getFechaRecojo()).substring(11, 16);
            String fechaactualaux = dateFormat.format(this.envio.getFechaRecojo()).substring(0, 10);
            parametro.put("empleado", nombreempleado);
            parametro.put("horaactual", horaactual);
            parametro.put("fechaactual", fechaactualaux);

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, enviods);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

            DateFormat df = new SimpleDateFormat("MM_dd_yyyy HH_mm");
            Date fechaactual = new Date();
            fechaactual = Calendar.getInstance().getTime();
            String reportDate = df.format(fechaactual);

            String nombreNotaSalida = "Nota de salida_" + this.envio.getNumDocVenta() + "_" + reportDate + ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombreNotaSalida));
            exporter.exportReport();

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //setModal(false);
            jviewer.setTitle(nombreNotaSalida);
            jviewer.setVisible(true);
            jviewer.setAlwaysOnTop(true);
            //CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente la Boleta Nro" + this.envio.getNumDocVenta() + "\n");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_outActionPerformed

    private void btn_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inActionPerformed
        // TODO add your handling code here:
//        NotaEntrada NotaEntradaDialog = new NotaEntrada(this.envio);
//        NotaEntradaDialog.setVisible(true);

        enviods = new EnvioDataSource();
        enviods.setEnvio(this.envio);

        try {
            String master = System.getProperty("user.dir")
                    + "/src/gui/reportes/nota_entrada.jasper";

            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
            } catch (JRException e) {

                return;
            }
            Map parametro = new HashMap();
            String nombreempleado = Sesion.getUsuario().getNombres() + " " + Sesion.getUsuario().getApellidos();
            //String horaactual = dateFormat.format(calendar.getTime()).substring(11, 16);
            //String fechaactualaux = dateFormat.format(calendar.getTime()).substring(0, 10);
            parametro.put("empleado", nombreempleado);
            //parametro.put("horaactual", horaactual);
            //parametro.put("fechaactual", fechaactualaux);

            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametro, enviods);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

            DateFormat df = new SimpleDateFormat("MM_dd_yyyy HH_mm");
            Date fechaactual = new Date();
            fechaactual = Calendar.getInstance().getTime();
            String reportDate = df.format(fechaactual);

            String nombreNotaEntrada = "Nota de Entrada_" + this.envio.getNumDocVenta() + "_" + reportDate + ".pdf";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombreNotaEntrada));
            exporter.exportReport();

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            //setModal(false);
            jviewer.setTitle(nombreNotaEntrada);
            jviewer.setVisible(true);
            jviewer.setAlwaysOnTop(true);
            //CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente la Boleta Nro" + this.envio.getNumDocVenta() + "\n");
        } catch (JRException e) {
            e.printStackTrace();
        }    
        
        
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
        } else {
            txt_destinatario.setText("");
        }
    }//GEN-LAST:event_btn_destinatarioActionPerformed

    private void btn_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturaActionPerformed
//        VistaPrevia_Factura VistaPrevia_FacturaDialog = new VistaPrevia_Factura(this.envio);
//        VistaPrevia_FacturaDialog.setVisible(true);
        if (this.envio.getTipoDocVenta().getValor().equals("Factura")) {
            enviods = new EnvioDataSource();
            enviods.setEnvio(this.envio);
            try {
                String master = System.getProperty("user.dir")
                        + "/src/gui/reportes/factura.jasper";

                JasperReport masterReport = null;
                try {
                    masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
                } catch (JRException e) {
                    //JOptionPane.showMessageDialog(null, "Error cargando la Guía de Remisión: " + e.getMessage(), "Mensaje",0);
                    return;
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, enviods);
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

                String nombredocFactura = "Factura_" + this.envio.getNumDocVenta() + ".pdf";
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombredocFactura));
                exporter.exportReport();

                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                //setModal(false);
                jviewer.setTitle(nombredocFactura);
                jviewer.setVisible(true);
                jviewer.setAlwaysOnTop(true);
                
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

        if (this.envio.getTipoDocVenta().getValor().equals("Boleta")) {
            enviods = new EnvioDataSource();
            enviods.setEnvio(this.envio);
            try {
                String master = System.getProperty("user.dir")
                        + "/src/gui/reportes/boleta.jasper";

                JasperReport masterReport = null;
                try {
                    masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
                } catch (JRException e) {
                    //JOptionPane.showMessageDialog(null, "Error cargando la Guía de Remisión: " + e.getMessage(), "Mensaje",0);
                    return;
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, enviods);
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

                String nombredocBoleta = "Boleta_" + this.envio.getNumDocVenta() + ".pdf";
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombredocBoleta));
                exporter.exportReport();

                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                //setModal(false);
                jviewer.setTitle(nombredocBoleta);
                jviewer.setVisible(true);
                jviewer.setAlwaysOnTop(true);
                //CReportes.mostrarMensajeSatisfaccion("Se guardó satisfactoriamente la Boleta Nro" + this.envio.getNumDocVenta() + "\n");
            } catch (JRException e) {
                e.printStackTrace();
            }
        }
        
        
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

            int docs = cmb_doc.getItemCount();
            String sDoc = remitente.getTipoDoc().getValorUnico();
            String sDocPago = "BOL";
            if (sDoc.equals("RUC")) {
                sDocPago = "FAC";
            }

            for (int i = 0; i < docs; i++) {
                Parametro p = (Parametro) cmb_doc.getItemAt(i);
                if (p.getValorUnico().equals(sDocPago)) {
                    cmb_doc.setSelectedItem(p);
                    break;
                }
            }
        } else {
            txt_remitente.setText("");
        }
    }//GEN-LAST:event_btn_remitenteActionPerformed

    private void txt_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenActionPerformed

    private void btn_bitacorarealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bitacorarealActionPerformed
        // TODO add your handling code here:
        escalads = new EscalaDataSource();
        escalads.setListaEscalas(envio.getEscalasOrdenadasAsc());
        if (escalads != null) {
            try {
                //JasperReport reporte = JasperCompileManager.compileReport("NetBeansProjects/FlyTrack/src/gui/reportes/ReporteAlmacen.jrxml");
                String master = System.getProperty("user.dir")
                        + "/src/gui/reportes/ReporteBitacoraReal.jasper";

                JasperReport masterReport = null;
                try {
                    masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
                } catch (JRException e) {
                    //JOptionPane.showMessageDialog(null, "Error cargando la Guía de Remisión: " + e.getMessage(), "Mensaje",0);
                    return;
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, escalads);
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                DateFormat df = new SimpleDateFormat("MM_dd_yyyy HH_mm");
                Date fechaactual = new Date();
                fechaactual = Calendar.getInstance().getTime();
                String reportDate = df.format(fechaactual);

                String nombreReporteBitacora = "Bitacora_Real_" + this.envio.getIdEnvio() +"_"+reportDate + ".pdf";
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombreReporteBitacora));
                exporter.exportReport();

                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                //setModal(false);

                jviewer.setTitle(nombreReporteBitacora);
                jviewer.setVisible(true);
                jviewer.setAlwaysOnTop(true);

            } catch (JRException e) {
                e.printStackTrace();
                ErrorDialog.mostrarError("Ocurrió un error ", this);

            }
        } else {
            ErrorDialog.mostrarError("Debe registrar un envio.", this);
        }


    }//GEN-LAST:event_btn_bitacorarealActionPerformed

    private void btn_bitacorateoricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bitacorateoricaActionPerformed
        // TODO add your handling code here:
        escalads = new EscalaDataSource();
        
        //escalads.setListaEscalas(envio.getEscalasOrdenadasAsc());
        
        List<Escala> listaEscalas = new ArrayList<Escala>();
        listaEscalas=envio.getEscalasOrdenadasAsc();
        
        List<Escala> listaEscalasaux = new ArrayList<Escala>();
        
        Escala auxescala;
        
       for (int i=0;i<listaEscalas.size();i++) {
            auxescala=listaEscalas.get(i);
            if (auxescala.isOriginal()) {
            listaEscalasaux.add(auxescala);
            }
        }
        
       escalads.setListaEscalas(listaEscalasaux); 
       
        if (escalads != null) {
            try {
                //JasperReport reporte = JasperCompileManager.compileReport("NetBeansProjects/FlyTrack/src/gui/reportes/ReporteAlmacen.jrxml");
                String master = System.getProperty("user.dir")
                        + "/src/gui/reportes/ReporteBitacoraTeorica.jasper";

                JasperReport masterReport = null;
                try {
                    masterReport = (JasperReport) JRLoader.loadObjectFromFile(master);//.loadObject(master);
                } catch (JRException e) {
                    //JOptionPane.showMessageDialog(null, "Error cargando la Guía de Remisión: " + e.getMessage(), "Mensaje",0);
                    return;
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, null, escalads);
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                DateFormat df = new SimpleDateFormat("MM_dd_yyyy HH_mm");
                Date fechaactual = new Date();
                fechaactual = Calendar.getInstance().getTime();
                String reportDate = df.format(fechaactual);

                String nombreReporteBitacora = "Bitacora_Teorica_" + this.envio.getIdEnvio() +"_"+reportDate + ".pdf";
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(nombreReporteBitacora));
                exporter.exportReport();

                JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                //setModal(false);

                jviewer.setTitle(nombreReporteBitacora);
                jviewer.setVisible(true);
                jviewer.setAlwaysOnTop(true);

            } catch (JRException e) {
                e.printStackTrace();
                ErrorDialog.mostrarError("Ocurrió un error ", this);

            }
        } else {
            ErrorDialog.mostrarError("Debe registrar un envio.", this);
        }
        
        
        
    }//GEN-LAST:event_btn_bitacorateoricaActionPerformed

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
                new EnvioAgregar(null, new javax.swing.JFrame()).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_almacen;
    private javax.swing.JButton btn_anular;
    private javax.swing.JButton btn_bitacorareal;
    private javax.swing.JButton btn_bitacorateorica;
    private javax.swing.JButton btn_destinatario;
    private javax.swing.JButton btn_destino;
    private javax.swing.JButton btn_factura;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_in;
    private javax.swing.JButton btn_out;
    private javax.swing.JButton btn_recalcular;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton btn_remitente;
    private javax.swing.JButton btn_ruta;
    private javax.swing.JButton btn_ruta1;
    private javax.swing.JComboBox cmb_doc;
    private javax.swing.JComboBox cmb_estado;
    private javax.swing.JComboBox cmb_estadoFactura;
    private javax.swing.JComboBox cmb_moneda;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_iva;
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
        if (this.wasNuevo) {
            return this.envio;
        } else {
            return null;
        }
    }
}
