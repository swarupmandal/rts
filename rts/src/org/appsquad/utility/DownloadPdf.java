package org.appsquad.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jdk.management.resource.internal.inst.FileInputStreamRMHooks;

import org.apache.commons.io.FileUtils;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zul.Messagebox;

public class DownloadPdf {

	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		String path=pdfNamewithPath.replace('\\','/');
		Messagebox.show(path);
		byte[] ba1 = new byte[102400];
		File myFile = new File(path );
		FileInputStream fis = new FileInputStream(myFile);
		
		Messagebox.show("BA Length::"+fis.read(ba1));
		int baLength;
		Messagebox.show("aaaaaaaaaaaa " + fis.available());
		ByteArrayOutputStream bios = new ByteArrayOutputStream();
		try {
			try {

				while ( (baLength = fis.read(ba1)) != -1) {
					System.out.println("%%%%%%%%%%%%%%");
					bios.write(ba1, 0, baLength);
				}
				Messagebox.show("After while::::::::::" + fis.available());
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
				//FileUtils.forceDelete(xlsFile);
				xlsFile.delete();
			}
		}
	}
	
	
}
