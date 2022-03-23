package com.lawencon.linovhrcommunity.dto.role;

import java.util.List;

public class GetAllRoleDtoRes {
	private String message;
	private List<GetAllRoleDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllRoleDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllRoleDtoDataRes> data) {
		this.data = data;
	}

	
}
