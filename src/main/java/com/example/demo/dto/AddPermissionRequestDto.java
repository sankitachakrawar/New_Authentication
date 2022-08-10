package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class AddPermissionRequestDto {


	@NotEmpty(message = "Permission is Required*permissionRequired")
	@NotNull(message = "Permission is Required*permissionRequired")
	private Long[] permissions;

	public Long[] getPermissions() {
		return permissions;
	}

	public void setPermissions(Long[] permissions) {
		this.permissions = permissions;
	}

	public AddPermissionRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddPermissionRequestDto(
			@NotEmpty(message = "Permission is Required*permissionRequired") @NotNull(message = "Permission is Required*permissionRequired") Long[] permissions) {
		super();
		this.permissions = permissions;
	}

	
	

}
