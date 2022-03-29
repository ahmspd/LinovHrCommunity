package com.lawencon.linovhrcommunity.dto.pricelist;

import java.math.BigInteger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdatePriceListDtoReq {

	@NotEmpty(message = "Id Is Null")
	private String id;

	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@Size(max = 30, min = 5, message = "Price Name Out Of Range")
	private String priceName;

	private BigInteger price;
	
	private String idPriceType;

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
