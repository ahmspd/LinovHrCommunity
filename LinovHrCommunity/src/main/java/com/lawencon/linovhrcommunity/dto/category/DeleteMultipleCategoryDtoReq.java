package com.lawencon.linovhrcommunity.dto.category;

import java.util.List;

public class DeleteMultipleCategoryDtoReq {
	private List<DeleteMultipleCategoryDtoDataReq> data;

	public List<DeleteMultipleCategoryDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultipleCategoryDtoDataReq> data) {
		this.data = data;
	}
}
