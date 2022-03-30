package com.lawencon.linovhrcommunity.dto.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCategoryDtoReq {
	@NotEmpty(message = "Id Is Null")
	private String id;

	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@Size(max = 30, min = 5, message = "Category Name Out Of Range")
	private String categoryName;

	@NotNull(message = "Version Is Null")
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
