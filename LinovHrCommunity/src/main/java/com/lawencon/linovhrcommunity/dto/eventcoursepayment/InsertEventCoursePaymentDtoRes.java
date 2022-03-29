package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

public class InsertEventCoursePaymentDtoRes {
	private String message;
	private InsertEventCoursePaymentDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InsertEventCoursePaymentDtoDataRes getData() {
		return data;
	}

	public void setData(InsertEventCoursePaymentDtoDataRes data) {
		this.data = data;
	}
}
