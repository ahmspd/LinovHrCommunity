package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Polling;

@Repository
public class PollingDaoImpl extends BaseDaoImpl<Polling> implements PollingDao {

	@Override
	public Polling save(Polling data) throws Exception {
		return super.save(data);
	}

	@Override
	public Polling findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Polling> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}