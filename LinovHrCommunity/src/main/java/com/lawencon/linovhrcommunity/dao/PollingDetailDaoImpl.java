package com.lawencon.linovhrcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.linovhrcommunity.model.PollingDetail;

@Repository
public class PollingDetailDaoImpl extends BaseDaoImpl<PollingDetail> implements PollingDetailDao {

	@Override
	public PollingDetail save(PollingDetail data) throws Exception {
		return super.save(data);
	}

	@Override
	public PollingDetail findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<PollingDetail> findAll() throws Exception {
		return getAll();
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

}
