package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.ApplyJob;


@Repository
public interface ApplyJobRepository extends CrudRepository<ApplyJob, Long>{

	//ArrayList<ApplyJobDto> findByJobId(Long id, Class<IApplyDto> class1);

	//Page<> findByOrderByIdDesc(Pageable paging, Class<> class1);

	//Page<> findByNameContainingIgnoreCaseOrderByIdDesc(String search, Pageable paging, Class<> class1);

	

}
