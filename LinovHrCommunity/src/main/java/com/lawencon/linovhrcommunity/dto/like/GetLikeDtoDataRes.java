package com.lawencon.linovhrcommunity.dto.like;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.threaddetail.GetThreadDetailDataDtoRes;

public class GetLikeDtoDataRes {
	private String id;
	private String idThread;
	private String title;
	private String contents;
	private String idFile;
	private String extensions;
	private byte[] contentsFile;
	private String threadTypeName;
	private Boolean isPremium;
	private LocalDateTime createdAt;
	private String createdBy;
	private String fullName;
	private List<GetCategoryDetailByThreadDtoRes> dataCategoryDetail;
	private List<GetThreadDetailDataDtoRes> dataThreadComment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdThread() {
		return idThread;
	}

	public void setIdThread(String idThread) {
		this.idThread = idThread;
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

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
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

	public List<GetCategoryDetailByThreadDtoRes> getDataCategoryDetail() {
		return dataCategoryDetail;
	}

	public void setDataCategoryDetail(List<GetCategoryDetailByThreadDtoRes> dataCategoryDetail) {
		this.dataCategoryDetail = dataCategoryDetail;
	}

	public List<GetThreadDetailDataDtoRes> getDataThreadComment() {
		return dataThreadComment;
	}

	public void setDataThreadComment(List<GetThreadDetailDataDtoRes> dataThreadComment) {
		this.dataThreadComment = dataThreadComment;
	}
}
