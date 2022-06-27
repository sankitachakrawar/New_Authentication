package com.example.demo.serviceImpl;

import java.io.File;
import java.net.URL;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.demo.services.EmailServiceIntf;


@Service
public class EmailServiceImpl implements EmailServiceIntf {

	@Autowired
	private JavaMailSender emailSender;

	private URL templateLocation;
	
	
	/*
	 * private EmailServiceIntf b;
	 * 
	 * 
	 * public EmailServiceImpl(@NonNull @Lazy EmailServiceIntf b) { this.b=b; }
	 */
	
	
	
	@Override
	public void sendSimpleMessage(String emailTo, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom("sankita@nimapinfotech.com");
		message.setTo(emailTo);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

	}

	@Override
	public void sendMessageWithAttachment() {
		try {

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			//message.setFrom("sankita@nimapinfotech.com");
			helper.setTo("sankitachakrawar6255@gmail.com");
			helper.setSubject("TEST SUBJECT");
			helper.setText("TEST TEXT");
			File tempDirectory = new File(templateLocation.getPath());
			File outputFile = new File(tempDirectory, "12345" + ".pdf");
			FileSystemResource file = new FileSystemResource(outputFile);
			helper.addAttachment("Invoice", file);
			emailSender.send(message);

		} catch (MessagingException e) {

			
			e.printStackTrace();

		}
		
	}

	

}