package com.lawencon.linovhrcommunity.dto.bookmark;

public class GetBookmarkThreadDtoRes {
	private String message;
	private GetBookmarkThreadDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetBookmarkThreadDtoDataRes getData() {
		return data;
	}
	public void setData(GetBookmarkThreadDtoDataRes data) {
		this.data = data;
	}
}
