package com.lawencon.linovhrcommunity.dto.category;

import java.util.List;

public class GetAllCategoryDtoRes {
	private String message;
	private List<GetAllCategoryDtoDataRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GetAllCategoryDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllCategoryDtoDataRes> data) {
		this.data = data;
	}
}
