package com.lawencon.linovhrcommunity.dto.file;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateFileDtoReq {

	@NotEmpty(message = "Id Is Null")
	private String id;

	@NotNull(message = "Version Is Null")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
