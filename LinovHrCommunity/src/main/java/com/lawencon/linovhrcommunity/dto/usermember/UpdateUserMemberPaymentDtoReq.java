package com.lawencon.linovhrcommunity.dto.usermember;

import javax.validation.constraints.NotNull;

public class UpdateUserMemberPaymentDtoReq {
	@NotNull(message = "Order is Empty")
	private String idOrder;
	
	@NotNull(message = "Order is Empty")
	private String idPaymentMethod;

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getIdPaymentMethod() {
		return idPaymentMethod;
	}

	public void setIdPaymentMethod(String idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}

}
