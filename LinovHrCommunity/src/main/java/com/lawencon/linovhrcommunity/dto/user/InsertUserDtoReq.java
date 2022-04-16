package com.lawencon.linovhrcommunity.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertUserDtoReq {
	@NotEmpty(message = "Email is empty!")
	@Size(max = 30, message = "Email is out of range!")
	private String email;
	
	private String password;
	
	@NotEmpty(message = "Id Role is empty!")
	private String idRole;
	private String registrationCode;
	private String fullName;
	private String phoneNumber;
	private String company;
	private String idIndustry;
	private String idPosition;
	private String idProvince;
	private String idCity;
	private String idFile;

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

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIdIndustry() {
		return idIndustry;
	}

	public void setIdIndustry(String idIndustry) {
		this.idIndustry = idIndustry;
	}

	public String getIdPosition() {
		return idPosition;
	}

	public void setIdPosition(String idPosition) {
		this.idPosition = idPosition;
	}

	public String getIdProvince() {
		return idProvince;
	}

	public void setIdProvince(String idProvince) {
		this.idProvince = idProvince;
	}

	public String getIdCity() {
		return idCity;
	}

	public void setIdCity(String idCity) {
		this.idCity = idCity;
	}

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}
}
