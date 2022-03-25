package com.lawencon.linovhrcommunity.dto.pricelist;

import java.util.List;

public class GetAllPriceListPageDtoRes {
	private Long total;
	private List<GetAllPriceListPageDtoDataRes> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<GetAllPriceListPageDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPriceListPageDtoDataRes> data) {
		this.data = data;
	}
}
