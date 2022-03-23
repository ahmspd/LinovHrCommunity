package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Position;

public interface PositionDao {
	Position save(Position data) throws Exception;

	Position findById(String id) throws Exception;

	List<Position> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
