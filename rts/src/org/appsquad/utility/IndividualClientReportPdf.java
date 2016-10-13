package org.appsquad.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	public void getDetails(String webAppPath, IndividualClientReportBean individualClientReportBean, ArrayList<IndividualClientReportBean> individualClientReportBeanList) 
						throws DocumentException, IOException{
		filePath = webAppPath+"reportIndv.pdf";
		individualClientReportList = individualClientReportBeanList;
		document = new Document(PageSize.A4, 2, 2, 20, 20);
		document.setMargins(-40, -60, 60, 0);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
		document.open();
		createPdfHeader();
		printDetails(individualClientReportBeanList);
		//DownloadPdf.download(filePath, "reportIndv.pdf");
		
		System.out.println("FILE PATH IS :"+filePath);
		document.close();
		openPdf(filePath);
		//DownloadPdf.download(filePath, "reportIndv.pdf");
		
	}
	
	void openPdf(String fileName) throws IOException{
		  if (Desktop.isDesktopSupported()) {
		   try {
		          File myFile = new File(fileName );
		          Desktop.getDesktop().open(myFile);
		      } catch (IOException ex) {
		         ex.printStackTrace();
		      }
		  }
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
		
			
		float[]	widths = {5f,6f, 2f, 4f, 6f, 6f, 4f, 4f};
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
			//document.add(headerTable); // for this double heading occurring

		}
        
        for(IndividualClientReportBean bean : list){
        	
        	//System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
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
			double j = 0.0;
			double i = bean.getYoExp();
			if(i> 0){
				j = Double.valueOf(i);
			}
			
			Paragraph headerParagraph = new Paragraph(String.valueOf(j));
			headerParagraph.getFont().setSize(5f);
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

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
	
	public void getSummary(String localFilePath, IndividualClientReportBean individualClientReportBean, ArrayList<IndividualClientReportBean> individualClientReportBeanList) throws DocumentException, IOException{
		
		filePath = localFilePath;
		
		individualClientReportList = individualClientReportBeanList;
		document = new Document(PageSize.A4, 2, 2, 20, 20);
		document.setMargins(-40, -60, 60, 0);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
		
		document.open();		
		
		createPdfHeader();
		
		printSummary(individualClientReportBeanList);
		DownloadPdf.download(filePath,"report.pdf");
		document.close();
		
	}
	
	void printSummary(ArrayList<IndividualClientReportBean> list) throws DocumentException{
		
		String[] headerLabes = {"STATUS", "", "", "", "No.of Resources"};
		
			
		float[]	widths = {4f,3f, 5f, 3f, 3f};
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
			//document.add(headerTable); // for this double heading occurring

		}
        
        for(IndividualClientReportBean bean : list){
        	
        	//System.out.println(bean.getrIdLabel() + " - " + bean.getrIdDateLabel() + " - " + bean.getYoExp() + " - " + bean.getSkillSetLabel() + " - " + bean.getEmailId() + " - " + bean.getCompanyName() + " - " + bean.getIntIntvValue() + " - " + bean.getClntIntvValue());
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
			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			headerParagraph.getFont().setStyle(Font.NORMAL);

			cell = new PdfPCell(headerParagraph);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

			headerTable.addCell(cell);

			}
		    
		    cell_3: {

			PdfPCell cell;
			
			Paragraph headerParagraph = new Paragraph(bean.getClientFullName());
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

			Paragraph headerParagraph = new Paragraph(bean.getNoOfReqLebel());
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
	
	
	
	
}
