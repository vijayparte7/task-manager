package com.vijay.taskmanager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class SendEmailOtp {
	@Autowired
	private JavaMailSender mailSender;

	public void send(String mailAdd, String subject, String body) {
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
