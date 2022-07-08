package com.example.demo.controllers;
import java.util.Calendar;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.AuthRequest;
import com.example.demo.entities.Candidate;
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	
	@PostMapping("/candidates") 
	public ResponseEntity<Candidate> addCandidate(@Valid @RequestBody Candidate candidate){
	  
	 
	@SuppressWarnings("unused")
	Candidate createdCandidate=this.candidateService.addCandidate(candidate);

	 // return new ResponseEntity(Map.of("message","Candidate created successfully!!"),HttpStatus.OK); 
	 return new ResponseEntity("Candidate Register Successfully",HttpStatus.OK);
	 }
	
	
	
	 @PostMapping("/sendmail")
	 public String sendMailmessage(@RequestBody Candidate candidate) {
		  @SuppressWarnings("unused")
		String email=candidate.getEmail();
		  String emailTo = null; 
		  String subject = null; 
		  String text = null;
		  		
	  emailService.sendMail(emailTo, subject, text, candidate);
	  return "email Send !!";
	  
	  
	  }
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@PutMapping("/candidates/{c_id}")
	public ResponseEntity<Candidate> updateCandidate(@Valid @RequestBody Candidate candidate,@PathVariable Long c_id){
		
		Candidate updatedCandidate=this.candidateService.updateCandidate(candidate, c_id);
		
		return new ResponseEntity("candidate deleted successfully",HttpStatus.OK);	
		
	}
	
	
	@DeleteMapping("/candidates/{c_id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("c_id")Long c_id){
		this.candidateService.deleteCandidate(c_id);
		return new  ResponseEntity<>(Map.of("message","Candidate delete sucesssfully!!"),HttpStatus.OK);
	}
	
	@GetMapping("/candidates")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		return ResponseEntity.ok(this.candidateService.getAllCandidates());
		
	}
	@GetMapping("/candidates/{c_id}")
	public ResponseEntity<Candidate> getSingleCandidate(@PathVariable Long c_id){
		return ResponseEntity.ok(this.candidateService.getCandidateById(c_id));
		
	}
	
	@PostMapping("/logout")
	 public ResponseEntity<?> logoutCandidate(@RequestBody Candidate candidate) throws Exception{
		String email=candidate.getEmail();
		String password=candidate.getPassword();
				candidate=candidateService.logout(email, password);
				return new ResponseEntity<>("Candidate logout successfully!!",HttpStatus.OK);
	} 
	
	@PutMapping("/changePass/{c_id}")
	public ResponseEntity<?> changePasswords(@PathVariable(value = "c_id") Long c_id,
			@Valid @RequestBody ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		try {

			candidateService.changePassword(c_id, userBody, request);
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
			final String token=jwtTokenUtil.generateTokenOnLogin(candidate.getEmail(),candidate.getPassword());
			final String url = "To confirm your account, please click here : " + "http://localhost:8088" + "/api/jobs/apply" + "?token=" + token;
			Calendar calender = Calendar.getInstance();
			calender.add(Calendar.MINUTE, 15);
			
			//candidateService.addCandidate(candidate);
			emailService.sendSimpleMessage(candidate.getEmail(), "subject", url);
			return new ResponseEntity(new SuccessResponseDto("Token send on your registered email id","loginLinkMail",null), HttpStatus.OK);
	  } catch(ResourceNotFoundException e) {
			return new  ResponseEntity(new ErrorResponseDto(e.getMessage(), "candidateNotFound"), HttpStatus.NOT_FOUND);
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
  

