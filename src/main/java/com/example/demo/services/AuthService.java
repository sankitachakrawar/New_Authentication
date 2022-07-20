package com.example.demo.services;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.entities.Candidate;

public interface AuthService {

	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request);
	
	public Candidate loginCandidate(String email,String password)throws Exception;
	
	
	
}
