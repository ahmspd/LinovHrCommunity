package com.lawencon.linovhrcommunity.dto.pricetype;

import java.util.List;

public class DeleteMultiplePriceTypeDtoReq {
	private List<DeleteMultiplePriceTypeDtoDataReq> data;

	public List<DeleteMultiplePriceTypeDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultiplePriceTypeDtoDataReq> data) {
		this.data = data;
	}
}
