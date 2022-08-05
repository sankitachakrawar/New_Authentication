package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.dto.IJobDto;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.JobRepository;
import com.example.demo.services.JobService;
import com.example.demo.utils.PaginationUsingFromTo;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	//add Job
	public void createJob(JobDto jobDto) {
		
		Job job=new Job();
		job.setName(jobDto.getName());
		job.setLocation(jobDto.getLocation());
		job.setDescription(jobDto.getDescription());
		job.setCTC(jobDto.getCTC());
		job.setRecruiterId(jobDto.getRecruiterId());
		System.out.println("Job!!!"+jobDto.getRecruiterId());
		jobRepository.save(job);
							
	}
	
	
	
	

	public Job dtoToJob(JobDto jobDto)
	{
	  Job job=new Job();
	  job.setName(jobDto.getName());
	  job.setLocation(jobDto.getLocation()); 
	  job.setDescription(jobDto.getDescription());
	  job.setCTC(jobDto.getCTC());
	  job.setRecruiterId(jobDto.getRecruiterId());
	  
	 return job; 
    
	} 
	public JobDto jobToDto(Job job) {
	  JobDto jobDto=new JobDto();
	  jobDto.setName(job.getName());
	  jobDto.setLocation(job.getLocation()); 
	  jobDto.setDescription(job.getDescription());
	  jobDto.setCTC(job.getCTC());
  jobDto.setRecruiterId(job.getRecruiterId());
	  return jobDto;
	  
	}
	
	//get job details by id
	@Override
	public JobDto getJobById(Long id) throws ResourceNotFoundException {
		
		Job job=this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hello","hello1", id));
		 return this.jobToDto(job);
	}
	
	
	//get all jobs with pagination
	@Override
	public Page<IJobDto> getAllJobs(String search, String from, String to) {
		
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return jobRepository.findByOrderByIdDesc(paging, IJobDto.class);
		} else {
			
			return jobRepository.findByNameContainingIgnoreCaseOrderByIdDesc(search, paging, IJobDto.class);
			
				
		}
		

	}

	//update job details
	@Override
	public Job updateJobDetails(Job job1, Long id) {
		Job jobs = this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("job", "id", id));
		jobs.setName(job1.getName());
		jobs.setLocation(job1.getLocation());
		jobs.setDescription(job1.getDescription());
		jobs.setCTC(job1.getCTC());
		Job updatedJob=this.jobRepository.save(jobs);
		//JobDto job2=this.jobToDto(updatedJob);			
		return updatedJob;
	
	}

	//delete job details
	@Override
	public void deleteJobDetails(Long id) {
		Job job=this.jobRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("job", "id", id));
		
		this.jobRepository.delete(job);
		
	}





	@Override
	public void findById(Long job_id) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		
	}




	
	
}
	
	
	
	
//get all job details
//@Override
//public List<JobDto> getAllJobs() {
//	
//	List<Job> jobs=this.jobRepository.findAll();
//	List<JobDto> jobDtos=jobs.stream().map(job->this.jobToDto(job)).collect(Collectors.toList());
//	return jobDtos;
//}
	
	
	
	
	
//@Override
//public void createJob(JobDto jobDto, Long id) {
//	Job job=new Job();
//	job.setName(jobDto.getName());
//	job.setLocation(jobDto.getLocation());
//	job.setPostTime(jobDto.getPostTime());
//	job.setApply(jobDto.getApply());
//	//job.setCandidate(jobDto.getCandidate());
//	
//	jobRepository.save(job);
	
 
	
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
	
	
	
	
//}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
 * @Override public List<Job> findPaginated(int pageNo, int pageSize) { Sort
 * idSort=Sort.by("postTime").descending();
 * 
 * Pageable paging=PageRequest.of(pageNo-1, pageSize,idSort); Page<Job>
 * pagedResult=jobRepository.findAll(paging);
 * 
 * return pagedResult.toList(); }
 */	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 /* 
	 * @Override public JobDto createJob(JobDto jobDto) { Job
	 * job=this.dtoToJob(jobDto); Job savedJobs=this.jobRepository.save(job);
	 * 
	 * return this.jobToDto(savedJobs); }
	 * 
	 * 
	 */ 



