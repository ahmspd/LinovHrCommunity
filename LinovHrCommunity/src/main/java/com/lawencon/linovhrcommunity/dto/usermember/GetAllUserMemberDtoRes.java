package com.lawencon.linovhrcommunity.dto.usermember;

import java.util.List;

public class GetAllUserMemberDtoRes {

	private String message;
	private Integer total;
	private List<GetAllUserMemberDtoDataRes> data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<GetAllUserMemberDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetAllUserMemberDtoDataRes> data) {
		this.data = data;
	}

}
