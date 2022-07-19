package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.demo.dto.EntityDto;
import com.example.demo.dto.EntityPermissionDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.IRoleListDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.EntityEntity;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.RolePermissionEntity;
import com.example.demo.entities.RolePermissionId;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.EntityRepository;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.RolePermissionRepository;
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

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

//	@Autowired
//	private UserRoleRepository userRoleRepository;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Override
	public Page<IRoleListDto> getAllRoles(String search, String from, String to) {

		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		Page<IRoleListDto> roles;

		if ((search == "") || (search == null) || (search.length() == 0)) {

			roles = roleRepository.findByIsActiveTrue(paging, IRoleListDto.class);

		} else {

			roles = roleRepository.findByRoleNameContainingIgnoreCaseAndIsActiveTrue(StringUtils.trimLeadingWhitespace(search), paging, IRoleListDto.class);

		}

		return roles;

	}

//	@SuppressWarnings("deprecation")
//	@Override
//	public void addRole(RoleDto roleDto, Long id) {
//
//		RoleEntity roleEntity = new RoleEntity();
//		roleEntity.setCreatedBy(this.authRepository.getById(id));
//		roleEntity.setUpdatedBy(this.authRepository.getById(id));
//		roleEntity.setRoleName(roleDto.getRoleName());
//		roleEntity.setDescription(roleDto.getDescription());
//		roleRepository.save(roleEntity);
//
//	}

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
	public IRoleDetailDto getRoleById(Long id) throws ResourceNotFoundException {

		IRoleDetailDto roleEntity = roleRepository.findById(id, IRoleDetailDto.class).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
		return roleEntity;

	}

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
	/*
	 * @Override public ArrayList<String> getPermissionByUserId(Long userId) {
	 * 
	 * ArrayList<RoleIdListDto> roleIds = userRoleRepository.findByPkUserId(userId,
	 * RoleIdListDto.class); ArrayList<Long> roles = new ArrayList<>();
	 * 
	 * for (int i = 0; i < roleIds.size(); i++) {
	 * 
	 * roles.add(roleIds.get(i).getPkRoleId());
	 * 
	 * }
	 * 
	 * List<IPermissionIdList> rolesPermission =
	 * rolePermissionRepository.findPkPermissionByPkRoleIdIn(roles,
	 * IPermissionIdList.class); ArrayList<String> permissions = new ArrayList<>();
	 * 
	 * for (IPermissionIdList element : rolesPermission) {
	 * 
	 * permissions.add(element.getPkPermissionActionName());
	 * 
	 * }
	 * 
	 * return permissions;
	 * 
	 * }
	 */

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

	@Override
	public RoleDto addRole(@Valid RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		roleRepository.save(roleEntity);
		return roleDto;
	}

}
