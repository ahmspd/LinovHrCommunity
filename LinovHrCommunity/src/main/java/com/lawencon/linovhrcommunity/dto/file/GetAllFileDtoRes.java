package com.lawencon.linovhrcommunity.dto.file;

import java.util.List;

public class GetAllFileDtoRes {

	private String message;
	private List<GetAllFileDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllFileDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllFileDtoDataRes> data) {
		this.data = data;
	}



}
