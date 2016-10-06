package org.appsquad.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;

public class IndividualClientReportPdf {
	
	private String filePath;
	
	private Document document= null;
	private PdfWriter writer = null;
	ParagraphBorder border; 
	
	ArrayList<IndividualClientReportBean> individualClientReportList = new ArrayList<IndividualClientReportBean>();
	
	public void getDetails(String localFilePath, IndividualClientReportBean individualClientReportBean, ArrayList<IndividualClientReportBean> individualClientReportBeanList) throws FileNotFoundException, DocumentException{
		
		filePath = localFilePath;
		System.out.println("File Path >>> >> > " + filePath);
		individualClientReportList = individualClientReportBeanList;
		document = new Document(PageSize.A4, 2, 2, 60, 40);
		document.setMargins(-40, -60, 60, 0);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
		document.open();
		
		createPdfHeader();
		printDetails(individualClientReportBeanList);
		//printPdfDetailsTest(individualClientReportBeanList);
		//createTable();
		document.close();
		
	}
	
	void createTable() throws DocumentException{
		
		String[] headerLabes = {"STATUS", "RESOURCE NAME", "Year Of Exp.", "CONTACT NO.", "EMAIL", "OTHER INFO", " INTERNAL INTERVIEW DATE", " CLIENT INTERVIEW DATE"};
		PdfPTable table = new PdfPTable(headerLabes.length);
		Font font;
		font = new Font(Font.getFamily("HELVETICA"), 10, Font.BOLD);
		//PdfPCell cell;
		for(int i =0 ;i<headerLabes.length ; i++){
			PdfPCell headCell = new PdfPCell(new Phrase(headerLabes[i]));
			table.addCell(headCell);
		}
		for(IndividualClientReportBean bean : individualClientReportList){
			
			System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
        	System.out.println("_______________________________________________________________________________________________________________");
			
        	if(bean.getrIdLabel()!=null){
        		PdfPCell cell = new PdfPCell(new Phrase(bean.getrIdDateLabel()));
				table.addCell(cell);
			}else{
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			
        	if(bean.getrIdDateLabel() != null){
        		PdfPCell cell = new PdfPCell(new Phrase(bean.getrIdDateLabel()));
    			table.addCell(cell);
        	}else {
        		PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			if(bean.getYoExp() != null){
				PdfPCell cell = new PdfPCell(new Phrase(bean.getYoExp()));
				table.addCell(cell);	
			}else {
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			if(bean.getSkillSetLabel() != null){
				PdfPCell cell = new PdfPCell(new Phrase(bean.getSkillSetLabel()));
				table.addCell(cell);
			}else {
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			if(bean.getEmailId() != null){
				PdfPCell cell = new PdfPCell(new Phrase(bean.getEmailId()));
				table.addCell(cell);
			}else {
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			if(bean.getIntIntvValue() != null){
				PdfPCell cell = new PdfPCell(new Phrase(bean.getIntIntvValue()));
				table.addCell(cell);
			}else {
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			if(bean.getClntIntvValue() != null){
				PdfPCell cell = new PdfPCell(new Phrase(bean.getClntIntvValue()));
				table.addCell(cell);
			}else {
				PdfPCell cell = new PdfPCell(new Phrase(""));
				table.addCell(cell);
			}
			
			
		}
		document.add(table);
		
		
	}
	
	
	
	void createPdfHeader() throws DocumentException{
		
		Paragraph paragraph = new Paragraph("Reform Consulting Pvt Ltd.");
		paragraph.getFont().setStyle(Font.BOLD);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.getFont().setSize(9f);
		document.add(paragraph);
		
	}
	
	void printDetails(ArrayList<IndividualClientReportBean> list) throws DocumentException{
		
		String[] headerLabes = {"STATUS", "RESOURCE NAME", "Year Of Exp.", "CONTACT NO.", "EMAIL", "OTHER INFO", " INTERNAL INTERVIEW DATE", " CLIENT INTERVIEW DATE"};
		
		//float[]	widths = {8f, 10f, 3f, 5f, 10f, 10f, 4f, 4f};	
		float[]	widths = {4f,4f, 4f, 4f, 4f, 4f, 4f, 4f};
		PdfPTable headerTable = new PdfPTable(widths);
		
		headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		headerTable.setSpacingBefore(10f);
        headerTable.setSpacingAfter(5f);
		
        for (int index = 0; index < headerLabes.length; index++) {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(headerLabes[index]);
			headerParagraph.getFont().setSize(5f);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			headerTable.addCell(cell);
			document.add(headerTable);

		}
        
        for(IndividualClientReportBean bean : list){
        	
        	System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
        	System.out.println("_______________________________________________________________________________________________________________");
        	cell_1: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

		    }
        
        	cell_2: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
		    
		    cell_3: {

			PdfPCell cell;
			//if(bean.getYoExp() != null)
			Paragraph headerParagraph = new Paragraph(bean.getYoExp());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
			
			cell_4: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
			
			cell_5: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getEmailId());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
			
			cell_6: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getCompanyName());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
			cell_7: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getIntIntvValue());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
			cell_8: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getClntIntvValue());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);

			}
        }
        document.add(headerTable);
		
	}
	
	
	void printPdfDetailsTest(ArrayList<IndividualClientReportBean> list) throws DocumentException{
		
		String[] headersLabels = {"STATUS", "RESOURCE NAME", "Year Of Exp.", "CONTACT NO.", "EMAIL", "OTHER INFO", " INTERNAL INTERVIEW DATE", " CLIENT INTERVIEW DATE"};

		float[] widths = { 12f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};
		

		PdfPTable dataTable = new PdfPTable(widths); 

		dataTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		//dataTable.setSpacingBefore(10f);

		//dataTable.setSpacingAfter(5f);
		
		PdfPCell cellHeader = new PdfPCell(new Phrase("FABRIC"));
		cellHeader.setColspan(10);
		cellHeader.setHorizontalAlignment(1);
		
		dataTable.addCell(cellHeader);
		
		for (int index = 0; index < headersLabels.length; index++) {

			PdfPCell cell;
			
			Paragraph headerParagraph = new Paragraph(headersLabels[index]);
			headerParagraph.getFont().setSize(5f);
			headerParagraph.getFont().setStyle(Font.BOLD);
			System.out.println("HEADERS " + headerParagraph);
			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			dataTable.addCell(cell);

		}

		
		
		for (IndividualClientReportBean bean : list) {

			System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
        	System.out.println("_______________________________________________________________________________________________________________");
			
			
			
			cell_1: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				dataTable.addCell(cell);

			}
		
		
		
			cell_2: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				dataTable.addCell(cell);

			}
			
			
			
			cell_3: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getYoExp());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				dataTable.addCell(cell);

			}
			
			
			cell_4: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				dataTable.addCell(cell);

			}
		
			cell_5: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getEmailId());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				dataTable.addCell(cell);

			}
			
			
			cell_6: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getCompanyName());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				dataTable.addCell(cell);

			}
			
			cell_7: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getIntIntvValue());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				dataTable.addCell(cell);

			}
			
			cell_8: {

				PdfPCell cell;

				Paragraph headerParagraph = new Paragraph(bean.getClntIntvValue());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				dataTable.addCell(cell);

			}
			
			
		}
		
	}
	
}
