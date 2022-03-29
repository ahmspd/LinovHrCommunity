package com.lawencon.linovhrcommunity.dto.pricelist;

import java.util.List;

public class DeleteMultiplePriceListDtoReq {
	private List<DeleteMultiplePriceListDtoDataReq> data;

	public List<DeleteMultiplePriceListDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultiplePriceListDtoDataReq> data) {
		this.data = data;
	}
}
