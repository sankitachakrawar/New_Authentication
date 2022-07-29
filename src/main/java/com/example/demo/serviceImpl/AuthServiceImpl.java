package com.example.demo.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Candidate;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.services.AuthService;


@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private CandidateRepository candidateRepository;
	
	//login
	@Override
	public Candidate loginCandidate(String email, String password) throws Exception {
		Candidate candidate = candidateRepository.getCandidateByEmail(email);
		if (candidate == null) {
			throw new Exception("You entered incorrect Email.");
		} else {
			if (candidate.getEmail().equals(email) && candidate.getPassword().equals(password)) {
				return candidate;
			}
			throw new Exception("You entered incorrect password.");
		}

	}

	     
	

}
