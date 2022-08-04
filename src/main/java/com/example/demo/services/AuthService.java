package com.example.demo.services;

import com.example.demo.entities.Candidate;

public interface AuthService {

	//public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request);
	
	public Candidate loginCandidate(String email,String password)throws Exception;
	
	

	//void applyToJob(Candidate candidate);
}
