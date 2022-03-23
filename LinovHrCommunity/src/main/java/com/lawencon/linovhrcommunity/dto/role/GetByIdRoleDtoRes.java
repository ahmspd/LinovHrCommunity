package com.lawencon.linovhrcommunity.dto.role;

public class GetByIdRoleDtoRes {
	private String message;
	private GetByIdRoleDtoDataRes data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GetByIdRoleDtoDataRes getData() {
		return data;
	}

	public void setData(GetByIdRoleDtoDataRes data) {
		this.data = data;
	}

}
