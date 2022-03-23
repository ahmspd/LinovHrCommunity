package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadModel;

public class ThreadModelDao extends BaseDaoImpl<ThreadModel> {

	public ThreadModel save(ThreadModel data) throws Exception {
		return super.save(data);
	}

	public ThreadModel findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadModel> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}