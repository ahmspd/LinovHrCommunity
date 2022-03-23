package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(length = 30, nullable = false)
	private String email;
	
	@Column(length = 255, nullable = false)
	private String password;
	
	@OneToOne
	@JoinColumn(name = "id_role", nullable = false)
	private Role role;
	
	@Column(name="registration_code", length = 10, nullable = false)
	private String registrationCode;
	
	@Column(name="is_member")
	private Boolean isMember;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public Boolean getIsMember() {
		return isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}
}
