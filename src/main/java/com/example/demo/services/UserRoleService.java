package com.example.demo.services;

import org.springframework.data.domain.Page;
import com.example.demo.entities.UserRoleEntity;

public interface UserRoleService {

	void deleteUserRole(Long user_Id);
	Page<UserRoleEntity> getAllUserRoles(String search, String from, String to);
}
