package com.lawencon.linovhrcommunity.dto.eventcoursepaymentdetail;

import java.util.List;

public class GetAllEventCoursePaymentDetailDtoRes {
	private List<GetAllEventCoursePaymentDetailDtoDataRes> data;

	public List<GetAllEventCoursePaymentDetailDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventCoursePaymentDetailDtoDataRes> data) {
		this.data = data;
	}
}
