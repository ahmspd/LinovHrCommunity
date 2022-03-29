package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.math.BigInteger;

public class InsertEventCoursePaymentDtoReq {

	private String[] idEvenCoursePaymentDetails;
	private String idPaymentMethod;
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
