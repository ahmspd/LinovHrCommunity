package com.lawencon.linovhrcommunity.dto.usermember;

import java.util.List;

public class GetAllUserMemberDtoRes {

	private String message;
	private List<GetAllUserMemberDtoDataRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GetAllUserMemberDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllUserMemberDtoDataRes> data) {
		this.data = data;
	}

}
