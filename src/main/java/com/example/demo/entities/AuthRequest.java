package com.example.demo.entities;


public class AuthRequest {

	private String username;

	private String password;
	
	public AuthRequest( String password,String username) {
		super();
		this.password = password;
		this.username=username;
	}
	public AuthRequest() {
		super();
		
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
