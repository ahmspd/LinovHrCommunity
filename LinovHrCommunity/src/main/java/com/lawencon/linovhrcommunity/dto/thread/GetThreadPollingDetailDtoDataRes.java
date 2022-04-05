package com.lawencon.linovhrcommunity.dto.thread;

import java.util.List;

import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetail.GetPollingDetailByPollingIdDto;

public class GetThreadPollingDetailDtoDataRes {
	private String id;
	private String title;
	private String contents;
	private String idFile;
	private String extendions;
	private byte[] contentsFile;
	private String threadTypeName;
	private Boolean isPremium;
	private String createdAt;
	private String createdBy;
	private String fullName;
	private String pollingName;
	private String idPolling;
	private List<GetCategoryDetailByThreadDtoRes> dataCategoryDetail;
	private List<GetPollingDetailByPollingIdDto> dataPollingDetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public String getExtendions() {
		return extendions;
	}

	public void setExtendions(String extendions) {
		this.extendions = extendions;
	}

	public byte[] getContentsFile() {
		return contentsFile;
	}

	public void setContentsFile(byte[] contentsFile) {
		this.contentsFile = contentsFile;
	}

	public String getThreadTypeName() {
		return threadTypeName;
	}

	public void setThreadTypeName(String threadTypeName) {
		this.threadTypeName = threadTypeName;
	}

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getIdPolling() {
		return idPolling;
	}

	public void setIdPolling(String idPolling) {
		this.idPolling = idPolling;
	}

	public List<GetCategoryDetailByThreadDtoRes> getDataCategoryDetail() {
		return dataCategoryDetail;
	}

	public void setDataCategoryDetail(List<GetCategoryDetailByThreadDtoRes> dataCategoryDetail) {
		this.dataCategoryDetail = dataCategoryDetail;
	}

	public List<GetPollingDetailByPollingIdDto> getDataPollingDetail() {
		return dataPollingDetail;
	}

	public void setDataPollingDetail(List<GetPollingDetailByPollingIdDto> dataPollingDetail) {
		this.dataPollingDetail = dataPollingDetail;
	}
}
