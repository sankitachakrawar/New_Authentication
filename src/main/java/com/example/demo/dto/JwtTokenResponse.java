package com.example.demo.dto;

public class JwtTokenResponse {

	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtTokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtTokenResponse(String token) {
		super();
		this.token = token;
	}
	
	
}
