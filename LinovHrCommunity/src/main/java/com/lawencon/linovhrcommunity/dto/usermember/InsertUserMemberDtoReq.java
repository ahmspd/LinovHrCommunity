package com.lawencon.linovhrcommunity.dto.usermember;

import javax.validation.constraints.NotEmpty;

public class InsertUserMemberDtoReq {

	@NotEmpty(message = "Price List Is Empty")
	private String idPriceList;

	public String getIdPriceList() {
		return idPriceList;
	}

	public void setIdPriceList(String idPriceList) {
		this.idPriceList = idPriceList;
	}

}
