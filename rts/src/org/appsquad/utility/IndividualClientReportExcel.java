package org.appsquad.utility;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.appsquad.bean.IndividualClientReportBean;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Executions;

public class IndividualClientReportExcel {

	public static void printCSV(ArrayList<IndividualClientReportBean> reportBeanList){
		File f = null;  boolean bool = false;
		try{
	         // create new file
	    	 String realPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
	  		
	  		String reportNamewithPath = realPath + "report.csv";
	  		System.out.println(reportNamewithPath);
	        f = new File(reportNamewithPath);
	         // tries to create new file in the system
	         bool = f.createNewFile();
	         
	         // prints
	         System.out.println("File created: "+bool);
	         if(f.exists())
	         // deletes file from the system
	         f.delete();
	         System.out.println("delete() method is invoked");
	         // delete() is invoked
	         f.createNewFile();
	       
	         // tries to create new file in the system
	         bool = f.createNewFile();
	         
	         // print
	         System.out.println("File created: "+bool);
	         FileOutputStream fileOutputStream = new FileOutputStream(f);
	         OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);    
	            Writer w = new BufferedWriter(osw);
	            w.write("STATUS, RESOURCE NAME,Year Of Exp.,CONTACT NO.,EMAIL,OTHER INFO, INTERNAL INTERVIEW DATE, CLIENT INTERVIEW DATE\n");
	            for(int i=0;i<reportBeanList.size();i++){
	            	w.write(reportBeanList.get(i).getrIdLabel()+","+reportBeanList.get(i).getrIdDateLabel()+","+reportBeanList.get(i).getYoExp()
	            			+","+reportBeanList.get(i).getSkillSetLabel()+","+reportBeanList.get(i).getEmailId()+","+reportBeanList.get(i).getCompanyName()
	            			+","+reportBeanList.get(i).getIntIntvValue()+","+reportBeanList.get(i).getClntIntvValue()+"\n");
	            }
	            w.close();
	           Desktop.getDesktop().open(f);
	           
	            FileInputStream fis = new FileInputStream(new File(reportNamewithPath));
	    		byte[] ba1 = new byte[1024];
	    		int baLength;
	    		ByteArrayOutputStream bios = new ByteArrayOutputStream();
	    		try {
	    			try {
	    				while ((baLength = fis.read(ba1)) != -1) {
	    					bios.write(ba1, 0, baLength);
	    				}
	    			} catch (Exception e1) {
	    			} finally {
	    				fis.close();
	    			}
	    			final AMedia amedia = new AMedia("report", "csv", "application/csv", bios.toByteArray());
	    			Filedownload.save(amedia);
	    		}catch(Exception exception){		
	    		}
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	
	public static void printSummaryCSV(ArrayList<IndividualClientReportBean> reportBeanList){
		File f = null;  boolean bool = false;
		try{
	         // create new file
	    	 String realPath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
	  		
	  		String reportNamewithPath = realPath + "report.csv";
	  		System.out.println(reportNamewithPath);
	        f = new File(reportNamewithPath);
	         // tries to create new file in the system
	         bool = f.createNewFile();
	         
	         // prints
	         System.out.println("File created: "+bool);
	         if(f.exists())
	         // deletes file from the system
	         f.delete();
	         System.out.println("delete() method is invoked");
	         // delete() is invoked
	         f.createNewFile();
	       
	         // tries to create new file in the system
	         bool = f.createNewFile();
	         
	         // print
	         System.out.println("File created: "+bool);
	         FileOutputStream fileOutputStream = new FileOutputStream(f);
	         OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);    
	            Writer w = new BufferedWriter(osw);
	            w.write("STATUS,,,,No.of Resources\n");
	            for(int i=0;i<reportBeanList.size();i++){
	            	w.write(reportBeanList.get(i).getrIdLabel()+","+reportBeanList.get(i).getrIdDateLabel()+","+reportBeanList.get(i).getClientFullName()
	            			+","+reportBeanList.get(i).getSkillSetLabel()+","+reportBeanList.get(i).getNoOfReqLebel()+"\n");
	            }
	            w.close();
	           Desktop.getDesktop().open(f);
	           
	            FileInputStream fis = new FileInputStream(new File(reportNamewithPath));
	    		byte[] ba1 = new byte[1024];
	    		int baLength;
	    		ByteArrayOutputStream bios = new ByteArrayOutputStream();
	    		try {
	    			try {
	    				while ((baLength = fis.read(ba1)) != -1) {
	    					bios.write(ba1, 0, baLength);
	    				}
	    			} catch (Exception e1) {
	    			} finally {
	    				fis.close();
	    			}
	    			final AMedia amedia = new AMedia("report", "csv", "application/csv", bios.toByteArray());
	    			Filedownload.save(amedia);
	    		}catch(Exception exception){		
	    		}
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	
	
	
	
	
	
}
