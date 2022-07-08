package com.example.demo.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import com.example.demo.dto.AssignJob;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.JobDto;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.Job;
import com.example.demo.services.EmailService;
import com.example.demo.services.JobService;

@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	private JobService jobService;
	@Autowired
	private EmailService emailService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/jobs/apply")
	public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
		jobService.createJob(jobDto, jobDto.getId());
				
		return new ResponseEntity("Applied to job successfully!!", HttpStatus.CREATED);

	}

	  @GetMapping("/jobs/{id}")
	  public ResponseEntity<?> getSingleJob(@PathVariable ("id") Long id){
		  return ResponseEntity.ok(this.jobService.getJobById(id));
		  
	  }
	  
	  
	  
	  @PutMapping("/jobs/{id}")
		public ResponseEntity<?> updateJob(@Valid @RequestBody JobDto jobDto,@PathVariable Long id){
			
			@SuppressWarnings("unused")
			JobDto updatedJob=this.jobService.updateJobDetails(jobDto, id);
			
			return new ResponseEntity<>("Data Updated Successfully!!",HttpStatus.OK);	
			
		}
	  
	  @DeleteMapping("/jobs/{id}")
		public ResponseEntity<?> deleteJobDetails(@PathVariable("id")Long id){
			this.jobService.deleteJobDetails(id);
			return new  ResponseEntity<>("Job details delete sucesssfully!!",HttpStatus.OK);
		}
	
		  
		  @GetMapping("/jobs")
			public ResponseEntity<List<JobDto>> getAllJobs(){
				return ResponseEntity.ok(this.jobService.getAllJobs());
				
			}
		  //Pagination of job list
		  @GetMapping()
			public ResponseEntity<?> getAllJobs(@RequestParam(defaultValue = "") String search,
					@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {
				Page<Job> users = jobService.getAllJobs(search, pageNo, size);
				if (users.getTotalElements() != 0) {
					return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
							new ListResponseDto(users.getContent(), users.getTotalElements())), HttpStatus.OK);
				}
				return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
			}
	  
		  //Job asssign to candidate
		  @PostMapping("/assignJob")
			public ResponseEntity<?> assignJob(@Valid @RequestBody AssignJob assignJob, HttpServletRequest request)
					throws Exception {
				try {
					String email = assignJob.getEmail();
					System.out.println(email);
				
					String title = assignJob.getTitle();
					System.out.println(title);
					
					final String url="Job applied successfully!!";
					emailService.sendSimpleMessage(assignJob.getEmail(), "subject", url);
					jobService.addJobToCandidate(email, title);
		

					return new ResponseEntity<>(new SuccessResponseDto("Job Assign to Candidate", "jobAssignToCandidate", assignJob),
							HttpStatus.CREATED);

				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(new ErrorResponseDto("Job Not Assign to Candidate", "jobNotAssignToCandidate"),
							HttpStatus.NOT_ACCEPTABLE);
				}

			}
		  
		  //Pagination of Applied jobs
		  @GetMapping("/jobs/applied")
			public ResponseEntity<?> getAllJobsApplied(@RequestParam(defaultValue = "") String search,
					@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {
				Page<Job> users = jobService.getAllJobsApplied(search, pageNo, size);
				if (users.getTotalElements() != 0) {
					return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
							new ListResponseDto(users.getContent(), users.getTotalElements())), HttpStatus.OK);
				}
				return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
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