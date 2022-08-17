package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.dto.AssignPermission;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.EntityDto;
import com.example.demo.dto.EntityPermissionDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IPermissionIdList;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.RoleCandidateDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.EntityEntity;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.RolePermissionEntity;
import com.example.demo.entities.RolePermissionId;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.EntityRepository;
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
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Override
	public RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException {

		RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		List<EntityEntity> entities = entityRepository.findAll();
		List<PermissionEntity> permissions = permissionRepository.findAll();
		ArrayList<IPermissionDto> rolesPermission = rolePermissionRepository.findByPkRoleId(id, IPermissionDto.class);
		ArrayList<EntityPermissionDto> entityPermission = new ArrayList<>();

		for (PermissionEntity permission : permissions) {

			EntityPermissionDto singleEntityPermisson = new EntityPermissionDto();
			singleEntityPermisson.setActionName(permission.getDescription());
			singleEntityPermisson.setId(permission.getId());
			singleEntityPermisson.setEntityId(permission.getEntityId().getId());
			singleEntityPermisson.setIsSelected(false);

			for (IPermissionDto element : rolesPermission) {

				if (permission.getId() == element.getPkPermissionId()) {

					singleEntityPermisson.setIsSelected(true);
					break;

				}

			}

			entityPermission.add(singleEntityPermisson);

		}

		ArrayList<EntityDto> entityDto = new ArrayList<>();

		for (EntityEntity element : entities) {

			Boolean isEntityEnabled = false;
			ArrayList<EntityPermissionDto> entityPermission1 = new ArrayList<>();

			for (int j = 0; j < entityPermission.size(); j++) {

				if (element.getId() == entityPermission.get(j).getEntityId()) {

					if (entityPermission.get(j).getIsSelected()) {

						isEntityEnabled = true;

					}

					entityPermission1.add(entityPermission.get(j));

				}

			}

			EntityDto singleEntityDto = new EntityDto();
			singleEntityDto.setId(element.getId());
			singleEntityDto.setEntityName(element.getEntityName());
			singleEntityDto.setIsSelected(isEntityEnabled);
			singleEntityDto.setPermissions(entityPermission1);
			entityDto.add(singleEntityDto);

		}

		RolePermissionDto rolePermissionDto = new RolePermissionDto();
		rolePermissionDto.setId(roleEntity.getId());
		rolePermissionDto.setRoleName(roleEntity.getRoleName());
		rolePermissionDto.setDescription(roleEntity.getDescription());
		rolePermissionDto.setEntity(entityDto);
		return rolePermissionDto;

	}


//	@Override
//	public RolePermissionDto getRoleAndPermissionById(Long id) throws ResourceNotFoundException {
//		
//		RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
//		List<PermissionEntity> permissions=permissionRepository.findAll();
//		ArrayList<IPermissionDto> rolesPermission = PermissionRepository.findByRoleId(id, IPermissionDto.class);
//		
//		for (PermissionEntity permission : permissions) {
//				PermissionDto dto=new PermissionDto();
//				dto.setActionName(permission.getActionName());
//				dto.setDescription(permission.getDescription());
//				 permissionRepository.findAll();
//	
//		}
//		RolePermissionDto rolePermissionDto = new RolePermissionDto();
//		rolePermissionDto.setId(roleEntity.getId());
//		rolePermissionDto.setRoleName(roleEntity.getRoleName());
//		rolePermissionDto.setDescription(roleEntity.getDescription());
//		rolePermissionDto.getActionName();
//		return rolePermissionDto;
//	}
	

//	@Override
//	public PermissionEntity getPermissionById(Long id) throws ResourceNotFoundException {
//		PermissionEntity entity=permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("permission","id",id));
//		
//		return entity;
//	}





	@Override
	public RoleCandidateDto getRoleAndCandidateById(Long id) throws ResourceNotFoundException {
		RoleEntity roleEntity=roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		List<Candidate> candidate=candidateRepository.findAll();
		
		for (Candidate candidates : candidate) {
			CandidateDto dto=new CandidateDto();
			dto.setName(candidates.getName());
			
			candidateRepository.findAll();

	}
		RoleCandidateDto roleCandidateDto = new RoleCandidateDto();
		roleCandidateDto.setId(roleEntity.getId());
		roleCandidateDto.setRoleName(roleEntity.getRoleName());

		return roleCandidateDto;
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
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public void addPermissionToRole(AssignPermission assignPermission) {
		try {
			ArrayList<RolePermissionEntity> roles=new ArrayList<>();
		
			PermissionEntity actionName=permissionRepository.findByActionNameContainingIgnoreCase(assignPermission.getActionName());
			
			RoleEntity roleName=roleRepository.findByRoleName(assignPermission.getRoleName());
			
			RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
			
			RolePermissionId rolePermissionId=new RolePermissionId(roleName,actionName);
			
			rolePermissionEntity.setPk(rolePermissionId);
			roles.add(rolePermissionEntity);
			rolePermissionRepository.saveAll(roles);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		}
}














//@Override
//public void addPermissionToRole(String actionName, String roleName) {
//	PermissionEntity permissionEntity=permissionRepository.findByActionNameContainingIgnoreCase(actionName);
//	
//	RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
//	
//	permissionEntity.getRoles().add(role);
//	
//	permissionRepository.save(permissionEntity);
//	
//}











//@Override
//public RoleEntity addRole(RoleEntity roleEntity)
//{
//	RoleEntity roleEntity2=new RoleEntity();
//	roleEntity2.setRoleName(roleEntity.getRoleName());
//	roleEntity2.setDescription(roleEntity.getDescription());
//	roleRepository.save(roleEntity2);
//	return roleEntity;
//}

//@Override
//public void addRoleToCandidate(String email, String roleName) {
//	Candidate candidate = candidateRepository.findByEmailContainingIgnoreCaseAndIsActiveTrue(email);
//	System.out.println("candidate>>"+candidate);
//	
//	RoleEntity role = roleRepository.findByRoleNameContainingIgnoreCase(roleName);
//	System.out.println("role>>"+role);
//	candidate.getRoles().add(role);
//	
//	candidateRepository.save(candidate);
//	System.out.println(candidate.getRoles().add(role));
//	
//}