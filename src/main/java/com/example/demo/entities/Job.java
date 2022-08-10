package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@Entity
@Table(name="job")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE job SET is_active=false WHERE id=?")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotBlank(message = "Name is Required*nameRequired")
	//@NotEmpty(message = "Name is Required*nameRequired")
	//@NotNull(message = "Name is Required*nameRequired")
	private String name;
	
	private String description;
	
	private String location;
	
	private String CTC;
	
	@UpdateTimestamp
	private Date postTime;
	
	@Column(name = "is_active")
	private boolean isActive = true;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiterId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCTC() {
		return CTC;
	}

	public void setCTC(String cTC) {
		CTC = cTC;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Recruiter getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Recruiter recruiterId) {
		this.recruiterId = recruiterId;
	}

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(Long id, String name, String description, String location, String cTC, Date postTime, boolean isActive,
			Recruiter recruiterId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		CTC = cTC;
		this.postTime = postTime;
		this.isActive = isActive;
		this.recruiterId = recruiterId;
	}

	
}





