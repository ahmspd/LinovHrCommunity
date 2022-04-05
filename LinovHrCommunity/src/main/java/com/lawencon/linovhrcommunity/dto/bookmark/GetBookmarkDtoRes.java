package com.lawencon.linovhrcommunity.dto.bookmark;

import java.util.List;

public class GetBookmarkDtoRes {
	private String message;
	private List<GetBookmarkDtoDataRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GetBookmarkDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetBookmarkDtoDataRes> data) {
		this.data = data;
	}
}
