package com.lawencon.linovhrcommunity.dto.user;

public class RegistrationCodeDtoRes {
	private String message;
	private RegistrationCodeDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RegistrationCodeDtoDataRes getData() {
		return data;
	}

	public void setData(RegistrationCodeDtoDataRes data) {
		this.data = data;
	}
}
