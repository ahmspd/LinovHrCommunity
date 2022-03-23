package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.ThreadDetail;

@Repository
public class ThreadDetailDaoImpl extends BaseDaoImpl<ThreadDetail> implements ThreadDetailDao {

	@Override
	public ThreadDetail save(ThreadDetail data) throws Exception {
		return super.save(data);
	}

	@Override
	public ThreadDetail findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<ThreadDetail> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
