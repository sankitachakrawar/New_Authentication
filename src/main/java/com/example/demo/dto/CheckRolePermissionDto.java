package com.example.demo.dto;




public class CheckRolePermissionDto {

	

	private String method;

	private String baseUrl;

	private String path;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public CheckRolePermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckRolePermissionDto(String method, String baseUrl, String path) {
		super();
		this.method = method;
		this.baseUrl = baseUrl;
		this.path = path;
	}

	

}
