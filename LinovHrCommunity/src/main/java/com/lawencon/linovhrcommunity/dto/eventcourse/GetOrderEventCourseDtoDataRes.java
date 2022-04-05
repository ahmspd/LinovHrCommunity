package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.time.LocalDateTime;

public class GetOrderEventCourseDtoDataRes {

	private String id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Boolean isAccept;
	private String idFile;
	private String paymentName;
	private String invoice;
	private LocalDateTime createdAt;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Boolean isAccept) {
		this.isAccept = isAccept;
	}

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
