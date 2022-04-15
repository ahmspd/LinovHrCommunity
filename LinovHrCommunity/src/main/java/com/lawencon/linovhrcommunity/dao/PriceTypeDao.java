package com.lawencon.linovhrcommunity.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PriceType;

@Repository
public class PriceTypeDao extends BaseDaoImpl<PriceType> {
	public Integer isPriceTypeCodeExist(String code) {
		String sql = "select count(id) from t_price_type where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	public Integer isPriceTypeIdExist(String id) {
		String sql = "select count(id) from t_price_type where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}
