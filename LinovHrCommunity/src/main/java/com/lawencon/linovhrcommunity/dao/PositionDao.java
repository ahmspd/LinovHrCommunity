package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Position;

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

}