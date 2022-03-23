package com.lawencon.linovhrcommunity.dto.pricelist;

import java.util.List;

public class GetAllPriceListDtoRes {

	private String message;
	private List<GetAllPriceListDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllPriceListDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPriceListDtoDataRes> data) {
		this.data = data;
	}



}
