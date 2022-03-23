package com.lawencon.linovhrcommunity.dto.paymentmethod;

public class UpdatePaymentMethodDtoRes {
	private String message;
	private UpdatePaymentMethodDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UpdatePaymentMethodDtoDataRes getData() {
		return data;
	}

	public void setData(UpdatePaymentMethodDtoDataRes data) {
		this.data = data;
	}

}
