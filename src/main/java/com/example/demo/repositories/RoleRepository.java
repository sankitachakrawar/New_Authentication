package com.example.demo.repositories;

import java.util.ArrayList;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserRoleEntity;


@Repository
//@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	
	Page<IRoleDetailDto> findByRoleNameContainingIgnoreCaseOrderByIdDesc(String title,Pageable paging,Class<IRoleDetailDto> roleDtos);
	Page<IRoleDetailDto> findByOrderByIdDesc(Pageable paging,Class<IRoleDetailDto> roleDtos);
	
	
	
	RoleEntity findByRoleName(String roleName);
	
	
	RoleEntity findByRoleNameContainingIgnoreCase(String roleName);
	
	ArrayList<RoleIdListDto> findById(Long id, Class<RoleIdListDto> class1);
	
	
	//RoleEntity findByRoleName(String roleName);

	
	
}
