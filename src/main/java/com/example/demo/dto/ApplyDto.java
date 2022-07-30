package com.example.demo.dto;

import java.util.Optional;

import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDto {
 public ApplyDto(Optional<Candidate> candidate2, Optional<Job> job2) {
		// TODO Auto-generated constructor stub
	}
private Candidate candidate;
 private Job job;
}
