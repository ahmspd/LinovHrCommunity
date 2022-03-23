package com.lawencon.linovhrcommunity.dto.user;

public class LoginUserDtoRes {
	private String message;
	private LoginUserDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginUserDtoDataRes getData() {
		return data;
	}
	public void setData(LoginUserDtoDataRes data) {
		this.data = data;
	}
}
