package com.example.demo.repositories;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IPermissionIdList;
import com.example.demo.entities.RolePermissionEntity;


public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

	ArrayList<IPermissionDto> findByPkRoleId(Long roleId, Class<IPermissionDto> IPermissionDto);

	List<IPermissionDto> findByPkRoleIdIn(ArrayList<Long> roleIds, Class<IPermissionDto> IPermissionDto);

	List<IPermissionIdList> findPkPermissionByPkRoleIdIn(ArrayList<Long> roleIds, Class<IPermissionIdList> IPermissionIdList);

	void deleteByPkRoleId(Long roleIds);

}
