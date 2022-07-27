package com.example.demo.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE Job SET is_active=false WHERE id=?")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is Required*nameRequired")
	@NotEmpty(message = "Name is Required*nameRequired")
	@NotNull(message = "Name is Required*nameRequired")
	private String name;
	
	private String description;
	
	private String location;
	
	private String ExpectedCTC;
	
	@UpdateTimestamp
	private Date postTime;
	
	@Column(name = "is_active")
	private boolean isActive = true;

	

	
}





