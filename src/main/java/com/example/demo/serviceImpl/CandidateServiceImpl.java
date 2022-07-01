package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.CandidateService;
import com.example.demo.exceptions.*;

@Service
public class CandidateServiceImpl implements CandidateService{


    private PasswordEncoder passwordEncoder;

    @Autowired
	private CandidateRepository candidateRepository;
	
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		this.candidateRepository=candidateRepository;
		this.passwordEncoder=new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	public CandidateDto createCandidate(CandidateDto candidateDto) {
		
		Candidate candidate =this.dtoToCandidate(candidateDto);
		
		Candidate savedCandidate=this.candidateRepository.save(candidate);	
		return this.candidateToDto(savedCandidate);
	
		
	}

	public Candidate dtoToCandidate(CandidateDto candidateDto) {
		Candidate candidate2=new Candidate();
		candidate2.setC_id(candidateDto.getC_id());
		candidate2.setName(candidateDto.getName());
		candidate2.setEmail(candidateDto.getEmail());
		candidate2.setPassword(passwordEncoder.encode(candidateDto.getPassword()));
		candidate2.setAddress(candidateDto.getAddress());
		candidate2.setUsername(candidateDto.getUsername());
		return candidate2;
	}
	public CandidateDto candidateToDto(Candidate candidate) {
		CandidateDto candidateDto=new CandidateDto();
		candidateDto.setC_id(candidate.getC_id());
		candidateDto.setName(candidate.getName());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setPassword(candidate.getPassword());
		candidateDto.setAddress(candidate.getAddress());
		candidateDto.setUsername(candidate.getUsername());
		return candidateDto;
		
	}



	@Override
	public CandidateDto updateCandidate(CandidateDto candidateDto, Long c_id) {
		Candidate candidate = this.candidateRepository.findById(c_id).orElseThrow(()->new ResourceNotFoundException("candidate", "c_id", c_id));
		candidate.setC_id(candidateDto.getC_id());
		candidate.setName(candidateDto.getName());
		candidate.setEmail(candidateDto.getEmail());
		candidate.setPassword(candidateDto.getPassword());
		candidate.setAddress(candidateDto.getAddress());
			Candidate updatedCandidate=this.candidateRepository.save(candidate);
			CandidateDto candidateDto2=this.candidateToDto(updatedCandidate);
			
			return candidateDto2;
			
	}



	@Override
	public CandidateDto getCandidateById(Long c_id) {
		Candidate candidate = this.candidateRepository.findById(c_id).orElseThrow(()->new ResourceNotFoundException("candidate", "c_id", c_id));
		return this.candidateToDto(candidate);
	}



	@Override
	public List<CandidateDto> getAllCandidates() {
		List<Candidate> candidates=this.candidateRepository.findAll();
		
		List<CandidateDto> candidateDtos=candidates.stream().map(candidate->this.candidateToDto(candidate)).collect(Collectors.toList());
		return candidateDtos;
	}



	@Override
	public void deleteCandidate(Long c_id) {
		Candidate candidate=this.candidateRepository.findById(c_id).orElseThrow(()->new ResourceNotFoundException("candidate", "c_id", c_id));
		
		this.candidateRepository.delete(candidate);
	}



	@Override
	public Candidate loginCandidate(String email, String password) throws Exception {
		Candidate candidate = candidateRepository.findByEmail(email);
		if (candidate == null) {
			throw new Exception("You entered incorrect Email.");
		} else {
			if (candidate.getEmail().equals(email) && candidate.getPassword().equals(password)) {
				return candidate;
			}
			throw new Exception("You entered incorrect password.");
		}
		
	}



	
	@Override
	public Candidate logout(String email, String password) throws Exception {
		
		Candidate candidate=candidateRepository.findByEmail(email);
		if (candidate == null) {
			throw new Exception("You entered incorrect Email.");
		}else {
			if((candidate.getEmail().equals(email)) && (candidate.getPassword().equals(password))) {
				return candidate;
				
			}
			throw new Exception("Invalid username and password!!!");
			
		}
		
	}



	@Override
	public Candidate findByEmail(String email){
		Candidate candidate = candidateRepository.findByEmailAndIsActiveTrue(email);//.orElseThrow(() -> new ResourceNotFoundException("candidate Not Found"));
		return candidate;
		
	}



	






	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @Override public void changePassword(Long userId, ChangePasswordDto userBody,
	 * HttpServletRequest request) throws ResourceNotFoundException {
	 * 
	 * UserEntity userData =
	 * candidateRepository.findByIdAndIsActiveTrue(userId).orElseThrow(() -> new
	 * ResourceNotFoundException("User Not Found")); final String requestTokenHeader
	 * = request.getHeader("Authorization"); String username = null; String jwtToken
	 * = null; JsonObject jsonObj = null; jwtToken =
	 * requestTokenHeader.substring(7); username =
	 * jwtTokenUtil.getEmailFromToken(jwtToken); jsonObj =
	 * JsonParser.parseString(username).getAsJsonObject(); UserDataDto userDatas =
	 * new UserDataDto(); userDatas.setUserId((jsonObj.get("id").getAsLong()));
	 * 
	 * if (userDatas.getUserId() == userData.getId()) {
	 * 
	 * if (!bcryptEncoder.matches(userBody.getNewPassword(),
	 * userData.getPassword())) {
	 * 
	 * System.out.println("." + userBody.getNewPassword()); System.out.println("." +
	 * userBody.getPassword()); System.out.println("." +
	 * bcryptEncoder.encode((userBody.getPassword())));
	 * 
	 * if (bcryptEncoder.matches(userBody.getPassword(), userData.getPassword())) {
	 * 
	 * userData.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
	 * System.out.println("New Password" + userData.getPassword());
	 * 
	 * } else {
	 * 
	 * throw new ResourceNotFoundException("Please enter old password correct");
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * throw new
	 * ResourceNotFoundException("password must be differ from old password");
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * throw new ResourceNotFoundException("Access Denied");
	 * 
	 * }
	 * 
	 * }
	 */






	/*
	 * @Override public Candidate findByEmail(String email) {
	 * 
	 * return null; }
	 */



	

