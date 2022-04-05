package com.lawencon.linovhrcommunity.dto.thread;

public class GetThreadDetailDtoRes {
	private String message;
	private GetThreadDataDtoRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetThreadDataDtoRes getData() {
		return data;
	}
	public void setData(GetThreadDataDtoRes data) {
		this.data = data;
	}
}
