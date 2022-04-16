package com.lawencon.linovhrcommunity.dto.like;

import javax.validation.constraints.NotEmpty;

public class InsertLikeDtoReq {
	@NotEmpty(message = "Id Thread is Null")
	private String idThread;

	public String getIdThread() {
		return idThread;
	}

	public void setIdThread(String idThread) {
		this.idThread = idThread;
	}
}
