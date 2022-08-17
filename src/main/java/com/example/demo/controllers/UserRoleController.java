package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ListResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.services.UserRoleService;

@RestController
@RequestMapping("/api")
public class UserRoleController {

	
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping("/userRole")
	public ResponseEntity<?> getAllUserRoles(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size){
		
		Page<UserRoleEntity> userRoleDto=userRoleService.getAllUserRoles(search, pageNo, size);
		if (userRoleDto.getTotalElements() != 0) {
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
					new ListResponseDto(userRoleDto.getContent(), userRoleDto.getTotalElements())), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/userRole/{id}")
	public ResponseEntity<?> deleteUserRole(@PathVariable("user_id")Long user_Id){
		this.userRoleService.deleteUserRole(user_Id);
		return new  ResponseEntity<>(Map.of("message","User Role delete sucesssfully!!"),HttpStatus.OK);
	}
	
	
	
}
