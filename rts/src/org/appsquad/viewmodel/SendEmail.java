package org.appsquad.viewmodel;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.appsquad.bean.TaskNameBean;

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
	
	public static Boolean generateAndSendEmailForApproveOrReject(String emailId,String status,Integer trackingID) {
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
		   generateMailMessage.setSubject("Approval Request Status From Resource Augmentation Tracking System");
		   
		   if(status.equalsIgnoreCase("Approve")){
		      emailBody =""+trackingID+" Number Approval Request Approved.";
		   }else{
			  emailBody =""+trackingID+" Number Approval Request Rejected."; 
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
	   String emailBody ="Approval Request Sent To You.";
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
	
	 
	public static Boolean generateAndSendEmailForTaskCreation(String emailId,String msg,TaskNameBean taskNameBean) {
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
		   generateMailMessage.setSubject("Task assigned from Resource Augmentation Tracking");
		   
		   String emailBody = "Hi "+taskNameBean.getUserprofileBean().getUserid()+", \n\n"+"A new task assigned to you, " + "\n\n" +"Task Name: "+msg.toUpperCase()+" "+"\nAssigned By: "+taskNameBean.getAssignedByUserId().toUpperCase()+""+"\nSchedule Date: "+taskNameBean.getScheduledDateSql()+""+"\n\nThanks";
		   
		   generateMailMessage.setContent(emailBody, "text/plain");
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
	
	public static Boolean sendPasswordInEmail(String emailId,String emailBody) {
		  Properties mailServerProperties;
		  Session getMailSession;
		  MimeMessage generateMailMessage;
		 
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
		   generateMailMessage.setSubject("Forgot Password From Resource Augmentation Tracking System");
		   
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
	
	public static Boolean sendUserDetailsInEmail(String emailId,String emailBody) {
		  Properties mailServerProperties;
		  Session getMailSession;
		  MimeMessage generateMailMessage;
		 
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
		   generateMailMessage.setSubject("New user registration From Resource Augmentation Tracking System");
		   
		   generateMailMessage.setContent(emailBody, "text/plain");
		   
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