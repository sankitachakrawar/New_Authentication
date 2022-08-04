package com.example.demo.serviceImpl;


import java.util.ArrayList;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.example.demo.entities.RolePermissionEntity;
import com.example.demo.entities.RolePermissionId;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.RoleServiceInterface;
import com.example.demo.utils.PaginationUsingFromTo;



@Service
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
	private RolePermissionRepository rolePermissionRepository;
	
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	
	@Override
	public void addPermissionsToRole(Long id, Long[] permissions) throws ResourceNotFoundException {

		roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		rolePermissionRepository.deleteByPkRoleId(id);
		int outerCount = permissions.length / batchSize;
		ArrayList<RolePermissionEntity> rolePermissionEntities = new ArrayList<>();

		for (int i = 0; i <= outerCount; i++) {

			for (int j = i * batchSize; j < (outerCount == i ? permissions.length : (i + 1) * batchSize); j++) {
				
				RolePermissionEntity rpe = new RolePermissionEntity();
				RoleEntity re = new RoleEntity();
				re.setId(id);
				PermissionEntity pe = new PermissionEntity();
				pe.setId(permissions[j]);
				RolePermissionId rpi = new RolePermissionId(re, pe);
				rpe.setPk(rpi);
				rolePermissionEntities.add(rpe);

			}

			rolePermissionRepository.saveAll(rolePermissionEntities);

		}

		return;

	}
	
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public ArrayList<String> getPermissionByUserId(Long userId) {

		ArrayList<RoleIdListDto> roleIds = userRoleRepository.findByPkUserId(userId, RoleIdListDto.class);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPkRoleId());

		}

		List<IPermissionIdList> rolesPermission = rolePermissionRepository.findPkPermissionByPkRoleIdIn(roles, IPermissionIdList.class);
		ArrayList<String> permissions = new ArrayList<>();

		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@Autowired
	private PermissionRepository permissionRepository;
//	@Override
//	public void addPermissionToRole(String actionName, String roleName) {
//		PermissionEntity permissionEntity=permissionRepository.findByActionNameContainingIgnoreCase(actionName);
//		
//		RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
//		
//		
//		permissionEntity.getRoles().add(role);
//		
//		
//	}

	
//	@Autowired
//	private CandidateRepository candidateRepository;
//	
//	@Override
//	public void addRoleToCandidate(String email, String roleName) {
//		Candidate candidate = candidateRepository.findByEmail(email);
//		RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
//		
//		candidate.getRoles().add(role);
//		System.out.println(candidate.getRoles().add(role));
//		
//	}



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

	
	
	
	
	
	
	
	
	
//
//	@Override
//	public PermissionEntity getPermissionById(Long id) throws ResourceNotFoundException {
//		PermissionEntity entity=permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("permission","id",id));
//		
//		return entity;
//	}


  






	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
