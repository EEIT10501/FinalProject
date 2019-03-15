package com.funwork.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendGmailService {

	@Autowired
	private JavaMailSender mail;

	public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

		try {
			MimeMessage mimeMessage = mail.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setSubject(subject);
			helper.setFrom(fromAddress);
			helper.setTo(toAddress);
			helper.setText(msgBody, true);
			mail.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
