package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class EditUserRequestDto extends CandidateDto {

	public EditUserRequestDto() {

	}

	public EditUserRequestDto(Long[] roles) {
		super();
		this.roles = roles;
	}

	private Long[] roles;

	public Long[] getRoles() {

		return roles;

	}

	public void setRoles(Long[] roles) {

		this.roles = roles;

	}

}
