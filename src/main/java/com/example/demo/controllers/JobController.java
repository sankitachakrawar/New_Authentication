package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.IJobDto;
import com.example.demo.dto.JobDto;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;
import com.example.demo.services.ApplyJobService;
import com.example.demo.services.CandidateService;
import com.example.demo.services.EmailService;
import com.example.demo.services.JobService;

@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobService jobService;
	 @Autowired
	private CandidateService candidateService;
			
	 @Autowired
	 private ApplyJobService applyJobService;
		  
	 @Autowired
	private EmailService emailService;

	@PreAuthorize("hasRole('createJob')")
	@PostMapping("/jobs")
	public ResponseEntity<?> createJob(@RequestBody JobDto jobDto) {
		
		jobService.createJob(jobDto);
			return new ResponseEntity<>("Job added successfully!!", HttpStatus.CREATED);
		
		}
		
	
	@PreAuthorize("hasRole('getSingleJob')")
	  @GetMapping("/jobs/{id}")
	  public ResponseEntity<?> getSingleJob(@PathVariable ("id") Long id){
		 JobDto job= jobService.getJobById(id);
		  return new ResponseEntity(new SuccessResponseDto("success","success",job),HttpStatus.OK);
		  
	  }
	  
	@PreAuthorize("hasRole('updateJob')")
	  @PutMapping("/jobs/{id}")
		public ResponseEntity<?> updateJob(@Valid @RequestBody Job job,@PathVariable Long id){
		
		this.jobService.updateJobDetails(job, id);
			return new ResponseEntity<>("Data Updated Successfully!!",HttpStatus.OK);	
			
		}
	  
	@PreAuthorize("hasRole('deleteJobDetails')")
	  @DeleteMapping("/jobs/{id}")
		public ResponseEntity<?> deleteJobDetails(@PathVariable("id")Long id){
			this.jobService.deleteJobDetails(id);
			return new  ResponseEntity<>("Job details delete sucesssfully!!",HttpStatus.OK);
		}
	
		  
		
		  //Pagination of job list
		  @PreAuthorize("hasRole('getAllJobs')")
		  @GetMapping("/jobs")
			public ResponseEntity<?> getAllJobs(@RequestParam(defaultValue = "") String search,
					@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {
				Page<IJobDto> jobs = jobService.getAllJobs(search, pageNo, size);
				if (jobs.getTotalElements() != 0) {
					return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
							new ListResponseDto(jobs.getContent(), jobs.getTotalElements())), HttpStatus.OK);
				}
				return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
			}
	  
	
		  
		 @PreAuthorize("hasRole('applyToJob')")
		  @PostMapping("/job/apply")
			public ResponseEntity<?> applyToJob(@Valid @RequestBody ApplyJobDto applyJobDto,HttpServletRequest request){
				try {
					Candidate candidate=candidateService.getCandidateById(applyJobDto.getCandidate_id());
					
					JobDto job=jobService.getJobById(applyJobDto.getJob_id());
						 
					applyJobService.applyToJob(applyJobDto);	
			 
					final String url = "Job applied successfully!!! ";
				
				emailService.sendSimpleMessage(candidate.getEmail(),"subject" , url);
				emailService.sendSimpleMessage(job.getRecruiterId().getEmail(),"subject" , url);
					return new ResponseEntity<>(new SuccessResponseDto("Job applied !Please chcek your registered email id!!", "jobapplied", applyJobDto),
						HttpStatus.CREATED);
				}catch(Exception e){
					return new ResponseEntity<>(new ErrorResponseDto("Job not applied", "jobnotapplied"),
							HttpStatus.BAD_REQUEST);
				}
			}
		
		
		 
		 
		 
		 
		 
		
}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
//@PreAuthorize("hasRole('getData')")
//@GetMapping("/appliedjob/{id}")
// public ResponseEntity<?> getData(@PathVariable ("id") Long id){
//	
//	 System.out.println("data>>"+this.jobService.getJobById(id));
//	 JobDto job=this.jobService.getJobById(id);
//	
//	 return new ResponseEntity(new SuccessResponseDto("success","success",job),HttpStatus.OK);
//	
//	  
// }