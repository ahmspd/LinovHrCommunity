package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_payment_method")
public class PaymentMethod extends BaseEntity {
	private static final long serialVersionUID = -5196455701225322056L;

	@Column(length = 10, nullable = false)
	private String code;

	@Column(name = "payment_name", length = 30, nullable = false)
	private String paymentName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

}
