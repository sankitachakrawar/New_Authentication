package com.example.demo.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Recruiter;
import com.example.demo.services.RecruiterService;

@RestController
@RequestMapping("/api")
public class RecruiterController {

	@Autowired
	private RecruiterService recruiterService;
	
	@PreAuthorize("hasRole('addRecruiter')")
	@PostMapping("/recruiter") 
	  public ResponseEntity<?> addRecruiter(@Valid @RequestBody Recruiter recruiter){
		 this.recruiterService.addRecruiter(recruiter);
		
	  return new ResponseEntity<>("Recruiter Register Successfully",HttpStatus.OK); 
	  }
	 
	
}
