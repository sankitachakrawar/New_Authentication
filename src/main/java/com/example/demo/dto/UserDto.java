package com.example.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class UserDto {


	private String name;

	private String summary;

	private String email;

	private String address;

	private Date dob;


	


}
