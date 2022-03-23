package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.linovhrcommunity.model.PollingDetail;

public interface PollingDetailDao {
	PollingDetail save(PollingDetail data) throws Exception;

	PollingDetail findById(String id) throws Exception;

	List<PollingDetail> findAll() throws Exception;

	boolean deleteById(String id) throws Exception;
}
