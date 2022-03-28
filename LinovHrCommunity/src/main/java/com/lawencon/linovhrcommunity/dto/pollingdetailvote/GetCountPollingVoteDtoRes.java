package com.lawencon.linovhrcommunity.dto.pollingdetailvote;

import java.util.List;

public class GetCountPollingVoteDtoRes {
	private Long total;
	private List<GetCountPollingVoteDtoDataRes> data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<GetCountPollingVoteDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetCountPollingVoteDtoDataRes> data) {
		this.data = data;
	}
}
