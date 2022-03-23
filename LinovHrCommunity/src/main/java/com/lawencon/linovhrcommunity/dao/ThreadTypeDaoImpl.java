package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadType;

@Repository
public class ThreadTypeDaoImpl extends BaseDaoImpl<ThreadType> implements ThreadTypeDao {

	@Override
	public ThreadType save(ThreadType data) throws Exception {
		return super.save(data);
	}

	@Override
	public ThreadType findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<ThreadType> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}