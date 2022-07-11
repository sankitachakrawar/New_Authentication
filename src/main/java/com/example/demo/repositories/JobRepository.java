package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Job;


@EnableJpaRepositories
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

	Page<Job> findByNameContainingIgnoreCaseOrderByIdDesc(String title,Pageable paging,Class<Job> jobDtos);
	Page<Job> findByOrderByIdDesc(Pageable paging,Class<Job> jobDtos);
	
	Page<Job> findByOrderByApply(Pageable paging,Class<Job> jobDto);
	Page<Job> findByNameContainingIgnoreCaseOrderByApply(String title,Pageable paging,Class<Job> jobDtos);
	
	Job findByNameContainingIgnoreCase(String name);
}
