package com.lawencon.linovhrcommunity.dto.bookmark;

import javax.validation.constraints.NotEmpty;

public class InsertBookmarkDtoReq {
	@NotEmpty(message = "Id Thread is Null")
	private String idThread;

	public String getIdThread() {
		return idThread;
	}

	public void setIdThread(String idThread) {
		this.idThread = idThread;
	}
}
