package com.lawencon.linovhrcommunity.dto.industry;

import java.util.List;

public class GetAllIndustryDtoRes {

	private String message;
	private List<GetAllIndustryDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllIndustryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllIndustryDtoDataRes> data) {
		this.data = data;
	}



}
