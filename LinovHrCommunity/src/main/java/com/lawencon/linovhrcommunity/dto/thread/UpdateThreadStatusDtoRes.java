package com.lawencon.linovhrcommunity.dto.thread;

public class UpdateThreadStatusDtoRes {
	private String message;
	private UpdateThreadStatusDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UpdateThreadStatusDtoDataRes getData() {
		return data;
	}

	public void setData(UpdateThreadStatusDtoDataRes data) {
		this.data = data;
	}
}
