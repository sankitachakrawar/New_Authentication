package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class AssignJob {

	@NotBlank(message = "Email is Required*emailRequired")
	@NotEmpty(message = "Email is Required*emailRequired")
	@NotNull(message = "Email is Required*emailRequired")
	private String email;
	
	@NotBlank(message = "Role Name is Required*roleNamelRequired")
	@NotEmpty(message = "Role Name is Required*roleNameRequired")
	@NotNull(message = "Role Name is Required*roleNameRequired")
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssignJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignJob(
			@NotBlank(message = "Email is Required*emailRequired") @NotEmpty(message = "Email is Required*emailRequired") @NotNull(message = "Email is Required*emailRequired") String email,
			@NotBlank(message = "Role Name is Required*roleNamelRequired") @NotEmpty(message = "Role Name is Required*roleNameRequired") @NotNull(message = "Role Name is Required*roleNameRequired") String name) {
		super();
		this.email = email;
		this.name = name;
	}

	
}
