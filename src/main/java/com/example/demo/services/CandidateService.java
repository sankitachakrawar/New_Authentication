package com.example.demo.services;

import java.util.List;


import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;


public interface CandidateService {

	
	CandidateDto createCandidate(CandidateDto candidate);
	
	CandidateDto updateCandidate(CandidateDto candidate, Long c_id);
	
	CandidateDto getCandidateById(Long c_id);
	
	List<CandidateDto> getAllCandidates();
	
	void deleteCandidate(Long c_id);

	public Candidate loginCandidate(String email, String password) throws Exception;
	

	
}
