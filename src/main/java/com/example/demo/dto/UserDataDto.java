package com.example.demo.dto;

import java.util.ArrayList;

import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {



	private Long userId;

	private String name;

	private String email;
	
	
	
	@JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	Optional<Date> dob = Optional.empty();

	Optional<ArrayList<UserRoleDto>> roles = Optional.empty();

	public void setDob(Date dob2) {
		
		
	}

	public void setRoles(ArrayList<UserRoleDto> userRoles) {
		
		
	}




}
