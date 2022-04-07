package com.lawencon.linovhrcommunity.dto.province;

import java.util.List;

public class GetAllProvinceDtoRes {
	private String message;
	private List<GetAllProvinceDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllProvinceDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllProvinceDtoDataRes> data) {
		this.data = data;
	}
}
