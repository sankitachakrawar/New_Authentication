package com.example.demo.services;

import java.util.List;


import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;


public interface CandidateService {

	
	CandidateDto createCandidate(CandidateDto candidate);
	
	CandidateDto updateCandidate(CandidateDto candidate,Integer c_id);
	
	CandidateDto getCandidateById(Integer c_id);
	
	List<CandidateDto> getAllCandidates();
	
	void deleteCandidate(Integer c_id);

	public Candidate loginCandidate(String email, String password) throws Exception;
	Candidate findByEmail(String email);


	
	
	
	
	
}
