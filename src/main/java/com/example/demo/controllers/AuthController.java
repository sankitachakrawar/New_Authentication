package com.example.demo.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.ser.loggerServiceInterface;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	  @PostMapping("/signin") 
	  public ResponseEntity<String> authenticateUser(@RequestBody CandidateDto candidate){ 
		  Authentication authentication = authenticationManager.authenticate(new
				  			UsernamePasswordAuthenticationToken( candidate.getEmail(),
				  							candidate.getPassword()));
	  
	  SecurityContextHolder.getContext().setAuthentication(authentication); return
	  new ResponseEntity<>("Candidate signed-in successfully!.", HttpStatus.OK); 
	  }
	  @GetMapping("/logout")
		public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token, HttpServletRequest request) throws Exception {

			loggerServiceInterface.logoutUser(token, ((CandidateDto) request.getAttribute("candidateData")).getC_id(), ((CandidateDto) request.getAttribute("candidateData")).getEmail());
			return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logoutSuccess"), HttpStatus.OK);

		}
	 
}
