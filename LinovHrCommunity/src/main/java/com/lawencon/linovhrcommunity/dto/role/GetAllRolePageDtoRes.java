package com.lawencon.linovhrcommunity.dto.role;

import java.util.List;

public class GetAllRolePageDtoRes {
	private Long total;
	private List<GetAllRolePageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllRolePageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllRolePageDtoDataRes> data) {
		this.data = data;
	}
}
