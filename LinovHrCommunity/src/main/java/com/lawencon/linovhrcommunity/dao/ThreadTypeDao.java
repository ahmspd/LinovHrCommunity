package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadType;

public class ThreadTypeDao extends BaseDaoImpl<ThreadType> {

	public ThreadType save(ThreadType data) throws Exception {
		return super.save(data);
	}

	public ThreadType findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadType> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}