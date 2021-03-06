package org.appsquad.utility;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.TaskNameBean;

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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TaskDescriptionReportPdf {
    private String filePath;
	private Document document= null;
	private PdfWriter writer = null;
	ParagraphBorder border; 
	private String printDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
	
	private ArrayList<TaskNameBean> taskDeatilsList = new ArrayList<TaskNameBean>();
	
	public void getDetails(String realPath, TaskNameBean taskNameBean, ArrayList<TaskNameBean> reportList,String name) 
			throws Exception{
		filePath = realPath+"report.pdf";
		String reportName = name;
		HeaderTable ht = new HeaderTable("Details");
		taskDeatilsList = reportList;
		document = new Document(PageSize.A4, 2, 5, 30, 50);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		document.open();
		writer.setPageEvent(ht);
		createPdfHeader(reportName);
		printDetails(taskDeatilsList);
		document.close();
		DownloadPdf.download(filePath, reportName+"--"+printDate);
     }
	
	public void getDetailsForWeekReport(String realPath, TaskNameBean taskNameBean, ArrayList<TaskNameBean> reportList,String name) 
			throws Exception{
		filePath = realPath+"report.pdf";
		String reportName = name;
		HeaderTable ht = new HeaderTable("Details");
		taskDeatilsList = reportList;
		document = new Document(PageSize.A4, 2, 5, 30, 50);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		document.open();
		writer.setPageEvent(ht);
		createPdfHeader(reportName);
		printDetailsForWeekReport(taskDeatilsList);
		document.close();
		DownloadPdf.download(filePath, reportName+"--"+printDate);
     }
	
	public void getDetailsForMonthReport(String realPath, TaskNameBean taskNameBean, ArrayList<TaskNameBean> reportList,String name) 
			throws Exception{
		filePath = realPath+"report.pdf";
		String reportName = name;
		HeaderTable ht = new HeaderTable("Details");
		taskDeatilsList = reportList;
		document = new Document(PageSize.A4, 2, 5, 30, 50);
		writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setBoxSize("art", new Rectangle(36, 54, 900, 850));
		document.open();
		writer.setPageEvent(ht);
		createPdfHeader(reportName);
		printDetailsForMonthReport(taskDeatilsList);
		document.close();
		DownloadPdf.download(filePath, reportName+"--"+printDate);
     }
	
     public void createPdfHeader(String reportName) throws Exception{
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
		
		font = new Font(Font.getFamily("HELVETICA"), 6, Font.BOLD);
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
	
    public void printDetails(ArrayList<TaskNameBean> list) throws DocumentException{
 		String[] headerLabes = {"Task Name","Task Description", "Assigned By","Person Responsible", "Creation Date","Schedule Date","Completion Date", "Status"};
 		float[]	widths = {10f,20f,10f,10f,10f,10f,10f,10f};
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
 		}
         
         System.out.println("in pdf page ::::"+list.size());
         
         for(TaskNameBean bean : list){
            cell_1: {
 			PdfPCell cell;
 				if(bean.getTaskDescription().length()>0){
 					Paragraph headerParagraph = new Paragraph(bean.getTaskDescription());
 					headerParagraph.getFont().setSize(5f);
 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 					headerParagraph.getFont().setStyle(Font.NORMAL);

 					cell = new PdfPCell(headerParagraph);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

 					headerTable.addCell(cell);
 				}
 		    }
         
         cell_2: {
 	 			PdfPCell cell;
 	 				if(bean.getTaskName().length()>0){
 	 					Paragraph headerParagraph = new Paragraph(bean.getTaskName());
 	 					headerParagraph.getFont().setSize(5f);
 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 					headerParagraph.getFont().setStyle(Font.NORMAL);

 	 					cell = new PdfPCell(headerParagraph);
 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

 	 					headerTable.addCell(cell);
 	 				}
 	 		    }
         
         	cell_3: {
 			PdfPCell cell;
 				if(bean.getAssignedByUserId().length()>0){
 					Paragraph headerParagraph = new Paragraph(bean.getAssignedByUserId());
 					headerParagraph.getFont().setSize(5f);
 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 					headerParagraph.getFont().setStyle(Font.NORMAL);
 					cell = new PdfPCell(headerParagraph);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 					headerTable.addCell(cell);
 				}
 			}
 		    
 		   cell_4: {
 	 			PdfPCell cell;
 	 				if(bean.getUserprofileBean().getUserid()!=null){
 	 					Paragraph headerParagraph = new Paragraph(bean.getUserprofileBean().getUserid());
 	 					headerParagraph.getFont().setSize(5f);
 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 					cell = new PdfPCell(headerParagraph);
 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 					headerTable.addCell(cell);
 	 				}
 	 			}
 			
 			cell_5: {
 	 	 			PdfPCell cell;
 	 	 				if(bean.getCreatedDateStr()!=null){
 	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getCreatedDateStr()) );
 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 					headerTable.addCell(cell);
 	 	 				}
 	 	 			}

 			
 	 	 			cell_6: {
 	 	 	 	 			PdfPCell cell;
 	 	 	 	 				if(bean.getScheduledDateStr()!=null){
 	 	 	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getScheduledDateStr()));
 	 	 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 				}
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
 	 	 	 	 	 					String compltDate = "";
 	 	 	 	 	 				    Paragraph headerParagraph = new Paragraph(compltDate);
	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 	 				}
 	 	 	 	 	 		}
 	 	 			
 	 	 	 		cell_8: {
 	 	 	 	 	 			PdfPCell cell;
 	 	 	 	 	 				if(bean.getStatus()!=null){
 	 	 	 	 	 					Paragraph headerParagraph = new Paragraph(bean.getStatus());
 	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 	 				}
 	 	 	 	 	 		} 			
 	 	 	 			
 	 	 	 	 	 	 		
         }
         document.add(headerTable);
 		
 	}
    
    public void printDetailsForWeekReport(ArrayList<TaskNameBean> list) throws DocumentException{
 		String[] headerLabes = {"Task Name", "assigned By", "Assigned To", "Creation Date", "Scheduled Date", "Status", "Week"};
 		float[]	widths = {20f,10f,10f,10f,10f,10f,8f};
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
 		}
         
         for(TaskNameBean bean : list){
            cell_1: {
 			PdfPCell cell;
 				if(bean.getTaskName().length()>0){
 					Paragraph headerParagraph = new Paragraph(bean.getTaskName());
 					headerParagraph.getFont().setSize(5f);
 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 					headerParagraph.getFont().setStyle(Font.NORMAL);

 					cell = new PdfPCell(headerParagraph);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

 					headerTable.addCell(cell);
 				}
 		    }
         
         	cell_2: {
 			PdfPCell cell;
 				if(bean.getAssignedByUserId().length()>0){
 					Paragraph headerParagraph = new Paragraph(bean.getAssignedByUserId());
 					headerParagraph.getFont().setSize(5f);
 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 					headerParagraph.getFont().setStyle(Font.NORMAL);
 					cell = new PdfPCell(headerParagraph);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 					headerTable.addCell(cell);
 				}
 			}
 		    
 		   cell_3: {
 	 			PdfPCell cell;
 	 				if(bean.getUserprofileBean().getUserid()!=null){
 	 					Paragraph headerParagraph = new Paragraph(bean.getUserprofileBean().getUserid());
 	 					headerParagraph.getFont().setSize(5f);
 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 					cell = new PdfPCell(headerParagraph);
 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 					headerTable.addCell(cell);
 	 				}
 	 			}
 			
 			cell_4: {
 	 	 			PdfPCell cell;
 	 	 				if(bean.getCreatedDateStr()!=null){
 	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getCreatedDateStr()));
 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 					headerTable.addCell(cell);
 	 	 				}
 	 	 			}
 			
 	 	 			cell_5: {
 	 	 	 	 			PdfPCell cell;
 	 	 	 	 				if(bean.getScheduledDateStr()!=null){
 	 	 	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getScheduledDateStr()));
 	 	 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 				}
 	 	 	 	 			}
 	 	 			
 			
 	 	 	 		cell_6: {
 	 	 	 	 	 			PdfPCell cell;
 	 	 	 	 	 				if(bean.getStatus()!=null){
 	 	 	 	 	 					Paragraph headerParagraph = new Paragraph(bean.getStatus());
 	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 	 				}
 	 	 	 	 	 		} 			
 	 	 	 	 	 		
 	 	 	 	 	cell_7: {
 	 	 	 	 	 			PdfPCell cell;
 	 	 	 	 	 			System.out.println("IN PDF FILE-> WEEK IS: "+bean.getWeek());
 	 	 	 	 	 				if(bean.getWeek()!=null){
 	 	 	 	 	 					Paragraph headerParagraph = new Paragraph(String.valueOf(bean.getWeek()));
 	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
 	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
 	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
 	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 	 	 	 	 	 					headerTable.addCell(cell);
 	 	 	 	 	 				}
 	 	 	 	 	 	} 		
         }
         document.add(headerTable);
 		
 	}
     
    public void printDetailsForMonthReport(ArrayList<TaskNameBean> list) throws DocumentException{
    	String[] headerLabes = {"Task Name", "assigned By", "Assigned To", "Creation Date", "Scheduled Date", "Status", "Week"};
 		float[]	widths = {20f,10f,10f,10f,10f,10f,8f};
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
 		}
         
         for(TaskNameBean bean : list){
             cell_1: {
  			PdfPCell cell;
  				if(bean.getTaskName().length()>0){
  					Paragraph headerParagraph = new Paragraph(bean.getTaskName());
  					headerParagraph.getFont().setSize(5f);
  					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  					headerParagraph.getFont().setStyle(Font.NORMAL);

  					cell = new PdfPCell(headerParagraph);
  					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

  					headerTable.addCell(cell);
  				}
  		    }
          
          	cell_2: {
  			PdfPCell cell;
  				if(bean.getAssignedByUserId().length()>0){
  					Paragraph headerParagraph = new Paragraph(bean.getAssignedByUserId());
  					headerParagraph.getFont().setSize(5f);
  					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  					headerParagraph.getFont().setStyle(Font.NORMAL);
  					cell = new PdfPCell(headerParagraph);
  					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  					headerTable.addCell(cell);
  				}
  			}
  		    
  		   cell_3: {
  	 			PdfPCell cell;
  	 				if(bean.getUserprofileBean().getUserid()!=null){
  	 					Paragraph headerParagraph = new Paragraph(bean.getUserprofileBean().getUserid());
  	 					headerParagraph.getFont().setSize(5f);
  	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  	 					headerParagraph.getFont().setStyle(Font.NORMAL);
  	 					cell = new PdfPCell(headerParagraph);
  	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 					headerTable.addCell(cell);
  	 				}
  	 			}
  			
  			cell_4: {
  	 	 			PdfPCell cell;
  	 	 				if(bean.getCreatedDateStr()!=null){
  	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getCreatedDateStr()));
  	 	 					headerParagraph.getFont().setSize(5f);
  	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
  	 	 					cell = new PdfPCell(headerParagraph);
  	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	 					headerTable.addCell(cell);
  	 	 				}
  	 	 			}
  			
  	 	 			cell_5: {
  	 	 	 	 			PdfPCell cell;
  	 	 	 	 				if(bean.getScheduledDateStr()!=null){
  	 	 	 	 					Paragraph headerParagraph = new Paragraph(Dateformatter.toStringDate(bean.getScheduledDateStr()));
  	 	 	 	 					headerParagraph.getFont().setSize(5f);
  	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
  	 	 	 	 					cell = new PdfPCell(headerParagraph);
  	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	 	 	 					headerTable.addCell(cell);
  	 	 	 	 				}
  	 	 	 	 			}
  	 	 			
  			
  	 	 	 		cell_6: {
  	 	 	 	 	 			PdfPCell cell;
  	 	 	 	 	 				if(bean.getStatus()!=null){
  	 	 	 	 	 					Paragraph headerParagraph = new Paragraph(bean.getStatus());
  	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
  	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
  	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
  	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	 	 	 	 					headerTable.addCell(cell);
  	 	 	 	 	 				}
  	 	 	 	 	 		} 			
  	 	 	 	 	 		
  	 	 	 	 	cell_7: {
  	 	 	 	 	 			PdfPCell cell;
  	 	 	 	 	 			System.out.println("IN PDF FILE-> MONTH IS: "+bean.getMonth());
  	 	 	 	 	 				if(bean.getMonth()!=null){
  	 	 	 	 	 					Paragraph headerParagraph = new Paragraph(String.valueOf(bean.getMonth()));
  	 	 	 	 	 					headerParagraph.getFont().setSize(5f);
  	 	 	 	 	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
  	 	 	 	 	 					headerParagraph.getFont().setStyle(Font.NORMAL);
  	 	 	 	 	 					cell = new PdfPCell(headerParagraph);
  	 	 	 	 	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  	 	 	 	 	 					headerTable.addCell(cell);
  	 	 	 	 	 				}
  	 	 	 	 	 	} 		
          }
         document.add(headerTable);
 		
 	}
    
	/************************************************************* GETTER AND SETTER METHOD **************************************************************/ 
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
	public ArrayList<TaskNameBean> getTaskDeatilsList() {
		return taskDeatilsList;
	}
	public void setTaskDeatilsList(ArrayList<TaskNameBean> taskDeatilsList) {
		this.taskDeatilsList = taskDeatilsList;
	}
}
