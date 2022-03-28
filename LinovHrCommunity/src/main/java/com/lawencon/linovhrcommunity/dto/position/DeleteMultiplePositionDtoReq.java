package com.lawencon.linovhrcommunity.dto.position;

import java.util.List;

public class DeleteMultiplePositionDtoReq {
	private List<DeleteMultiplePositionDtoDataReq> data;

	public List<DeleteMultiplePositionDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultiplePositionDtoDataReq> data) {
		this.data = data;
	}
}
