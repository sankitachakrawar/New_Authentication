package com.example.demo.services;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;

public interface CandidateService {
	
	Candidate updateCandidate(Candidate candidate, Long id);
	
	Candidate getCandidateById(Long id);
	
	List<Candidate> getAllCandidates();
	
	void deleteCandidate(Long id);

	Candidate findByEmail(String email);

	void changePassword(Long id, @Valid ChangePasswordDto userBody, HttpServletRequest request);
	
	Boolean comparePassword(String password, String hashPassword);
	
	//void addJobToCandidate(Candidate candidate);
	
	

	Candidate findById(Long candidate_id);

	void forgotPasswordConfirm(@NotNull(message = "token is Required*tokenRequired") String token,
			@Valid ForgotPasswordDto userBody, HttpServletRequest request);

	
	public void addCandidate(CandidateDto candidateDto);

	

	
	

	
	
	
	
}
