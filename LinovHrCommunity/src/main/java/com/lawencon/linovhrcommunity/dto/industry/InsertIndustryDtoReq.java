package com.lawencon.linovhrcommunity.dto.industry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertIndustryDtoReq {

	@NotEmpty(message = "Code Is Empty")
	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@NotEmpty(message = "Industry Name Is Empty")
	@Size(max = 30, min = 5, message = "Industry Name Out Of Range")
	private String industryName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}


}
