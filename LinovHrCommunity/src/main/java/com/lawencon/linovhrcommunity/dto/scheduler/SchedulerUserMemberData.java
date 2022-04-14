package com.lawencon.linovhrcommunity.dto.scheduler;

public class SchedulerUserMemberData {

	private Integer totalFireCount;
	private Boolean runForever;
	private Long repeatIntervalMs;
	private Long initialOffsetMs;
	private String idUser;
	private String idUpdatedBy;

	public Integer getTotalFireCount() {
		return totalFireCount;
	}

	public void setTotalFireCount(Integer totalFireCount) {
		this.totalFireCount = totalFireCount;
	}

	public Boolean getRunForever() {
		return runForever;
	}

	public void setRunForever(Boolean runForever) {
		this.runForever = runForever;
	}

	public Long getRepeatIntervalMs() {
		return repeatIntervalMs;
	}

	public void setRepeatIntervalMs(Long repeatIntervalMs) {
		this.repeatIntervalMs = repeatIntervalMs;
	}

	public Long getInitialOffsetMs() {
		return initialOffsetMs;
	}

	public void setInitialOffsetMs(Long initialOffsetMs) {
		this.initialOffsetMs = initialOffsetMs;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUpdatedBy() {
		return idUpdatedBy;
	}

	public void setIdUpdatedBy(String idUpdatedBy) {
		this.idUpdatedBy = idUpdatedBy;
	}

}
