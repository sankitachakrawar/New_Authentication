package com.example.demo.entities;

import java.util.Set;

public class AuthRequest {

	private String username;
	private String email;
	private String password;
	
	public AuthRequest(String email, String password,String username) {
		super();
		this.email = email;
		this.password = password;
		this.username=username;
	}
	public AuthRequest() {
		super();
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserame() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
