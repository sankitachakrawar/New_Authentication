package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="error_logger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLoggerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "message", length = 100000)
	private String message;
	
	@Column(name="url")
	private String url;
	
	@Column(name="method")
	@Enumerated(EnumType.STRING)
	private MethodEnum method;
	
	@Column(name="host")
	private String host;
	
	@Column(name="body", length = 10000)
	private String body;
	
	@Column(name="token")
	private String token;
	
	@Column(name="created_at")
	@CreationTimestamp
	private Date createdAt;
	
	
	
	

}
