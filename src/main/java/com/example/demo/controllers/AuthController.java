package com.example.demo.controllers;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordRequestDto;
import com.example.demo.dto.JwtTokenResponse;
import com.example.demo.dto.LoggerDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.serviceImpl.CandidateServiceImpl;
import com.example.demo.services.*;
import com.example.demo.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {


	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private EmailService emailService;
	 
	@Autowired
	private ForgotPasswordServiceIntf forgotPasswordServiceIntf;
	
	@Autowired
	private LoggerServiceInterface loggerServiceInterface;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	 
	
	
	@SuppressWarnings("static-access")
	@PostMapping("/forgot-pass")
	public ResponseEntity<?> forgotPass(@Valid @RequestBody ForgotPasswordRequestDto forgotPassBody, HttpServletRequest request) throws Exception {

			try {
					;
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
				forgotPasswordServiceIntf.createForgotPasswordRequest(candidate.getId(), token, calender.getTime());
				//emailService.sendSimpleMessage(candidate.getEmail(), "Test", url);
				emailService.sendSimpleMessage(candidate.getEmail(),"subject" , url);
				return new ResponseEntity<>(new SuccessResponseDto("Password reset link sent on Registerd Email", "passwordRestLinkMail", null), HttpStatus.OK);

			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "candidateNotFound"), HttpStatus.NOT_FOUND);

			}

		}
	@Autowired
	private CandidateServiceImpl candidateServiceImpl;
	 
	 @SuppressWarnings("static-access")
	@PostMapping("/login")
		public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthRequestDto authenticationRequest) throws Exception, ResourceNotFoundException {

			try {

				Candidate candidate= candidateService.findByEmail(authenticationRequest.getEmail());

				if (!candidateServiceImpl.comparePassword(authenticationRequest.getPassword(), candidate.getPassword())) {

					return new ResponseEntity<>(new ErrorResponseDto("Invalid Credential", "invalidCreds"), HttpStatus.UNAUTHORIZED);
				}
				
				System.out.println("DATA>>"+candidate.getEmail());
				final String token = jwtTokenUtil.generateTokenOnLogin(candidate.getEmail(), candidate.getPassword());
				LoggerDto logger = new LoggerDto();
				logger.setToken(token);
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.HOUR, 1);
				logger.setExpireAt(calender.getTime());
				loggerServiceInterface.createLogger(logger,candidate);
				return new ResponseEntity<>(new SuccessResponseDto("Success", "success", new AuthResponseDto(token,candidate.getEmail(),candidate.getName(),candidate.getId())), HttpStatus.OK);
				
			} catch (ResourceNotFoundException e) {

				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

			}
	 }
	
	 @Autowired
	 private CustomUserDetailsService customUserDetailsService;
	
	 @GetMapping("/refresh_token")
	    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
	        String authToken = request.getHeader("Authorization");
	        final String token = authToken.substring(7);
	        String username = jwtTokenUtil.getUsernameFromToken(token);
	        customUserDetailsService.loadUserByUsername(username);

	        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
	            String refreshedToken = jwtTokenUtil.refreshToken(token);
	            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
	        } else {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
	 
	 @Autowired
	 private AuthService authService;
	 
	 @Autowired
	 private ApplyJobService applyJobService;
	 
	 @Autowired
	 private JobService jobService;
	 @PostMapping("/apply")
		public ResponseEntity<?> applyToJob(@Valid @RequestBody ApplyJobDto applyJobDto,HttpServletRequest request){
			try {
		 candidateService.findById(applyJobDto.getCandidate_id());
		
		 System.out.println("DATA>>"+applyJobDto.getCandidate_id());
		 
		 jobService.findById(applyJobDto.getJob_id());
		 
		 System.out.println("DATAAAAAAAAA>>"+applyJobDto.getJob_id());
		 
			applyJobService.applyToJob(applyJobDto);
			System.out.println("hello>>"+applyJobDto);
			
				return new ResponseEntity<>(new SuccessResponseDto("Job applied", "jobapplied", applyJobDto),
					HttpStatus.CREATED);
			}catch(Exception e){
				return new ResponseEntity<>(new ErrorResponseDto("Job not applied", "jobnotapplied"),
						HttpStatus.BAD_REQUEST);
			}
		}
	 
	 @GetMapping("/appliedJob")
		public ResponseEntity<List<ApplyJobDto>> getAll(){
		 
			List<ApplyJob> data=this.applyJobService.getAll();
			
			return new ResponseEntity(new SuccessResponseDto("Success", "success", data),HttpStatus.OK);
			
		}
	 
	 
	 
}	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//	  @GetMapping("/logout")  
//	    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
//	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
//	        if (auth != null){      
//	           new SecurityContextLogoutHandler().logout(request, response, auth);  
//	        }  
//	         return "redirect:/";  
//	     }  
//	
	 
	
	 








//	 @GetMapping("/logout")
//public ResponseEntity<?> logout(@RequestHeader("Authorization") String token, HttpServletRequest request) throws Exception {
//
//  System.out.println("&&&&@&&&&&&&&logout1");
//	loggerServiceInterface.logout(token);
//	System.out.println("logout2");
//	return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logoutSuccess"), HttpStatus.OK);
//
//}
