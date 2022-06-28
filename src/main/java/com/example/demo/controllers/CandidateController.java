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
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.CandidateService;
import com.example.demo.services.UserService;


@RestController
@RequestMapping("/api")
public class CandidateController {

	/*
	 * @Autowired private CandidateRepository candidateRepository;
	 */

	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private UserService userService;
	/*
	 * @Autowired private EmailServiceIntf emailServiceIntf;
	 */
	
	@PostMapping("/candidates")
	public ResponseEntity<CandidateDto> createCandidate(@Valid @RequestBody CandidateDto candidateDto){
		
		CandidateDto createdCandidateDto=this.candidateService.createCandidate(candidateDto);
		return new ResponseEntity<>(createdCandidateDto,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/candidates/{c_id}")
	public ResponseEntity<CandidateDto> updateCandidate(@Valid @RequestBody CandidateDto candidateDto,@PathVariable Long c_id){
		
		CandidateDto updatedCandidate=this.candidateService.updateCandidate(candidateDto, c_id);
		
		return ResponseEntity.ok(updatedCandidate);	
		
	}
	
	
	@DeleteMapping("/candidates/{c_id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("c_id")Long c_id){
		this.candidateService.deleteCandidate(c_id);
		return new  ResponseEntity<>(Map.of("message","Candidate delete sucesssfully!!"),HttpStatus.OK);
	}
	
	@GetMapping("/candidates")
	public ResponseEntity<List<CandidateDto>> getAllCandidates(){
		return ResponseEntity.ok(this.candidateService.getAllCandidates());
		
	}
	@GetMapping("/candidates/{c_id}")
	public ResponseEntity<CandidateDto> getSingleCandidate(@PathVariable Long c_id){
		return ResponseEntity.ok(this.candidateService.getCandidateById(c_id));
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) throws Exception {
		String email = candidate.getEmail();
		String password = candidate.getPassword();
		candidate = candidateService.loginCandidate(email, password);
		//logger.info("Login Rest Controller Implementation : createUser() method");
		//return ResponseEntity.ok().body(candidate);
		
		return new  ResponseEntity(Map.of("message","Candidate login sucesssfully!!"),HttpStatus.OK);
	}
	@PutMapping("/changePass/{id}")
	public ResponseEntity<?> changePasswords(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		try {

			userService.changePassword(userId, userBody, request);
			return new ResponseEntity<>(new SuccessResponseDto("password Updated", "password Updated succefully", null),
					HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Access Denied"), HttpStatus.BAD_GATEWAY);

		}

	}
	  @PutMapping("/forgot-pass-confirm") public ResponseEntity<?>
	  forgotPassword(@Valid @RequestBody ForgotPasswordDto userBody,
	  HttpServletRequest request) throws ResourceNotFoundException {
	  
	  try {
	  
	  userService.forgotPasswordConfirm(userBody.getToken(), userBody, request);
	  return new ResponseEntity<>(new SuccessResponseDto("password Updated",
	 "password Updated succefully", null), HttpStatus.OK);
	  
	  } catch (ResourceNotFoundException e) {
	  
	  return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),
	  "Access Denied"), HttpStatus.BAD_GATEWAY);
	  
	 }
	  
 }
	
}



/*
 * @Autowired private CandidateRepository candidateRepository;
 * 
 * @Autowired private Candidate candidate;
 * 
 * @Autowired private ConfirmationTokenRepository confirmationTokenRepository;
 * 
 * @Autowired private EmailService emailService;
 * 
 * public void sendMail() { candidateRepository.save(candidate);
 * 
 * ConfirmationToken confirmationToken=new ConfirmationToken(candidate);
 * 
 * confirmationTokenRepository.save(confirmationToken);
 * 
 * SimpleMailMessage message=new SimpleMailMessage();
 * message.setTo(candidate.getEmail()); message.setSubject("Apply sucessfully");
 * message.setText("To confirm your account please click here:"
 * +"http://localhost:8088/confirm-account?token="+confirmationToken.
 * getConfirmationtoken());
 * 
 * emailService.sendMail(message); System.out.println("sucessfully!!!");
 * 
 * 
 * }
 * 
 * @RequestMapping(value="/confirm-account",method=
 * {RequestMethod.GET,RequestMethod.POST}) public void
 * confirmAccount(@RequestParam("token") String confirmationToken) {
 * 
 * ConfirmationToken
 * token=confirmationTokenRepository.findByConfirmationToken(confirmationToken);
 * 
 * if(token!=null) { Candidate
 * candidate=candidateRepository.findByEmailIdIgnoreCase(token.getCandidate().
 * getEmail()); candidate.setEnabled(true); candidateRepository.save(candidate);
 * System.out.println("account verified"); }else {
 * System.out.println("This link is invalid"); } }
 */
  

