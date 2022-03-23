package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Polling;

@Repository
public class PollingDao extends BaseDaoImpl<Polling> {

	public Polling save(Polling data) throws Exception {
		return super.save(data);
	}

	public Polling findById(String id) throws Exception {
		return getById(id);
	}

	public List<Polling> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}