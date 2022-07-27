package com.example.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobDto implements Serializable{

	private static final long serialVersionUID = 5926468583005150707L;
	
	private Long job_id;
	
	private Long candidate_id;
}
