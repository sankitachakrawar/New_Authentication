package com.example.demo.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JobDto;

import com.example.demo.entities.Job;
import com.example.demo.repositories.JobRepository;
import com.example.demo.services.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public List<JobDto> getAllJobs() {
		
		List<Job> job=this.jobRepository.findAll();
		
		List<JobDto> jobDtos=job.stream().map(jobs->this.jobToDto(jobs)).collect(Collectors.toList());
		
		
		return jobDtos;
		
		
		
	}
	
	public Job dtoToJob(JobDto jobDto) {
		Job job=new Job();
		job.setJ_id(jobDto.getJ_id());
		job.setTitle(jobDto.getTitle());
		job.setLocation(jobDto.getLocation());
		job.setCandidate(job.getCandidate());
		job.setApply(jobDto.getApply());
		return job;
	}
	public JobDto jobToDto(Job job) {
		JobDto jobDto=new JobDto();
		jobDto.setJ_id(job.getJ_id());
		jobDto.setTitle(job.getTitle());
		jobDto.setLocation(job.getLocation());
		jobDto.setApply(job.getApply());
		
		return jobDto;
		
	}

	@Override
	public JobDto createJob(JobDto jobDto) {
		Job job=this.dtoToJob(jobDto);
		Job savedJobs=this.jobRepository.save(job);
		return this.jobToDto(savedJobs);
	}

	
	@Override
	public List<Job> findPaginated(int pageNo, int pageSize) {
		
		
		Sort idSort=Sort.by("postTime").descending();
		
		Pageable paging=PageRequest.of(pageNo-1, pageSize,idSort);
		Page<Job> pagedResult=jobRepository.findAll(paging);
		
		
		return pagedResult.toList();
		
	}

}
