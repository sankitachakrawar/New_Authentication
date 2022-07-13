package com.example.demo.serviceImpl;

import java.util.Date;




import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Forgot_password_request;
import com.example.demo.entities.Job;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.ForgotPasswordRequestRepository;
import com.example.demo.repositories.JobRepository;
import com.example.demo.services.CandidateService;
import com.example.demo.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;


import com.example.demo.exceptions.*;

@Service
public class CandidateServiceImpl implements CandidateService {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private ForgotPasswordRequestRepository forgotPasswordRequestRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwttokenUtil;
	
	@Autowired
	private JobRepository jobRepository;

	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {

		// Candidate candidate =this.dtoToCandidate(candidateDto);
		Candidate candidate1 = new Candidate();
		candidate1.setName(candidate.getName());
		candidate1.setEmail(candidate.getEmail());
		candidate1.setPassword(passwordEncoder.encode(candidate.getPassword()));
		candidate1.setAddress(candidate.getAddress());
		candidate1.setUsername(candidate.getUsername());
		candidate1.setJobs(candidate.getJobs());
		candidate1.setRecruiters(candidate.getRecruiters());
		Candidate savedCandidate = this.candidateRepository.save(candidate1);
		return savedCandidate;

	}

	public Candidate dtoToCandidate(CandidateDto candidateDto) {
		Candidate candidate2 = new Candidate();
		candidate2.setC_id(candidateDto.getC_id());
		candidate2.setName(candidateDto.getName());
		candidate2.setEmail(candidateDto.getEmail());
		candidate2.setPassword(passwordEncoder.encode(candidateDto.getPassword()));
		candidate2.setAddress(candidateDto.getAddress());
		candidate2.setUsername(candidateDto.getUsername());

		return candidate2;
	}

	public CandidateDto candidateToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setC_id(candidate.getC_id());
		candidateDto.setName(candidate.getName());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setPassword(candidate.getPassword());
		candidateDto.setAddress(candidate.getAddress());
		candidateDto.setUsername(candidate.getUsername());
		return candidateDto;

	}

	@Override
	public Candidate updateCandidate(Candidate candidate1, Long c_id) {
		Candidate candidate = this.candidateRepository.findById(c_id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "c_id", c_id));
		candidate.setC_id(candidate1.getC_id());
		candidate.setName(candidate1.getName());
		candidate.setEmail(candidate1.getEmail());
		candidate.setPassword(candidate1.getPassword());
		candidate.setAddress(candidate1.getAddress());
		Candidate updatedCandidate = this.candidateRepository.save(candidate);
		// CandidateDto candidateDto2=this.candidateToDto(updatedCandidate);

		return updatedCandidate;

	}

	@Override
	public Candidate getCandidateById(Long c_id) {
		Candidate candidate = this.candidateRepository.findById(c_id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "c_id", c_id));
		return candidate;
	}

	@Override
	public List<Candidate> getAllCandidates() {
		List<Candidate> candidates = this.candidateRepository.findAll();

		// List<CandidateDto>
		// candidateDtos=candidates.stream().map(candidate->this.candidateToDto(candidate)).collect(Collectors.toList());
		return candidates;
	}

	@Override
	public void deleteCandidate(Long c_id) {
		Candidate candidate = this.candidateRepository.findById(c_id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "c_id", c_id));

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

		Candidate candidate = candidateRepository.findByEmail(email);
		if (candidate == null) {
			throw new Exception("You entered incorrect Email.");
		} else {
			if ((candidate.getEmail().equals(email)) && (candidate.getPassword().equals(password))) {
				return candidate;

			}
			throw new Exception("Invalid username and password!!!");

		}

	}

	
	  @Override public Candidate findByEmail(String email) { Candidate candidate =
	  candidateRepository.findByEmailContainingIgnoreCase(email); return candidate;
	  
	 }
	 

	@Override
	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request) {
		DecodedJWT jwt = JWT.decode(token); // give the current date
		Date CurrentDate = new Date(System.currentTimeMillis());

		// compare current date and expiredDate.
		if (CurrentDate.before(jwt.getExpiresAt())) {

			if (userBody.getPassword().equals(userBody.getConfirmpassword())) {

				// extract the email from token
				String username = null;
				String jwtToken = null; // get the token from payload
				jwtToken = userBody.getToken(); // get the email from token
				username = jwttokenUtil.getEmailFromToken(jwtToken); // check if that email exist in database
				// grab the the user entity if email exist in db.
				Candidate candidate = candidateRepository.findByEmail(username);
																				
																				
				
				candidate.setPassword(bcryptEncoder.encode(userBody.getPassword()));

			} else {

				throw new ResourceNotFoundException("password and confirm password must be a same");

			}

		} else {

			Forgot_password_request forgot_password_request = forgotPasswordRequestRepository
					.getByTokenOrderByIdDesc(token).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
			forgot_password_request.setIsActive(false);
			throw new ResourceNotFoundException("Reset the password time out");

		}
	}

	@Override
	public CandidateDto createCandidate(CandidateDto candidate) {
		
		return null;
	}

	
	
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return bcryptEncoder.matches(password, hashPassword);

	}

	@Override
	public void addJobToCandidate(String email, String name) {
		
		Candidate candidate = candidateRepository.findByEmailContainingIgnoreCaseAndIsActiveTrue(email);
		
		Job job = jobRepository.findByName(name);
	
		candidate.getJobs().add(job);
		
		
		
	}
	
	

	



	
}
	