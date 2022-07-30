package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.IPermissionDto;
import com.example.demo.entities.PermissionEntity;



public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	PermissionEntity findByActionNameContainingIgnoreCase(String actionName);

	static ArrayList<IPermissionDto> findByRoleId(Long id, Class<IPermissionDto> class1) {
		// TODO Auto-generated method stub
		return null;
	}
}
