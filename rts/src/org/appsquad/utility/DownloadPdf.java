package org.appsquad.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;

public class DownloadPdf {

	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		 
		  FileInputStream fis = new FileInputStream(new File(pdfNamewithPath));
		  byte[] ba1 = new byte[1024];
		  int baLength;
		  ByteArrayOutputStream bios = new ByteArrayOutputStream();
		  try {
			  System.out.println("1ST METHOD");
		   try {
			   System.out.println("2ND METHOD");
			   System.out.println("PATH NAME IS :"+pdfNamewithPath);
		    while ((baLength = fis.read(ba1)) != -1) {
		     System.out.println("%%%%%%%%%%%%%%");
		     bios.write(ba1, 0, baLength);

		    }
		   } catch (Exception e1) {
			   e1.printStackTrace();
		   } finally {
		    fis.close();
		   }
		   final AMedia amedia = new AMedia(fileName, "pdf", "application/pdf", bios.toByteArray());
		   Filedownload.save(amedia);
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } /*finally {
		   File xlsFile = new File(pdfNamewithPath);
		   if (xlsFile.exists()) {
			System.out.println("DELETE");
		    xlsFile.delete();
		   }
		  }*/
		 }
}
