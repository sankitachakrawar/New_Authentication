package com.example.demo.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private final String token;

	//private List<IPermissionDto> permission;

	/*
	 * public List<IPermissionDto> getPermission() {
	 * 
	 * return permission;
	 * 
	 * }
	 */
	private String email;

	private String name;

	private Long id;

	
	
	

	
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