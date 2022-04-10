package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.util.List;

public class GetAllEventCoursePaymentDtoRes {
	private Integer total;
	private List<GetAllEventCoursePaymentDtoDataRes> data;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<GetAllEventCoursePaymentDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllEventCoursePaymentDtoDataRes> data) {
		this.data = data;
	}
}
