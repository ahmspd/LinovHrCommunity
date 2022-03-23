package com.lawencon.linovhrcommunity.dto.threadtype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertThreadTypeDtoReq {
	@NotEmpty(message = "Error: Code is Empty! Please fill Code.")
	@Size(min = 5, max = 10, message = "Error:Code max 10 character!")
	private String code;

	@NotEmpty(message = "Error: Name is Empty! Please fill Name.")
	@Size(min = 5, max = 30, message = "Error:Name max 30 character!")
	private String threadTypeName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getThreadTypeName() {
		return threadTypeName;
	}

	public void setThreadTypeName(String threadTypeName) {
		this.threadTypeName = threadTypeName;
	}

}
