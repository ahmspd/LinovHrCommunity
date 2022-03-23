package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PriceList;

@Repository
public class PriceListDao extends BaseDaoImpl<PriceList> {

	public PriceList save(PriceList data) throws Exception {
		return super.save(data);
	}

	public PriceList findById(String id) throws Exception {
		return getById(id);
	}

	public List<PriceList> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}