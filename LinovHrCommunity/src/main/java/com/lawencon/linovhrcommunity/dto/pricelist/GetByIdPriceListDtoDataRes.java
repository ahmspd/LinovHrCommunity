package com.lawencon.linovhrcommunity.dto.pricelist;

import java.math.BigInteger;

public class GetByIdPriceListDtoDataRes {

	private String id;
	private String code;
	private String priceName;
	private String priceTypeName;
	private String idPriceType;
	private BigInteger price;
	private Integer version;
	private Boolean isActive;
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
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public String getIdPriceType() {
		return idPriceType;
	}
	public void setIdPriceType(String idPriceType) {
		this.idPriceType = idPriceType;
	}
	public BigInteger getPrice() {
		return price;
	}
	public void setPrice(BigInteger price) {
		this.price = price;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
