package com.example.demo.services;

import java.util.Date;

import com.example.demo.entities.Forgot_password_request;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface ForgotPasswordServiceIntf {

	public Forgot_password_request getRequest(String token) throws ResourceNotFoundException;

	public void createForgotPasswordRequest(Long userId, String token, Date expireAt);

	public void markRequestAsSuccess(Long userId, String token) throws ResourceNotFoundException;

	public void markRequestAsExpire(Long userId, String token) throws ResourceNotFoundException;

}
