package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.entities.Candidate;



public class RoleDto {

	
	@NotBlank(message = "Role Name is Required*roleNameRequired")
	@NotEmpty(message = "Role Name is Required*roleNameRequired")
	@NotNull(message = "Role Name is Required*roleNameRequired")
	private String roleName;

	private String description;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleDto(
			@NotBlank(message = "Role Name is Required*roleNameRequired") @NotEmpty(message = "Role Name is Required*roleNameRequired") @NotNull(message = "Role Name is Required*roleNameRequired") String roleName,
			String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}
	
	


	
	
}
