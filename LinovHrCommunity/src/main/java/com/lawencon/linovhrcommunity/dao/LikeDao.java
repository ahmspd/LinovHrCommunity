package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Like;

public class LikeDao extends BaseDaoImpl<Like> {

	public Like save(Like data) throws Exception {
		return super.save(data);
	}

	public Like findById(String id) throws Exception {
		return getById(id);
	}

	public List<Like> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}