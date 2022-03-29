package com.lawencon.linovhrcommunity.dto.threadtype;

import java.util.List;

public class DeleteMultipleThreadTypeDtoReq {
	private List<DeleteMultipleThreadTypeDtoDataReq> data;

	public List<DeleteMultipleThreadTypeDtoDataReq> getData() {
		return data;
	}

	public void setData(List<DeleteMultipleThreadTypeDtoDataReq> data) {
		this.data = data;
	}
}
