package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Candidate;
import com.example.demo.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	

	  @Override
	  public String sendMail(String emailTo, String subject, String text, Candidate candidate) {
		  SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
	  simpleMailMessage.setFrom("abcxyz625500@gmail.com");
	  simpleMailMessage.setTo(candidate.getEmail());
	  
	  simpleMailMessage.setSubject("Apply sucessfully");
	  simpleMailMessage.setText("Text demo");
	  javaMailSender.send(simpleMailMessage); 
	  return "Email Send";
	  }

	  @Override
		public void sendSimpleMessage(String emailTo, String subject, String text) {

			SimpleMailMessage message = new SimpleMailMessage();
			 message.setFrom("abcxyz625500@gmail.com");
			message.setTo(emailTo);
			message.setSubject(subject);
			message.setText(text);
			javaMailSender.send(message);

		}


		
	}
	 
	

