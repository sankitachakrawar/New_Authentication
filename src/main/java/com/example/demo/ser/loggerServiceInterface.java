package com.example.demo.ser;


import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;

public interface loggerServiceInterface {

	LoggerEntity getLoggerDetail(String token);

	void createLogger(LoggerDto loggerDto, Candidate candidate);

	static void logoutUser(String token, int i, String email) {
		// TODO Auto-generated method stub
		
	}

}
