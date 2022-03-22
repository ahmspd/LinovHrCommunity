package com.lawencon.linovhrcommunity.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_event_course")
public class EventCourse extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@ManyToOne
	@JoinColumn(name = "id_event_course_type", nullable = true)
	private EventCourseType eventCourseType;

	@Column(name = "title", length = 35)
	private String title;

	@Column(name = "event_course_location", length = 50, nullable = true)
	private String eventCourseLocation;

	@Column(name = "price", nullable = true)
	private Integer price;

	@Column(name = "date_start", nullable = true)
	private Date dateStart;

	@Column(name = "date_end", nullable = true)
	private Date dateEnd;

	@Column(name = "time_start", nullable = true)
	private Time timeStart;

	@Column(name = "time_end", nullable = true)
	private Time timeEnd;

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

	public String getEventCourseLocation() {
		return eventCourseLocation;
	}

	public void setEventCourseLocation(String eventCourseLocation) {
		this.eventCourseLocation = eventCourseLocation;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
