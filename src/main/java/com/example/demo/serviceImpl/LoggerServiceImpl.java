package com.example.demo.serviceImpl;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.LoggerDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.LoggerEntity;
import com.example.demo.repositories.LoggerRepository;
import com.example.demo.services.LoggerServiceInterface;


@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerServiceInterface {

	public LoggerServiceImpl() {


	}

	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public void createLogger(LoggerDto loggerDto, Candidate candidate) {

		LoggerEntity logger = new LoggerEntity();
		logger.setId(candidate);
		logger.setToken(loggerDto.getToken());
		logger.setCreatedAt(loggerDto.getCreatedAt());
		logger.setExpireAt(loggerDto.getExpireAt());
		loggerRepository.save(logger);

	}

	@Transactional
	@Override
	public void logoutUser(String token) {

		final String token1 = token.substring(7);

		loggerRepository.removeByToken(token1);

	}

	@Override
	public List<LoggerEntity> getAllDetails() {
		
		return this.loggerRepository.findAll();
	}
	
	
	
	
	
	
	
	
	
	

}













