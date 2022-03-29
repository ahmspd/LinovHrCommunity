package com.lawencon.linovhrcommunity.constant;

public enum ThreadTypeCode {
//	TYP001	Thread
//	TYP002	Artikel
//	TPP001	Premium
//	PL0001	Polling
	THREAD("TYP001"),ARTICLE("TYP002"),PREMIUM("TPP001"),POLLING("PL0001");
	
	private String detail;
	
	ThreadTypeCode(String detail){
		this.detail = detail;
	}
	
	public String getDetail() {
		return this.detail;
	}
}
