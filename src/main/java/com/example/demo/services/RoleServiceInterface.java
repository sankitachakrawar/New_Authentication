package com.example.demo.services;
import org.springframework.data.domain.Page;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface RoleServiceInterface {

	//Page<IRoleListDto> getAllRoles(String search, String from, String to);

	RoleDto addRole(RoleDto roleDto);

	RoleEntity updateRole(RoleDto roleData,Long id)throws ResourceNotFoundException;
	
	void deleteRole(Long id) throws ResourceNotFoundException;

	RoleEntity getRoleById(Long id) throws ResourceNotFoundException;

	Page<IRoleDetailDto> getAllRoles(String search, String from, String to);
	
	 void addRoleToCandidate(String email, String roleName);
	
	void addPermissionToRole(String actionName, String roleName);
	
	RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException;
}
