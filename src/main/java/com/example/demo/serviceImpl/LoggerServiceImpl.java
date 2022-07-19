package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;
import com.example.demo.repositories.LoggerRepository;
import com.example.demo.services.LoggerServiceInterface;
import com.example.demo.utils.CacheOperation;

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

		LoggerEntity logger;

		if (!cache.isKeyExist(token, token)) {

			logger = loggerRepository.findByToken(token);
			cache.addInCache(token, token, logger);

		} else {

			logger = (LoggerEntity) cache.getFromCache(token, token);

		}

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

//	@Transactional
//	@Override
//	public void logoutUser(String token, Long userId, String email) {
//
//		final String userToken = token.substring(7);
//		cache.removeKeyFromCache(userToken);
//		cache.removeKeyFromCache(userId + "permission");
//		cache.removeKeyFromCache(email);
//		loggerRepository.removeByToken(userToken);
//
//	}

}
