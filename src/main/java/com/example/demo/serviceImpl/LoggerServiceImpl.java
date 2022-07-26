package com.example.demo.serviceImpl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;
import com.example.demo.repositories.LoggerRepository;
import com.example.demo.services.LoggerServiceInterface;
import com.example.demo.utils.JwtTokenUtil;

@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerServiceInterface {

	public LoggerServiceImpl() {


	}


	@Autowired
	private LoggerRepository loggerRepository;



	@Override
	public LoggerEntity getLoggerDetail(String token) {

		LoggerEntity logger = null;

//		if (!cache.isKeyExist(token, token)) {
//
//			logger = loggerRepository.findByToken(token);
//			cache.addInCache(token, token, logger);
//
//		} else {
//
//			logger = (LoggerEntity) cache.getFromCache(token, token);
//
//		}

		return logger;// loggerRepository.findByToken(token);

	}

	@Override
	public void createLogger(LoggerDto loggerDto, Candidate candidate) {

		LoggerEntity logger = new LoggerEntity();
		logger.setId(candidate);
		logger.setToken(loggerDto.getToken());
		logger.setExpireAt(loggerDto.getExpireAt());
		loggerRepository.save(logger);

	}

	


	
	
	
	
	
	
	
	
	
	

}













