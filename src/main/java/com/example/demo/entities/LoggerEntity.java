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

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "logger")
public class LoggerEntity {

	
	
	private static final long serialVersionUID = 1L;

	public LoggerEntity() {

		// TODO Auto-generated constructor stub
	}


	

	public LoggerEntity(Long id, Candidate c_id, String token, Date createdAt, Date expireAt) {
		super();
		this.id = id;
		this.c_id = c_id;
		this.token = token;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
	}


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Candidate c_id;

	@Column(name = "token", length = 512)
	private String token;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "expire_at")
	private Date expireAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getC_id() {
		return c_id;
	}

	public void setC_id(Candidate c_id) {
		this.c_id = c_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
