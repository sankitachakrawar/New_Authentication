package com.example.demo.services;

import org.springframework.data.domain.Page;

import com.example.demo.entities.RolePermissionEntity;

public interface RolePermissionService {

	
	Page<RolePermissionEntity> getAllRolesPermissions(String search, String from, String to);
}
