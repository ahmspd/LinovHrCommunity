package com.lawencon.linovhrcommunity.dto.position;

import java.util.List;

public class GetAllPositionDtoRes {

	private String message;
	private List<GetAllPositionDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllPositionDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPositionDtoDataRes> data) {
		this.data = data;
	}



}
