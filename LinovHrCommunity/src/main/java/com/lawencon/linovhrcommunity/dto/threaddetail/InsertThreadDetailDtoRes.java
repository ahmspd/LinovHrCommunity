package com.lawencon.linovhrcommunity.dto.threaddetail;

public class InsertThreadDetailDtoRes {
	private String message;
	private InsertThreadDetailDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InsertThreadDetailDtoDataRes getData() {
		return data;
	}

	public void setData(InsertThreadDetailDtoDataRes data) {
		this.data = data;
	}
}
