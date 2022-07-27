package com.example.demo.services;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.IRoleListDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface RoleServiceInterface {

	Page<IRoleListDto> getAllRoles(String search, String from, String to);

	//void addRole(RoleDto roleDto, Long id);

	//RoleEntity updateRole(RoleDto roleData, Long id, Long updateBy) throws ResourceNotFoundException;

	RoleEntity updateRole(RoleDto roleData,Long id)throws ResourceNotFoundException;
	
	void deleteRole(Long id) throws ResourceNotFoundException;

	IRoleDetailDto getRoleById(Long id) throws ResourceNotFoundException;

	RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException;

	//ArrayList<String> getPermissionByUserId(Long userId);

	void addPermissionsToRole(Long id, Long[] permissions) throws ResourceNotFoundException;

	RoleDto addRole(RoleDto roleDto);

	//RoleEntity addRole(RoleEntity roleEntity);

}
