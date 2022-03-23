package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Like;

public interface LikeDao {
	Like save(Like data) throws Exception;

	Like findById(String id) throws Exception;

	List<Like> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
