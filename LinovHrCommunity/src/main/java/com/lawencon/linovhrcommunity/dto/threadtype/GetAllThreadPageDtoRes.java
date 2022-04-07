package com.lawencon.linovhrcommunity.dto.threadtype;

import java.util.List;

import com.lawencon.linovhrcommunity.dto.thread.GetThreadDataDtoRes;

public class GetAllThreadPageDtoRes {
	private Integer total;
	private List<GetThreadDataDtoRes> data;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<GetThreadDataDtoRes> getData() {
		return data;
	}
	public void setData(List<GetThreadDataDtoRes> data) {
		this.data = data;
	}
}
