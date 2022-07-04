package com.example.demo.serviceImpl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Forgot_password_request;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ForgotPasswordRequestRepository;
import com.example.demo.services.ForgotPasswordServiceIntf;


@Service("forgotPasswordRequestServiceImpl")
public class ForgotPasswordRequestServiceImpl implements ForgotPasswordServiceIntf {

	public ForgotPasswordRequestServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ForgotPasswordRequestRepository forgotPasswordRequestRepository;

	@Override
	public Forgot_password_request getRequest(String token) throws ResourceNotFoundException {

		Forgot_password_request request = forgotPasswordRequestRepository.getByTokenOrderByIdDesc(token).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
		return request;

	}

	@Override
	public void createForgotPasswordRequest(Long c_id, String token, Date expireAt) {

		Forgot_password_request newRequest = new Forgot_password_request();
		newRequest.setToken(token);
		newRequest.setC_id(c_id);
		forgotPasswordRequestRepository.save(newRequest);
	}

	@Override
	public void markRequestAsSuccess(Long c_id, String token) throws ResourceNotFoundException {

		Forgot_password_request request = forgotPasswordRequestRepository.getByTokenAndUserIdOrderByIdDesc(token, c_id).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
		request.setIsActive(false);
		request.setSuccessAt(new Date());
		request.setLinkUsedAt(new Date());
		forgotPasswordRequestRepository.save(request);

	}

	@Override
	public void markRequestAsExpire(Long c_id, String token) throws ResourceNotFoundException {

		Forgot_password_request request = forgotPasswordRequestRepository.getByTokenAndUserIdOrderByIdDesc(token, c_id).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
		request.setIsActive(false);
		forgotPasswordRequestRepository.save(request);

	}

}
