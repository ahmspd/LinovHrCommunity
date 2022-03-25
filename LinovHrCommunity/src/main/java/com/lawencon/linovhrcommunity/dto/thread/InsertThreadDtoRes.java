package com.lawencon.linovhrcommunity.dto.thread;

public class InsertThreadDtoRes {
	private String message;
	private InsertThreadDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InsertThreadDtoDataRes getData() {
		return data;
	}

	public void setData(InsertThreadDtoDataRes data) {
		this.data = data;
	}
}
