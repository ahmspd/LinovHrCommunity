package com.lawencon.linovhrcommunity.dto.eventcourse;

import java.math.BigInteger;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.lawencon.linovhrcommunity.dto.categorydetail.GetCategoryDetailByEventCourseDtoRes;

public class GetByIdEventCourseDtoDataRes {

	private String id;
	private String contents;
	private String title;
	private String eventCourseLocation;
	private BigInteger price;
	private Date dateStart;
	private Date dateEnd;
	private Time timeStart;
	private Time timeEnd;
	private String idFile;
	private String fileExtensions;
	private byte[] fileContents;
	private List<GetCategoryDetailByEventCourseDtoRes> dataCategoryDetail;
	private String createdBy;
	private String fullName;
	private LocalDateTime createdAt;
	private Integer version;
	private Boolean isActive;
	private Boolean isJoin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public String getFileExtensions() {
		return fileExtensions;
	}

	public void setFileExtensions(String fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public byte[] getFileContents() {
		return fileContents;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}

	public List<GetCategoryDetailByEventCourseDtoRes> getDataCategoryDetail() {
		return dataCategoryDetail;
	}

	public void setDataCategoryDetail(List<GetCategoryDetailByEventCourseDtoRes> dataCategoryDetail) {
		this.dataCategoryDetail = dataCategoryDetail;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(Boolean isJoin) {
		this.isJoin = isJoin;
	}
}
