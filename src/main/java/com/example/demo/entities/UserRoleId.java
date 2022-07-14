package com.example.demo.entities;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class UserRoleId implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Candidate candidate;
	@ManyToOne
	private RoleEntity role;

	
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;

		}

		if ((obj == null) || (getClass() != obj.getClass())) {

			return false;

		}

		UserRoleId other = (UserRoleId) obj;
		return Objects.equals(role, other.role) && Objects.equals(candidate, other.candidate);

	}

}
