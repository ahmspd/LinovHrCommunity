package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_price_type", uniqueConstraints = @UniqueConstraint(name = "price_type_code_bk", columnNames = "code"))
public class PriceType extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@Column(length = 10, nullable = false)
	private String code;

	@Column(name = "price_type_name", length = 30, nullable = false)
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
