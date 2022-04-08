package com.lawencon.linovhrcommunity.dto.user;

public class UpdatePasswordDtoRes {
	private String message;
	private UpdatePasswordDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UpdatePasswordDtoDataRes getData() {
		return data;
	}

	public void setData(UpdatePasswordDtoDataRes data) {
		this.data = data;
	}
}
