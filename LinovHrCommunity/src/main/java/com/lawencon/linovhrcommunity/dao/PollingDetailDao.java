package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetail;

public class PollingDetailDao extends BaseDaoImpl<PollingDetail> {

	public PollingDetail save(PollingDetail data) throws Exception {
		return super.save(data);
	}

	public PollingDetail findById(String id) throws Exception {
		return getById(id);
	}

	public List<PollingDetail> findAll() throws Exception {
		return getAll();
	}

	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
