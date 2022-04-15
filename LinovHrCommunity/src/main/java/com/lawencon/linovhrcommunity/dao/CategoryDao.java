package com.lawencon.linovhrcommunity.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Category;

@Repository
public class CategoryDao extends BaseDaoImpl<Category> {
	public Integer isCategoryCodeExist(String code) {
		String sql = "select count(id) from t_category where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	public Integer isCategoryIdExist(String id) {
		String sql = "select count(id) from t_category where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}
