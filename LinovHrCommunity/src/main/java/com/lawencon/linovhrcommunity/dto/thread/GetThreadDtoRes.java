package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

public class GetThreadDtoRes {
	private String message;
	private List<GetThreadDataDtoRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GetThreadDataDtoRes> getData() {
		return data;
	}
	public void setData(List<GetThreadDataDtoRes> data) {
		this.data = data;
	}
}
