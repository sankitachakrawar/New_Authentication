package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.RolePermissionEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.services.RolePermissionService;
import com.example.demo.utils.PaginationUsingFromTo;

@Service

public class RolePermissionServiceImpl implements RolePermissionService{

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public Page<RolePermissionEntity> getAllRolesPermissions(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return rolePermissionRepository.findByOrderByPk(paging, RolePermissionEntity.class);
		} else {
			
			return rolePermissionRepository.findByIsActiveContainingIgnoreCaseOrderByPk(search, paging, RolePermissionEntity.class);
			
				
		}
	}

	
	
}
