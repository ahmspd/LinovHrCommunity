package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

public class InsertEventCoursePaymentDtoReq {

	private String[] idEvenCoursePaymentDetails;
	private String idPaymentMethod;
	
	@NotNull(message = "Total Price is Empty!")
	private BigInteger totalPrice;

	public String[] getIdEvenCoursePaymentDetails() {
		return idEvenCoursePaymentDetails;
	}

	public void setIdEvenCoursePaymentDetails(String[] idEvenCoursePaymentDetails) {
		this.idEvenCoursePaymentDetails = idEvenCoursePaymentDetails;
	}

	public String getIdPaymentMethod() {
		return idPaymentMethod;
	}

	public void setIdPaymentMethod(String idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}

	public BigInteger getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigInteger totalPrice) {
		this.totalPrice = totalPrice;
	}

}
