package com.lawencon.linovhrcommunity.dto.pollingdetailvote;

public class InsertPollingVoteDtoRes {
	private String message;
	private InsertPollingVoteDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InsertPollingVoteDtoDataRes getData() {
		return data;
	}

	public void setData(InsertPollingVoteDtoDataRes data) {
		this.data = data;
	}
}
