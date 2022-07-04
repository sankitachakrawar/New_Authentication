package com.example.demo.dto;

import java.util.ArrayList;

import java.util.Optional;



public class UserDataDto {

	private Long c_id;

	private String name;

	private String email;

	private String username;

	private String address;

	

	Optional<ArrayList<UserRoleDto>> roles = Optional.empty();

	public UserDataDto() {

	}

	public UserDataDto(Long userId, String name, String email,String address,String username) {

		super();
		this.c_id = c_id;
		this.name = name;
		this.email = email;
		this.address=address;
		this.username=username;
		
	}

public void setC_id(Long c_id) {
	this.c_id = c_id;
}
public Long getC_id() {
	return c_id;
}
	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public Optional<ArrayList<UserRoleDto>> getRoles() {

		return roles;

	}

	public void setRoles(ArrayList<UserRoleDto> roles) {

		this.roles = Optional.ofNullable(roles);

	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
}
