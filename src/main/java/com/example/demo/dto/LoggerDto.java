package com.example.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggerDto {


	public String token;

	public Long userId;

	public Date expireAt;


	public void setOtp(int otp) {
		// TODO Auto-generated method stub
		
	}

}
