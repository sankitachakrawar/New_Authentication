package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityDto {

	
	private Long id;

	private String entityName;

	private Boolean isSelected;

	private List<EntityPermissionDto> permissions;

	

}
