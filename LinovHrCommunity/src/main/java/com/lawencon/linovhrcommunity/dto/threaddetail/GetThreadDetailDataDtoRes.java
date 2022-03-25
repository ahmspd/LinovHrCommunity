package com.lawencon.linovhrcommunity.dto.threaddetail;

public class GetThreadDetailDataDtoRes {
	private String id;
	private String idThread;
	private String contents;
	private String createdBy;
	private String createdAt;
	private String fullName;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
