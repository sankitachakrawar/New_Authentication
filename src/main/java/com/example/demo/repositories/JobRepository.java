package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.IJobDto;
import com.example.demo.entities.Job;

@EnableJpaRepositories
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

	Page<IJobDto> findByNameContainingIgnoreCaseOrderByIdDesc(String title,Pageable paging,Class<IJobDto> jobDtos);
	Page<IJobDto> findByOrderByIdDesc(Pageable paging,Class<IJobDto> jobDtos);
	
	Job findByName(String name);

	
	
}
