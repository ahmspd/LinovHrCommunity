package com.lawencon.linovhrcommunity.dto.paymentmethod;

import java.util.List;

public class GetAllPaymentMethodPageDtoRes {
	private Long total;
	private List<GetAllPaymentMethodPageDtoDataRes> data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<GetAllPaymentMethodPageDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllPaymentMethodPageDtoDataRes> data) {
		this.data = data;
	}
}
