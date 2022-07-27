package com.example.demo.services;

import org.springframework.data.domain.Page;
import com.example.demo.dto.IJobDto;
import com.example.demo.dto.JobDto;

public interface JobService {

	public void  createJob(JobDto jobDto,Long id);
	
	public JobDto getJobById(Long id);
	
	JobDto updateJobDetails(JobDto job, Long id);
	
	void deleteJobDetails(Long id);

	Page<IJobDto> getAllJobs(String search, String from, String to);

	public void findById(Long job_id);
	
	
}
