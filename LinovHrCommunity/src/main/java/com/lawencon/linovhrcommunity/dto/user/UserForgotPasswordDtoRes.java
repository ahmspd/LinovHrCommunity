package com.lawencon.linovhrcommunity.dto.user;

public class UserForgotPasswordDtoRes {
	private String message;
	private UserForgotPasswordDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserForgotPasswordDtoDataRes getData() {
		return data;
	}

	public void setData(UserForgotPasswordDtoDataRes data) {
		this.data = data;
	}
}
