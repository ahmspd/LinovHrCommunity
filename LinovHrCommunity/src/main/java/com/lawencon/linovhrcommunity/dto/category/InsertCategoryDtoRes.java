package com.lawencon.linovhrcommunity.dto.category;

public class InsertCategoryDtoRes {
	private String message;
	private InsertCategoryDtoDataRes data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InsertCategoryDtoDataRes getData() {
		return data;
	}
	public void setData(InsertCategoryDtoDataRes data) {
		this.data = data;
	}
}
