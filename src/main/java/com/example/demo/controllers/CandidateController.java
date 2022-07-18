package com.example.demo.controllers;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AssignJob;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.AuthRequest;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Recruiter;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.CandidateService;
import com.example.demo.services.EmailService;
import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.JwtUtil;



@RestController
@RequestMapping("/api")
public class CandidateController {

	
	  @SuppressWarnings("unused")
	@Autowired
	  private CandidateRepository candidateRepository;
	 

	@Autowired
	private CandidateService candidateService;

	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
		
	@Autowired
	private JwtUtil jwtUtil;
	
	//@Autowired
	private Recruiter recruiter;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/candidates") 
	public ResponseEntity<Candidate> applyJob(@Valid @RequestBody Candidate candidate){

		@SuppressWarnings("unused")
		Candidate savedcandidate=this.candidateService.addCandidate(candidate);
		final String url="Job applied successfully!!";
		emailService.sendSimpleMessage(candidate.getEmail(), "subject", url);
		//emailService.sendSimpleMessage(recruiter.getEmail(), "subject", url);
		return new ResponseEntity("Candidate Registered Successfully ",HttpStatus.OK);
	 }
	

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@PutMapping("/candidates/{id}")
	public ResponseEntity<Candidate> updateCandidate(@Valid @RequestBody Candidate candidate,@PathVariable Long id){
		
		Candidate updatedCandidate=this.candidateService.updateCandidate(candidate, id);
		
		return new ResponseEntity("candidate deleted successfully",HttpStatus.OK);	
		
	}
	
	
	@DeleteMapping("/candidates/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("id")Long id){
		this.candidateService.deleteCandidate(id);
		return new  ResponseEntity<>(Map.of("message","Candidate delete sucesssfully!!"),HttpStatus.OK);
	}
	
	@GetMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		return ResponseEntity.ok(this.candidateService.getAllCandidates());
		
	}
	@GetMapping("/candidates/{id}")
	public ResponseEntity<Candidate> getSingleCandidate(@PathVariable Long id){
		return ResponseEntity.ok(this.candidateService.getCandidateById(id));
		
	}
	
	
	@PutMapping("/changePass/{id}")
	public ResponseEntity<?> changePasswords(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		try {

			candidateService.changePassword(id, userBody, request);
			return new ResponseEntity<>(new SuccessResponseDto("password Updated", "password Updated succefully", null),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Access Denied"), HttpStatus.BAD_GATEWAY);

		}

	}

	  @PutMapping("/forgot-pass-confirm")
	  public ResponseEntity<?>
	  forgotPassword(@Valid @RequestBody ForgotPasswordDto userBody,HttpServletRequest request) throws ResourceNotFoundException {
	  
	  try {
	  System.out.println("password");
	  candidateService.forgotPasswordConfirm(userBody.getToken(), userBody, request);
	  return new ResponseEntity<>("password Updated",HttpStatus.OK);
	  
	  } catch (ResourceNotFoundException e) {
	  
	  return new ResponseEntity<>( "Access Denied", HttpStatus.BAD_GATEWAY);
	  
	 }
	  }
	  
	
	  
	  @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	        	System.out.println("token01");

	        	authenticationManager.authenticate(	    		 
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        	
	        	System.out.println("token1");
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getUsername());
	    }
	  
	  @Autowired
	  private JwtTokenUtil jwtTokenUtil;
	  
	  @SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
		@PostMapping("/login")
		public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) throws Exception {
			
		try {
			String email = candidate.getEmail();
			String password = candidate.getPassword();
			candidate = candidateService.loginCandidate(email, password);
			//final String token=jwtTokenUtil.generateTokenOnLogin(candidate.getEmail(),candidate.getPassword());
			final String url = "To confirm your account, please click here : " + "http://localhost:8088" + "/api/jobs" ;
			//Calendar calender = Calendar.getInstance();
			//calender.add(Calendar.MINUTE, 15);
			
			//candidateService.addCandidate(candidate);
			emailService.sendSimpleMessage(candidate.getEmail(), "subject", url);
			return new ResponseEntity(new SuccessResponseDto("Login successfully ,Check your registered email id","loginLinkMail",null), HttpStatus.OK);
			} catch(ResourceNotFoundException e) {
			return new  ResponseEntity(new ErrorResponseDto(e.getMessage(), "candidateNotFound"), HttpStatus.NOT_FOUND);
			}
	}
	 
	  
	  @PostMapping("/logout")
		 public ResponseEntity<?> logoutCandidate(@RequestBody Candidate candidate) throws Exception{
			String email=candidate.getEmail();
			String password=candidate.getPassword();
					candidate=candidateService.logout(email, password);
					return new ResponseEntity<>("Candidate logout successfully!!",HttpStatus.OK);
		} 
	
	  
		@PostMapping("/assignJob")
		public ResponseEntity<?> assignRole(@Valid @RequestBody AssignJob assignRole, HttpServletRequest request)
				throws Exception {
			try {
				String email = assignRole.getEmail();
				String name = assignRole.getName();
				System.out.println(email);
				System.out.println(name);
			

				candidateService.addJobToCandidate(email, name);
				return new ResponseEntity<>(new SuccessResponseDto("Role Assign to User", "roleAssignToUser", assignRole),
						HttpStatus.CREATED);

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ErrorResponseDto("Role Not Assign to User", "roleNotAssignToUser"),
						HttpStatus.NOT_ACCEPTABLE);
			}

		}
	  	 	  


}
 
	  
	  
	 
	  
/*
 * @PostMapping("/candidates") public ResponseEntity<?>
 * addCandidate(@Valid @RequestBody Candidate candidate){ Candidate
 * createdCandidate=this.candidateService.registerCandidate(candidate);
 * 
 * 
 * return new ResponseEntity("Candidate Register Successfully",HttpStatus.OK); }
 */  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	




