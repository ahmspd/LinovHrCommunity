package com.lawencon.linovhrcommunity.dto.thread;

import javax.validation.constraints.NotNull;

public class UpdateThreadStatusDtoReq {
	@NotNull(message = "Id is required")
	private String id;
	
	@NotNull(message = "isActive is required")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
