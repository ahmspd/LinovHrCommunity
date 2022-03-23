package com.lawencon.linovhrcommunity.dto.position;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertPositionDtoReq {

	@NotEmpty(message = "Code Is Empty")
	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@NotEmpty(message = "Position Name Is Empty")
	@Size(max = 30, min = 5, message = "Position Name Out Of Range")
	private String positionName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
