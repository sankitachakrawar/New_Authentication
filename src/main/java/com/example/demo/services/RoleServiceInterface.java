package com.example.demo.services;

import java.util.ArrayList;
import org.springframework.data.domain.Page;

import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.IRoleListDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptions.ResourceNotFoundException;


public interface RoleServiceInterface {

	

	ArrayList<String> getPermissionByUserId(Long userId);

	

}
