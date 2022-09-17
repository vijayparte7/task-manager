package com.vijay.taskmanager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request")
public class MailOtp extends Thread {
	@Autowired
	private JavaMailSender mailSender;
	
	String mailAdd;
	String subject;
	String body;
	
	

	public JavaMailSender getMailSender() {
		return mailSender;
	}



	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}



	public String getMailAdd() {
		return mailAdd;
	}



	public void setMailAdd(String mailAdd) {
		this.mailAdd = mailAdd;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public void run() {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		try {
			messageHelper.setTo(mailAdd);
			messageHelper.setSubject(subject);
			messageHelper.setText(body);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
