package com.example.demo.repositories;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ApplyJobDto;
import com.example.demo.entities.Candidate;


@Repository
public interface AuthRepository extends JpaRepository<Candidate, Long> {

	Candidate findByEmail(String email);

	void save(@Valid ApplyJobDto applyJobDto);
	
	
}