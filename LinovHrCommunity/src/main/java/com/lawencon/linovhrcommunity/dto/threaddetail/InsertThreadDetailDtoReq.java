package com.lawencon.linovhrcommunity.dto.threaddetail;

import javax.validation.constraints.NotEmpty;

public class InsertThreadDetailDtoReq {
	@NotEmpty(message = "Id Thread is Empty!")
	private String idThread;
	
	@NotEmpty(message = "Contents is Empty")
	private String contents;

	public String getIdThread() {
		return idThread;
	}

	public void setIdThread(String idThread) {
		this.idThread = idThread;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
}
