package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Bookmark;

@Repository
public class BookmarkDao extends BaseDaoImpl<Bookmark>{

	public Bookmark save(Bookmark data) throws Exception {
		return super.save(data);
	}

	public Bookmark findById(String id) throws Exception {
		return getById(id);
	}

	public List<Bookmark> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}

