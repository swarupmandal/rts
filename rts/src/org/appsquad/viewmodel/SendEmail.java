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
	public static boolean validator(String email) {
		boolean isValid = false;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;
	}
	
	public static Boolean generateAndSendEmailForApproveOrReject(String emailId,String status) {
		  Properties mailServerProperties;
		  Session getMailSession;
		  MimeMessage generateMailMessage;
		  String emailBody;
		  //
		  mailServerProperties = System.getProperties();
		  mailServerProperties.put("mail.smtp.port", "587");
		  mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		  mailServerProperties.put("mail.smtp.auth", "true");
		  mailServerProperties.put("mail.smtp.user", "sentmail95@gmail.com");
		  mailServerProperties.put("mail.smtp.password", "sunnydutta123");
		  mailServerProperties.put("mail.smtp.starttls.enable", "true");
		  System.out.println("Mail Server Properties have been setup successfully..");
		 
		  //
		  getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		  generateMailMessage = new MimeMessage(getMailSession);
		  try {
		   generateMailMessage.setFrom(new InternetAddress("sentmail95@gmail.com"));
		   generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId));
		   //generateMailMessage.addRecipient(Message.RecipientType.CC,new InternetAddress("prolayjit.dutta@appsquad.in"));
		   generateMailMessage.setSubject("Approval Request Status");
		   
		   if(status.equalsIgnoreCase("Approve")){
		      emailBody ="Approval Request Approved";
		   }else{
			  emailBody ="Approval Request Rejected"; 
		   }
		   generateMailMessage.setContent(emailBody, "text/html");
		   System.out.println("Mail Session has been created successfully..");
		   
		   //
		   Transport transport = getMailSession.getTransport("smtp");
		   transport.connect("smtp.gmail.com","sentmail95@gmail.com", "sunnydutta123");
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
	
	public static Boolean generateAndSendEmail(String emailId) {
	  Properties mailServerProperties;
	  Session getMailSession;
	  MimeMessage generateMailMessage;
	  //
	  mailServerProperties = System.getProperties();
	  mailServerProperties.put("mail.smtp.port", "587");
	  mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
	  mailServerProperties.put("mail.smtp.auth", "true");
	  mailServerProperties.put("mail.smtp.user", "sentmail95@gmail.com");
	  mailServerProperties.put("mail.smtp.password", "sunnydutta123");
	  mailServerProperties.put("mail.smtp.starttls.enable", "true");
	  System.out.println("Mail Server Properties have been setup successfully..");
	 
	  //
	  getMailSession = Session.getDefaultInstance(mailServerProperties, null);
	  generateMailMessage = new MimeMessage(getMailSession);
	  try {
	   generateMailMessage.setFrom(new InternetAddress("sentmail95@gmail.com"));
	   generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId));
	   //generateMailMessage.addRecipient(Message.RecipientType.CC,new InternetAddress("prolayjit.dutta@appsquad.in"));
	   generateMailMessage.setSubject("Approval Request Sent From Resource Augmentation Tracking System");
	   String emailBody ="You Have Got An Approval Request.";
	   generateMailMessage.setContent(emailBody, "text/html");
	   System.out.println("Mail Session has been created successfully..");
	   
	   //
	   Transport transport = getMailSession.getTransport("smtp");
	   transport.connect("smtp.gmail.com","sentmail95@gmail.com", "sunnydutta123");
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
	
	 
	public static Boolean generateAndSendEmailForTaskCreation(String emailId,String msg) {
		  Properties mailServerProperties;
		  Session getMailSession;
		  MimeMessage generateMailMessage;
		  //
		  mailServerProperties = System.getProperties();
		  mailServerProperties.put("mail.smtp.port", "587");
		  mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		  mailServerProperties.put("mail.smtp.auth", "true");
		  mailServerProperties.put("mail.smtp.user", "sentmail95@gmail.com");
		  mailServerProperties.put("mail.smtp.password", "sunnydutta123");
		  mailServerProperties.put("mail.smtp.starttls.enable", "true");
		  System.out.println("Mail Server Properties have been setup successfully..");
		 
		  //
		  getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		  generateMailMessage = new MimeMessage(getMailSession);
		  try {
		   generateMailMessage.setFrom(new InternetAddress("sentmail95@gmail.com"));
		   generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId));
		   generateMailMessage.setSubject("Task Name Creation Status Sent From Resource Augmentation Tracking System");
		   String emailBody ="A New Task Assigned For You.\\n Task Name:"+msg+" ";
		   generateMailMessage.setContent(emailBody, "text/html");
		   System.out.println("Mail Session has been created successfully..");
		   
		   //
		   Transport transport = getMailSession.getTransport("smtp");
		   transport.connect("smtp.gmail.com","sentmail95@gmail.com", "sunnydutta123");
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
	
	 public static void myLogger(String email, boolean valid) {
		System.out.println(email + " is " + (valid ? "a" : "not a") + " valid email address\n");
	 }
     public static void main(String[] args) {
    	 System.out.println("SMS CALLING PAGE");
	 }
}