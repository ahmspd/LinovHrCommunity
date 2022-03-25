package com.lawencon.linovhrcommunity.dto.position;

import java.util.List;

public class GetAllPositionPageDtoRes {
	private Long total;
	private List<GetAllPositionPageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllPositionPageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPositionPageDtoDataRes> data) {
		this.data = data;
	}
}
