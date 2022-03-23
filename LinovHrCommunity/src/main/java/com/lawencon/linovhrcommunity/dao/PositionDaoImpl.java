package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Position;

@Repository
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {

	@Override
	public Position save(Position data) throws Exception {
		return super.save(data);
	}

	@Override
	public Position findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Position> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}