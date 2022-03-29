package com.lawencon.linovhrcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_category")
public class Category extends BaseEntity {
	
	private static final long serialVersionUID = -5196455701225322056L;
	
	@Column(name = "category_name", length = 10, nullable = false)
	private String categoryName;
	
	@Column(name = "code", length = 30, unique = true, nullable = false)
	private String code;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
