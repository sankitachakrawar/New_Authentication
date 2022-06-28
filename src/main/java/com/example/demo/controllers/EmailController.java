package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Candidate;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.EmailService;

@RestController
public class EmailController {

	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	
	
	
	
	  @PostMapping("/sendmail") public String sendMailmessage(@RequestBody Candidate candidate) {
		  String email=candidate.getEmail();
		  String emailTo = null; 
		  String subject = null; 
		  String text = null;
		  		
	  emailService.sendMail(emailTo, subject, text, candidate);
	  return "email Send !!";
	  
	  
	  }
	 
	
	

	/*
	 * @GetMapping("/mail") public String sendMail() { return
	 * emailService.sendSimpleEmail(); }
	 */
}
