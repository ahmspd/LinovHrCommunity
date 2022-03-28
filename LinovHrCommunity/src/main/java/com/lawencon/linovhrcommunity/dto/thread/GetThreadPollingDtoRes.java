package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

public class GetThreadPollingDtoRes {
	private String message;
	private List<GetThreadPollingDtoDataRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GetThreadPollingDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetThreadPollingDtoDataRes> data) {
		this.data = data;
	}
}
