package org.appsquad.utility;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.MonthReportBean;
import org.appsquad.bean.OverdueWeeklyBean;
import org.appsquad.bean.TaskNameBean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class OverdueWeekPdf {
	private String filePath;
	private Document document= null;
	private PdfWriter writer = null;
	ParagraphBorder border; 
	private String printDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
	private ArrayList<OverdueWeeklyBean> finalList = null;
	
	public void getDetails(String realPath,String name,ArrayList<OverdueWeeklyBean> list)throws Exception
	 {
		 filePath = realPath+"report.pdf";
		 String reportName = name;
		 finalList = list;
		 HeaderTable ht = new HeaderTable("Details");
		 document = new Document(PageSize.A4, 2, 5, 30, 50);
		 writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		 writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		 document.open();
		 writer.setPageEvent(ht);
		 createHeader(reportName);
		 createDetails(reportName,finalList);
		 document.close();
		 DownloadPdf.download(filePath, reportName+"--"+printDate);
	}
	
	
	public void createHeader(String reportName) throws Exception{
		PdfPTable table;
		float tableHeight;
		Image image = Image.getInstance("http://appsquad.cloudapp.net:8080/pdf_lg.png");
		float[] colWidths = {8, 16};
		table = new PdfPTable(colWidths);
		Font font;
		table.setTotalWidth(900);
		
		//Report_name and date table
		PdfPTable rightTable = new PdfPTable(2);
		rightTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		rightTable.setWidthPercentage(60);
		
		font = new Font(Font.getFamily("HELVETICA"), 8, Font.BOLD);
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
        tableHeight = table.getTotalHeight();
		document.add(table);
		
	}
	
	public void createDetails(String name, ArrayList<OverdueWeeklyBean> list) throws Exception {
		 System.out.println("INSIDE METHOD list size::: "+list.size() );
	        PdfPTable table = new PdfPTable(2);
	        table.setSplitLate(false);
	        table.setWidths(new int[]{10, 30});
	        for (OverdueWeeklyBean overdueWeeklyBean : list) {
	        	cell_1: {
					PdfPCell cell;
				
					Paragraph headerParagraph = new Paragraph(overdueWeeklyBean.getWeek());
					headerParagraph.getFont().setSize(10f);
					headerParagraph.setAlignment(Element.ALIGN_CENTER);
					headerParagraph.getFont().setStyle(Font.NORMAL);
					cell = new PdfPCell(headerParagraph);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
			
		            }
	            table.addCell(createtable(overdueWeeklyBean.getWeekList()));
	        }
	        document.add(table);
	 }
	
	
	 public static PdfPTable createtable(ArrayList<TaskNameBean> list) throws Exception{
			String[] headerLabes = {"Task Name","Task Description","Assigned By","Assigned To","Creation Date","Schedule Date","Completion Date"};
			float[]	widths = {15F,25F,15F,15F,15F,15F,15F};
	 		PdfPTable headerTable = new PdfPTable(widths);
	 		headerTable.setWidthPercentage(96);
			
	 		headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

	        for (int index = 0; index < headerLabes.length; index++) {         	
	 			PdfPCell cell;
	 			Paragraph headerParagraph = new Paragraph(headerLabes[index]);
	 			headerParagraph.getFont().setSize(5f);
	 			headerParagraph.getFont().setStyle(Font.BOLD);
	 			cell = new PdfPCell(headerParagraph);
	 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 			headerTable.addCell(cell);
	 		}
	 		
	        for(TaskNameBean bean : list){
	        	 cell_1: {
	 					PdfPCell cell;
	 				
	 					Paragraph headerParagraph = new Paragraph(bean.getTaskDescription().toUpperCase());
	 					headerParagraph.getFont().setSize(5f);
	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 					headerParagraph.getFont().setStyle(Font.NORMAL);
	 					cell = new PdfPCell(headerParagraph);
	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 					headerTable.addCell(cell);
	 			
	 		            }
	        
	             cell_2: {
		 					PdfPCell cell;
		 				
		 					Paragraph headerParagraph = new Paragraph(bean.getTaskName().toUpperCase());
		 					headerParagraph.getFont().setSize(5f);
		 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
		 					headerParagraph.getFont().setStyle(Font.NORMAL);
		 					cell = new PdfPCell(headerParagraph);
		 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		 					headerTable.addCell(cell);
		 			
		 		            }
	        
	            cell_3: {
	 		            PdfPCell cell;

	 		            Paragraph headerParagraph = new Paragraph(bean.getAssignedByUserId());
		 				headerParagraph.getFont().setSize(5f);
		 				headerParagraph.setAlignment(Element.ALIGN_CENTER);
		 				headerParagraph.getFont().setStyle(Font.NORMAL);
		 				cell = new PdfPCell(headerParagraph);
		 				cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		 				headerTable.addCell(cell);
		 			
		 		    } 
	 		 
	 		 
	 		    cell_4: {
			 		 PdfPCell cell;
                     	Paragraph headerParagraph = new Paragraph(String.valueOf(bean.getUserprofileBean().getUserid()));
 			 			headerParagraph.getFont().setSize(5f);
 			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
 			 			headerParagraph.getFont().setStyle(Font.NORMAL);
 			 			cell = new PdfPCell(headerParagraph);
 			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 			 			headerTable.addCell(cell);
                    
			 	  }
		 		    
		 	    cell_5: {
				 		 PdfPCell cell;
	                     	Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getCreatedDateStr()));
	 			 			headerParagraph.getFont().setSize(5f);
	 			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 			 			headerParagraph.getFont().setStyle(Font.NORMAL);
	 			 			cell = new PdfPCell(headerParagraph);
	 			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 			 			headerTable.addCell(cell);
	                    
				 	  }    
		 		    
		 		cell_6: {
				 		 PdfPCell cell;
	                     	Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getScheduledDateStr()));
	 			 			headerParagraph.getFont().setSize(5f);
	 			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 			 			headerParagraph.getFont().setStyle(Font.NORMAL);
	 			 			cell = new PdfPCell(headerParagraph);
	 			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 			 			headerTable.addCell(cell);
	                    
				 	  }
				 	  
			    cell_7: {
					 		 PdfPCell cell;
					 		  if(bean.getActualCompletionDateStr()!=null){
					 			    Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getActualCompletionDateStr()));
			 			 			headerParagraph.getFont().setSize(5f);
			 			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			 			cell = new PdfPCell(headerParagraph);
			 			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			 			headerTable.addCell(cell);
			                      
					 		  }else{
					 			    String actualCompl = "";
					 			    Paragraph headerParagraph = new Paragraph(actualCompl);
			 			 			headerParagraph.getFont().setSize(5f);
			 			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			 			cell = new PdfPCell(headerParagraph);
			 			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			 			headerTable.addCell(cell);
			                      
					 		  }
					 	  } 	  
		 		    
	          }
			return headerTable;
		}
	
	/****************************************************************************************************************************************************/
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public PdfWriter getWriter() {
		return writer;
	}
	public void setWriter(PdfWriter writer) {
		this.writer = writer;
	}
	public ParagraphBorder getBorder() {
		return border;
	}
	public void setBorder(ParagraphBorder border) {
		this.border = border;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public ArrayList<OverdueWeeklyBean> getFinalList() {
		return finalList;
	}
	public void setFinalList(ArrayList<OverdueWeeklyBean> finalList) {
		this.finalList = finalList;
	}
}
