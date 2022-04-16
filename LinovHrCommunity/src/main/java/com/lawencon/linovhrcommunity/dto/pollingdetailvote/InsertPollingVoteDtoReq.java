package com.lawencon.linovhrcommunity.dto.pollingdetailvote;

import javax.validation.constraints.NotEmpty;

public class InsertPollingVoteDtoReq {
	@NotEmpty(message = "Polling Detail is Empty")
	private String idPollingDetail;

	public String getIdPollingDetail() {
		return idPollingDetail;
	}

	public void setIdPollingDetail(String idPollingDetail) {
		this.idPollingDetail = idPollingDetail;
	}
}
