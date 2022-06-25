package com.example.demo.entities;

public class JwtResponse {
	 private String jwtToken;

	public JwtResponse(String token) {
		// TODO Auto-generated constructor stub
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + "]";
	}
	 
	 
}
