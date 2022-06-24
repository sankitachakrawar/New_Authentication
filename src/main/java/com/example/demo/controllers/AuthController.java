package com.example.demo.controllers;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordRequestDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.*;
import com.example.demo.utils.AppSetting;
import com.example.demo.utils.jwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
@RestController
public class AuthController {

	@Autowired
	private forgotPasswordServiceIntf forgotPasswordServiceIntf;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private EmailServiceIntf emailServiceIntf;
	
	@Autowired
	private AppSetting appSetting;
	
	  @Autowired
	    private AuthenticationManager authenticationManager;
	
	 @PostMapping("/signin")
	 public ResponseEntity<String>  authenticateUser(@RequestBody CandidateDto candidate){
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( candidate.getEmail(),
				 				candidate.getPassword()));
	  
		 	SecurityContextHolder.getContext().setAuthentication(authentication); 
		 	return new ResponseEntity<>("Candidate signed-in successfully!.", HttpStatus.OK); }
	
	
	@PostMapping("/forgot-pass")
	public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotPasswordRequestDto forgotPassBody, HttpServletRequest request) throws Exception {

		try {

			Candidate candidate = candidateService.findByEmail(forgotPassBody.getEmail());
			final String token = jwtTokenUtil.generateTokenOnForgotPass(candidate.getEmail());
			final String baseUrl = appSetting.getAllAppSetting().getSettings().get("frontendbaseurl");
			final String passUrl = appSetting.getAllAppSetting().getSettings().get("forgotpasswordurl");
			final String url = "To confirm your account, please click here : " + baseUrl + passUrl + "?token=" + token;
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.MINUTE, 5);
			forgotPasswordServiceIntf.createForgotPasswordRequest(candidate.getC_id(), token, calender.getTime());
			emailServiceIntf.sendSimpleMessage(candidate.getEmail(), "Test", url);
			return new ResponseEntity<>(new SuccessResponseDto("Password reset link sent on Registerd Email", "passwordRestLinkMail", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

		}

	}
	 
}
