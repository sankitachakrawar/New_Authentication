package com.example.demo.serviceImpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.ApplyJobRepository;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.ApplyJobService;


@Service
public class ApplyJobServiceImpl implements ApplyJobService{

	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	//@Autowired
	private CandidateDto candidateDto;
	 
	 //apply to job
	@Override
	public void applyToJob(ApplyJobDto applyJobDto) {
		ApplyJob app=new ApplyJob();
		app.setCandidate_id(applyJobDto.getCandidate_id());
		app.setJob_id(applyJobDto.getJob_id());
		applyJobRepository.save(app);
		
	}

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Override
	public List<Candidate> getAllCandidates() {
		Candidate candidate =(Candidate) this.candidateRepository.findAll();
		return (List<Candidate>) candidate;
	}

	@Override
	public ApplyJob getDataById(Long id) {
		
		ApplyJob job= this.applyJobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));
		return job;
	}

	


		

}












////get all jobs with pagination
//@Override
//public Page<IJobDto> getAllJobs(String search, String from, String to) {
//	
//	Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
//	if ((search == "") || (search == null) || (search.length() == 0)) {
//		
//		return applyJobRepository.findByOrderByIdDesc(paging, IJobDto.class);
//	} else {
//		
//		return applyJobRepository.findByNameContainingIgnoreCaseOrderByIdDesc(search, paging, IJobDto.class);
//		
//			
//	}