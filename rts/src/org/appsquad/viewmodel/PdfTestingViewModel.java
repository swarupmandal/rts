package org.appsquad.viewmodel;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.MonthReportBean;
import org.appsquad.utility.DownloadPdf;
import org.appsquad.utility.HeaderTable;
import org.appsquad.utility.ParagraphBorder;

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

public class PdfTestingViewModel {
	 private String filePath;
	 private Document document= null;
	 private PdfWriter writer = null;
	 ParagraphBorder border; 
	 private String printDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
	 private LinkedHashSet<MonthReportBean> finalList = null;
	 
	 public void getDetails(String realPath,String name,LinkedHashSet<MonthReportBean> list)throws Exception
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
		 createPdfHeader(reportName,finalList);
		 document.close();
		 DownloadPdf.download(filePath, reportName+"--"+printDate);
	}
	 
	 
	 public void getDetailsForResource(String realPath,String name,LinkedHashSet<MonthReportBean> list)throws Exception
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
		 createPdfHeaderForResource(reportName,finalList);
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
	 
	 public void createPdfHeader(String name, LinkedHashSet<MonthReportBean> list) throws Exception {
		 System.out.println("INSIDE METHOD list size::: "+list.size() );
	        PdfPTable table = new PdfPTable(2);
	        table.setSplitLate(false);
	        table.setWidths(new int[]{8, 30});
	        for (MonthReportBean monthReportBean : list) {
	        	
	        	cell_1: {
					PdfPCell cell;
				
						if(monthReportBean.getMonthName().equalsIgnoreCase("")){
							Paragraph headerParagraph = new Paragraph("");
							headerParagraph.getFont().setSize(10f);
							headerParagraph.setAlignment(Element.ALIGN_CENTER);
							headerParagraph.getFont().setStyle(Font.NORMAL);
							cell = new PdfPCell(headerParagraph);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(cell);
					
						}else{
							Paragraph headerParagraph = new Paragraph(monthReportBean.getMonthName().toUpperCase());
							headerParagraph.getFont().setSize(10f);
							headerParagraph.setAlignment(Element.ALIGN_CENTER);
							headerParagraph.getFont().setStyle(Font.NORMAL);
							cell = new PdfPCell(headerParagraph);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							table.addCell(cell);
				
						}
		            }
	            //table.addCell(monthReportBean.getMonthName().toUpperCase());
	        
	        	if(monthReportBean.getMonthName().toUpperCase().equalsIgnoreCase("")){
	        		 table.addCell(demoCreatetable(monthReportBean.getCurrentOpportunitiesReportGenerationBeanList()));
	        	}else{
	        		 table.addCell(createtable(monthReportBean.getCurrentOpportunitiesReportGenerationBeanList()));
	        	}
	        
	        }
	        document.add(table);
	 }
	 
	 public static PdfPTable createtable(ArrayList<CurrentOpportunitiesReportGenerationBean> monthWiseDataList) throws Exception{
			String[] headerLabes = {"Client Name","Resource Name","Charge Out Rate(Monthly)","Margin(%)","Margin"};
			float[]	widths = {30F,20F,20F,13F,20F};
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
	 		
	        for(CurrentOpportunitiesReportGenerationBean bean : monthWiseDataList){
	        	 cell_1: {
	 					PdfPCell cell;
	 				
	 					Paragraph headerParagraph = new Paragraph(bean.getClientInformationBean().getFullName());
	 					headerParagraph.getFont().setSize(5f);
	 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 					headerParagraph.getFont().setStyle(Font.NORMAL);
	 					cell = new PdfPCell(headerParagraph);
	 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 					headerTable.addCell(cell);
	 			
	 		            } 
	        
	        cell_2: {
	 		            PdfPCell cell;

	 		            Paragraph headerParagraph = new Paragraph(bean.getResourceMasterBean().getFullName());
		 				headerParagraph.getFont().setSize(5f);
		 				headerParagraph.setAlignment(Element.ALIGN_CENTER);
		 				headerParagraph.getFont().setStyle(Font.NORMAL);
		 				cell = new PdfPCell(headerParagraph);
		 				cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		 				headerTable.addCell(cell);
		 			
		 		    } 
	 		 
	 		
	 		cell_3:{
		 		    	PdfPCell cell;
		 		    	if(bean.getCurrentOpportunitiesReportBean().getChargeOutRate()!=null){
		 		    		Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString());
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
				 			headerParagraph.getFont().setStyle(Font.NORMAL);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 			headerTable.addCell(cell);	
		 		    	}else{
		 		    		String chargerate = "";
		 		    		Paragraph headerParagraph = new Paragraph(chargerate);
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
				 			headerParagraph.getFont().setStyle(Font.NORMAL);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 			headerTable.addCell(cell);	
		 		    	}
		 		   }
		 		    
		 		   cell_4:{
		 		    	PdfPCell cell;
		 		    	if(bean.getCurrentOpportunitiesBean().getPercentage()!=null){
		 		    		Paragraph headerParagraph = new Paragraph(String.valueOf(bean.getCurrentOpportunitiesBean().getPercentage()));
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
				 			headerParagraph.getFont().setStyle(Font.NORMAL);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 			headerTable.addCell(cell);
		 		    	}else{
		 		    		String percentage = "";
		 		    		Paragraph headerParagraph = new Paragraph(percentage);
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
                        if(bean.getCurrentOpportunitiesBean().getMargin()!=null){
                        	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
    			 			headerParagraph.getFont().setSize(5f);
    			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
    			 			headerParagraph.getFont().setStyle(Font.NORMAL);
    			 			cell = new PdfPCell(headerParagraph);
    			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    			 			headerTable.addCell(cell);
                        }else{
                        	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
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
	 
	 public static PdfPTable demoCreatetable(ArrayList<CurrentOpportunitiesReportGenerationBean> monthWiseDataList) throws Exception{
			String[] headerLabes = {"","","","",""};
			float[]	widths = {30F,20F,20F,13F,20F};
	 		PdfPTable headerTable = new PdfPTable(widths);
	 		headerTable.setWidthPercentage(96);
	 		headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	 		
	        for (int index = 0; index < headerLabes.length; index++) {         	
	 			PdfPCell cell;
	 			Paragraph headerParagraph = new Paragraph(headerLabes[index]);
	 			headerParagraph.getFont().setSize(5f);
	 			headerParagraph.getFont().setStyle(Font.BOLD);
	 			cell = new PdfPCell(headerParagraph);
	 			cell.setBorder(PdfPCell.NO_BORDER);
	 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	 			headerTable.addCell(cell);
	 		}
	 		
	        for(CurrentOpportunitiesReportGenerationBean bean : monthWiseDataList){
	        	
	        	cell_1: {
	 			PdfPCell cell;
	 			    String a1 = "";
                	Paragraph headerParagraph = new Paragraph(a1);
		 			headerParagraph.getFont().setSize(5f);
		 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
		 			headerParagraph.getFont().setStyle(Font.BOLD);
		 			cell = new PdfPCell(headerParagraph);
		 			cell.setBorder(PdfPCell.NO_BORDER);
		 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 			headerTable.addCell(cell);
	 		  }
	        	
	        cell_2: {
		 			PdfPCell cell;
		 			String a2 = "";
	                	Paragraph headerParagraph = new Paragraph(a2);
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			 			headerParagraph.getFont().setStyle(Font.BOLD);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setBorder(PdfPCell.NO_BORDER);
			 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 			headerTable.addCell(cell);
		 		  }
	 		  
	        
	        	cell_3: {
			 			PdfPCell cell;
		                if(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString()!=null){
		                	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString());
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				 			headerParagraph.getFont().setStyle(Font.BOLD);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setBorder(PdfPCell.NO_BORDER);
				 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				 			headerTable.addCell(cell);
		                }
			 		  }
	        	 
		 		 cell_4: {
				 				PdfPCell cell;
				 				String a3 = "";
			                	Paragraph headerParagraph = new Paragraph(a3);
					 			headerParagraph.getFont().setSize(5f);
					 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
					 			headerParagraph.getFont().setStyle(Font.BOLD);
					 			cell = new PdfPCell(headerParagraph);
					 			cell.setBorder(PdfPCell.NO_BORDER);
					 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					 			headerTable.addCell(cell);
				 		  }  
		 		  
	 		           
		 		  cell_5: {
			 			PdfPCell cell;
	                    if(bean.getCurrentOpportunitiesBean().getMarginString()!=null){
	                    	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				 			headerParagraph.getFont().setStyle(Font.BOLD);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setBorder(PdfPCell.NO_BORDER);
				 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				 			headerTable.addCell(cell);
	                    }
			 		}
	          }
			return headerTable;
		} 
	 
	public static PdfPTable createtableForResource(ArrayList<CurrentOpportunitiesReportGenerationBean> monthWiseDataList) throws Exception{
		String[] headerLabes = {"Resource Name","Client Name","Charge Out Rate(Monthly)","Margin(%)","Margin"};
		float[]	widths = {20F,30F,20F,13F,20F};
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
 		
        for(CurrentOpportunitiesReportGenerationBean bean : monthWiseDataList){
        	
        	 cell_1: {
 					PdfPCell cell;
 				
 					Paragraph headerParagraph = new Paragraph(bean.getResourceMasterBean().getFullName());
 					headerParagraph.getFont().setSize(5f);
 					headerParagraph.setAlignment(Element.ALIGN_CENTER);
 					headerParagraph.getFont().setStyle(Font.NORMAL);
 					cell = new PdfPCell(headerParagraph);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 					headerTable.addCell(cell);
 			
 		            } 
        
        cell_2: {
 		            PdfPCell cell;

 		            Paragraph headerParagraph = new Paragraph(bean.getClientInformationBean().getFullName());
	 				headerParagraph.getFont().setSize(5f);
	 				headerParagraph.setAlignment(Element.ALIGN_CENTER);
	 				headerParagraph.getFont().setStyle(Font.NORMAL);
	 				cell = new PdfPCell(headerParagraph);
	 				cell.setHorizontalAlignment(Element.ALIGN_CENTER);

	 				headerTable.addCell(cell);
	 			
	 		    } 
 		 
 		 cell_3:{
	 		    	PdfPCell cell;
	 		    	if(bean.getCurrentOpportunitiesReportBean().getChargeOutRate()!=null){
	 		    		Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString());
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			headerTable.addCell(cell);	
	 		    	}else{
	 		    		String chargerate = "";
	 		    		Paragraph headerParagraph = new Paragraph(chargerate);
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			headerTable.addCell(cell);	
	 		    	}
	 		   }
	 		    
	 	   cell_4:{
	 		    	PdfPCell cell;
	 		    	if(bean.getCurrentOpportunitiesBean().getPercentage()!=null){
	 		    		Paragraph headerParagraph = new Paragraph(String.valueOf(bean.getCurrentOpportunitiesBean().getPercentage()));
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			headerTable.addCell(cell);
	 		    	}else{
	 		    		String percentage = "";
	 		    		Paragraph headerParagraph = new Paragraph(percentage);
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
                    if(bean.getCurrentOpportunitiesBean().getMargin()!=null){
                    	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			 			headerParagraph.getFont().setStyle(Font.NORMAL);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 			headerTable.addCell(cell);
                    }else{
                    	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
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
	
	public static PdfPTable demoCreatetableForResource(ArrayList<CurrentOpportunitiesReportGenerationBean> monthWiseDataList) throws Exception{
		String[] headerLabes = {"","","","",""};
		float[]	widths = {20F,30F,20F,13F,20F};
 		PdfPTable headerTable = new PdfPTable(widths);
 		headerTable.setWidthPercentage(96);
 		headerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
 		
        for (int index = 0; index < headerLabes.length; index++) {         	
 			PdfPCell cell;
 			Paragraph headerParagraph = new Paragraph(headerLabes[index]);
 			headerParagraph.getFont().setSize(5f);
 			headerParagraph.getFont().setStyle(Font.BOLD);
 			cell = new PdfPCell(headerParagraph);
 			cell.setBorder(PdfPCell.NO_BORDER);
 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
 			headerTable.addCell(cell);
 		}
 		
        for(CurrentOpportunitiesReportGenerationBean bean : monthWiseDataList){
        	
        	cell_1: {
 			PdfPCell cell;
 			    String a1 = "";
            	Paragraph headerParagraph = new Paragraph(a1);
	 			headerParagraph.getFont().setSize(5f);
	 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
	 			headerParagraph.getFont().setStyle(Font.BOLD);
	 			cell = new PdfPCell(headerParagraph);
	 			cell.setBorder(PdfPCell.NO_BORDER);
	 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	 			headerTable.addCell(cell);
 		  }
        	
        cell_2: {
	 			PdfPCell cell;
	 			String a2 = "";
                	Paragraph headerParagraph = new Paragraph(a2);
		 			headerParagraph.getFont().setSize(5f);
		 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
		 			headerParagraph.getFont().setStyle(Font.BOLD);
		 			cell = new PdfPCell(headerParagraph);
		 			cell.setBorder(PdfPCell.NO_BORDER);
		 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 			headerTable.addCell(cell);
	 		  }
 		  
        
        	cell_3: {
		 			PdfPCell cell;
	                if(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString()!=null){
	                	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesReportBean().getChargeOutRateString());
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			 			headerParagraph.getFont().setStyle(Font.BOLD);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setBorder(PdfPCell.NO_BORDER);
			 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 			headerTable.addCell(cell);
	                }
		 		  }
        	 
	 		 cell_4: {
			 				PdfPCell cell;
			 				String a3 = "";
		                	Paragraph headerParagraph = new Paragraph(a3);
				 			headerParagraph.getFont().setSize(5f);
				 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
				 			headerParagraph.getFont().setStyle(Font.BOLD);
				 			cell = new PdfPCell(headerParagraph);
				 			cell.setBorder(PdfPCell.NO_BORDER);
				 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				 			headerTable.addCell(cell);
			 		  }  
	 		  
 		           
	 		  cell_5: {
		 			PdfPCell cell;
                    if(bean.getCurrentOpportunitiesBean().getMarginString()!=null){
                    	Paragraph headerParagraph = new Paragraph(bean.getCurrentOpportunitiesBean().getMarginString());
			 			headerParagraph.getFont().setSize(5f);
			 			headerParagraph.setAlignment(Element.ALIGN_RIGHT);
			 			headerParagraph.getFont().setStyle(Font.BOLD);
			 			cell = new PdfPCell(headerParagraph);
			 			cell.setBorder(PdfPCell.NO_BORDER);
			 			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 			headerTable.addCell(cell);
                    }
		 		}
          }
		return headerTable;
	} 
	
	 public void createPdfHeaderForResource(String name, LinkedHashSet<MonthReportBean> list) throws Exception {
		 System.out.println("INSIDE METHOD list size::: "+list.size() );
	        PdfPTable table = new PdfPTable(2);
	        table.setSplitLate(false);
	        table.setWidths(new int[]{8, 30});
	        for (MonthReportBean monthReportBean : list) {
	        	cell_1: {
	        		PdfPCell cell;
				
	        		if(monthReportBean.getMonthName().toUpperCase().equalsIgnoreCase("")){
	        			Paragraph headerParagraph = new Paragraph("");
	        			headerParagraph.getFont().setSize(10f);
		        		headerParagraph.setAlignment(Element.ALIGN_CENTER);
		        		headerParagraph.getFont().setStyle(Font.NORMAL);
		        		cell = new PdfPCell(headerParagraph);
		        		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        		table.addCell(cell);
	        		}else{
	        			Paragraph headerParagraph = new Paragraph(monthReportBean.getMonthName().toUpperCase());
	        			headerParagraph.getFont().setSize(10f);
		        		headerParagraph.setAlignment(Element.ALIGN_CENTER);
		        		headerParagraph.getFont().setStyle(Font.NORMAL);
		        		cell = new PdfPCell(headerParagraph);
		        		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        		table.addCell(cell);
	        		}
	        		//Paragraph headerParagraph = new Paragraph(monthReportBean.getMonthName().toUpperCase());
	    
	            }
	        	
	            //table.addCell(monthReportBean.getMonthName().toUpperCase());
	        
	            if(monthReportBean.getMonthName().toUpperCase().equalsIgnoreCase("")){
	            	table.addCell(demoCreatetableForResource(monthReportBean.getCurrentOpportunitiesReportGenerationBeanList()));
	            }else{
	            	table.addCell(createtableForResource(monthReportBean.getCurrentOpportunitiesReportGenerationBeanList()));
	            }
	            
	        }
	        document.add(table);
	 }

    
	/********************************************************** GETTER AND SETTER METHOD ***************************************************************/ 
	 
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
	public LinkedHashSet<MonthReportBean> getFinalList() {
		return finalList;
	}
	public void setFinalList(LinkedHashSet<MonthReportBean> finalList) {
		this.finalList = finalList;
	}
}
