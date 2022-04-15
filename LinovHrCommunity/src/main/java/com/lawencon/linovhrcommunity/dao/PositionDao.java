package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Position;

@Repository
public class PositionDao extends BaseDaoImpl<Position> {

	public Position save(Position data) throws Exception {
		return super.save(data);
	}

	public Position findById(String id) throws Exception {
		return getById(id);
	}

	public List<Position> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	public Integer isPositionCodeExist(String code) {
		String sql = "select count(id) from t_position where code = :code";
		Object result = createNativeQuery(sql).setParameter("code", code).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
	public Integer isPositionIdExist(String id) {
		String sql = "select count(id) from t_position where id = :id";
		Object result = createNativeQuery(sql).setParameter("id", id).getSingleResult();
		Integer res = Integer.valueOf(result.toString());
		return res;
	}
}