package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class GetAllEventCoursePaymentDtoDataRes {
	private String id;
	private String idFile;
	private BigInteger totalprice;
	private String paymentName;
	private String fullName;
	private String phoneNumber;
	private String email;
	private LocalDateTime createdAt;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public BigInteger getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigInteger totalprice) {
		this.totalprice = totalprice;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullNmae) {
		this.fullName = fullNmae;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
