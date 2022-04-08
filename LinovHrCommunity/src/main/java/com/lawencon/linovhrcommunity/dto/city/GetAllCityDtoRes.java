package com.lawencon.linovhrcommunity.dto.city;

import java.util.List;

public class GetAllCityDtoRes {
	private String message;
	private List<GetAllCityDtoDataRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<GetAllCityDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllCityDtoDataRes> data) {
		this.data = data;
	}
}
