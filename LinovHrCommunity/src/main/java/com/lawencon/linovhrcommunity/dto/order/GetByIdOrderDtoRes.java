package com.lawencon.linovhrcommunity.dto.order;

public class GetByIdOrderDtoRes {
	private String message;
	private GetByIdOrderDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetByIdOrderDtoDataRes getData() {
		return data;
	}
	public void setData(GetByIdOrderDtoDataRes data) {
		this.data = data;
	}
}
