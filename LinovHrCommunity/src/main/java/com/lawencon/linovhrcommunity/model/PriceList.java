package com.lawencon.linovhrcommunity.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_price_list", uniqueConstraints = @UniqueConstraint(name = "price_list_bk", columnNames = "code"))
public class PriceList extends BaseEntity {
	private static final long serialVersionUID = -9034083546988015483L;

	@Column(length = 10, nullable = false)
	private String code;

	@Column(name = "price_name", length = 30, nullable = false)
	private String priceName;

	@Column(nullable = false)
	private BigInteger price;

	@OneToOne
	@JoinColumn(name = "id_price_type", referencedColumnName = "id")
	private PriceType priceType;

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

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

}
