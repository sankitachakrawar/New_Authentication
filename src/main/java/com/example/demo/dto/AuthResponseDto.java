package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class AuthResponseDto implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private final String token;

//	private List<IPermissionDto> permission;
//
//	
//	  public List<IPermissionDto> getPermission() {
//	  
//	  return permission;
//	  
//	  }
	 
	private String email;

	private String name;

	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToken() {
		return token;
	}

	public AuthResponseDto(String token, String email, String name, Long id) {
		super();
		this.token = token;
		this.email = email;
		this.name = name;
		this.id = id;
	}

	
	
	

	
	/*
	 * public AuthResponseDto(String jwttoken, List<IPermissionDto> permission,
	 * String email, String name, Long id) { super(); this.jwttoken = jwttoken;
	 * this.permission = permission; this.email = email; this.name = name; this.id =
	 * id; }
	 * 
	 * public void setPermission(List<IPermissionDto> permission) {
	 * 
	 * this.permission = permission;
	 * 
	 * }
	 */

//	public String getToken() {
//
//		return this.jwttoken;
//
//	}

}