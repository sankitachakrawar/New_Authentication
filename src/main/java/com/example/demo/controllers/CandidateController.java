package com.example.demo.controllers;


import java.util.List;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidateService;


@RestController
@RequestMapping("/api")
public class CandidateController {

	/*
	 * @Autowired private CandidateRepository candidateRepository;
	 */

	@Autowired
	private CandidateService candidateService;
	
	
	
	
	@PostMapping("/candidates")
	public ResponseEntity<CandidateDto> createCandidate(@Valid @RequestBody CandidateDto candidateDto){
		
		CandidateDto createdCandidateDto=this.candidateService.createCandidate(candidateDto);
		return new ResponseEntity<>(createdCandidateDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/candidates/{c_id}")
	public ResponseEntity<CandidateDto> updateCandidate(@Valid @RequestBody CandidateDto candidateDto,@PathVariable Integer c_id){
		
		CandidateDto updatedCandidate=this.candidateService.updateCandidate(candidateDto, c_id);
		
		return ResponseEntity.ok(updatedCandidate);	
		
	}
	
	
	@DeleteMapping("/candidates/{c_id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("c_id")Integer c_id){
		this.candidateService.deleteCandidate(c_id);
		return new  ResponseEntity<>(Map.of("message","Candidate delete sucesssfully!!"),HttpStatus.OK);
	}
	
	@GetMapping("/candidates")
	public ResponseEntity<List<CandidateDto>> getAllCandidates(){
		return ResponseEntity.ok(this.candidateService.getAllCandidates());
		
	}
	@GetMapping("/candidates/{c_id}")
	public ResponseEntity<CandidateDto> getSingleCandidate(@PathVariable Integer c_id){
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
	
	
}
/*
 * @RequestMapping("send-mail") public String send(String email) {
 * 
 * 
 * candidate.setEmail("sankitachakrawar@gmail.com");
 * 
 * try { mailService.sendEmail(candidate); } catch (MailException mailException)
 * { System.out.println(mailException); } return
 * "Congratulations! Your mail has been send to the user."; }
 * 
 * }
 */
	
	
	
	

	
	/*
	 * @PostMapping("/postAll") public Candidate applyJob(@RequestBody JobDto
	 * jobDto) { return candidateRepository.save(jobDto.get);
	 * 
	 * }
	 * 
	 * @GetMapping("/getAll") public List<Candidate> findAllInfo(){ return
	 * candidateRepository.findAll(); }
	 */

