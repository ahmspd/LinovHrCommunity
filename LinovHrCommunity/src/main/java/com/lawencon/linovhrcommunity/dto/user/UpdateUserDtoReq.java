package com.lawencon.linovhrcommunity.dto.user;

public class UpdateUserDtoReq {
	private String id;
	private String fullName;
	private String phoneNumber;
	private String postalCode;
	private String idIndustry;
	private String idPosition;
	private String registrationCode;
	private String idCity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	public String getIdCity() {
		return idCity;
	}
	public void setIdCity(String idCity) {
		this.idCity = idCity;
	}
}
