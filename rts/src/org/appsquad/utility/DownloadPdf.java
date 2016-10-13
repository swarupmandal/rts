package org.appsquad.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.FileUtils;
import org.appsquad.viewmodel.DemoFinalDownload;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;

public class DownloadPdf {

	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		
		List<String> filenames = new ArrayList<String>();
		filenames.add(pdfNamewithPath);
		byte[] buf = new byte[2048];
	       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(baos);
        
        FileInputStream fis = new FileInputStream(filenames.toString());
        System.out.println("fis.size  "+fis.toString());
        BufferedInputStream bis = new BufferedInputStream(fis);
        int bytesRead;
        while ((bytesRead = bis.read(buf)) != -1) {
        	    System.out.println("&&&&&&&&&&&&");
        		out.write(buf, 0, bytesRead);
            }
           
		/*System.out.println("PATH " + pdfNamewithPath);
		//FileInputStream fis = new FileInputStream(new File(pdfNamewithPath));
		FileInputStream fis = new FileInputStream(pdfNamewithPath);
		byte[] ba1 = new byte[1024];
		int baLength;
		ByteArrayOutputStream bios = new ByteArrayOutputStream();
		FileInputStream f = null;
		try {

			try {
				
				f =  new FileInputStream(pdfNamewithPath);
				System.out.println("AVVVVVVVVV " + f.available());
				System.out.println("Size " + f.read(ba1));
				while ((baLength = fis.read(ba1)) != -1) {
					System.out.println("----------");
					bios.write(ba1, 0, baLength);

				}
			} catch (Exception e1) {

			} finally {

				fis.close();

			}

			final AMedia amedia = new AMedia(fileName, "pdf", "application/pdf", bios.toByteArray());

			Filedownload.save(amedia);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			File xlsFile = new File(pdfNamewithPath);

			if (xlsFile.exists()) {

				System.out.println("exists!!!!!!!!!!!!!!!!!!!");
				//FileUtils.forceDelete(xlsFile);
				xlsFile.delete();
			}
		}*/
	}
	
	
}
