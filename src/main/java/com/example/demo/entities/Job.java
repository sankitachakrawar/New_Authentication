package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is Required*nameRequired")
	@NotEmpty(message = "Name is Required*nameRequired")
	@NotNull(message = "Name is Required*nameRequired")
	private String name;
	
	private String location;
	@UpdateTimestamp
	private Date postTime;
	private boolean apply;
	/*
	 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
	 * } public String getName() { return name; } public void setName(String name) {
	 * this.name = name; } public String getLocation() { return location; } public
	 * void setLocation(String location) { this.location = location; } public Date
	 * getPostTime() { return postTime; } public void setPostTime(Date postTime) {
	 * this.postTime = postTime; }
	 * 
	 * 
	 * public boolean isApply() { return apply; } public void setApply(boolean
	 * apply) { this.apply = apply; } public Job() { super(); // TODO Auto-generated
	 * constructor stub } public Job(Long id, String name, String location, Date
	 * postTime, boolean apply) { super(); this.id = id; this.name = name;
	 * this.location = location; this.postTime = postTime; this.apply = apply; }
	 * 
	 */
	
	
}
