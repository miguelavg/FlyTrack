/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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

    
}
