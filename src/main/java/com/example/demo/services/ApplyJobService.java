package com.example.demo.services;



import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.IJobDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.entities.Candidate;

public interface ApplyJobService {
	
	void applyToJob(ApplyJobDto applyJobDto);
	
	List<ApplyJob> getAll();
	
	List<Candidate> getAllCandidates();
}
