package com.example.demo.services;
import java.util.List;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;

public interface JobService {

	JobDto createJob(JobDto jobDto);
	List<JobDto> getAllJobs();
	
	
	List<Job> findPaginated(int pageNo,int pageSize);
	
	JobDto getJobById(Integer j_id);
	
	List<Job> findPaginatedByApply(int pageNo,int pageSize);
	
	
}
