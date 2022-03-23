package com.lawencon.linovhrcommunity.dto.paymentmethod;

import java.util.List;

public class GetAllPaymentMethodDtoRes {
	private String message;
	private List<GetAllPaymentMethodDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllPaymentMethodDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPaymentMethodDtoDataRes> data) {
		this.data = data;
	}

	
}
