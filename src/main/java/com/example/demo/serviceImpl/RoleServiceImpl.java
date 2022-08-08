package com.example.demo.serviceImpl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IPermissionIdList;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.PermissionDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.RoleServiceInterface;
import com.example.demo.utils.PaginationUsingFromTo;



@Service
public class RoleServiceImpl implements RoleServiceInterface {

	public RoleServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Override
	public RoleDto addRole(@Valid RoleDto roleDto) {
	
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		//roleEntity.setCandidateId(roleDto.getCandidateId());
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
	

	@Autowired
	private PermissionRepository permissionRepository;
	@Override
	public void addPermissionToRole(String actionName, String roleName) {
		PermissionEntity permissionEntity=permissionRepository.findByActionNameContainingIgnoreCase(actionName);
		
		RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
		
		permissionEntity.getRoles().add(role);
		
		permissionRepository.save(permissionEntity);
		
	}


	@Override
	public RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException {
		
		RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		List<PermissionEntity> permissions=permissionRepository.findAll();
		ArrayList<IPermissionDto> rolesPermission = PermissionRepository.findByRoleId(id, IPermissionDto.class);
		
		for (PermissionEntity permission : permissions) {
				PermissionDto dto=new PermissionDto();
				dto.setActionName(permission.getActionName());
				dto.setDescription(permission.getDescription());
				 permissionRepository.findAll();
	
		}
		RolePermissionDto rolePermissionDto = new RolePermissionDto();
		rolePermissionDto.setId(roleEntity.getId());
		rolePermissionDto.setRoleName(roleEntity.getRoleName());
		rolePermissionDto.setDescription(roleEntity.getDescription());
		return rolePermissionDto;
	}
	

	@Override
	public PermissionEntity getPermissionById(Long id) throws ResourceNotFoundException {
		PermissionEntity entity=permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("permission","id",id));
		
		return entity;
	}


	@Override
	public void addRoleToCandidate(String email, String roleName) {
		Candidate candidate = candidateRepository.findByEmailContainingIgnoreCaseAndIsActiveTrue(email);
		System.out.println("candidate>>"+candidate);
		
		RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
		System.out.println("role>>"+role);
		candidate.getRoles().add(role);
		
		candidateRepository.save(candidate);
		System.out.println(candidate.getRoles().add(role));
		
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
