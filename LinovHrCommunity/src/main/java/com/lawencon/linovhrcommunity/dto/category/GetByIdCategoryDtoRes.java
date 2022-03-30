package com.lawencon.linovhrcommunity.dto.category;

public class GetByIdCategoryDtoRes {
	private String message;
	private GetByIdCategoryDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetByIdCategoryDtoDataRes getData() {
		return data;
	}
	public void setData(GetByIdCategoryDtoDataRes data) {
		this.data = data;
	}
}
