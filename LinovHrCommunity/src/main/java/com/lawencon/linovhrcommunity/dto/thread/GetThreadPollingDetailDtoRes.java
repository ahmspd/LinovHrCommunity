package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

public class GetThreadPollingDetailDtoRes {
	private String message;
	private GetThreadPollingDetailDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetThreadPollingDetailDtoDataRes getData() {
		return data;
	}

	public void setData(GetThreadPollingDetailDtoDataRes data) {
		this.data = data;
	}
}
