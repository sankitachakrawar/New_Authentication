package com.example.demo.serviceImpl;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.IPermissionIdList;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.RoleServiceInterface;


@Transactional
@Service
public class RoleServiceImpl implements RoleServiceInterface {

	public RoleServiceImpl() {

		// TODO Auto-generated constructor stub
	}


	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	/*
	 * @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") private int
	 * batchSize;
	 */


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

	
}
