package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AssignPermission;
import com.example.demo.dto.AssignRole;
import com.example.demo.dto.PermissionRequestDto;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;

public interface PermissionServiceInterface {

	void addPermission(PermissionRequestDto permissionBody);

	void editPermission(PermissionRequestDto permissionBody, Long permissionId) throws ResourceNotFoundException;

	void deletePermission(Long permissionId) throws ResourceNotFoundException;

	List<PermissionEntity> getAllPermissions();
	
	
}
