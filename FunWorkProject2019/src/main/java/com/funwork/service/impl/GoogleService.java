package com.funwork.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GoogleService {

  public static final Logger logger = Logger.getLogger("com.funwork");
  
  @Autowired
  private JavaMailSender mail;

  /**
   * Send Email by Gmail.
   */
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
      logger.warning(e.getMessage());
    }
  }

  /**
   * Google idToken Verify. 
   * Return null if fail.
   */
  public GoogleIdToken idTokenVerify(String idtoken) {
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
        JacksonFactory.getDefaultInstance())
            .setAudience(
                Collections.singletonList("784516300990-g9mc0al77s74lmmi0q6hb9777k3om0qj"
                    + ".apps.googleusercontent.com"))
            .build();
    GoogleIdToken idToken = null;
    try {
      idToken = verifier.verify(idtoken);
    } catch (GeneralSecurityException e) {
      logger.warning("驗證時出現GeneralSecurityException異常");
    } catch (IOException e) {
      logger.warning("驗證時出現IOException異常");
    }
    return idToken;
  }
}