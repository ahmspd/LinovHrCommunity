package com.lawencon.linovhrcommunity.dto.threadtype;

import java.util.List;

public class GetAllThreadTypeDtoRes {
	private String message;
	private List<GetAllThreadTypeDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllThreadTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadTypeDtoDataRes> data) {
		this.data = data;
	}

	
}
