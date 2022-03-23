package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.Polling;

public interface PollingDao {
	Polling save(Polling data) throws Exception;

	Polling findById(String id) throws Exception;

	List<Polling> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
