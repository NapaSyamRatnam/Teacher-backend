package com.erp.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	public Boolean sendEmail(String to,String subject, String body) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(mimeMessage);
		
		boolean isSent=false;
		
	    try {
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body);
			
			javaMailSender.send(mimeMessage);
			isSent=true;
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return isSent;
		
		
	}

	

    public boolean sendEmailAttachment(String to, String subject, String body, byte[] attachmentData, String attachmentName) throws IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // true indicates multipart message
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body);

            // Add attachment
            DataSource attachmentSource = new ByteArrayDataSource(attachmentData, "application/pdf");
            helper.addAttachment(attachmentName, attachmentSource);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
}
}
