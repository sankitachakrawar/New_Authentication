package com.example.demo.services;

import com.example.demo.entities.Candidate;
public interface EmailService {

	public String sendMail(String emailTo,String subject,String text,Candidate candidate);

	public void sendSimpleMessage(String email, String string, String url);

	
}
