package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InsertEventCourseDtoReq {
	@NotEmpty(message = "Event Course is Null!")
	private String idEventCourseType;
	
	@NotEmpty(message = "Price List is Null!")
	private String idPriceList;
	
	@NotEmpty(message = "Category is Null!")
	private String[] idCategories;
	
	@NotEmpty(message = "Contents is Empty!")
	private String contents;
	
	@NotEmpty(message = "Title is Empty!")
	@Size(min = 5, max = 35, message = "Title is out of Range !")
	private String title;
	
	@NotEmpty(message = "Event Course Location is Empty!")
	@Size(min = 5, max = 50, message = "Event Course Location is Out of Range!")
	private String eventCourseLocation;
	
	@NotNull(message = "Price is Null!")
	private BigInteger price;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = " Date Start is Null !")
	private Date dateStart;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = " Date End is Null !")
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
