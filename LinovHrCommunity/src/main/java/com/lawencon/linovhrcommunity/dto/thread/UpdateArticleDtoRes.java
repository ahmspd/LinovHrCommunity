package com.lawencon.linovhrcommunity.dto.thread;

public class UpdateArticleDtoRes {
	private String message;
	private UpdateArticleDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UpdateArticleDtoDataRes getData() {
		return data;
	}
	public void setData(UpdateArticleDtoDataRes data) {
		this.data = data;
	}
}
