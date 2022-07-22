package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;
import com.example.demo.repositories.LoggerRepository;
import com.example.demo.services.LoggerServiceInterface;
import com.example.demo.utils.CacheOperation;

import ch.qos.logback.classic.Logger;

@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerServiceInterface {

	public LoggerServiceImpl() {


	}


	@Autowired
	private LoggerRepository loggerRepository;

	@Autowired
	private CacheOperation cache;

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
	
	//logout
			@Transactional
			@Override
			public void logout(String token) {
				
				final String userToken=token.substring(7);
//				cache.removeKeyFromCache(userToken);
//				cache.removeKeyFromCache(id+"");
//				cache.removeKeyFromCache(email);
				LoggerEntity log = loggerRepository.findByToken(userToken);
				log.setToken(null);
				
				loggerRepository.save(log);
			}

}
