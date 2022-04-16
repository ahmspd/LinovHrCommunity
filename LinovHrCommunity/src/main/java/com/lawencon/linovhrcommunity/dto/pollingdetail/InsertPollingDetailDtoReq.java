package com.lawencon.linovhrcommunity.dto.pollingdetail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertPollingDetailDtoReq {
	@NotEmpty(message = "Polling Name is empty!")
	@Size(min = 5, max = 50, message = "Polling Name is out of range!")
	private String pollingName;

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}
}
