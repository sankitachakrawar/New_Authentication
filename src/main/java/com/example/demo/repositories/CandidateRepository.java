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

	Candidate findByEmailContainingIgnoreCase(String email);
	Candidate findByEmailContainingIgnoreCaseAndIsActiveTrue(String search);
	
	public Candidate findByUsername(String username);

	public Optional<Candidate> findByIdAndIsActiveTrue(Long id);

	public ArrayList<RoleIdListDto> findById(Long id, Class<RoleIdListDto> class1);

	public void removeByToken(String userToken);

	
	

	


}









//public Candidate findByEmailAndIsActiveTrue(Long userId);

	//public Optional<Candidate> findById(Long c_id);

	//public Optional<Candidate> findByIdAndIsActiveTrue(Long c_id);

	//public ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> class1);
	// Optional<Candidate> findByEmailContainingIgnoreCase(String email);

	//public Optional<Candidate> findByEmailContainingIgnoreCase(String email);

	
	// @Query(value = "SELECT * FROM Candidate where email in: email", nativeQuery = true)
	//	Optional<Candidate> findByEmailContainingIgnoreCaseOrderByNameAsc(List<String> email);