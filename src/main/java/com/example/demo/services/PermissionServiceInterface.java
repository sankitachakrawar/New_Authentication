package com.example.demo.services;

import com.example.demo.dto.PermissionRequestDto;
import com.example.demo.exceptions.ResourceNotFoundException;

public interface PermissionServiceInterface {

	void addPermission(PermissionRequestDto permissionBody);

	void editPermission(PermissionRequestDto permissionBody, Long permissionId) throws ResourceNotFoundException;

	void deletePermission(Long permissionId) throws ResourceNotFoundException;

}
