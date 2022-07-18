package com.example.demo.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityRequestDto {

	

	@NotBlank(message = "Entity Name is Required*entityNameRequired")
	@NotEmpty(message = "Entity Name is Required*entityNameRequired")
	@NotNull(message = "Entity Name is Required*entityNameRequired")
	private String entityName;

	@NotBlank(message = "Entity Description is Required*entityDescriptionRequired")
	@NotEmpty(message = "Entity Description is Required*entityDescriptionRequired")
	@NotNull(message = "Entity Description is Required*entityDescriptionRequired")
	private String description;

	

}
