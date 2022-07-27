package com.example.demo.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ApplyJobDto;
import com.example.demo.dto.IJobDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.repositories.ApplyJobRepository;
import com.example.demo.services.ApplyJobService;
import com.example.demo.utils.PaginationUsingFromTo;

@Service
public class ApplyJobServiceImpl implements ApplyJobService{

	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	 //apply to job
	@Override
	public void applyToJob(ApplyJobDto applyJobDto) {
		ApplyJob app=new ApplyJob();
		app.setCandidate_id(applyJobDto.getCandidate_id());
		app.setJob_id(applyJobDto.getJob_id());
		applyJobRepository.save(app);
		
	}

	@Override
	public List<ApplyJob> getAll() {
	
		return (List<ApplyJob>) this.applyJobRepository.findAll();
	}

//	//get all jobs with pagination
//	@Override
//	public Page<IJobDto> getAllJobs(String search, String from, String to) {
//		
//		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
//		if ((search == "") || (search == null) || (search.length() == 0)) {
//			
//			return applyJobRepository.findByOrderByIdDesc(paging, IJobDto.class);
//		} else {
//			
//			return applyJobRepository.findByNameContainingIgnoreCaseOrderByIdDesc(search, paging, IJobDto.class);
//			
//				
//		}
		

}
