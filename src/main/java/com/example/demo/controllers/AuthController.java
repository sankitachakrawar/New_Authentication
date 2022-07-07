package com.example.demo.controllers;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordRequestDto;
import com.example.demo.dto.LoggerDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.AuthRequest;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.*;


import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.JwtUtil;

@RestController
public class AuthController {


	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private EmailService emailService;
	 
	@Autowired
	private ForgotPasswordServiceIntf forgotPasswordServiceIntf;
	
	
	

	
	 @Autowired
	 private JwtTokenUtil jwtTokenUtil;
	 
	 @Autowired
	 private JwtUtil jwtUtil;
	
	 @PostMapping("/forgot-pass")
		public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotPasswordRequestDto forgotPassBody, HttpServletRequest request) throws Exception {

			try {
					System.out.println("token1");
					System.out.println(forgotPassBody.getEmail());
				Candidate candidate = candidateService.findByEmail(forgotPassBody.getEmail());
				System.out.println("candidate>>"+candidate);
				
				
				//final String token = jwtUtil.generateToken(candidate.getEmail());
				final String token = jwtTokenUtil.generateTokenOnForgotPass(candidate.getEmail());
				System.out.println("token2");
				
				//final String baseUrl = appSetting.getAllAppSetting().getSettings().get("http://localhost:8088");
				//final String passUrl = appSetting.getAllAppSetting().getSettings().get("/forgot-password");
				//final String url = "To confirm your account, please click here : " + baseUrl + passUrl + "?token=" + token;
				
				
				final String url = "To confirm your account, please click here : " + "http://localhost:8088" + "/forgot-pass-confirm" + "?token=" + token;
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.MINUTE, 10);
				forgotPasswordServiceIntf.createForgotPasswordRequest(candidate.getC_id(), token, calender.getTime());
				//emailService.sendSimpleMessage(candidate.getEmail(), "Test", url);
				emailService.sendSimpleMessage(candidate.getEmail(),"subject" , url);
				return new ResponseEntity<>(new SuccessResponseDto("Password reset link sent on Registerd Email", "passwordRestLinkMail", null), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

			}

		}
	 
	
	
	
	
	
	
	/*
	 * @SuppressWarnings("static-access")
	 * 
	 * @PostMapping("/login") public ResponseEntity<?>
	 * createAuthenticationToken(@Valid @RequestBody AuthRequestDto
	 * authenticationRequest) throws Exception, ResourceNotFoundException {
	 * 
	 * try {
	 * 
	 * Candidate candidate
	 * =candidateService.findByEmail(authenticationRequest.getEmail());
	 * 
	 * if (!authService.comparePassword(authenticationRequest.getPassword(),
	 * candidate.getPassword())) {
	 * 
	 * return new ResponseEntity<>(new ErrorResponseDto("Invalid Credential",
	 * "invalidCreds"), HttpStatus.UNAUTHORIZED); }
	 * 
	 * System.out.println("DATa"+candidate.getEmail());
	 * 
	 * final String token = jwtTokenUtil.generateToken(candidate);
	 * 
	 * //List<IPermissionDto> permissions =
	 * candidateService.getUserPermission(candidate.getC_id()); LoggerDto logger =
	 * new LoggerDto(); logger.setToken(token); Calendar calender =
	 * Calendar.getInstance(); calender.add(Calendar.HOUR_OF_DAY, 5);
	 * logger.setExpireAt(calender.getTime());
	 * loggerServiceInterface.createLogger(logger, candidate); return new
	 * ResponseEntity<>(new SuccessResponseDto("Success", "success", new
	 * AuthResponseDto(token,candidate.getEmail(),candidate.getName(),candidate.
	 * getC_id())), HttpStatus.OK);
	 * 
	 * } catch (ResourceNotFoundException e) {
	 * 
	 * return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),
	 * "userNotFound"), HttpStatus.NOT_FOUND);
	 * 
	 * }
	 * 
	 * }
	 */	 
	
	/*
	 * userServiceInterface.forgotPasswordConfirm(userBody.getToken(), userBody,
	 * request); return new ResponseEntity<>(new
	 * SuccessResponseDto("password Updated", "password Updated succefully", null),
	 * HttpStatus.OK);
	 * 
	 */
	 /*
		 * @PostMapping("/token") public ResponseEntity<String>
		 * authenticateUser(@RequestBody CandidateDto candidate){ Authentication
		 * authentication = authenticationManager.authenticate(new
		 * UsernamePasswordAuthenticationToken( candidate.getEmail(),
		 * candidate.getPassword()));
		 * 
		 * SecurityContextHolder.getContext().setAuthentication(authentication); return
		 * new ResponseEntity<>("Candidate signed-in successfully!.", HttpStatus.OK);
		 * 
		 * }
		 */
		/*
		 * @PostMapping("/logout") public ResponseEntity<?>
		 * logoutUser(@RequestHeader("Authorization") String token, HttpServletRequest
		 * request) throws Exception {
		 * 
		 * loggerServiceInterface.logoutUser(token, ((CandidateDto)
		 * request.getAttribute("candidateData")).getC_id(), ((CandidateDto)
		 * request.getAttribute("candidateData")).getEmail()); return new
		 * ResponseEntity<>(new ErrorResponseDto("Logout Successfully",
		 * "logoutSuccess"), HttpStatus.OK);
		 * 
		 * }
		 */
	
}
