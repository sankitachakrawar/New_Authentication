package com.example.demo.services;

import org.springframework.data.domain.Page;
import com.example.demo.dto.IJobDto;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface JobService {

	public void  createJob(JobDto jobDto);
	
	public JobDto getJobById(Long id) throws ResourceNotFoundException;
	
	Job updateJobDetails(Job job, Long id);
	
	void deleteJobDetails(Long id);

	Page<IJobDto> getAllJobs(String search, String from, String to);

	public void findById(Long job_id);

	public void findAll();
	
	
}
