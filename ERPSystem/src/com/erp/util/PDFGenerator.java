package com.erp.util;

import java.io.FileOutputStream;
import java.util.List;

import com.erp.model.Inventory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    public static void generateReport(List<Inventory> list) {

        try {

            String path = "C:/Users/rishi/Desktop/inventory_report.pdf";

            System.out.println("Creating PDF...");

            Document document = new Document();

            PdfWriter.getInstance(document,
                    new FileOutputStream(path));

            document.open();

            document.add(new Paragraph("ERP Inventory Report"));

            document.close();

            System.out.println("PDF Generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}