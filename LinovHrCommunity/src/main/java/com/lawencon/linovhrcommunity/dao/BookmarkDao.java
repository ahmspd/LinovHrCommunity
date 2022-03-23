package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Bookmark;

public interface BookmarkDao {
	Bookmark save(Bookmark data) throws Exception;

	Bookmark findById(String id) throws Exception;

	List<Bookmark> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
