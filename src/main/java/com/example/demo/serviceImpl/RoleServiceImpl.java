package com.example.demo.serviceImpl;


import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.IJobDto;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.IRoleListDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.entities.Job;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.RoleServiceInterface;
import com.example.demo.utils.PaginationUsingFromTo;


@Transactional
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleServiceInterface {

	public RoleServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private RoleRepository roleRepository;

	
	@Override
	public RoleDto addRole(@Valid RoleDto roleDto) {
	
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		roleEntity.setCandidateId(roleDto.getCandidateId());
		System.out.println(roleEntity);
		
		roleRepository.save(roleEntity);
		return roleDto;
	
	}
	
	
	public RoleEntity dtoToRole(RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
	
		
		
		return roleEntity;
	}

	public RoleDto roleToDto(RoleEntity roleEntity) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(roleEntity.getRoleName());
		roleDto.setDescription(roleEntity.getDescription());
		
		return roleDto;

	}
	
	
	
	



	@Override
	public RoleEntity updateRole(RoleDto roleData, Long id) throws ResourceNotFoundException {

		RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		roleEntity.setRoleName(roleData.getRoleName());
		roleEntity.setDescription(roleData.getDescription());
		roleRepository.save(roleEntity);
		return roleEntity;

	}

	@Override
	public void deleteRole(Long id) throws ResourceNotFoundException {

		RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		roleEntity.setIsActive(false);
		roleRepository.save(roleEntity);
		return;

	}

	@Override
	public RoleEntity getRoleById(Long id) throws ResourceNotFoundException {
		
		RoleEntity role=this.roleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hello","hello1", id));
		 return role;


	}


	@Override
	public Page<IRoleDetailDto> getAllRoles(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return roleRepository.findByOrderByIdDesc(paging, IRoleDetailDto.class);
		} else {
			
			return roleRepository.findByRoleNameContainingIgnoreCaseOrderByIdDesc(search, paging, IRoleDetailDto.class);
			
				
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public RoleEntity addRole(RoleEntity roleEntity)
//	{
//		RoleEntity roleEntity2=new RoleEntity();
//		roleEntity2.setRoleName(roleEntity.getRoleName());
//		roleEntity2.setDescription(roleEntity.getDescription());
//		roleRepository.save(roleEntity2);
//		return roleEntity;
//	}
	

}
