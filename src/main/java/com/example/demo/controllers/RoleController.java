package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AddPermissionRequestDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.IRoleDetailDto;
import com.example.demo.dto.IRoleListDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.RolePermissionDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.dto.UserDataDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.RoleServiceInterface;
import com.example.demo.dto.ListResponseDto;

@RestController
@RequestMapping("/api")
public class RoleController {

	public RoleController() {

	}

	@Autowired
	private RoleServiceInterface roleServiceInterface;
	
	@GetMapping("/role")
	public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {

		Page<IRoleListDto> roles = roleServiceInterface.getAllRoles(search, pageNo, size);

		if (roles.getTotalElements() != 0) {

			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", new ListResponseDto(roles.getContent(), roles.getTotalElements())), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);

	}

	
	@PostMapping("/role")
	public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto, HttpServletRequest request) {
		
		RoleDto dto=this.roleServiceInterface.addRole(roleDto);
		
		return new ResponseEntity<>("Role added successfully",HttpStatus.OK);
		
		
		
		
//		try {	System.out.println("role1");
//			this.roleServiceInterface.addRole(roleDto);
//			return new ResponseEntity<>("Role Added Successfully!!",HttpStatus.OK);
//		}catch(Exception e) {
//				return new ResponseEntity<>("Role Not Added",HttpStatus.BAD_REQUEST);
//		}
	}

	
	@PutMapping("/role/{id}")
	public ResponseEntity<?> editRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody RoleDto roleDto, HttpServletRequest request) {
			try {
			
		roleServiceInterface.updateRole(roleDto, roleId);

			return new ResponseEntity<>("Role updated successfully", HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>("Role not found",HttpStatus.BAD_REQUEST);
			}

	}

	
	@DeleteMapping("/role/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(value = "id") Long roleId, HttpServletRequest request) {

		try {

			roleServiceInterface.deleteRole(roleId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	
	@GetMapping("/role/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable(value = "id") Long roleId) {

		try {

			IRoleDetailDto roleEntity = roleServiceInterface.getRoleById(roleId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", roleEntity), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

		}

	}


	@GetMapping("/permission_role/{id}")
	public ResponseEntity<?> getRoleAndPermissionById(@PathVariable(value = "id") Long roleId) {

		try {

			RolePermissionDto rolePermissionData = roleServiceInterface.getRoleAndPermissionById(roleId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", rolePermissionData), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	
	@PostMapping("/permission_role/{id}")
	public ResponseEntity<?> AddPermissionToRole(@PathVariable(value = "id") Long roleId, @Valid @RequestBody AddPermissionRequestDto permissions) {

		try {

			roleServiceInterface.addPermissionsToRole(roleId, permissions.getPermissions());
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "roleNotFound"), HttpStatus.NOT_FOUND);

		}

	}

}
