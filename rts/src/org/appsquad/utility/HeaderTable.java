package org.appsquad.utility;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class HeaderTable extends PdfPageEventHelper {

	protected PdfPTable table;
	protected float tableHeight;
	//public String logoPath = "localhost:8080/pdf_lg.png";
	
	//public String logoPath = "C:\\Users\\swarup\\Downloads\\pdf_lg.png";
	
	
	
	public HeaderTable(String reportName) throws Exception{
		
	}
	
	int c = 1;
	public void onEndPage(PdfWriter writer, Document document){
		
		System.out.println(document.top());
		System.out.println(document.topMargin());
		PdfContentByte cb = writer.getDirectContent();
		Font ffont = new Font(Font.FontFamily.HELVETICA,12, Font.NORMAL);
		Phrase footer = new Phrase("Page no."+c,ffont);
		/*table.writeSelectedRows(0, -1,
                document.left(),
                document.top() + ((document.topMargin() + tableHeight) / 2),
                writer.getDirectContent());*/
		
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                footer,
                510,
                document.bottom()+10 , 0);
		//System.out.println("C ------------ " + c);
		c++;
	}
	
	public float getTableHeight() {
	        return tableHeight;
	}
	
	
}
