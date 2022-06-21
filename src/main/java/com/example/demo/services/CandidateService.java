package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CandidateDto;

public interface CandidateService {

	
	CandidateDto createCandidate(CandidateDto candidate);
	
	CandidateDto updateCandidate(CandidateDto candidate,Integer c_id);
	
	CandidateDto getCandidateById(Integer c_id);
	
	List<CandidateDto> getAllCandidates();
	
	void deleteCandidate(Integer c_id);
	
	
	
	
}
