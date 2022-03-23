package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadModel;

@Repository
public class ThreadModelDaoImpl extends BaseDaoImpl<ThreadModel> implements ThreadModelDao {

	@Override
	public ThreadModel save(ThreadModel data) throws Exception {
		return super.save(data);
	}

	@Override
	public ThreadModel findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<ThreadModel> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}