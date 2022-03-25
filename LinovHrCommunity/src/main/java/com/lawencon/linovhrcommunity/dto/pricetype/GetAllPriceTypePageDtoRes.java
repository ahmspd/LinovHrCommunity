package com.lawencon.linovhrcommunity.dto.pricetype;

import java.util.List;

public class GetAllPriceTypePageDtoRes {
	private Long total;
	private List<GetAllPriceTypePageDtoDataRes> data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<GetAllPriceTypePageDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllPriceTypePageDtoDataRes> data) {
		this.data = data;
	}
}
