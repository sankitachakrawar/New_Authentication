package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;


@EnableJpaRepositories
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

	Page<Job> findByTitleContainingIgnoreCaseOrderByIdDesc(String title,Pageable paging,Class<Job> jobDtos);

	Job findByTitleContainingIgnoreCase(String title);
}
