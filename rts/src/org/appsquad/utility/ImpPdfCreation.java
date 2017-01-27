package org.appsquad.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ImpPdfCreation {
	/* public static final String DEST = "results/tables/nested_tables.pdf";
	
	 public void createPdf(String dest) throws IOException, DocumentException {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        
	        PdfPTable table = new PdfPTable(2);
	        table.setSplitLate(false);
	        table.setWidths(new int[]{10, 10});
	        
	        
	        
	        
	        PdfPTable innertable = new PdfPTable(2);
	        innertable.setWidths(new int[]{1, 15});
	        for (int i = 0; i < 1; i++) {
	            innertable.addCell(String.valueOf(i + 1));
	            innertable.addCell("First Inner Table");
	        }
	        
	        PdfPTable table = new PdfPTable(2);
	        table.setSplitLate(false);
	        table.setWidths(new int[]{10, 10});
	        for (int i = 0; i < 5; i++) {
	            table.addCell(innertable);
	            table.addCell(innertable);
	            table.getDefaultCell().setBorder(0);
	        }
	        System.out.println("PDF CREATED");
	        document.add(table);
	        document.close();
	    }
	 
	 	public static void main(String[] args) throws IOException, DocumentException {
	        File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new ImpPdfCreation().createPdf(DEST);
	        
	    }*/
}
