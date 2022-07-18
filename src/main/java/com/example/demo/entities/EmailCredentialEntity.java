package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.LoggerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_credential")
public class EmailCredentialEntity implements Serializable {


	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "env")
	private String env;

	@Column(name = "host")
	private String host;

	@Column(name = "port")
	private int port;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "smtp_auth")
	private Boolean smtpAuth;

	@Column(name = "tls_enable")
	private Boolean tlsEnable;

	

}
