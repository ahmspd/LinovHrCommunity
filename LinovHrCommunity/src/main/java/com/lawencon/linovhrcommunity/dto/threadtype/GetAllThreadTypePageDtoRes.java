package com.lawencon.linovhrcommunity.dto.threadtype;

import java.util.List;

public class GetAllThreadTypePageDtoRes {
	private Long total;
	private List<GetAllThreadTypePageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllThreadTypePageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadTypePageDtoDataRes> data) {
		this.data = data;
	}
}
