package org.appsquad.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.appsquad.bean.IndividualClientReportBean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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
	
	private String printDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
	
	ArrayList<IndividualClientReportBean> individualClientReportList = new ArrayList<IndividualClientReportBean>();
	
	public void getDetails(String realPath, IndividualClientReportBean individualClientReportBean, ArrayList<IndividualClientReportBean> individualClientReportBeanList,String rn) 
						throws Exception{
		filePath = realPath+"report.pdf";
		
		String reportName = rn;
		
		HeaderTable ht = new HeaderTable("Details");
		
		individualClientReportList = individualClientReportBeanList;
		
		document = new Document(PageSize.A4, 2, 2, 5, 10);
		
		//document.setMargins(1, 1, 2, 2);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		
		document.open();
		
		writer.setPageEvent(ht);
		createPdfHeader(reportName);
		printDetails(individualClientReportBeanList);
		document.close();
		DownloadPdf.download(filePath, reportName+"--"+printDate);
		
		
	}
	
	void openPdf(String fileName) throws IOException{
		System.out.println("Open pdf called..");
		if (Desktop.isDesktopSupported()) {
		   try {
		          File myFile = new File(fileName );
		          Desktop.getDesktop().open(myFile);
		      } catch (IOException ex) {
		         ex.printStackTrace();
		      }
		  }
		 }
	
	void createPdfHeader(String reportName) throws Exception{
		
		
		PdfPTable table;
		float tableHeight;

		Image image = Image.getInstance("http://appsquad.cloudapp.net:8080/pdf_lg.png");
		
		//Image image = Image.getInstance("C:\\Users\\swarup\\Downloads\\pdf_lg.png");
		
		float[] colWidths = {8, 16};
		table = new PdfPTable(colWidths);
		
		Font font;
		table.setTotalWidth(900);
		
		
		//Report_name and date table
		PdfPTable rightTable = new PdfPTable(2);
		rightTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		rightTable.setWidthPercentage(60);
		
		font = new Font(Font.getFamily("HELVETICA"), 14, Font.BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(reportName,font));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		//cell.setBorderColor(BaseColor.BLUE);
		rightTable.addCell(cell);
		
		font = new Font(Font.getFamily("HELVETICA"), 8, Font.NORMAL);
		cell = new PdfPCell(new Phrase("Date: "+printDate, font));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		//cell.setBorderColor(BaseColor.RED);
		rightTable.addCell(cell);
		
		
		//Logo_table
		PdfPTable logoTable = new PdfPTable(3);
		logoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
		logoTable.getDefaultCell().setBorderColor(BaseColor.WHITE);
		logoTable.addCell(image);
		
		cell = new PdfPCell(new Phrase());
		cell.setBorder(Rectangle.NO_BORDER);
		logoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase());
		cell.setBorder(Rectangle.NO_BORDER);
		logoTable.addCell(cell);
		
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.addCell(logoTable);
        table.addCell(rightTable);
        table.setWidthPercentage(100);
        //table.getDefaultCell().setBorderColor(BaseColor.GREEN);
        tableHeight = table.getTotalHeight();
		
		document.add(table);
		
	}
	
	void printDetails(ArrayList<IndividualClientReportBean> list) throws DocumentException{
		
		String[] headerLabes = {"STATUS", "RESOURCE NAME", "Year Of Exp.", "CONTACT NO.", "EMAIL", "OTHER INFO", " INTERNAL INTERVIEW DATE", " CLIENT INTERVIEW DATE"};
		
			
		float[]	widths = {10f,12f, 4f, 8f, 12f, 12f, 8f, 8f};
		PdfPTable headerTable = new PdfPTable(widths);
		headerTable.setWidthPercentage(96);
		
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
			//document.add(headerTable); // for this double heading occurring

		}
        
        for(IndividualClientReportBean bean : list){
        	
        	//System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
        	cell_1: {

			PdfPCell cell;
			
			if(bean.getEmailId().length()>1){
				Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				headerTable.addCell(cell);
			}else{
				Font font =new Font(Font.getFamily("HELVETICA"), 14, Font.BOLD, BaseColor.GRAY);
				
				Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
				headerParagraph.getFont().setSize(5f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.BOLD);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				headerTable.addCell(cell);
			}
			

		    }
        
        	cell_2: {

			PdfPCell cell;
			if(bean.getEmailId().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

			Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
				
			}

			}
		    
		    cell_3: {

			PdfPCell cell;
			double j = 0.0;
			double i = bean.getYoExp();
			if(i> 0){
				j = Double.valueOf(i);
			}
			if(i>0){
			Paragraph headerParagraph = new Paragraph(String.valueOf(j));
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);
			}else {
			Paragraph headerParagraph = new Paragraph();
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);
			}

			}
			
			cell_4: {

			PdfPCell cell;
			if(bean.getEmailId().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

			Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
					
			}
			
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
			if(bean.getEmailId().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getCompanyName());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

			Paragraph headerParagraph = new Paragraph(bean.getCompanyName());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
				
				
			}
			}
			cell_7: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getIntIntvValue());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);

			}
			cell_8: {

			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(bean.getClntIntvValue());
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);

			}
        }
        document.add(headerTable);
		
	}
	
	public void getSummary(String localFilePath, IndividualClientReportBean individualClientReportBean, ArrayList<IndividualClientReportBean> individualClientReportBeanList, String rn) throws Exception{
		
		//filePath = localFilePath;
		filePath = localFilePath+"report.pdf";
		individualClientReportList = individualClientReportBeanList;
		String reportName =rn;
		HeaderTable ht = new HeaderTable("Details");
		
		//Image image1 = Image.getInstance("http://appsquad.cloudapp.net:8080/pdf_lg.png");
        //document.add(image1);
        
		document = new Document(PageSize.A4, 2, 2, 5, 10);
		//document.setMargins(1, 1, 2, 2);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		
		document.open();		
		writer.setPageEvent(ht);
		createPdfHeader(reportName);
		
		printSummary(individualClientReportBeanList);
		document.close();
		//openPdf(filePath);
		DownloadPdf.download(filePath,reportName+"--"+printDate);
		
	}
	
	void printSummary(ArrayList<IndividualClientReportBean> list) throws DocumentException{
		
		String[] headerLabes = {"STATUS", "", "", "", "No.of Resources"};
		
			
		float[]	widths = {6f,5f, 7f, 5f, 5f};
		PdfPTable headerTable = new PdfPTable(widths);
		headerTable.setWidthPercentage(96);
		
		headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		headerTable.setSpacingBefore(10f);
        headerTable.setSpacingAfter(5f);
        
		
        for (int index = 0; index < headerLabes.length; index++) {
        	
			PdfPCell cell;

			Paragraph headerParagraph = new Paragraph(headerLabes[index]);
			headerParagraph.getFont().setSize(8f);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);

			headerTable.addCell(cell);
			//document.add(headerTable); // for this double heading occurring

		}
        
        for(IndividualClientReportBean bean : list){
        	
        	//System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
        	cell_1: {

			PdfPCell cell;

			if(bean.getrIdDateLabel().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
			headerParagraph.getFont().setSize(6f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

				Paragraph headerParagraph = new Paragraph(bean.getrIdLabel());
				headerParagraph.getFont().setSize(6f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				headerTable.addCell(cell);
				
			}
			
		    }
        
        	cell_2: {

			PdfPCell cell;
			if(bean.getrIdDateLabel().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
			headerParagraph.getFont().setSize(6f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);
			}else {

				Paragraph headerParagraph = new Paragraph(bean.getrIdDateLabel());
				headerParagraph.getFont().setSize(6f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				headerTable.addCell(cell);
				
			}
			}
		    
		    cell_3: {

			PdfPCell cell;
			if(bean.getrIdDateLabel().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getClientFullName());
			headerParagraph.getFont().setSize(6f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

				Paragraph headerParagraph = new Paragraph(bean.getClientFullName());
				headerParagraph.getFont().setSize(6f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				headerTable.addCell(cell);
				
			}
			}
			
			cell_4: {

			PdfPCell cell;
			if(bean.getrIdDateLabel().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
			headerParagraph.getFont().setSize(6f);
			headerParagraph.setAlignment(Element.ALIGN_LEFT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			headerTable.addCell(cell);
			}else {

				Paragraph headerParagraph = new Paragraph(bean.getSkillSetLabel());
				headerParagraph.getFont().setSize(6f);
				headerParagraph.setAlignment(Element.ALIGN_LEFT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);

				headerTable.addCell(cell);
				
			}
			}
			
			cell_5: {

			PdfPCell cell;
			if(bean.getrIdDateLabel().length()>1){
			Paragraph headerParagraph = new Paragraph(bean.getNoOfReqLebel());
			headerParagraph.getFont().setSize(6f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.BOLD);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);
			}else {

				Paragraph headerParagraph = new Paragraph(bean.getNoOfReqLebel());
				headerParagraph.getFont().setSize(6f);
				headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				headerParagraph.getFont().setStyle(Font.NORMAL);

				cell = new PdfPCell(headerParagraph);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

				headerTable.addCell(cell);
				
			}
			}
			
        }
        document.add(headerTable);
		
	}
	
	
	
	
}
