package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Industry;

public class IndustryDao extends BaseDaoImpl<Industry> {

	public Industry save(Industry data) throws Exception {
		return super.save(data);
	}

	public Industry findById(String id) throws Exception {
		return getById(id);
	}

	public List<Industry> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}