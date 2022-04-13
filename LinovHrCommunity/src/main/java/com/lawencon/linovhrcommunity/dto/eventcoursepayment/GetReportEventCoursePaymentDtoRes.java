package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.util.List;

public class GetReportEventCoursePaymentDtoRes {
	private Integer total;
	private Float totalPrice;
	private List<GetReportEventCoursePaymentDtoDataRes> data;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<GetReportEventCoursePaymentDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetReportEventCoursePaymentDtoDataRes> data) {
		this.data = data;
	}
}
