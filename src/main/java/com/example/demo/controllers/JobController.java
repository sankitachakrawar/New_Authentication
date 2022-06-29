package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;
import com.example.demo.services.JobService;

@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobService jobService;
	
	
	@PostMapping("/jobs")
	public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto){
	JobDto createdJobDto=this.jobService.createJob(jobDto);
	
	return new ResponseEntity<>(createdJobDto,HttpStatus.CREATED);
	
	}
	@GetMapping("/jobs")
	public ResponseEntity<List<JobDto>> getAllJobs(){
		return ResponseEntity.ok(this.jobService.getAllJobs());
		
	}
	
	  @GetMapping("/jobs/{pageNo}/{pageSize}")
	  public List<Job> getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
	  
	  
	 return jobService.findPaginated(pageNo, pageSize); }
	 
	
	/*
	 * @GetMapping("/jobs/{pageNo}/{pageSize}") public List<Job>
	 * getPaginated(@PathVariable int pageNo, @PathVariable int pageSize){
	 * 
	 * 
	 * return jobService.findAppliedPaginated(pageNo, pageSize);
	 * 
	 * }
	 */
	
}
