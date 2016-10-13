package org.appsquad.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;

public class DownloadPdf {

	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		System.out.println("PATH " + pdfNamewithPath);
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
		}
	}
	
	
}
