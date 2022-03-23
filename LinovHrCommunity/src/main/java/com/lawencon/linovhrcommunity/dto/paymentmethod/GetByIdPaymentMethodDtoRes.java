package com.lawencon.linovhrcommunity.dto.paymentmethod;

public class GetByIdPaymentMethodDtoRes {
	private String message;
	private GetByIdPaymentMethodDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetByIdPaymentMethodDtoDataRes getData() {
		return data;
	}

	public void setData(GetByIdPaymentMethodDtoDataRes data) {
		this.data = data;
	}

}
