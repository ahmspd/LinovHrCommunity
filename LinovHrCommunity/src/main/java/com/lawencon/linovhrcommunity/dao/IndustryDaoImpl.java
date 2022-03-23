package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Industry;

@Repository
public class IndustryDaoImpl extends BaseDaoImpl<Industry> implements IndustryDao {

	@Override
	public Industry save(Industry data) throws Exception {
		return super.save(data);
	}

	@Override
	public Industry findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Industry> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}