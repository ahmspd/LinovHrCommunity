package com.lawencon.linovhrcommunity.dto.city;

import java.util.List;

public class GetAllCityByProvinceDtoRes {
	private String message;
	private List<GetAllCityByProvinceDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllCityByProvinceDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllCityByProvinceDtoDataRes> data) {
		this.data = data;
	}
}
