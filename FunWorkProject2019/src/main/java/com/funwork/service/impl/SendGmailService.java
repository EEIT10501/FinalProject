package com.funwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendGmailService {

	@Autowired
	private MailSender mail;

	public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);
		mail.send(msg);
	}

}
