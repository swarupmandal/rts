package org.appsquad.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class TimeSheetDownloadUtility {
	public static void download(String path) throws Exception{
    	 byte[] buf = new byte[2048];
    	 HttpServletResponse response = null;
         
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ZipOutputStream out = new ZipOutputStream(baos);
         System.out.println("*******"+path.toString());
         FileInputStream fis = new FileInputStream(path.toString());
         BufferedInputStream bis = new BufferedInputStream(fis);
       
         File file = new File(path.toString());
         String entryname = file.getName();
         out.putNextEntry(new ZipEntry(entryname));
         int bytesRead;
         while ((bytesRead = bis.read(buf)) != -1) {
         out.write(buf, 0, bytesRead);
         }
         out.closeEntry();
         bis.close();
         fis.close();
         
         out.flush();
         baos.flush();
         out.close();
         baos.close();
         ServletOutputStream sos = response.getOutputStream();
         response.setContentType("Content-type: text/zip");
 		 response.setHeader("Content-Disposition","attachment; filename=mytest.zip");
         sos.write(baos.toByteArray());
         out.flush();
         out.close();
         sos.flush();
    }
}
