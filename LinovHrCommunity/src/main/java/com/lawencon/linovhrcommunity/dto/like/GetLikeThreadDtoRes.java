package com.lawencon.linovhrcommunity.dto.like;

public class GetLikeThreadDtoRes {
	private String message;
	private GetLikeThreadDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetLikeThreadDtoDataRes getData() {
		return data;
	}

	public void setData(GetLikeThreadDtoDataRes data) {
		this.data = data;
	}
}
