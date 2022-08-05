package com.example.demo.controllers;
import java.util.List;




import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AssignRole;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.services.CandidateService;
import com.example.demo.services.RoleServiceInterface;


@RestController
@RequestMapping("/api")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;



	//@PreAuthorize("hasRole('updateCandidate')")
	@PutMapping("/candidates/{id}")
	public ResponseEntity<?> updateCandidate(@Valid @RequestBody Candidate candidate,@PathVariable Long id){
		
		candidateService.updateCandidate(candidate, id);
		
		return new ResponseEntity<>("candidate updated successfully",HttpStatus.OK);	
		
	}
	
	//@PreAuthorize("hasRole('deleteCandidate')")
	@DeleteMapping("/candidates/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("id")Long id){
		this.candidateService.deleteCandidate(id);
		return new  ResponseEntity<>(Map.of("message","Candidate delete sucesssfully!!"),HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('getAllCandidates')")
	@GetMapping("/candidates")
	public ResponseEntity<List<CandidateDto>> getAllCandidates(){
		List<Candidate> data=this.candidateService.getAllCandidates();
		
		return new ResponseEntity(new SuccessResponseDto("Success", "success", data),HttpStatus.OK);
		
	}
	
	//@PreAuthorize("hasRole('getSingleCandidate')")
	@GetMapping("/candidates/{id}")
	public ResponseEntity<Candidate> getSingleCandidate(@PathVariable Long id){
		
		return ResponseEntity.ok(this.candidateService.getCandidateById(id));
		
	}
	
	//@PreAuthorize("hasRole('changePasswords')")
	@PutMapping("/changePass/{id}")
	public ResponseEntity<?> changePasswords(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		try {

		candidateService.changePassword(id, userBody, request);
			return new ResponseEntity<>(new SuccessResponseDto("password Updated", "password Updated successfully", null),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Access Denied"), HttpStatus.BAD_GATEWAY);

		}

	}

		
	  @PutMapping("/forgot-pass-confirm")
	  public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto,HttpServletRequest request) throws ResourceNotFoundException {
	  
	  try {
	 
		  candidateService.forgotPasswordConfirm(forgotPasswordDto.getToken(), forgotPasswordDto, request);
	System.out.println("password>>"+forgotPasswordDto);
	  return new ResponseEntity<>("password Updated",HttpStatus.OK);
	  
	  } catch (ResourceNotFoundException e) {
	  
	  return new ResponseEntity<>( "Access Denied", HttpStatus.BAD_GATEWAY);
	  
	 }
	  }

	
	  
}
 
	  
	  
	 

	  
	  
 
	  
	  
	  
	  
	  

  
	  
	  
	  





//@PostMapping("/assignJob")
//public ResponseEntity<?> assignRole(@Valid @RequestBody AssignJob assignRole, HttpServletRequest request)
//		throws Exception {
//	try {
//		String email = assignRole.getEmail();
//		String name = assignRole.getName();
//		System.out.println(email);
//		System.out.println(name);
//	
//
//		candidateService.addJobToCandidate(email, name);
//		return new ResponseEntity<>(new SuccessResponseDto("Role Assign to User", "roleAssignToUser", assignRole),
//				HttpStatus.CREATED);
//
//	} catch (Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<>(new ErrorResponseDto("Role Not Assign to User", "roleNotAssignToUser"),
//				HttpStatus.NOT_ACCEPTABLE);
//	}
//
//}
