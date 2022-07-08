package com.example.demo.controllers;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordRequestDto;
import com.example.demo.dto.SuccessResponseDto;
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
	 
	 @SuppressWarnings("unused")
	@Autowired
	 private JwtUtil jwtUtil;
	
	 @SuppressWarnings("static-access")
	@PostMapping("/forgot-pass")
		public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotPasswordRequestDto forgotPassBody, HttpServletRequest request) throws Exception {

			try {
					//System.out.println("token1");
					//System.out.println(forgotPassBody.getEmail());
				Candidate candidate = candidateService.findByEmail(forgotPassBody.getEmail());
				System.out.println("candidate>>"+candidate);
				
				
				//final String token = jwtUtil.generateToken(candidate.getEmail());
				final String token = jwtTokenUtil.generateTokenOnForgotPass(candidate.getEmail());
				//System.out.println("token2");
				
				//final String baseUrl = appSetting.getAllAppSetting().getSettings().get("http://localhost:8088");
				//final String passUrl = appSetting.getAllAppSetting().getSettings().get("/forgot-password");
				//final String url = "To confirm your account, please click here : " + baseUrl + passUrl + "?token=" + token;
				
				
				final String url = "To confirm your account, please click here : " + "http://localhost:8088" + "/forgot-pass-confirm" + "?token=" + token;
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.MINUTE, 15);
				forgotPasswordServiceIntf.createForgotPasswordRequest(candidate.getC_id(), token, calender.getTime());
				//emailService.sendSimpleMessage(candidate.getEmail(), "Test", url);
				emailService.sendSimpleMessage(candidate.getEmail(),"subject" , url);
				return new ResponseEntity<>(new SuccessResponseDto("Password reset link sent on Registerd Email", "passwordRestLinkMail", null), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "candidateNotFound"), HttpStatus.NOT_FOUND);

			}

		}
	
}
