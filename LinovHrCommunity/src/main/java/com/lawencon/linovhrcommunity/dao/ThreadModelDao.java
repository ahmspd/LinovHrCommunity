package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.ThreadModel;

public interface ThreadModelDao {
	ThreadModel save(ThreadModel data) throws Exception;

	ThreadModel findById(String id) throws Exception;

	List<ThreadModel> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
