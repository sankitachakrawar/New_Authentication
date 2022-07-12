package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Recruiter;
import com.example.demo.repositories.RecruiterRepository;
import com.example.demo.services.RecruiterService;

@Service
public class RecruiterServiceImpl implements RecruiterService{

	@Autowired
	private RecruiterRepository recruiterRepository;
	
	@Override
	public Recruiter addRecruiter(Recruiter recruiter) {
	
		Recruiter recruiter2=new Recruiter();
		recruiter2.setName(recruiter.getName());
		recruiter2.setEmail(recruiter.getEmail());
		
		Recruiter savedRecruiter=this.recruiterRepository.save(recruiter2);
		return savedRecruiter;  
	}

}
