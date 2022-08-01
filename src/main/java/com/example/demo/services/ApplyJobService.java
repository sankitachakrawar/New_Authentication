package com.example.demo.services;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.IJobDto;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface ApplyJobService {
	
	void applyToJob(ApplyJobDto applyJobDto);
	
	 ApplyJob getDataById(Long id);
	
	List<Candidate> getAllCandidates();
	
	//Optional<Candidate> getCandidateById(Long id);
	
	Optional<Job> getJobById(Long id); 
	
	//ApplyJobDto getJobAndCandidateById(Long id)throws ResourceNotFoundException;
}
