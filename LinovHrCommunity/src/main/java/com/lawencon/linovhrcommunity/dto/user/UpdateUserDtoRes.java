package com.lawencon.linovhrcommunity.dto.user;

public class UpdateUserDtoRes {
	private String message;
	private UpdateUserDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UpdateUserDtoDataRes getData() {
		return data;
	}

	public void setData(UpdateUserDtoDataRes data) {
		this.data = data;
	}
}
