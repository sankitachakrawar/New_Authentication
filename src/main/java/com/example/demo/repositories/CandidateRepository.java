package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.ResourceNotFoundException;

@Repository
@EnableJpaRepositories
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
		
	public Candidate findByEmail(String email);

	public Candidate findByEmailAndIsActiveTrue(String username) throws ResourceNotFoundException;

	public Candidate findByEmailAndIsActiveTrue(Long userId);

	public Optional<Candidate> findById(Long c_id);

	public Optional<Candidate> findByIdAndIsActiveTrue(Long c_id);

	public ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> class1);


	//public Optional<Candidate> findByEmailContainingIgnoreCase(String email);

	



}
