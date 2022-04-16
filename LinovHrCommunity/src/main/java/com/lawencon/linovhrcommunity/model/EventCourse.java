package com.lawencon.linovhrcommunity.model;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_event_course")
public class EventCourse extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@OneToOne
	@JoinColumn(name = "id_event_course_type", referencedColumnName = "id", nullable = false)
	private EventCourseType eventCourseType;

	@Column(name = "title", length = 35)
	private String title;
	
	@Column(name = "contents", nullable = false, columnDefinition = "text")
	private String contents;

	@Column(name = "event_course_location", length = 50, nullable = false)
	private String eventCourseLocation;

	@Column(name = "price", nullable = false)
	private BigInteger price;

	@Column(name = "date_start", nullable = false)
	private Date dateStart;

	@Column(name = "date_end", nullable = false)
	private Date dateEnd;

	@Column(name = "time_start", nullable = false)
	private Time timeStart;

	@Column(name = "time_end", nullable = true)
	private Time timeEnd;

	@OneToOne
	@JoinColumn(name = "id_price_list", referencedColumnName = "id", nullable = false)
	private PriceList priceList;
	
	@ManyToOne
	@JoinColumn(name = "id_file", unique = true)
	private File file;

	public EventCourseType getEventCourseType() {
		return eventCourseType;
	}

	public void setEventCourseType(EventCourseType eventCourseType) {
		this.eventCourseType = eventCourseType;
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

	public PriceList getPriceList() {
		return priceList;
	}

	public void setPriceList(PriceList priceList) {
		this.priceList = priceList;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
