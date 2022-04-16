package com.lawencon.linovhrcommunity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_category", uniqueConstraints = @UniqueConstraint(name = "category_bk", columnNames = "code"))
public class Category extends BaseEntity {

	private static final long serialVersionUID = -5196455701225322056L;

	@Column(name = "category_name", length = 30, nullable = false)
	private String categoryName;

	@Column(name = "code", length = 7, nullable = false)
	private String code;

	@OneToMany(mappedBy = "category")
	private List<CategoryDetail> categoryDetail;

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

	public List<CategoryDetail> getCategoryDetail() {
		return categoryDetail;
	}

	public void setCategoryDetail(List<CategoryDetail> categoryDetail) {
		this.categoryDetail = categoryDetail;
	}

}
