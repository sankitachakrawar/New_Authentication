package com.example.demo.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.IJobListDto;
import com.example.demo.dto.JobDto;
import com.example.demo.entities.Job;


@EnableJpaRepositories
@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{

	Page<IJobListDto> findByOrderByj_idDesc(Pageable paging, Class<IJobListDto> class1);

	Page<IJobListDto> findByOrderByApplyDesc(Pageable paging, Class<IJobListDto> class1);

	//Page<JobDto> findBy(Pageable paging, Class<JobDto> class1);

	//Page<JobDto> TitleContainingIgnoreCaseOrderByIdDesc(
			//String search, String search2, String search3, Pageable paging, Class<JobDto> class1);

	/*
	 * @Query("SELECT new com.example.demo.dto.ViewResponse(j.title, c.name) FROM Job j Join j.candidate c"
	 * ) public List<ViewResponse> getJoinInformation();
	 */
}
