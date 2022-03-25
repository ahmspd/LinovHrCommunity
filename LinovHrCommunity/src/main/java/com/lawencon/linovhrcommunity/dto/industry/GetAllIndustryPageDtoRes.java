package com.lawencon.linovhrcommunity.dto.industry;

import java.util.List;

public class GetAllIndustryPageDtoRes {
	private Long total;
	private List<GetAllIndustryPageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllIndustryPageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllIndustryPageDtoDataRes> data) {
		this.data = data;
	}
}
