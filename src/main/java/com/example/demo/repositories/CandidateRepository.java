package com.example.demo.repositories;

import java.util.ArrayList;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.ICandidateDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.Candidate;


@Repository
@EnableJpaRepositories
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	@Query(value="SELECT * FROM candidate c WHERE c.email = :email", nativeQuery = true)
	public Candidate getCandidateByEmail(@Param("email") String email);

	public Candidate findByEmail(String email);

	Optional<Candidate> findByEmailContainingIgnoreCase(String email);
	
	//Candidate findByEmailContainingIgnoreCase(String email);
	Candidate findByEmailContainingIgnoreCaseAndIsActiveTrue(String search);
	
	public Candidate findByUsername(String username);

	public Optional<Candidate> findByIdAndIsActiveTrue(Long id);

	public ArrayList<RoleIdListDto> findById(Long id, Class<RoleIdListDto> class1);

	
	 @Query("SELECT c FROM Candidate c WHERE c.username = :username")
	    public Candidate getCandidateByUsername(@Param("username") String username);

	public Page<ICandidateDto> findByOrderById(Pageable paging, Class<ICandidateDto> class1);

	public Page<ICandidateDto> findByNameContainingIgnoreCaseOrderById(String search, Pageable paging,
			Class<ICandidateDto> class1);


	
	
}









//public Candidate findByEmailAndIsActiveTrue(Long userId);

	//public Optional<Candidate> findById(Long c_id);

	//public Optional<Candidate> findByIdAndIsActiveTrue(Long c_id);

	//public ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> class1);
	// Optional<Candidate> findByEmailContainingIgnoreCase(String email);

	//public Optional<Candidate> findByEmailContainingIgnoreCase(String email);

	
	// @Query(value = "SELECT * FROM Candidate where email in: email", nativeQuery = true)
	//	Optional<Candidate> findByEmailContainingIgnoreCaseOrderByNameAsc(List<String> email);