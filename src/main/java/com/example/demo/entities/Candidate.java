package com.example.demo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidate")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE candidate SET is_active=false WHERE id=?")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	private String email;
	
	private String password;

	@NotEmpty
	private String address;
	
	@Column(name = "is_active")
	private boolean isActive = true;
	
	private boolean isEnabled;

	private String username;

}







