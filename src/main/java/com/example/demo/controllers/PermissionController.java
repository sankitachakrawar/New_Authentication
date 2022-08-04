package com.example.demo.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.PermissionRequestDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.PermissionEntity;
import com.example.demo.exceptionHandling.ResourceNotFoundException;
import com.example.demo.services.PermissionServiceInterface;
import com.example.demo.services.RoleServiceInterface;



@RestController
@RequestMapping("/api")
@Validated
public class PermissionController {

	public PermissionController() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private PermissionServiceInterface permissionServiceInterface;

	@PreAuthorize("hasRole('addPermission')")
	@PostMapping("/permission")
	public ResponseEntity<?> addPermission(@Valid @RequestBody PermissionRequestDto permissionBody) {

		permissionServiceInterface.addPermission(permissionBody);
		return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('editPermission')")
	@PutMapping("/permission/{id}")
	public ResponseEntity<?> editPermission(@PathVariable(value = "id") Long permissionId, @Valid @RequestBody PermissionRequestDto permissionBody) throws ResourceNotFoundException {

		try {

			permissionServiceInterface.editPermission(permissionBody, permissionId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "permissionNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	@PreAuthorize("hasRole('deletePermission')")
	@DeleteMapping("/permission/{id}")
	public ResponseEntity<?> editEntity(@PathVariable(value = "id") Long permissionId) throws ResourceNotFoundException {

		try {

			permissionServiceInterface.deletePermission(permissionId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "permissionNotFound"), HttpStatus.NOT_FOUND);

		}

	}
	
	

}
