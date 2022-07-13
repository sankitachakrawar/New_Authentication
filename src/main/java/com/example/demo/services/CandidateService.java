package com.example.demo.services;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.entities.Candidate;


public interface CandidateService {

	Candidate addCandidate(Candidate candidate);
	CandidateDto createCandidate(CandidateDto candidate);
	
	//Candidate registerCandidate(Candidate candidate);
	
	//CandidateDto updateCandidate(CandidateDto candidate, Long c_id);
	Candidate updateCandidate(Candidate candidate, Long c_id);
	
	//CandidateDto getCandidateById(Long c_id);
	Candidate getCandidateById(Long c_id);
	
	
	
	List<Candidate> getAllCandidates();
	
	//List<CandidateDto> getAllCandidates();
	
	void deleteCandidate(Long c_id);

	public Candidate loginCandidate(String email, String password) throws Exception;

	public Candidate logout(String email,String password) throws Exception;

	Candidate findByEmail(String email);

	//void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request);

	//List<IPermissionDto> getUserPermission(Long c_id);

	//	void changePassword(Long c_id, @Valid ChangePasswordDto userBody, HttpServletRequest request);

	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request);
	
	Boolean comparePassword(String password, String hashPassword);
	

	void addJobToCandidate(String email,String name);
	
}
