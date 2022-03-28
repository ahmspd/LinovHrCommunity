package com.lawencon.linovhrcommunity.dto.paymentmethod;

import java.util.List;

public class DeleteMultiplePaymentMethodDtoReq {
	private List<DeleteMultiplePaymentMethodDtoDataReq> data;

	public List<DeleteMultiplePaymentMethodDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultiplePaymentMethodDtoDataReq> data) {
		this.data = data;
	}
}
