/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Envio;
import beans.Sesion;
import controllers.CValidator;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
//import javax.swing.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 *
 * @author joao
 */
public class CReportes {
    
        public static void crearPDF_Trazabilidad(String direccionDelDocumento,String nombreDocumento,
                String autor, String empresa,String tituloEnElDocumento, float[] anchos 
               ,JTable tablaJava) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(direccionDelDocumento));
        document.open();
        document.addTitle(nombreDocumento);
        document.addSubject("Using iText");

        document.addKeywords("Java, PDF, iText, NetBeans");
        document.addAuthor(empresa);

        caratulaPDF_Trazabilidad(document,tituloEnElDocumento,autor,empresa);
        reporteEnPDF_Trazabilidad(document,tablaJava,anchos);
        document.close();

        return;
    }
        
        
        
    private static void caratulaPDF_Trazabilidad(Document document,String tituloDocumento,String autor, String empresa) throws Exception {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Paragraph preface1 = new Paragraph();

            preface1=new Paragraph("Reporte elaborado por "+autor+" el dia "+dateFormat.format(calendar.getTime()),FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface1.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface1, 1);
            
            Paragraph preface2 = null;

            preface2=new Paragraph("Propiedad de "+empresa, FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface2.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface2, 1);

            Paragraph preface3 = null;

            preface3=new Paragraph(tituloDocumento,FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL));
            preface3.setAlignment(Element.ALIGN_CENTER);

            lineaVacia(preface3, 1);

            Paragraph preface4 = new Paragraph();
            preface4=new Paragraph("Historial de clientes  "+" "+".",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
            preface4.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface4, 1);
            lineaVacia(preface4, 1);

            document.add(preface1);
            document.add(preface2);
            document.add(preface3);
            document.add(preface4);
    }
    
    
    
        private static void reporteEnPDF_Trazabilidad(Document document,JTable tablaJava, float[] anchos) throws Exception {
//INICIO TABLA
        PdfPTable table = new PdfPTable(tablaJava.getColumnCount());
        table.setWidthPercentage(95);
        table.setWidths(anchos);

        PdfPCell c1 = null;
        Phrase p1 = null;
        for(int i=0;i<tablaJava.getColumnCount();i++){
            p1 = new Phrase(tablaJava.getColumnName(i));
            c1 = new PdfPCell(p1);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setNoWrap(true);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(c1);
        }

        table.setHeaderRows(1);

        for(int i=0;i<tablaJava.getRowCount();i++){
            table.addCell(tablaJava.getValueAt(i, 0).toString());
            table.addCell(tablaJava.getValueAt(i, 1).toString());
            table.addCell(tablaJava.getValueAt(i, 2).toString());
            table.getRow(i+1).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
            table.getRow(i+1).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
            table.getRow(i+1).getCells()[2].setHorizontalAlignment(Element.ALIGN_LEFT);
        }
//FIN TABLA
        document.add(table);
    }
        
              
    public static void mostrarMensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void mostrarMensajeSatisfaccion(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
        
    public static void mostrarMensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    
    private static void lineaVacia(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                    paragraph.add(new Paragraph(" "));
            }
    }
    
    
public static void crearPDF_Trazabilidad_Factura(String direccionDelDocumento,String nombreDocumento,
String autor, String empresa,String tituloEnElDocumento, float[] anchos 
,Envio envio) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(direccionDelDocumento));
        document.open();
        document.addTitle(nombreDocumento);
        document.addSubject("Using iText");

        document.addKeywords("Java, PDF, iText, NetBeans");
        document.addAuthor(empresa);

        caratulaPDF_Trazabilidad_Factura(document,tituloEnElDocumento,autor,empresa);
        reporteEnPDF_Trazabilidad_Factura(document,envio,anchos);
        document.close();

        return;
    }



        private static void reporteEnPDF_Trazabilidad_Factura(Document document,Envio envio, float[] anchos) throws Exception {
//INICIO TABLA
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(95);
        table.setWidths(anchos);

        PdfPCell c1 = null;
        Phrase p1 = null;
//        for(int i=0;i<tablaJava.getColumnCount();i++){
//            p1 = new Phrase(tablaJava.getColumnName(i));
//            c1 = new PdfPCell(p1);
//            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            c1.setNoWrap(true);
//            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table.addCell(c1);
//        }
        
              p1 = new Phrase("Nro de Articulos");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Descripción de la mercancia");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Valor Unitario");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Valor Total");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              

        table.setHeaderRows(1);

        Integer i=envio.getNumPaquetes();
        //i.toString();
        table.addCell(i.toString());
        table.getRow(0).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell("Envio del Aeropuerto de Origen: \n"+envio.getOrigen().getNombre()+"\n \n"+ "Al Aeropuerto de Destino: \n"+envio.getDestino().getNombre()+"\n");
        table.getRow(0).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        
        //Double j=envio.getMonto();
        //j.toString()
        table.addCell(CValidator.formatNumber(envio.getUnitario()));
        table.getRow(0).getCells()[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(CValidator.formatNumber(envio.getMonto()));
        table.getRow(0).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(" ");
        table.getRow(1).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell(" ");
        table.getRow(1).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        
         table.addCell("IGV:");
        table.getRow(1).getCells()[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(CValidator.formatNumber(envio.getImpuesto()*envio.getMonto() ));
        table.getRow(1).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        //Double jk=(double) (20/100);
//        Double k=envio.getMonto()*(Double)0.2;
//        //Double k=envio.get
//        table.addCell(k.toString());
//        table.getRow(1).getCells()[2].setHorizontalAlignment(Element.ALIGN_RIGHT);

        table.addCell(" ");
        table.getRow(2).getCells()[0].setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(" ");
        table.getRow(2).getCells()[1].setHorizontalAlignment(Element.ALIGN_CENTER);
        
        //Double l=j+k;
        //l.toString()
        table.addCell("Total: ");
        table.getRow(2).getCells()[2].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell(CValidator.formatNumber((envio.getMonto())+(envio.getImpuesto()*envio.getMonto())));
        table.getRow(2).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getRow(3).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
//        for(int i=0;i<tablaJava.getRowCount();i++){
//            table.addCell(tablaJava.getValueAt(i, 0).toString());
//            table.addCell(tablaJava.getValueAt(i, 1).toString());
//            table.addCell(tablaJava.getValueAt(i, 2).toString());
//            table.getRow(i+1).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.getRow(i+1).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.getRow(i+1).getCells()[2].setHorizontalAlignment(Element.ALIGN_LEFT);
//        }
//FIN TABLA
        
        
        
        document.add(table);
    }
        
    private static void caratulaPDF_Trazabilidad_Factura(Document document,String tituloDocumento,String autor, String empresa) throws Exception {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Paragraph preface1 = new Paragraph();

            preface1=new Paragraph("Factura elaborada por "+autor+" el dia "+dateFormat.format(calendar.getTime()),FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface1.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface1, 1);
            
            Paragraph preface3 = null;

            preface3=new Paragraph(tituloDocumento + "\n",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL));
            preface3.setAlignment(Element.ALIGN_CENTER);

            lineaVacia(preface3, 1);
            
            
            Paragraph preface2 = null;

            preface2=new Paragraph("Empresa: "+empresa + " ", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface2.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface2, 1);
            
            Paragraph preface4 = null;
            
            preface4=new Paragraph("Dirección: Av. Universitaria 1801 San Miguel", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface4.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface4, 1);

            Paragraph preface5 = null;
            
            preface5=new Paragraph("País     : Peru ", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface5.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface5, 1);
            
            Paragraph preface6 = null;
            preface6=new Paragraph("Telefono : 4546354"+" \n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface6.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface6, 1);
            



//            Paragraph preface4 = new Paragraph();
//            preface4=new Paragraph("Historial de clientes  "+" "+".",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
//            preface4.setAlignment(Element.ALIGN_LEFT);
//
//            lineaVacia(preface4, 1);
//            lineaVacia(preface4, 1);

            document.add(preface1);
            document.add(preface3);
       
            document.add(preface4);
            document.add(preface5);
            document.add(preface6);    
    }
    
    public static void crearPDF_Trazabilidad_NotaSalida(String direccionDelDocumento,Envio envio) throws Exception {
       
    Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(direccionDelDocumento));
        document.open();
        document.addTitle("Nota de salida");
        document.addSubject("Using iText");

        document.addKeywords("Java, PDF, iText, NetBeans");
        document.addAuthor("Flytrack");

        caratulaPDF_Trazabilidad_NotaSalida(document,"Nota de salida",envio);
       //reporteEnPDF_Trazabilidad_Factura(document,envio,anchos);
        document.close();

        return;
    }
    private static void caratulaPDF_Trazabilidad_NotaSalida(Document document,String tituloDocumento,Envio envio) throws Exception {
            
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Image imagen= Image.getInstance("../imagenes/logo48x48.png");
            imagen.setAlignment(Image.ALIGN_CENTER | Image.TEXTWRAP);
            
            
            
            Paragraph preface50 = new Paragraph();
         
            
            preface50=new Paragraph("   Nota de Salida   \n",FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL));
            preface50.setAlignment(Element.ALIGN_MIDDLE);
            
            Paragraph preface1 = new Paragraph();
         
            
            preface1=new Paragraph("   Flytrack   \n",FontFactory.getFont(FontFactory.COURIER, 16, Font.NORMAL));
            preface1.setAlignment(Element.ALIGN_MIDDLE);

            Paragraph preface42 ;
            
            preface42=new Paragraph("N° "+ envio.getIdEnvio()+ "\t  \t  \t"+"Fecha "+dateFormat.format(calendar.getTime()).substring(0, 10)+"\n",FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface42.setAlignment(Element.ALIGN_MIDDLE);
            
            Paragraph preface3 = null;

            preface3=new Paragraph("Empleado  "+ Sesion.getUsuario().getNombres()+" "+Sesion.getUsuario().getApellidos()+ "\t \t Hora  "+dateFormat.format(calendar.getTime()).substring(11, 16)+"\n",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL));
            preface3.setAlignment(Element.ALIGN_MIDDLE);

            lineaVacia(preface3, 3);
            
            Paragraph preface30 = null;

            preface30=new Paragraph("Destinatario  "+ envio.getDestinatario().getNombres()+" "+envio.getDestinatario().getApellidos()+"\t"+"Cantidad  "+envio.getNumPaquetes()+"\n",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL));
            preface30.setAlignment(Element.ALIGN_MIDDLE);

            lineaVacia(preface30, 2);

//            Paragraph preface4 = new Paragraph();
//            preface4=new Paragraph("Historial de clientes  "+" "+".",FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
//            preface4.setAlignment(Element.ALIGN_LEFT);
//
//            lineaVacia(preface4, 1);
//            lineaVacia(preface4, 1);
            
            document.add(imagen);
            document.add(preface50);
            document.add(preface1);
      
            document.add(preface42);
            document.add(preface3);
            document.add(preface30);    
    }
    
    public static void crearPDF_Trazabilidad_Nota_Entrada(String direccionDelDocumento,String nombreDocumento,
String autor, String empresa,String tituloEnElDocumento, float[] anchos 
,Envio envio) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(direccionDelDocumento));
        document.open();
        document.addTitle(nombreDocumento);
        document.addSubject("Using iText");

        document.addKeywords("Java, PDF, iText, NetBeans");
        document.addAuthor(empresa);

        caratulaPDF_Trazabilidad_Nota_Entrada(document,tituloEnElDocumento,autor,empresa,envio);
        //reporteEnPDF_Trazabilidad_Nota_Entrada(document,envio,anchos);
        document.close();

        return;
    }
    
    
    private static void caratulaPDF_Trazabilidad_Nota_Entrada(Document document,String tituloDocumento,String autor, String empresa,Envio envio) throws Exception {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Paragraph preface1 = new Paragraph();

            preface1=new Paragraph("Nota de entrada elaborada por "+autor+" el dia "+dateFormat.format(calendar.getTime()),FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface1.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface1, 1);
            
            Paragraph preface3 = null;

            preface3=new Paragraph(tituloDocumento + "\n",FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.NORMAL));
            preface3.setAlignment(Element.ALIGN_CENTER);

            lineaVacia(preface3, 1);
            
            
            Paragraph preface2 = null;

            preface2=new Paragraph("Empresa: "+empresa + " ", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface2.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface2, 1);
            
            Paragraph preface4 = null;
            
            preface4=new Paragraph("Dirección: Av. Universitaria 1801 San Miguel", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface4.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface4, 1);

            Paragraph preface5 = null;
            
            preface5=new Paragraph("País     : Peru ", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface5.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface5, 1);
            
            Paragraph preface6 = null;
            preface6=new Paragraph("Telefono : 4546354"+" \n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface6.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface6, 1);
            
            Paragraph preface7 = null;
            preface7=new Paragraph("Aeropuerto: "+" "+envio.getOrigen().getNombre()+"\n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface7.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface7, 1);
            
            Paragraph preface8 = null;
            preface8=new Paragraph("Destinatario: "+" "+envio.getDestinatario().getNombres()+"  "+envio.getDestinatario().getApellidos()+" "+"\n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface8.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface8, 1);
            
            Paragraph preface9 = null;
            preface9=new Paragraph("Cantidad de paquetes: "+" "+envio.getNumDocVenta()+" "+"\n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface9.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface9, 1);
            
            Paragraph preface10 = null;
            preface10=new Paragraph("Fecha: "+" "+envio.getFechaRegistro()+" "+"\n", FontFactory.getFont(FontFactory.COURIER, 12, Font.NORMAL));
            preface10.setAlignment(Element.ALIGN_LEFT);

            lineaVacia(preface9, 1);
            
            
            
            document.add(preface1);
            document.add(preface3);
            document.add(preface2);
            document.add(preface4);
            document.add(preface5);
            document.add(preface6);    
            document.add(preface7);  
            document.add(preface8);  
            document.add(preface9);  
            document.add(preface10);  
    }
    
    private static void reporteEnPDF_Trazabilidad_Nota_Entrada(Document document,Envio envio, float[] anchos) throws Exception {
//INICIO TABLA
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(95);
        table.setWidths(anchos);

        PdfPCell c1 = null;
        Phrase p1 = null;

        
              p1 = new Phrase("Nro de Articulos");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Descripción de la mercancia");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Valor Unitario");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              
              p1 = new Phrase("Valor Total");
              c1 = new PdfPCell(p1);
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
              c1.setNoWrap(true);
              c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
              table.addCell(c1);
              

        table.setHeaderRows(1);

        Integer i=envio.getNumPaquetes();
        //i.toString();
        table.addCell(i.toString());
        table.getRow(0).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell("Envio del Aeropuerto de Origen: \n"+envio.getOrigen().getNombre()+"\n \n"+ "Al Aeropuerto de Destino: \n"+envio.getDestino().getNombre()+"\n");
        table.getRow(0).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        
        //Double j=envio.getMonto();
        //j.toString()
        table.addCell(CValidator.formatNumber(envio.getUnitario()));
        table.getRow(0).getCells()[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(CValidator.formatNumber(envio.getMonto()));
        table.getRow(0).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(" ");
        table.getRow(1).getCells()[0].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell(" ");
        table.getRow(1).getCells()[1].setHorizontalAlignment(Element.ALIGN_LEFT);
        
         table.addCell("IGV:");
        table.getRow(1).getCells()[2].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(CValidator.formatNumber(envio.getImpuesto()*envio.getMonto() ));
        table.getRow(1).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        table.addCell(" ");
        table.getRow(2).getCells()[0].setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(" ");
        table.getRow(2).getCells()[1].setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell("Total: ");
        table.getRow(2).getCells()[2].setHorizontalAlignment(Element.ALIGN_LEFT);
        
        table.addCell(CValidator.formatNumber((envio.getMonto())+(envio.getImpuesto()*envio.getMonto())));
        table.getRow(2).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getRow(3).getCells()[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        document.add(table);
    }

    
    
        

    
    
    
}
