package com.example.demo.dto;

import java.io.Serializable;




public class ApplyJobDto implements Serializable{

	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long job_id;
	
	private Long candidate_id;

	public Long getJob_id() {
		return job_id;
	}

	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	public Long getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(Long candidate_id) {
		this.candidate_id = candidate_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ApplyJobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyJobDto(Long job_id, Long candidate_id) {
		super();
		this.job_id = job_id;
		this.candidate_id = candidate_id;
	}

	
}
