package com.lawencon.linovhrcommunity.dto.industry;

import java.util.List;

public class DeleteMultipleIndustryDtoReq {
	private List<DeleteMultipleIndustryDtoDataReq> data;

	public List<DeleteMultipleIndustryDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultipleIndustryDtoDataReq> data) {
		this.data = data;
	}
}
