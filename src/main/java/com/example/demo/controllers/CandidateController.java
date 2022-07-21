package com.example.demo.controllers;
import java.util.List;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AssignJob;
import com.example.demo.dto.AssignRole;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.AuthService;
import com.example.demo.services.CandidateService;

@RestController
@RequestMapping("/api")
public class CandidateController {

	
	  @SuppressWarnings("unused")
	@Autowired
	  private CandidateRepository candidateRepository;
	 

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private AuthService authService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/candidates") 
	public ResponseEntity<Candidate> registerCandidate(@Valid @RequestBody Candidate candidate){

		@SuppressWarnings("unused")
		Candidate savedcandidate=this.candidateService.addCandidate(candidate);

		return new ResponseEntity("Candidate Registered Successfully ",HttpStatus.OK);
	 }
	

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@PutMapping("/candidates/{id}")
	public ResponseEntity<Candidate> updateCandidate(@Valid @RequestBody Candidate candidate,@PathVariable Long id){
		
		Candidate updatedCandidate=this.candidateService.updateCandidate(candidate, id);
		
		return new ResponseEntity("candidate updated successfully",HttpStatus.OK);	
		
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
	  public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordDto userBody,HttpServletRequest request) throws ResourceNotFoundException {
	  
	  try {
	 
	 authService.forgotPasswordConfirm(userBody.getToken(), userBody, request);
	  return new ResponseEntity<>("password Updated",HttpStatus.OK);
	  
	  } catch (ResourceNotFoundException e) {
	  
	  return new ResponseEntity<>( "Access Denied", HttpStatus.BAD_GATEWAY);
	  
	 }
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
	  
	  
//@PostMapping("/logout")
//	 public ResponseEntity<?> logoutCandidate(@RequestBody Candidate candidate) throws Exception{
//		String email=candidate.getEmail();
//		String password=candidate.getPassword();
//		
//				candidate=candidateService.logout(email, password,token);
//				return new ResponseEntity<>("Candidate logout successfully!!",HttpStatus.OK);
//	}	  
	  
	  
	  
	  
	  
//@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
//	@PostMapping("/login")
//	public ResponseEntity<Candidate> loginCandidate(@RequestBody Candidate candidate) throws Exception {
//		
//	try {
//		System.out.println("login...............");
//		String email =candidate.getEmail() ;
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
//		System.out.println("login!!!!!!!!!!!!!!!");
//		String password = candidate.getPassword();
//		System.out.println("login $$$$$$$$$$$$$");
//		//if(candidate.equals(candidate.getEmail()) && encoder.matches(password, candidate.getPassword())) {
//			System.out.println("login1");
//			final String token = jwtTokenUtil.generateToken(candidate);
//			candidate.setToken(token);
//			Calendar calender = Calendar.getInstance();
//			calender.add(Calendar.MINUTE, 15);
//			System.out.println("login2");
//			candidate = candidateService.loginCandidate(email, password);
//			System.out.println("login3");
//			return new ResponseEntity(new SuccessResponseDto("Login successfully","loginSuccess",token), HttpStatus.OK);
//		}
//		//else {
//catch(Exception e) {
//		return new  ResponseEntity(new ErrorResponseDto("Candidate not found", "candidateNotFound"), HttpStatus.NOT_FOUND);
//		}
//}
  
	  
	  
	  
//	  @PostMapping("/authenticate")
//public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//    try {
//    	System.out.println("token01");
//
//    	authenticationManager.authenticate(	    		 
//        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//    	
//    	System.out.println("token1");
//    } catch (Exception ex) {
//        throw new Exception("inavalid username/password");
//    }
//    return jwtUtil.generateToken(authRequest.getUsername());
//}
	




