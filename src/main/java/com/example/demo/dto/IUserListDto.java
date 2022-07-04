package com.example.demo.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public interface IUserListDto {

	public Long getId();

	public String getName();

	public String getEmail();

	@Value("#{target.designationId.name}")
	public String getDesignationName();

	public String getGender();

	@Value("#{@calculateExpUtil.getEmpExp(target.careerStartDate)}")
	public String getExperience();

	public Boolean getIsActive();
	@JsonFormat(pattern = "yyyy-MM-dd",shape = Shape.STRING)
	public Date getdateOfJoining(); 

}
