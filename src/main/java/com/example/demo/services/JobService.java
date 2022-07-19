package com.example.demo.services;
import java.util.List;
import org.springframework.data.domain.Page;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;

public interface JobService {

	public void  createJob(JobDto jobDto,Long id,String token);
	
	 public List<JobDto> getAllJobs();
	
	public JobDto getJobById(Long id,String token);
	
	JobDto updateJobDetails(JobDto job, Long id);
	
	void deleteJobDetails(Long id);

	Page<Job> getAllJobs(String search, String from, String to);
	
	Page<Job> getAllJobsApplied(String search,String from,String to);

}
