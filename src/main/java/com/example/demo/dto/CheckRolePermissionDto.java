package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRolePermissionDto {

	

	private String method;

	private String baseUrl;

	private String path;

	

}
