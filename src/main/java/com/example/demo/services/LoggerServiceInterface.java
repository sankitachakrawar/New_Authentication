package com.example.demo.services;

import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;

public interface LoggerServiceInterface {

	LoggerEntity getLoggerDetail(String token);

	void createLogger(LoggerDto loggerDto, Candidate candidate);

	void logoutUser(String token, Long c_id, String email);

}