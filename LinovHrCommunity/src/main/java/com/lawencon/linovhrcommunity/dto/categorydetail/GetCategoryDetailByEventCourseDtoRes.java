package com.lawencon.linovhrcommunity.dto.categorydetail;

public class GetCategoryDetailByEventCourseDtoRes {
	private String idCategoryDetail;
	private String idCategory;
	private String categoryName;

	public String getIdCategoryDetail() {
		return idCategoryDetail;
	}

	public void setIdCategoryDetail(String idCategoryDetail) {
		this.idCategoryDetail = idCategoryDetail;
	}

	public String getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
