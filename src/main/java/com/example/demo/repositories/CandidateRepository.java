package com.example.demo.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
		
	public Candidate findByEmail(String email);

	public Candidate findByEmailAndIsActiveTrue(String username) throws ResourceNotFoundException;

	public Candidate findByEmailAndIsActiveTrue(Long userId);

	public Optional<Candidate> findById(Long c_id);

	//Candidate findByEmailIdIgnoreCase(String email);



}
