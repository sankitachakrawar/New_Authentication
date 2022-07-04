package com.example.demo.repositories;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.UserRoleEntity;


public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	ArrayList<RoleIdListDto> findByPkUserId(Long userId, Class<RoleIdListDto> RoleIdListDto);

	void deleteByPkUserId(Long userId);

	//ArrayList<IUserRoleDetailDto> findByPkUserIdAndPkUserIsActiveTrue(Long userId, Class<IUserRoleDetailDto> IUserRoleDetailDto);

}
