package com.lawencon.linovhrcommunity.dto.dashboard;

public class GetDashboardDtoRes {
	private String message;
	private GetDashboardDataDtoRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetDashboardDataDtoRes getData() {
		return data;
	}

	public void setData(GetDashboardDataDtoRes data) {
		this.data = data;
	}
}
