package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lawencon.linovhrcommunity.dto.category.GetAllCategoryThreadDetail;
import com.lawencon.linovhrcommunity.dto.pollingdetail.InsertPollingDetailDtoReq;

public class InsertThreadDtoReq {
	
	@NotEmpty(message = "Title Is Empty")
	@Size(max = 200, min = 5, message = "Title Out Of Range")
	private String title;
	
	@NotEmpty(message = "Contents Is Empty")
	@Size(min = 5, message = "Contents Out Of Range")
	private String contents;
	
	@NotNull(message = "Thread Type is required")
	private String idThreadType;
	
	private Boolean isPremium;
	private String pollingName;
	private Boolean isActive;
	private List<InsertPollingDetailDtoReq> dataPolling;
	private List<GetAllCategoryThreadDetail> dataCategory;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public List<InsertPollingDetailDtoReq> getDataPolling() {
		return dataPolling;
	}
	public void setDataPolling(List<InsertPollingDetailDtoReq> dataPolling) {
		this.dataPolling = dataPolling;
	}
	public List<GetAllCategoryThreadDetail> getDataCategory() {
		return dataCategory;
	}
	public void setDataCategory(List<GetAllCategoryThreadDetail> dataCategory) {
		this.dataCategory = dataCategory;
	}
}
