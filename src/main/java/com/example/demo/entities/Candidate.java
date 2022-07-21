package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.validation.constraints.NotEmpty;
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
	
//	@NotEmpty
//	@Size(min=4,message="Name must be min of 4 characters !!")
	//@Column(name="candidate_name" , nullable=false , length=100)
	private String name;
	
	
	
//	@Email(message="Email address is not valid!!")
//	@NotBlank(message="email is mandatory")
//	@Pattern(regexp = "^(.+)@(.+)$",message="Email address is not valid!")
	private String email;
	
//	@NotEmpty
//	@Size(min=3,max=8,message="Password must be min of 3 characters or max of 8 chracters ")
//	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="Password must be min of 3 characters or max of 8 chracters ")
	private String password;

	@NotEmpty
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

	private String token;

	private String roleName;
}







