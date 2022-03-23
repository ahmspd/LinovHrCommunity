package com.lawencon.linovhrcommunity.dto.eventcoursetype;

public class GetByIdEventCourseTypeDtoRes {
	private String message;
	private GetByIdEventCourseTypeDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetByIdEventCourseTypeDtoDataRes getData() {
		return data;
	}

	public void setData(GetByIdEventCourseTypeDtoDataRes data) {
		this.data = data;
	}

}
