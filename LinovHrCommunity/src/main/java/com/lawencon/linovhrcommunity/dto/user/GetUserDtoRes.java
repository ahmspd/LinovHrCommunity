package com.lawencon.linovhrcommunity.dto.user;

public class GetUserDtoRes {
	private String message;
	private GetUserDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetUserDtoDataRes getData() {
		return data;
	}

	public void setData(GetUserDtoDataRes data) {
		this.data = data;
	}
}
