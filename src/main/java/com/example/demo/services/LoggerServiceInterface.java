package com.example.demo.services;


import java.util.List;

import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;

public interface LoggerServiceInterface {

	void createLogger(LoggerDto loggerDto, Candidate candidate);

	void logoutUser(String token);

	List<LoggerEntity> getAllDetails();
	
}





