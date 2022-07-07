package com.example.demo.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.JobRepository;
import com.example.demo.services.JobService;
import com.example.demo.utils.PaginationUsingFromTo;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public void createJob(JobDto jobDto, Long id) {
		Job job=new Job();
		job.setTitle(jobDto.getTitle());
		job.setLocation(jobDto.getLocation());
		job.setPostTime(jobDto.getPostTime());
		job.setApply(jobDto.getApply());
		//job.setCandidate(jobDto.getCandidate());
		
		jobRepository.save(job);
		
	 
		
		/*
		 * String email=candidateDto.getEmail();
		 * if(candidateDto.getEmail().equals(email)) { final String uri=
		 * "http://localhost:8088/api/sendmail"; RestTemplate restTemplate=new
		 * RestTemplate(); String result=restTemplate.getForObject(uri, String.class);
		 * 
		 * return null;
		 * 
		 * 
		 * }else {
		 */
		
		
		
		
	}

	public Job dtoToJob(JobDto jobDto)
	{ Job job=new Job();
	  job.setId(jobDto.getId()); 
	  job.setTitle(jobDto.getTitle());
	  job.setLocation(jobDto.getLocation()); 
	  job.setPostTime(jobDto.getPostTime());
	  //job.setApply(jobDto.getApply()); job.setCandidate(job.getCandidate());
	 return job; 
	 } 
	public JobDto jobToDto(Job job) {
		JobDto jobDto=new JobDto();
	  jobDto.setId(job.getId()); 
	  jobDto.setTitle(job.getTitle());
	  jobDto.setLocation(job.getLocation()); 
	  jobDto.setApply(job.getApply());
	  jobDto.setPostTime(job.getPostTime());
	  //jobDto.setCandidate(job.getCandidate()); 
	  return jobDto;
	  
	  }
	
	
	@Override
	public JobDto getJobById(Long id) {
		
		Job job=this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("job", "id", id));
		 return this.jobToDto(job);
	}

	
	
	/*
	 * @Override public List<Job> findPaginated(int pageNo, int pageSize) { Sort
	 * idSort=Sort.by("postTime").descending();
	 * 
	 * Pageable paging=PageRequest.of(pageNo-1, pageSize,idSort); Page<Job>
	 * pagedResult=jobRepository.findAll(paging);
	 * 
	 * return pagedResult.toList(); }
	 */
	@Override
	public JobDto updateJobDetails(JobDto job, Long id) {
		Job jobs = this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("job", "id", id));
		jobs.setTitle(job.getTitle());
		jobs.setLocation(job.getLocation());
		jobs.setPostTime(job.getPostTime());
		jobs.setApply(job.getApply());
		//jobs.setCandidate(job.getCandidate());
			Job updatedJob=this.jobRepository.save(jobs);
			JobDto job2=this.jobToDto(updatedJob);
			
			return job2;
	
	}

	@Override
	public void deleteJobDetails(Long id) {
		Job job=this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("job", "id", id));
		
		this.jobRepository.delete(job);
		
	}

	@Override
	public List<JobDto> getAllJobs() {
		
		List<Job> jobs=this.jobRepository.findAll();
		List<JobDto> jobDtos=jobs.stream().map(job->this.jobToDto(job)).collect(Collectors.toList());
		return jobDtos;
	}

	@Override
	public Page<Job> getAllJobs(String search, String from, String to) {
		
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			//return jobRepository.findAll(paging);
			return jobRepository.findByOrderByIdDesc(paging, Job.class);
		} else {
			//search = StringUtils.trimLeadingWhitespace(search);
			//search = StringUtils.trimTrailingWhitespace(search);
			return jobRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(search, paging, Job.class);
			
			//return jobRepository.findByTitleContainingIgnoreCaseOrderByPostTimeDesc(search, paging, Job.class);
			
		}
		

	}
	@Autowired
	private CandidateRepository candidateRepository;
	//assignJob
	@Override
	public void addJobToCandidate(String email, String title) {
		Candidate candidate = candidateRepository.findByEmailContainingIgnoreCase(email);
		Job job = jobRepository.findByTitleContainingIgnoreCase(title);
		
		System.out.println("candidate>> "+candidate+"\nJob>> "+job);
		
		candidate.getJobs().add(job);
		
		System.out.println("candidate2>>"+candidate.getJobs().add(job));
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @Autowired private JobRepository jobRepository;
	 * 
	 * @Override public List<JobDto> getAllJobs() {
	 * 
	 * List<Job> job=this.jobRepository.findAll();
	 * 
	 * List<JobDto>
	 * jobDtos=job.stream().map(jobs->this.jobToDto(jobs)).collect(Collectors.toList
	 * ());
	 * 
	 * 
	 * return jobDtos;
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public Job dtoToJob(JobDto jobDto) { Job job=new Job();
	 * job.setJ_id(jobDto.getJ_id()); job.setTitle(jobDto.getTitle());
	 * job.setLocation(jobDto.getLocation()); job.setPostTime(jobDto.getPostTime());
	 * //job.setApply(jobDto.getApply()); job.setCandidate(job.getCandidate());
	 * return job; } public JobDto jobToDto(Job job) { JobDto jobDto=new JobDto();
	 * jobDto.setJ_id(job.getJ_id()); jobDto.setTitle(job.getTitle());
	 * jobDto.setLocation(job.getLocation()); //jobDto.setApply(job.getApply());
	 * jobDto.setPostTime(job.getPostTime());
	 * jobDto.setCandidate(job.getCandidate()); return jobDto;
	 * 
	 * }
	 * 
	 * @Override public JobDto createJob(JobDto jobDto) { Job
	 * job=this.dtoToJob(jobDto); Job savedJobs=this.jobRepository.save(job);
	 * 
	 * return this.jobToDto(savedJobs); }
	 * 
	 * 
	 * @Override public List<Job> findAppliedPaginated(int pageNo, int pageSize) {
	 * Sort applySort=Sort.by("apply").descending();
	 * 
	 * Pageable paging1=PageRequest.of(pageNo-1, pageSize,applySort); Page<Job>
	 * pagedResult1=jobRepository.findAll(paging1);
	 * 
	 * 
	 * return pagedResult1.toList(); }
	 * 
	 * 
	 * 
	 * 
	 * @Override public List<Job> findPaginated(int pageNo, int pageSize) {
	 * 
	 * 
	 * Sort idSort=Sort.by("postTime").descending();
	 * 
	 * Pageable paging=PageRequest.of(pageNo-1, pageSize,idSort); Page<Job>
	 * pagedResult=jobRepository.findAll(paging);
	 * 
	 * 
	 * return pagedResult.toList();
	 * 
	 * }
	 * 
	 * @Override public JobDto getJobById(Integer j_id) { Job job =
	 * this.jobRepository.findById(j_id).orElseThrow(()->new
	 * ResourceNotFoundException("job", "j_id", j_id)); return this.jobToDto(job);
	 * 
	 * }
	 * 
	 * @Override public List<Job> findPaginatedByApply(int pageNo, int pageSize) {
	 * 
	 * Sort sort=Sort.by("apply").descending(); Pageable
	 * paging1=PageRequest.of(pageNo-1, pageSize, sort); Page<Job>
	 * pageResult1=jobRepository.findAll(paging1);
	 * 
	 * return pageResult1.toList(); }
	 * 
	 * 
	 */


