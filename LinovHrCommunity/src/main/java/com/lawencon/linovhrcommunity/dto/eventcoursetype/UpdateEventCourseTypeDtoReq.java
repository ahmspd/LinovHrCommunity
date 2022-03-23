package com.lawencon.linovhrcommunity.dto.eventcoursetype;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateEventCourseTypeDtoReq {
	@NotEmpty(message = "id is null !")
	private String id;

	@NotEmpty(message = "Error: Code is Empty! Please fill Code.")
	@Size(min = 5, max = 10, message = "Error:Code max 10 character!")
	private String code;

	@NotEmpty(message = "Error: Name is Empty! Please fill Name.")
	@Size(min = 5, max = 30, message = "Error:Name max 30 character!")
	private String eventTypeName;

	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
