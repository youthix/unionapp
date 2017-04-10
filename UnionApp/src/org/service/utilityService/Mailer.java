package org.service.utilityService;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.presentation.dto.user.User;
import org.presentation.dto.user.UserList;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
	
	final String USER_NAME = "wfs@unik-apps.com";
	final String PASSWORD = "admin";
	
	public void approvalMail(UserList userListObj ){

		String from = USER_NAME;
		String pass = PASSWORD;
		
		for(User u: userListObj.getUl()){
			String[] to = { u.getUsNa() };

			String subject = "WFS User Registration";
			String body = "Your registration request is successfully approved.Welcome to WFS !";
			sendMail(from, pass, to, subject, body);	
		}	
	}
	
	public void denialMail(UserList userListObj){

		String from = USER_NAME;
		String pass = PASSWORD;
		
		for(User u: userListObj.getUl()){
			String[] to = { u.getUsNa() };

			String subject = "WFS User Registration";
			String body = "Your registration request is not approved.Please contact WFS Admin !";
			sendMail(from, pass, to, subject, body);	
		}		
	}
	
	public void forgotPasswordMail(UserList userListObj,String password){

		String from = USER_NAME;
		String pass = PASSWORD;
		
		for(User u: userListObj.getUl()){
			String[] to = { u.getUsNa() };

			String subject = "New Password";
			String body = "Your new password is : "+password;
			sendMail(from, pass, to, subject, body);	
		}		
	}
	
	public void sendMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		/*
		 * String host = "smtp.gmail.com";
		 * props.put("mail.smtp.starttls.enable", "true");
		 * props.put("mail.smtp.host", host); props.put("mail.smtp.user", from);
		 * props.put("mail.smtp.password", pass); props.put("mail.smtp.port",
		 * "587"); props.put("mail.smtp.auth", "true");
		 */

		String host = "unik-apps.com";
		/* props.put("mail.smtp.starttls.enable", "true"); */
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		System.out.println("Mail Sent");
	}

}
