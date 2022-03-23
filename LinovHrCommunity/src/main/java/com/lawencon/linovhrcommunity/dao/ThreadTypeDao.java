package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.ThreadType;

public interface ThreadTypeDao {
	ThreadType save(ThreadType data) throws Exception;

	ThreadType findById(String id) throws Exception;

	List<ThreadType> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
