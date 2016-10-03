package org.appsquad.viewmodel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096; 

    /**
     * Default constructor. 
     */
    public DemoServlet() {
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
    	
    	String passCode = request.getParameter("key");
		System.out.println("in servlet page:"+passCode);
		String[] parts = passCode.split(",");
		
        List<String> filenames = new ArrayList<String>();
        for(int i =0;i<parts.length;i++){
			filenames.add(DemoFinalDownload.fetchingPathFromDbAnother(parts[i]));
		}
		
        byte[] buf = new byte[2048];
       
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(baos);
        
        for (int i=0; i<filenames.size(); i++) {
        FileInputStream fis = new FileInputStream(filenames.get(i).toString());
        BufferedInputStream bis = new BufferedInputStream(fis);
      
        File file = new File(filenames.get(i).toString());
        String entryname = file.getName();
        out.putNextEntry(new ZipEntry(entryname));
        int bytesRead;
        while ((bytesRead = bis.read(buf)) != -1) {
        out.write(buf, 0, bytesRead);
        }
        out.closeEntry();
        bis.close();
        fis.close();
        }
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