package com.lawencon.linovhrcommunity.dto.dashboard;

public class GetDashboardDataDtoRes {
	private Integer event;
	private Integer course;
	private Integer thread;
	private Integer article;
	private Integer user;
	private Integer userPremium;

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public Integer getThread() {
		return thread;
	}

	public void setThread(Integer thread) {
		this.thread = thread;
	}

	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getUserPremium() {
		return userPremium;
	}

	public void setUserPremium(Integer userPremium) {
		this.userPremium = userPremium;
	}
}
