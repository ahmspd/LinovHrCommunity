package com.lawencon.linovhrcommunity.dto.pricetype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertPriceTypeDtoReq {

	@NotEmpty(message = "Code Is Empty")
	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@NotEmpty(message = "Price Type Name Is Empty")
	@Size(max = 30, min = 5, message = "Price Type Name Out Of Range")
	private String priceTypeName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
}
