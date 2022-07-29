package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.IJobDto;
import com.example.demo.entities.ApplyJob;
import com.example.demo.entities.Candidate;

@Repository
public interface ApplyJobRepository extends CrudRepository<ApplyJob, Long>{

	//Page<> findByOrderByIdDesc(Pageable paging, Class<> class1);

	//Page<> findByNameContainingIgnoreCaseOrderByIdDesc(String search, Pageable paging, Class<> class1);

	

}
