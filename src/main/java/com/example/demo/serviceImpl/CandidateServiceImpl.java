package com.example.demo.serviceImpl;


import java.io.IOException;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.*;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.JobRepository;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.services.CandidateService;
import com.example.demo.utils.JwtTokenUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class CandidateServiceImpl implements CandidateService {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private CandidateRepository candidateRepository;

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
	public void addCandidate(Candidate candidate) {

		String email = candidate.getEmail();
		candidate.setEmail(email.toLowerCase());
		String password=passwordEncoder.encode(candidate.getPassword());
		candidate.setPassword(password);	
		candidate.setActive(true);		
		candidateRepository.save(candidate);
		
	}
	
	

	public Candidate dtoToCandidate(CandidateDto candidateDto) {
		Candidate candidate2 = new Candidate();
		candidate2.setId(candidateDto.getCandidateId());
		candidate2.setName(candidateDto.getName());
		candidate2.setEmail(candidateDto.getEmail());
		candidate2.setPassword(passwordEncoder.encode(candidateDto.getPassword()));
		candidate2.setAddress(candidateDto.getAddress());
		candidate2.setUsername(candidateDto.getUsername());		
		return candidate2;
	}

	public CandidateDto candidateToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setCandidateId(candidate.getId());
		candidateDto.setName(candidate.getName());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setPassword(candidate.getPassword());
		candidateDto.setAddress(candidate.getAddress());
		candidateDto.setUsername(candidate.getUsername());
		return candidateDto;

	}

	@Override
	public Candidate updateCandidate(Candidate candidate1, Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));
		candidate.setId(candidate1.getId());
		candidate.setName(candidate1.getName());
		candidate.setEmail(candidate1.getEmail());
		candidate.setPassword(candidate1.getPassword());
		candidate.setAddress(candidate1.getAddress());
		Candidate updatedCandidate = this.candidateRepository.save(candidate);
		// CandidateDto candidateDto2=this.candidateToDto(updatedCandidate);

		return updatedCandidate;

	}

	@Override
	public Candidate getCandidateById(Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));
		
		return candidate;
	}

	@Override
	public List<Candidate> getAllCandidates() {
		
		return this.candidateRepository.findAll();
	}

	@Override
	public void deleteCandidate(Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));

		this.candidateRepository.deleteById(id);
	}

	
	@Override
	public Candidate findByEmail(String email) {
		Candidate candidate = candidateRepository.findByEmail(email);
		return candidate;

	}

	@Override
	public CandidateDto createCandidate(CandidateDto candidate) {

		return null;
	}


//	@Override
//	public void addJobToCandidate(String email, String name) {
//
//		Candidate candidate = candidateRepository.findByEmailContainingIgnoreCaseAndIsActiveTrue(email);
//
//		Job job = jobRepository.findByName(name);
//
////		candidate.getJobs().add(job);
//
//	}
	
	
	
	@Override
	public void changePassword(Long id, ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		Candidate candidate = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate Not Found"));
		final String requestTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken = null;
		//JsonObject jsonObj = null;
		jwtToken = requestTokenHeader.substring(7);
		email = jwttokenUtil.getEmailFromToken(jwtToken);
		System.out.println("objcet>>"+email);
		//jsonObj = JsonParser.parseString(email).getAsJsonObject();
		CandidateDto candidatedata = new CandidateDto();
		candidatedata.setEmail(email.toString());
		System.out.println("data>>>"+candidatedata);
		
		if (candidatedata.getEmail()!= candidate.getEmail()) {
			System.out.println("dto>>"+candidatedata.getEmail()+"entity>>"+candidate.getEmail());
			if (!bcryptEncoder.matches(userBody.getNewPassword(), candidate.getPassword())) {
 
				if (bcryptEncoder.matches(userBody.getPassword(), candidate.getPassword())) {

					candidate.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
					if (userBody.getNewPassword().equals(userBody.getConfPassword())) {
						candidate.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
					} else {
						throw new ResourceNotFoundException("new password and confirm password must be same");
					}

				} else {

					throw new ResourceNotFoundException("Please enter old password correct");
				}

			} else {
				throw new ResourceNotFoundException("password must be differ from old password");
			}

		} else {
			throw new ResourceNotFoundException("Access Denied");
		}
		
	}


	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	
	  @Override public List<IPermissionDto> getCandidatePermission(Long id) throws
	  IOException {
	  
	  ArrayList<RoleIdListDto> roleIds=candidateRepository.findById(id,RoleIdListDto.class);
	  ArrayList<Long> roles = new ArrayList<>();
	  
	  for (int i = 0; i < roleIds.size(); i++) {
	  
	  roles.add(roleIds.get(i).getPkRoleId());
	  
	  }
	  
	  return rolePermissionRepository.findByPkRoleIdIn(roles,IPermissionDto.class);
	 
	 }
	
	  
	  @Override
		public Boolean comparePassword(String password, String hashPassword) {

			return bcryptEncoder.matches(password, hashPassword);

		}

	@Override
	public Candidate findById(Long candidate_id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	

	

	
	  
}







