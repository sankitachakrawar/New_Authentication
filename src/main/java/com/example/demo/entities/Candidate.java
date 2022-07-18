package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotEmpty
	//@Size(min=4,message="Name must be min of 4 characters !!")
	//@Column(name="candidate_name" , nullable=false , length=100)
	private String name;
	
	
	
	//@Email(message="Email address is not valid!!")
//	@NotBlank(message="email is mandatory")
	//@Pattern(regexp = "^(.+)@(.+)$")
	
	private String email;
	
	//@NotEmpty
	//@Size(min=3,max=8,message="Password must be min of 3 characters or max of 8 chracters")
	//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="Password must be min of 3 characters or max of 8 chracters")
	private String password;

	//@NotEmpty
	private String address;
	
	@Column(name = "is_active")
	private boolean isActive = true;
	
	
	private boolean isEnabled;

	private String username;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_jobs", joinColumns = @JoinColumn(name = "c_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "j_id", referencedColumnName = "id"))
	private Collection<Job> jobs = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Recruiter> recruiters=new ArrayList<>();

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.candidate", cascade = CascadeType.ALL)
	//@OneToMany(fetch =  FetchType.LAZY)
	//private List<UserRoleEntity> userRole;
	private Collection<UserRoleEntity> userRole=new ArrayList<>();

	
	
	
	
	
}







/*
 * public Long getC_id() { return c_id; }
 * 
 * public void setC_id(Long c_id) { this.c_id = c_id; }
 * 
 * public String getName() { return name; }
 * 
 * public void setName(String name) { this.name = name; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public String getPassword() { return password; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * public String getAddress() { return address; }
 * 
 * public void setAddress(String address) { this.address = address; }
 * 
 * public Boolean getIsActive() { return isActive; }
 * 
 * public void setIsActive(Boolean isActive) { this.isActive = isActive; }
 * 
 * public boolean isEnabled() { return isEnabled; }
 * 
 * public void setEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }
 * 
 * public String getUsername() { return username; }
 * 
 * public void setUsername(String username) { this.username = username; }
 * 
 * public Collection<Job> getJobs() { return jobs; }
 * 
 * public void setJobs(Collection<Job> jobs) { this.jobs = jobs; }
 * 
 * public Candidate() { super(); // TODO Auto-generated constructor stub }
 * 
 * public Candidate(Long c_id, String name, String email, String password,
 * String address, Boolean isActive, boolean isEnabled, String username,
 * Collection<Job> jobs) { super(); this.c_id = c_id; this.name = name;
 * this.email = email; this.password = password; this.address = address;
 * this.isActive = isActive; this.isEnabled = isEnabled; this.username =
 * username; this.jobs = jobs; }
 * 
 */