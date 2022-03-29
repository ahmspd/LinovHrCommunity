package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsertEventCourseDtoReq {

	private String idEventCourseType;
	private String idPriceList;
	private String[] idCategories;
	private String contents;
	private String title;
	private String eventCourseLocation;
	private BigInteger price;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateEnd;
	
	private Time timeStart;
	
	private Time timeEnd;

	public String getIdEventCourseType() {
		return idEventCourseType;
	}

	public void setIdEventCourseType(String idEventCourseType) {
		this.idEventCourseType = idEventCourseType;
	}

	public String getIdPriceList() {
		return idPriceList;
	}

	public void setIdPriceList(String idPriceList) {
		this.idPriceList = idPriceList;
	}

	public String[] getIdCategories() {
		return idCategories;
	}

	public void setIdCategories(String[] idCategory) {
		this.idCategories = idCategory;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventCourseLocation() {
		return eventCourseLocation;
	}

	public void setEventCourseLocation(String eventCourseLocation) {
		this.eventCourseLocation = eventCourseLocation;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

}
