package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadDetail;

public class ThreadDetailDao extends BaseDaoImpl<ThreadDetail> {

	public ThreadDetail save(ThreadDetail data) throws Exception {
		return super.save(data);
	}

	public ThreadDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<ThreadDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
