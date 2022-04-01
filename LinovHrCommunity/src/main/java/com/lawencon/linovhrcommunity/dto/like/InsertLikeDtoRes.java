package com.lawencon.linovhrcommunity.dto.like;

public class InsertLikeDtoRes {
	private String message;
	private InsertLikeDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InsertLikeDtoDataRes getData() {
		return data;
	}
	public void setData(InsertLikeDtoDataRes data) {
		this.data = data;
	}
}
