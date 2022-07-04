package com.example.demo.entities;

import java.util.Objects;


import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
@SuppressWarnings("serial")
public class UserRoleId implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleId() {

		super();

		// TODO Auto-generated constructor stub
	}

	public UserRoleId(Candidate candidate, RoleEntity role) {

		super();
		this.candidate = candidate;
		this.role = role;

	}

	private Candidate candidate;

	private RoleEntity role;

	@ManyToOne

	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne
	public RoleEntity getRole() {

		return role;

	}

	public void setRole(RoleEntity role) {

		this.role = role;

	}

	@Override
	public int hashCode() {

		return Objects.hash(role, candidate);

	}

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
