package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Like;

@Repository
public class LikeDaoImpl extends BaseDaoImpl<Like> implements LikeDao {

	@Override
	public Like save(Like data) throws Exception {
		return super.save(data);
	}

	@Override
	public Like findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Like> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}