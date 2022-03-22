package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_price_list")
public class PriceList extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@Column(nullable = false)
	private String code;

	@Column(name = "price_name", nullable = false)
	private String priceName;

	@Column(nullable = false)
	private Integer price;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
