package com.lawencon.linovhrcommunity.constant;

public enum PriceListDuration {
	ONEMONTH("PL0001", 30), THREEMONTH("PL0002", 90);

	private String code;
	private Integer duration;

	PriceListDuration(String code, Integer duration) {
		this.code = code;
		this.duration = duration;
	}

	public String getCode() {
		return this.code;
	}

	public Integer getDuration() {
		return this.duration;
	}
}
