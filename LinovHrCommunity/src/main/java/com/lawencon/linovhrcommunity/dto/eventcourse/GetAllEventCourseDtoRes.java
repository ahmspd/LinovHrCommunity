package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.util.List;

public class GetAllEventCourseDtoRes {
	private List<GetAllEventCourseDtoDataRes> data;

	public List<GetAllEventCourseDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventCourseDtoDataRes> data) {
		this.data = data;
	}
}
