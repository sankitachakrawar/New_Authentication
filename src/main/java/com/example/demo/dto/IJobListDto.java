package com.example.demo.dto;

import java.util.Date;

public interface IJobListDto {

	public int j_id();
	
	public String title();
	
	public String location();
	public Boolean apply();
	public Date postTime();
	
}
