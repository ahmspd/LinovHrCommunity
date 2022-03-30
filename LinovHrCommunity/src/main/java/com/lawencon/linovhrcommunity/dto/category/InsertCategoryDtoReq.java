package com.lawencon.linovhrcommunity.dto.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertCategoryDtoReq {
	@NotEmpty(message = "Code Is Empty")
	@Size(max = 10, min = 5, message = "Code Out Of Range")
	private String code;

	@NotEmpty(message = "Category Name Is Empty")
	@Size(max = 30, min = 5, message = "Industry Name Out Of Range")
	private String categoryName;

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
}
