package Util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.prism.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CodigoDeBarras {

    public static void getCode(String texto, int num) {
        try {
            float x = 35;
            float y = 755;
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("Prueba.pdf"));
            doc.open();
            Barcode128 code = new Barcode128();
            code.setCode(texto);
            com.itextpdf.text.Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            for (int i = 0; i < num; i++) {
                img.setAbsolutePosition(x, y);
                x += img.getWidth() + 30;
                doc.add(img);
                if (((i + 1) % 5 == 0) && i != 0) {
                    y -= 70;
                    x = 35;
                    if ((i + 1) % 55 == 0) {
                        doc.newPage();
                        y = 755;
                    }
                }
            }

            System.out.println("Impresion finalizada");
            doc.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CodigoDeBarras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
