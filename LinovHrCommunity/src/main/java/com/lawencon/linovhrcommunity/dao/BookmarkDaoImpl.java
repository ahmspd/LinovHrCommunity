package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Bookmark;

@Repository
public class BookmarkDaoImpl extends BaseDaoImpl<Bookmark> implements BookmarkDao{
	@Override
	public Bookmark save(Bookmark data) throws Exception {
		return super.save(data);
	}

	@Override
	public Bookmark findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Bookmark> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
