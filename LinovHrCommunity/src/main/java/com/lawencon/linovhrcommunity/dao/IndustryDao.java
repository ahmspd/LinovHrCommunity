package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Industry;

@Repository
public class IndustryDao extends BaseDaoImpl<Industry> {

	public Industry save(Industry data) throws Exception {
		return super.save(data);
	}

	public Industry findById(String id) throws Exception {
		return getById(id);
	}

	public List<Industry> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	public Integer isIndustryCodeExist(String code) {
		String sql = "select count(id) from t_industry where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	
	public Integer isIndustryIdExist(String id) {
		String sql = "select count(id) from t_industry where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}