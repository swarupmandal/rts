package org.appsquad.viewmodel;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail{
	
	public static Boolean generateAndSendEmail() { 
	     System.out.println("Invoice generation starting . . .!");
	     Properties mailServerProperties;
	     Session getMailSession;
	     MimeMessage generateMailMessage;
	     // Step1
	  System.out.println("\n 1st ===> setup Mail Server Properties..");
	  mailServerProperties = System.getProperties();
	  mailServerProperties.put("mail.smtp.port", "587");
	  mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
	  mailServerProperties.put("mail.smtp.auth", "true");
	  mailServerProperties.put("mail.smtp.user", "sentmail95@gmail.com");
	  mailServerProperties.put("mail.smtp.password", "sunnydutta123");
	  mailServerProperties.put("mail.smtp.starttls.enable", "true");
	  System.out.println("Mail Server Properties have been setup successfully..");
	 
	  // Step2
	  System.out.println("\n 2nd ===> get Mail Session..");
	  getMailSession = Session.getDefaultInstance(mailServerProperties, null);
	  generateMailMessage = new MimeMessage(getMailSession);
	  try {
	   generateMailMessage.setFrom(new InternetAddress("sentmail95@gmail.com"));
	   generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress("prolayjitdutta@gmail.com"));
	   generateMailMessage.setSubject("Order delivered from EazeLyf");
	   String emailBody ="hello this is body";
	   generateMailMessage.setContent(emailBody, "text/html");
	   System.out.println("Mail Session has been created successfully..");
	   // Step3
	   System.out.println("\n 4th ===> Get Session and Send mail");
	   Transport transport = getMailSession.getTransport("smtp");
	  
	   // Enter your correct gmail UserID and Password
	   // if you have 2FA enabled then provide App Specific Password
	   //transport.connect("smtp.gmail.com", "<----- Your GMAIL ID ----->", "<----- Your GMAIL PASSWORD ----->");
	   transport.connect("sentmail95@gmail.com", "sunnydutta123");
	   transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
	   transport.close();
	   return true;
	  } catch (AddressException e) {
	   e.printStackTrace();
	  } catch (MessagingException e) {
	   e.printStackTrace();
	  }
	  return false;
	 }
	
    public static void main(String[] args) {
		generateAndSendEmail();
	}
}