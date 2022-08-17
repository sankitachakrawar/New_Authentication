package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.RolePermissionEntity;

import com.example.demo.services.RolePermissionService;

@RestController
@RequestMapping("/api")
public class RolePermissionController {

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@GetMapping("/rolePermission")
	public ResponseEntity<?> getAllRolesPermission(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
		
		Page<RolePermissionEntity> rolePermissionEntity=rolePermissionService.getAllRolesPermissions(search, pageNo, size);
		if (rolePermissionEntity.getTotalElements() != 0) {
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
					new ListResponseDto(rolePermissionEntity.getContent(), rolePermissionEntity.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
	}
}
