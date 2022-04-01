package com.lawencon.linovhrcommunity.dto.usermember;

import javax.validation.constraints.NotNull;

public class UpdateUserMemberPaymentDtoReq {
	@NotNull(message = "Order is Empty")
	private String idOrder;

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

}
