package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.ThreadDetail;

public interface ThreadDetailDao {
	ThreadDetail save(ThreadDetail data) throws Exception;

	ThreadDetail findById(String id) throws Exception;

	List<ThreadDetail> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
