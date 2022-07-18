package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSettingDto {

	

	private String key;

	private String value;

	private Boolean isAdminOnly;

	private String newValue;


}
