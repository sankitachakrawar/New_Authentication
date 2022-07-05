package com.example.demo.services;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.IJobListDto;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;

public interface JobService {

	public void  createJob(JobDto jobDto,Integer j_id);
	//List<JobDto> getAllJobs();
	
	Page<IJobListDto> getAllJobs(String search, String from, String to);
	
	//List<Job> findPaginated(int pageNo,int pageSize);
	
	public JobDto getJobById(Integer j_id);
	
	//List<Job> findPaginatedByApply(int pageNo,int pageSize);
	
	JobDto updateJobDetails(JobDto job, Integer j_id);
	
	void deleteJobDetails(Integer j_id);

	//Page<JobDto> getAllJobs(String search, String from, String to);
}
