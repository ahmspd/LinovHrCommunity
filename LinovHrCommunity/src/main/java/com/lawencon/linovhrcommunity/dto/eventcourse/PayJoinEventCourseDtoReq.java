package com.lawencon.linovhrcommunity.dto.eventcourse;

public class PayJoinEventCourseDtoReq {

	private String idOrder;
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
