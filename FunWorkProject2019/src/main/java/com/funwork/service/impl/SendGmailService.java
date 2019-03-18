package com.funwork.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

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

	public GoogleIdToken idTokenVertify(String idtoken) {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance())
						.setAudience(Collections.singletonList(
								"784516300990-g9mc0al77s74lmmi0q6hb9777k3om0qj.apps.googleusercontent.com"))
						.build();
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idtoken);
		} catch (GeneralSecurityException e) {
			System.out.println("驗證時出現GeneralSecurityException異常");
		} catch (IOException e) {
			System.out.println("驗證時出現IOException異常");
		}
		return idToken;
	}

}
