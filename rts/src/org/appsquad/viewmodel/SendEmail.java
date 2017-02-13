package org.appsquad.viewmodel;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.utility.Dateformatter;

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

	public static Boolean generateAndSendEmail(String emailId,String ccEmailId,CurrentOpportunitiesBean opportunitiesBean) {
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
			generateMailMessage.addRecipient(Message.RecipientType.CC,new InternetAddress(ccEmailId));
			generateMailMessage.setSubject("Approval Request From Resource Augmentation Tracking System");
			String emailBody = messegeForApprover(opportunitiesBean);
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

			//String emailBody = "Hi "+taskNameBean.getUserprofileBean().getUserid()+","
			//		+ " \n\n"+"A new task assigned to you, " + "\n\n" +"Task Name: "+msg.toUpperCase()+" "+"\nAssigned By: "+taskNameBean.getAssignedByUserId().toUpperCase()+""+"\nSchedule Date: "+taskNameBean.getScheduledDateSql()+""+"\n\nThanks";
			String emailBody =  taskAssignedBodyMessage(taskNameBean);
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

	public static Boolean sendPasswordInEmail(UserprofileBean user,String emailBody) {
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
			generateMailMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
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

	public static String taskAssignedBodyMessage(TaskNameBean taskNameBean){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>"
				+ "<body>"
				+ "<div style=\"border: 1px solid green;box-shadow: 10px 10px 5px #888888;background-color: #ffffcc;\">"
				+ "<h2 style=\"text-align: center;font:bold;color: green; \">TASK ASSIGNED</h2>"
				+ "<hr>"
				+ "<div style=\"margin-left: 30px;\">"
				+ "<strong style=\"font-size: 24px;\">Hello "+taskNameBean.getUserprofileBean().getUsername()+",</strong>"
				+ "<p style=\"font-size: 17px;\">A new task is asssigned for you. The detailed description for your task is given below.</p>"
				+ "<strong style=\"font-size: 18px;\"><u>Task Detail Description</u>:</strong><br>"
				+ "<p>"
				+ "<strong>Task-Name: </strong><span>"+taskNameBean.getTaskDescription()+"</span><br><br>"
				+ "<strong>Description: </strong><span> "
				+ taskNameBean.getTaskName()
				+ "</span><br><br>"
				+ "<strong>Assigned By: </strong><span> "+taskNameBean.getAssignedByUserId().toUpperCase()+"</span><br><br>"
				+ "<strong>Schedule Date: </strong><span> "+Dateformatter.toStringDate(taskNameBean.getScheduledDateSql().toString()) +"</span><br><br>"
				+ "</p>"
				+ "<h4>Thanks.</h4>"
				+ "</div>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
		return sb.toString();
	}
	
	
	public static String messegeForApprover(CurrentOpportunitiesBean opportunitiesBean){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>"
				+ "<body>"
				+ "<div style=\"border: 1px solid green;box-shadow: 10px 10px 5px #888888;background-color: #ffffcc;\">"
				+ "<h2 style=\"text-align: center;font:bold;color: green; \">BILLING APPROVAL REQUEST</h2>"
				+ "<hr>"
				+ "<div style=\"margin-left: 30px;\">"
				+ "<strong style=\"font-size: 24px;\">Hello "+opportunitiesBean.getBean().getUserID()+",</strong>"
				+ "<p style=\"font-size: 17px;\">One billing confirmation for approval is requested for you.</p>"
				+ "<strong style=\"font-size: 18px;\"><u>The details description is given below</u>:-</strong><br>"
				+ "<p>"
				+ "<strong>Requirement ID: </strong><span>"+opportunitiesBean.getRid()+"</span><br><br>"
				+ "<strong>Client-Name: </strong><span>"+opportunitiesBean.getClientName()+"</span><br><br>"
				+ "<strong>Resource Name: </strong><span> "
				+ opportunitiesBean.getResourceName()
				+ "</span><br><br>"
				+ "</p>"
				+ "<p style=\"font-size: 17px;\">Kindly approve/reject as per your requirement.</p>"
				+ "<h4>Thanks.</h4>"
				+ "</div>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
		return sb.toString();
	}
	
	
	public static String forgotPasswordBodyMessage(UserprofileBean user){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>"
				+ "<body>"
				+ "<div style=\"border: 1px solid green;box-shadow: 10px 10px 5px #888888;background-color: #ffffcc;\">"
				+ "<h2 style=\"text-align: center;font:bold;color: green; \">FORGOT PASSWORD</h2>"
				+ "<hr>"
				+ "<div style=\"margin-left: 30px;margin-right: 30px;\">"
				+ "<strong style=\"font-size: 24px;\">Hello "+user.getUsername()+",</strong>"
				+ "<p style=\"font-size: 17px;\">A system generated temporary password is asssigned for you. Kindly login with the password given below.</p>"
				+ "<strong style=\"font-size: 18px;\"><u>Your temporary password</u>:</strong><br>"
				+ "<p>"
				+ "<strong>Password: </strong><span>"+user.getSystemPassword()+"</span><br><br>"
				+ "</p>"
				+ "<h4>Thanks.</h4>"
				+ "</div>"
				+ "</div>"
				+ "</body>"
				+ "</html>");
		return sb.toString();
	}
	
	public static void myLogger(String email, boolean valid) {
		System.out.println(email + " is " + (valid ? "a" : "not a") + " valid email address\n");
	}
	public static void main(String[] args) {
		System.out.println("SMS CALLING PAGE");
	}
}