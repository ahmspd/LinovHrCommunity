package com.lawencon.linovhrcommunity.dto.thread;

import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByThreadDtoRes;
import com.lawencon.linovhrcommunity.dto.pollingdetail.GetPollingDetailByPollingIdDto;
import com.lawencon.linovhrcommunity.dto.threaddetail.GetThreadDetailDataDtoRes;

public class GetThreadDataDtoRes {
	private String id;
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
	private String date;
	private Integer like;
	private Integer bookmark;
	private Integer comment;
	private String idPolling;
	private String pollingName;
	private Boolean isActive;
	private List<GetCategoryDetailByThreadDtoRes> dataCategoryDetail;
	private List<GetThreadDetailDataDtoRes> dataThreadComment;
	private List<GetPollingDetailByPollingIdDto> dataThreadPolling;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getBookmark() {
		return bookmark;
	}
	public void setBookmark(Integer bookmark) {
		this.bookmark = bookmark;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public String getIdPolling() {
		return idPolling;
	}
	public void setIdPolling(String idPolling) {
		this.idPolling = idPolling;
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
	public List<GetPollingDetailByPollingIdDto> getDataThreadPolling() {
		return dataThreadPolling;
	}
	public void setDataThreadPolling(List<GetPollingDetailByPollingIdDto> dataThreadPolling) {
		this.dataThreadPolling = dataThreadPolling;
	}
}
