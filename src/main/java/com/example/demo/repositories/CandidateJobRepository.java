package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.AssignJob;

public interface CandidateJobRepository extends JpaRepository<AssignJob, String>{

	
	public AssignJob findByEmailAndNameContainingIgnoreCase(String email,String name);
}
