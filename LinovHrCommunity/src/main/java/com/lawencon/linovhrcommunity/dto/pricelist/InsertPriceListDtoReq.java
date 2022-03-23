package com.lawencon.linovhrcommunity.dto.pricelist;

import java.math.BigInteger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertPriceListDtoReq {

	@NotEmpty(message = "Code Is Empty")
	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@NotEmpty(message = "Price Name Is Empty")
	@Size(max = 30, min = 5, message = "Price Name Out Of Range")
	private String priceName;

	@NotNull(message = "Price Is Null")
	private BigInteger price;

	@NotNull(message = "ID Price Type Is Null")
	private String idPriceType;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getIdPriceType() {
		return idPriceType;
	}

	public void setIdPriceType(String idPriceType) {
		this.idPriceType = idPriceType;
	}
}
