package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Province;

@Repository
public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements ProvinceDao {

	@Override
	public Province save(Province data) throws Exception {
		return super.save(data);
	}

	@Override
	public Province findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<Province> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}