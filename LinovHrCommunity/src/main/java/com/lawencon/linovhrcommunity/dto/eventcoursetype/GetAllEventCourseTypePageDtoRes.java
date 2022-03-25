package com.lawencon.linovhrcommunity.dto.eventcoursetype;

import java.util.List;

public class GetAllEventCourseTypePageDtoRes {
	private Long total;
	private List<GetAllEventCourseTypePageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllEventCourseTypePageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllEventCourseTypePageDtoDataRes> data) {
		this.data = data;
	}
}
