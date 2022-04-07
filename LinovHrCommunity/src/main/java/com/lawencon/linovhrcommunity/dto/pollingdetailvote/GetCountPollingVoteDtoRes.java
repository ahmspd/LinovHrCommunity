package com.lawencon.linovhrcommunity.dto.pollingdetailvote;

import java.util.List;

public class GetCountPollingVoteDtoRes {
	private Integer total;
	private List<GetCountPollingVoteDtoDataRes> data;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<GetCountPollingVoteDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetCountPollingVoteDtoDataRes> data) {
		this.data = data;
	}
}
