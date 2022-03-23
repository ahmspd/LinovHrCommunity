package com.lawencon.linovhrcommunity.dto.eventcoursetype;

import java.util.List;

public class GetAllEventCourseTypeDtoRes {
	private String message;
	private List<GetAllEventCourseTypeDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllEventCourseTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventCourseTypeDtoDataRes> data) {
		this.data = data;
	}

	
}
