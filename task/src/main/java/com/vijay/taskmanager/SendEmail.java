package com.vijay.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
@Component
@Scope(value = "request")
public class SendEmail {
	@Autowired
	private MailOtp mailOtp;
	public void send(String email, String subject, String body){
		mailOtp.setBody(body);
		mailOtp.setMailAdd(email);
		mailOtp.setSubject(subject);
		mailOtp.start();
	}

}
