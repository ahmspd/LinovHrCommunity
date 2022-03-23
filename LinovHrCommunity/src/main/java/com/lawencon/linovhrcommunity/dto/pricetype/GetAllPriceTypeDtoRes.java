package com.lawencon.linovhrcommunity.dto.pricetype;

import java.util.List;

public class GetAllPriceTypeDtoRes {

	private String message;
	private List<GetAllPriceTypeDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllPriceTypeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPriceTypeDtoDataRes> data) {
		this.data = data;
	}
}
