package org.appsquad.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InvoiceServlet
 */
@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String passCode = request.getParameter("key");
		 byte[] buf = new byte[2048];
		 
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ZipOutputStream out = new ZipOutputStream(baos);
	     System.out.println("*******"+passCode.toString());
	     FileInputStream fis = new FileInputStream(passCode.toString());
	     BufferedInputStream bis = new BufferedInputStream(fis);
	   
	     File file = new File(passCode.toString());
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
