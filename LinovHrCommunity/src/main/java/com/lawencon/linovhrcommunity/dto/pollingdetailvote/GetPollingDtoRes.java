package com.lawencon.linovhrcommunity.dto.pollingdetailvote;

public class GetPollingDtoRes {
	private String message;
	private GetPollingDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetPollingDtoDataRes getData() {
		return data;
	}
	public void setData(GetPollingDtoDataRes data) {
		this.data = data;
	}
}
