package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestDto {

	

	@NotBlank(message = "Action Name is Required*actionNameRequired")
	@NotEmpty(message = "Action Name is Required*actionNameRequired")
	@NotNull(message = "Action Name is Required*actionNameRequired")
	public String actionName;

	@NotBlank(message = "Base URL is Required*baseUrlRequired")
	@NotEmpty(message = "Base URL is Required*baseUrlRequired")
	@NotNull(message = "Base URL is Required*baseUrlRequired")
	public String baseUrl;

	@NotBlank(message = "Description is Required*descriptionRequired")
	@NotEmpty(message = "Description is Required*descriptionRequired")
	@NotNull(message = "Description is Required*descriptionRequired")
	public String description;

	@NotBlank(message = "Method is Required*methodRequired")
	@NotEmpty(message = "Method is Required*methodRequired")
	@NotNull(message = "Method is Required*methodRequired")
	public String method;

	public String path;




}
