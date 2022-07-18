package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPermissionRequestDto {

	

	@NotEmpty(message = "Permission is Required*permissionRequired")
	@NotNull(message = "Permission is Required*permissionRequired")
	private Long[] permissions;

	

}
