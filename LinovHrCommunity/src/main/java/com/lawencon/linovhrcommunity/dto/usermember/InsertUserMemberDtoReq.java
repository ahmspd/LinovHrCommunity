package com.lawencon.linovhrcommunity.dto.usermember;

import javax.validation.constraints.NotEmpty;

public class InsertUserMemberDtoReq {

	@NotEmpty(message = "Price List Is Empty")
	private String idPriceList;

	@NotEmpty(message = "Payment Method Is Empty")
	private String idPaymentMethod;

	public String getIdPriceList() {
		return idPriceList;
	}

	public void setIdPriceList(String idPriceList) {
		this.idPriceList = idPriceList;
	}

	public String getIdPaymentMethod() {
		return idPaymentMethod;
	}

	public void setIdPaymentMethod(String idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}

}
