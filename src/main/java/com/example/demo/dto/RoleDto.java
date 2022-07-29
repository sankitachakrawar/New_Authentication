package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.entities.Candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

	
	@NotBlank(message = "Role Name is Required*roleNameRequired")
	@NotEmpty(message = "Role Name is Required*roleNameRequired")
	@NotNull(message = "Role Name is Required*roleNameRequired")
	private String roleName;

	private String description;
	
	//@NotNull(message = "candidateId is Required*candidateIdRequired")
	private Candidate candidateId;


	
	
}
