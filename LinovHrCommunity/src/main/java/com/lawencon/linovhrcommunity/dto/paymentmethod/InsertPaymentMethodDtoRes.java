package com.lawencon.linovhrcommunity.dto.paymentmethod;

public class InsertPaymentMethodDtoRes {
	private String message;
	private InsertPaymentMethodDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InsertPaymentMethodDtoDataRes getData() {
		return data;
	}

	public void setData(InsertPaymentMethodDtoDataRes data) {
		this.data = data;
	}

}
