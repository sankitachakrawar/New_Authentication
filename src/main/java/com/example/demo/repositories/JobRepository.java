package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ViewResponse;
import com.example.demo.entities.Job;



public interface JobRepository extends JpaRepository<Job, Integer>{

	@Query("SELECT new com.example.demo.dto.ViewResponse(j.title, c.name) FROM Job j Join j.candidate c")
	public List<ViewResponse> getJoinInformation();
}
