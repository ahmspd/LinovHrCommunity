package com.lawencon.linovhrcommunity.dto.pricetype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdatePriceTypeDtoReq {

	@NotEmpty(message = "Id Is Null")
	private String id;

	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@Size(max = 30, min = 5, message = "Price Name Out Of Range")
	private String priceTypeName;

	@NotNull(message = "Version Is Null")
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
