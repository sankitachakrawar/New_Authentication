package com.example.demo.serviceImpl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.PermissionRequestDto;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.services.PermissionServiceInterface;



@Service
public class PermissionServiceImpl implements PermissionServiceInterface {

	public PermissionServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private EntityRepository entityRepository;

	
	@SuppressWarnings("deprecation")
	@Override
	public void addPermission(PermissionRequestDto permissionBody) {

		PermissionEntity newPermission = new PermissionEntity();
		newPermission.setActionName(permissionBody.getActionName());
		newPermission.setBaseUrl(permissionBody.getBaseUrl());
		newPermission.setDescription(permissionBody.getDescription());
		newPermission.setMethod(permissionBody.getMethod());
		newPermission.setPath(permissionBody.getPath());
		newPermission.setEntityId(entityRepository.getById(permissionBody.getEntityId()));
		permissionRepository.save(newPermission);
		return;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void editPermission(PermissionRequestDto permissionBody, Long permissionId) throws ResourceNotFoundException {

		PermissionEntity permissionData = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Permission Not Found"));
		permissionData.setActionName(permissionBody.getActionName());
		permissionData.setBaseUrl(permissionBody.getBaseUrl());
		permissionData.setDescription(permissionBody.getDescription());
		permissionData.setMethod(permissionBody.getMethod());
		permissionData.setPath(permissionBody.getPath());
		permissionRepository.save(permissionData);
		return;

	}

	@Override
	public void deletePermission(Long permissionId) throws ResourceNotFoundException {

		PermissionEntity permissionData = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Permission Not Found"));
		permissionData.setIsActive(false);
		permissionRepository.save(permissionData);
		return;

	}

	@Override
	public List<PermissionEntity> getAllPermissions() {
		
		return permissionRepository.findAll();
	}

	
		
	

}
