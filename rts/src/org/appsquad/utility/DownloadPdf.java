package org.appsquad.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Filedownload;

public class DownloadPdf {

	public static void download(String pdfNamewithPath, String fileName) throws IOException{
		String path=pdfNamewithPath.replace('\\','/');
		FileInputStream fis = new FileInputStream(new File(path));
		byte[] ba1 = new byte[1024];
		int baLength;
		ByteArrayOutputStream bios = new ByteArrayOutputStream();
		try {
			try {

				while ((baLength = fis.read(ba1)) != -1) {
					System.out.println("%%%%%%%%%%%%%%");
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
				//FileUtils.forceDelete(xlsFile);
				xlsFile.delete();
			}
		}
	}
	
	
}
