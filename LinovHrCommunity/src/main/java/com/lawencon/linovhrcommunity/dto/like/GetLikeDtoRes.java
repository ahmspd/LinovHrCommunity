package com.lawencon.linovhrcommunity.dto.like;

import java.util.List;

public class GetLikeDtoRes {
	private String message;
	private List<GetLikeDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetLikeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetLikeDtoDataRes> data) {
		this.data = data;
	}
}
