package org.appsquad.utility;

import java.util.*;  

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*; 
import javax.mail.util.ByteArrayDataSource;

public class EmailWithPdf{
	
	 public static void writePdf(OutputStream outputStream) throws Exception {
	        Document document = new Document();
	        PdfWriter.getInstance(document, outputStream);
	        document.open();
	        document.addTitle("Test PDF");
	        document.addSubject("Testing email PDF");
	        document.addKeywords("iText, email");
	        document.addAuthor("Jee Vang");
	        document.addCreator("Jee Vang");
	         
	        Paragraph paragraph = new Paragraph();
	        paragraph.add(new Chunk("hello!"));
	        paragraph.add(new Chunk("hello!"));
	        paragraph.add(new Chunk("hello!"));
	        paragraph.add(new Chunk("hello!"));
	        paragraph.add(new Chunk("hello!"));
	        document.add(paragraph);
	         
	        document.close();
	    }
	
	 public static void main(String [] args){  
		  
		  String to="prolayjitdutta@gmail.com";//change accordingly  
		  final String user="sentmail95@gmail.com";//change accordingly  
		  final String password="sunnydutta123";//change accordingly  
		   
		  //1) get the session object     
		  Properties properties = System.getProperties();  
		  properties = System.getProperties();
		  properties.put("mail.smtp.port", "587");
		  properties.put("mail.smtp.host", "smtp.gmail.com");
		  properties.put("mail.smtp.auth", "true");
		  properties.put("mail.smtp.user", "sentmail95@gmail.com");
		  properties.put("mail.smtp.password", "sunnydutta123");
		  properties.put("mail.smtp.starttls.enable", "true");  
		  
		  Session session = Session.getDefaultInstance(properties,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,password);  
		   }  
		  });  
		     
		  //2) compose message     
		  try{  
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(user));  
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		    message.setSubject("Message Aleart");  
		      
		    //3) create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
		    messageBodyPart1.setText("This is message body");  
		  
		    //now write the PDF content to the output stream
		    ByteArrayOutputStream outputStream = null;
            outputStream = new ByteArrayOutputStream();
            try {
				writePdf(outputStream);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            byte[] bytes = outputStream.toByteArray();
             
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf"); 
		     
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(pdfBodyPart);  
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart);  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("DYNAMIC PDF GENERATED AND MAIL SENT. ");  
		   }catch (MessagingException ex) {
			   ex.printStackTrace();
	     }  
	}  
}  
