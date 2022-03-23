package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.PriceList;

public interface PriceListDao {
	PriceList save(PriceList data) throws Exception;

	PriceList findById(String id) throws Exception;

	List<PriceList> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
