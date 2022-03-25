package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;

public class InsertThreadDtoReq {
	private String title;
	private String contents;
	private String idThreadType;
	private Boolean isPremium;
	private String pollingName;
	private List<InsertPollingDetailDtoReq> data;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIdThreadType() {
		return idThreadType;
	}

	public void setIdThreadType(String idThreadType) {
		this.idThreadType = idThreadType;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public List<InsertPollingDetailDtoReq> getData() {
		return data;
	}

	public void setData(List<InsertPollingDetailDtoReq> data) {
		this.data = data;
	}
}
