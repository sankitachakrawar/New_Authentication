package com.example.demo.services;
import java.util.List;
import com.example.demo.dto.JobDto;

public interface JobService {

	JobDto createJob(JobDto jobDto);
	List<JobDto> getAllJobs();
}
