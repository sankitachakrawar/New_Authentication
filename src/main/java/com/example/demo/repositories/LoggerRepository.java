package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.LoggerEntity;


@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
	// @Query("SELECT c FROM LoggerEntity c WHERE c.token = :token")
	// LoggerEntity findByToken(@Param("token")String token);

	LoggerEntity findByToken(String token);

	void removeByToken(String token);

}
