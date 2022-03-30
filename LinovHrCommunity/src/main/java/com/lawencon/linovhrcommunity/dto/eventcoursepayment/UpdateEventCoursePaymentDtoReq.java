package com.lawencon.linovhrcommunity.dto.eventcoursepayment;

import javax.validation.constraints.NotEmpty;

public class UpdateEventCoursePaymentDtoReq {

	@NotEmpty(message = "Id Is Null")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
