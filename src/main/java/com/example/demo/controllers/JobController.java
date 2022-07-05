package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.IJobListDto;
import com.example.demo.dto.JobDto;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Job;
import com.example.demo.services.JobService;

@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobService jobService;

	/*
	 * @PostMapping("/jobs") public String createJob(@Valid @RequestBody Job job) {
	 * 
	 * Job job1=jobRepository.save(job);
	 * 
	 * return ("Data added successfully!!"); }
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/jobs")
	public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
		jobService.createJob(jobDto, jobDto.getJ_id());

		return new ResponseEntity("Candidate applied to job successfully!!", HttpStatus.CREATED);

	}

	
	
	 
	  
	  
	  @GetMapping("/jobs/{j_id}")
	  public ResponseEntity<?> getSingleJob(@PathVariable ("j_id") int j_id){
		  return ResponseEntity.ok(this.jobService.getJobById(j_id));
		  
	  }
	  
	  
	  
	  @PutMapping("/jobs/{j_id}")
		public ResponseEntity<?> updateJob(@Valid @RequestBody JobDto jobDto,@PathVariable Integer j_id){
			
			JobDto updatedJob=this.jobService.updateJobDetails(jobDto, j_id);
			
			return new ResponseEntity<>("Data Updated Successfully!!",HttpStatus.OK);	
			
		}
	  
	  @DeleteMapping("/jobs/{j_id}")
		public ResponseEntity<?> deleteJobDetails(@PathVariable("j_id")Integer j_id){
			this.jobService.deleteJobDetails(j_id);
			return new  ResponseEntity<>("Job details delete sucesssfully!!",HttpStatus.OK);
		}
		
	  
	  
	  
		//Pagination
		  @GetMapping("/jobs")
		  public ResponseEntity<?>getAllJobDetails(@RequestParam(defaultValue = "") String search,
		  
				  @RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {
		  
		  Page<IJobListDto> jobs = jobService.getAllJobs(search, pageNo, size);
		  
		  if (jobs.getTotalElements() != 0) {
		  
		  return new ResponseEntity<>(new SuccessResponseDto("Success", "success", new
		  ListResponseDto(jobs.getContent(), jobs.getTotalElements())), HttpStatus.OK);
		  
		  }
		  
		  return new ResponseEntity<>(new ErrorResponseDto("Data Not Found","dataNotFound"), HttpStatus.NOT_FOUND);
		  
		  }
		 
	  
	  
	  
}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	/*
	 * @GetMapping("/jobs/{j_id}") public ResponseEntity<JobDto>
	 * getSingleJob(@PathVariable Integer j_id){ return
	 * ResponseEntity.ok(this.jobService.getJobById(j_id));
	 * 
	 * }
	 */
	 
	 /* 
	 * @GetMapping("/getinfo") public List<ViewResponse> getJoinInformation(){
	 * return jobRepository.getJoinInformation(); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */


		/*
		 * @GetMapping("/jobs/{pageNo}/{pageSize}") public List<Job>
		 * getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
		 * 
		 * return jobService.findPaginated(pageNo, pageSize); }
		 */