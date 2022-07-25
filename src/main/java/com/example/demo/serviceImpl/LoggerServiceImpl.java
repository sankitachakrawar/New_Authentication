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

	@Override
	public void logoutCandidate(String token) {
		final String token1=token.substring(7);
		LoggerEntity log = loggerRepository.findByToken(token1);
		log.setToken(null);
	
		loggerRepository.save(log);
		
	}
//	
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//	
//	
//	  @Override
//	    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//	                                Authentication authentication) throws IOException {
//	  
//	        String token = this.jwtTokenUtil.extractToken(request);
//	        if (token != null) {
//	            this.loggerRepository.deleteToken(token);
//	        }
//	        response.setStatus(HttpServletResponse.SC_OK);
//	        response.getWriter().flush();
//	    }

}













////logout
//@Transactional
//@Override
//public void logout(String token) {
//	
//	final String userToken=token.substring(7);
////	cache.removeKeyFromCache(userToken);
////	cache.removeKeyFromCache(id+"");
////	cache.removeKeyFromCache(email);
//	LoggerEntity log = loggerRepository.findByToken(userToken);
//	log.setToken(null);
//	
//	loggerRepository.save(log);
//}