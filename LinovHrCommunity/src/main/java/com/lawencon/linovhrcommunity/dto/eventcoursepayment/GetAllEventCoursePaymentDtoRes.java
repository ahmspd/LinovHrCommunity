package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import java.util.List;

public class GetAllEventCoursePaymentDtoRes {
	private List<GetAllEventCoursePaymentDtoDataRes> data;

	public List<GetAllEventCoursePaymentDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventCoursePaymentDtoDataRes> data) {
		this.data = data;
	}
}
