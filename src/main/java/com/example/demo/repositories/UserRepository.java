package com.example.demo.repositories;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.IUserListDto;
import com.example.demo.entities.UserEntity;

import java.util.*;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndIsActiveTrue(String email);

	Optional<UserEntity> findByIdAndIsActiveTrue(Long id);

	Page<IUserListDto> findByOrderByIdDesc(Pageable page, Class<IUserListDto> IUserListDto);
	
	Page<IUserListDto> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDesignationIdNameContainingIgnoreCaseOrderByIdDesc(String name, String email,String DesignationName, Pageable page, Class<IUserListDto> IUserListDto);
	
	List<IUserListDto> findByDateOfJoiningBetween(Date from,Date to);

	Optional<UserEntity> findByEmailContainingIgnoreCase(String email);
	// IUserRoleDetailDto findById(Long userId, Class<IUserRoleDetailDto> IUserRoleDetailDto);
}
