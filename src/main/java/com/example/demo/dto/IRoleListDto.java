package com.example.demo.dto;

import java.io.Serializable;

public interface IRoleListDto extends Serializable {

	public Long getId();

	public String getRoleName();

	public String getDescription();

	public Boolean getIsActive();

}
