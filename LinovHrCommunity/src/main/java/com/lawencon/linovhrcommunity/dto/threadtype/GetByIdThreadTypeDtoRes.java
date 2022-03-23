package com.lawencon.linovhrcommunity.dto.threadtype;

public class GetByIdThreadTypeDtoRes {
	private String message;
	private GetByIdThreadTypeDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetByIdThreadTypeDtoDataRes getData() {
		return data;
	}

	public void setData(GetByIdThreadTypeDtoDataRes data) {
		this.data = data;
	}

}
