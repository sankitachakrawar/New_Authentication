package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityPermissionDto {

	

	private Long id;

	private String actionName;

	private Boolean isSelected;

	private Long entityId;

	

}
