package com.lawencon.linovhrcommunity.dto.category;

import java.util.List;

public class GetAllCategoryPageDtoRes {
	private Long total;
	private List<GetAllCategoryPageDtoDataRes> data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<GetAllCategoryPageDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllCategoryPageDtoDataRes> data) {
		this.data = data;
	}
}
