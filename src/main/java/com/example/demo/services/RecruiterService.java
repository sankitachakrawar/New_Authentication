package com.example.demo.services;

import com.example.demo.entities.Recruiter;

public interface RecruiterService {

	Recruiter addRecruiter(Recruiter recruiter);

	Recruiter findByEmail(String email);
	
}
