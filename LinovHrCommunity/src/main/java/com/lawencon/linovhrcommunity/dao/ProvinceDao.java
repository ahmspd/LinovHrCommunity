package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.Province;

@Repository
public class ProvinceDao extends BaseDaoImpl<Province> {

	public Province save(Province data) throws Exception {
		return super.save(data);
	}

	public Province findById(String id) throws Exception {
		return getById(id);
	}

	public List<Province> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}